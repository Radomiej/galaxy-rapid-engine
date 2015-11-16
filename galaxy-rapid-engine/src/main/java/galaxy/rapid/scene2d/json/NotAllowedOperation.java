package galaxy.rapid.scene2d.json;

public class NotAllowedOperation extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1897248034379297659L;

	public NotAllowedOperation() {
	}
	
	public NotAllowedOperation(String message) {
		super(message);
	}

}
