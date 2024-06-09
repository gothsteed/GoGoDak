package conatainer;

import java.util.HashMap;
import java.util.Map;

public class DIContainer {
	
	private Map<Class<?>, Object> objects = new HashMap<Class<?>, Object>();
	
	public void registerObject(Class<?> interfaceClass, Object implementation) {
		objects.put(interfaceClass, implementation);
	}
	
	public <T> T getObject(Class<T> interfaceClass) {
		return interfaceClass.cast(objects.get(interfaceClass));
	}

}
