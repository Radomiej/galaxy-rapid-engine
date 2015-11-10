package galaxy.rapid.log;

public class RapidLog {
	@SuppressWarnings("rawtypes")
	Class clazz;
	
	@SuppressWarnings("rawtypes")
	public RapidLog(Class class1) {
		this.clazz = class1;
	}

	public void debug(String message) {
		System.out.println("debug: " + clazz.getName() + " : " + message);
	}
	public void info(String message) {
		System.out.println("info: " + clazz.getName() + " : " + message);
	}

	public void warn(String message) {
		System.err.println("warn: " + clazz.getName() + " : " + message);
	}

	public void error(String message) {
		System.err.println("error: " + clazz.getName() + " : " + message);
	}
	
	@Deprecated
	public static void debug(Class clazz, String message) {
		System.out.println("debug: " + clazz.getName() + " : " + message);
	}
	@Deprecated
	public static void info(Class clazz, String message) {
		System.out.println("info: " + clazz.getName() + " : " + message);
	}

}
