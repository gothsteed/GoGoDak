package conatainer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.oracle.wls.shaded.org.apache.regexp.recompile;

import conatainer.annotation.Autowired;

public class DIInjector {
	private DIContainer diContainer;

	public DIInjector(DIContainer diContainer) {
		this.diContainer = diContainer;
	}

	/*
	 * public void injectDependencies(Object object) { Field[] fields =
	 * object.getClass().getDeclaredFields();
	 * 
	 * for (Field field : fields) { if (field.isAnnotationPresent(Autowired.class))
	 * { Class<?> fieldType = field.getType();
	 * 
	 * Object dependency = diContainer.getObject(fieldType);
	 * 
	 * if (dependency == null) { continue; }
	 * 
	 * field.setAccessible(true); try { field.set(object, dependency); } catch
	 * (IllegalAccessException e) { throw new
	 * RuntimeException("failed to inject dependency"); } } }
	 * 
	 * }
	 */

	public <T> T createInjectedObject(Class<T> clazz) {
		try {

			Constructor<?>[] constructors = clazz.getConstructors();
			for (Constructor<?> constructor : constructors) {
				if (constructor.isAnnotationPresent(Autowired.class)) {
					T object = injectConstructorDependencies(constructor);
					return object;
				}

			}

			T instance = clazz.getDeclaredConstructor().newInstance();
			injectFieldDependency(instance);
			return instance;

		} catch (Exception e) {
			throw new RuntimeException("create injected object failed");
		}

	}

	private <T> T injectConstructorDependencies(Constructor<?> constructor) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException   {
    	Class<?>[] parameterType = constructor.getParameterTypes();
    	Object[] dependencies = new Object[parameterType.length];
    	
    	for(int i=0; i<parameterType.length; i++) {
    		dependencies[i] = diContainer.getObject(parameterType[i]);
    		
    		if(dependencies[i] == null) {
    			throw new RuntimeException("no dependency found");
    		}
    	}
    	
	    T instance = (T) constructor.newInstance(dependencies);
		injectFieldDependency(instance);
        return instance;
    	
    
    }

	private <T> void injectFieldDependency(Object instance) {
		Field[] fields = instance.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(Autowired.class)) {
				Class<?> fieldType = field.getType();

				Object dependency = diContainer.getObject(fieldType);

				if (dependency == null) {
					continue;
				}

				field.setAccessible(true);
				try {
					field.set(instance, dependency);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("failed to inject dependency");
				}
			}
		}

	}

}
