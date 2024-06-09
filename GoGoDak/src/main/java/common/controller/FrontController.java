package common.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import conatainer.DIContainer;
import conatainer.DIInjector;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,      // 10 MB
	    maxRequestSize = 1024 * 1024 * 100   // 100 MB
	)

@WebServlet(
	      description = "사용자가 웹에서 *.up 을 했을 경우 이 서블릿이 응답을 해주도록 한다.",
	      urlPatterns = {"*.dk"},
	      initParams = { 
	      @WebInitParam(name = "propertyConfig", value = "C:\\git\\GoGoDak\\GoGoDak\\src\\main\\webapp\\WEB-INF\\command.properties", description = "*.dk 에 대한 클래스의 매핑파일"), 
	      @WebInitParam(name = "daoConfig", value = "C:\\git\\GoGoDak\\GoGoDak\\src\\main\\webapp\\WEB-INF\\daoConfig.properties", description = "DAO 클래스의 매핑파일")
	      })

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Class<?>> cmdMap = new HashMap<>();
	
	
	private DIContainer disContainer = new DIContainer();
	private DIInjector diInjector = new DIInjector(disContainer);

	public void init(ServletConfig config) throws ServletException {

		/*
		 * 웹브라우저 주소창에서 *.up 을 하면 FrontController 서블릿이 응대를 해오는데 맨 처음에 자동적으로 실행되어지는 메소드가
		 * init(ServletConfig config) 이다. 여기서 중요한 것은 init(ServletConfig config) 메소드는
		 * WAS(톰캣)가 구동되어진 후 딱 1번만 init(ServletConfig config) 메소드가 실행되어지고, 그 이후에는 실행이 되지
		 * 않는다. 그러므로 init(ServletConfig config) 메소드에는 FrontController 서블릿이 동작해야할 환경설정을
		 * 잡아주는데 사용된다.
		 */

//		System.out.println("-----확인용: 서블릿 front controller intit  한번만 실행됨");

		FileInputStream fileInputStream = null;
		
		
		String properties = config.getInitParameter("propertyConfig");
		System.out.println(properties);
		
		
		String daoConfig = config.getInitParameter("daoConfig");

		System.out.println(daoConfig);

		try {
			
			fileInputStream = new FileInputStream(daoConfig);
			// fileInputStream은 파일을 읽어옴

			Properties daoPr = new Properties();
			daoPr.load(fileInputStream);
			
			Enumeration<Object> daoKeys = daoPr.keys();
			
			
			while(daoKeys.hasMoreElements()) {
				String key = (String) daoKeys.nextElement();
				System.out.println("dao key : " + key);
				
				String impleClassName = daoPr.getProperty(key).trim();
				
				if(impleClassName != null) {
					
					Class<?> interfaceClass = Class.forName(key);
					Class<?> impleClassType = Class.forName(impleClassName);
					// <?> 은 generic 인데 어떤 클래스 타입인지는 모르지만 하여튼 클래스 타입이 들어온다는 뜻이다.
					// String 타입으로 되어진 className 을 클래스화 시켜주는 것이다.
					// 주의할 점은 실제로 String 으로 되어져 있는 문자열이 클래스로 존재해야만 한다는 것이다.

					Constructor<?> constructor = impleClassType.getDeclaredConstructor();

					Object object = constructor.newInstance();
					
					disContainer.registerObject(interfaceClass, object);
					
				}
				
			}

			fileInputStream = new FileInputStream(properties);
			// fileInputStream은 파일을 읽어옴

			Properties pr = new Properties();

			// Properties 는 Collection 중 HashMap 계열중의 하나로써
			// "key","value"으로 이루어져 있는것이다.
			// 그런데 중요한 것은 Properties 는 key도 String 타입이고, value도 String 타입만 가능하다는 것이다.
			// key는 중복을 허락하지 않는다. value 값을 얻어오기 위해서는 key값 만 알면 된다.

			pr.load(fileInputStream);


			// 왜 Object???
			Enumeration<Object> keys = pr.keys();
			// 모든 키들만 가져옴

			while (keys.hasMoreElements()) {

				String key = (String) keys.nextElement();
				System.out.println("key : " + key);

				/*
				 * System.out.println(key); System.out.println(pr.getProperty(key));
				 */

				String className = pr.getProperty(key);

				if (className != null) {

					className = className.trim();

					Class<?> classType = Class.forName(className);
					// <?> 은 generic 인데 어떤 클래스 타입인지는 모르지만 하여튼 클래스 타입이 들어온다는 뜻이다.
					// String 타입으로 되어진 className 을 클래스화 시켜주는 것이다.
					// 주의할 점은 실제로 String 으로 되어져 있는 문자열이 클래스로 존재해야만 한다는 것이다.


					//Object object = constructor.newInstance();
					Object object = diInjector.createInjectedObject(classType);
					disContainer.registerObject(classType, object);

					cmdMap.put(key, classType);

				}

			}
			
			

		} catch (NoSuchMethodException e) {

			System.out.println("!!!!!no such constructor!!!!!");
			e.printStackTrace();
		}

		catch (ClassNotFoundException e) {
			System.out.println("!!!!!class dose not exists!!!!");
			e.printStackTrace();
		}

		catch (IOException e) {
			System.out.println(properties + " dose not exists!!!!");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		System.out.println("url : " + url);

		

		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);


		String key = uri.substring(request.getContextPath().length());

		//AbstractController controller = (AbstractController) cmdMap.get(key);
		
		Class<?> controllerClazz = cmdMap.get(key);
		AbstractController controller = (AbstractController) disContainer.getObject(controllerClazz);

		if (controller == null) {
			System.out.println("no available controller");
		} else {

			try {
				controller.execute(request, response);

				if (!controller.isRedirect()) {


					if (controller.getViewPage() != null) {

						RequestDispatcher dispatcher = request.getRequestDispatcher(controller.getViewPage());
						dispatcher.forward(request, response);
					}

				} else {


					if (controller.getViewPage() != null) {

						response.sendRedirect(controller.getViewPage());
						return;
					}

				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
