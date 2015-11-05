package galaxy.rapid.log;

public class RapidLogFactory {

	public static RapidLog getLogger(Class<? extends Object> class1) {
		return new RapidLog(class1);
	}

}
