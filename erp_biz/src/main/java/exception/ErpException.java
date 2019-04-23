package exception;

public class ErpException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErpException(String message){
		super(message);
	}
	public ErpException(){
		super();
	}
}
