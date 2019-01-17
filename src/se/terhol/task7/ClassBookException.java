package se.terhol.task7;

/**
 * @author Jan Papousek
 */
public class ClassBookException extends Exception
{

    public ClassBookException() {
        super();
    }

    public ClassBookException(String msg) {
        super(msg);
    }
    
    public ClassBookException(Throwable cause) {
        super(cause);
    }
    
    public ClassBookException(String msg, Throwable cause) {
        super(msg, cause);
    }    
    
}
