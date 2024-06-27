package com.nisum.demo.exception;

public class EmailExistException extends RuntimeException {
	private static final long serialVersionUID = 2292682047608770203L;
	public static final String EMAIL_EXIST = "El correo ya registrado";
    
    public EmailExistException(String message) {
        super(message);

    }
    
    public EmailExistException(String message, Throwable cause) {
        super(message, cause);

    	
    }


}
