package com.idquantique.quantis;

public class QuantisException extends Exception {

    /**
     * Constructs a new QuantisExecption with null as its detail message.
     */
    public QuantisException() {
    }

    /**
     * Constructs a new QuantisExecption with the specified detail message.
     * 
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     */
    public QuantisException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of
     * (cause==null ? null : cause.toString()) (which typically contains the class
     * and detail message of cause).
     * 
     * @param cause the cause (which is saved for later retrieval by the
     *              Throwable.getCause() method). (A null value is permitted, and
     *              indicates that the cause is nonexistent or unknown.)
     */
    public QuantisException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new QuantisExecption with the specified detail message and
     * cause. Note that the detail message associated with cause is <em>not</em>
     * automatically incorporated in this exception's detail message.
     * 
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the Throwable.getMessage() method.
     * @param cause   the cause (which is saved for later retrieval by the
     *                Throwable.getCause() method). (A null value is permitted, and
     *                indicates that the cause is nonexistent or unknown.)
     */
    public QuantisException(String message, Throwable cause) {
        super(message, cause);
    }
}

