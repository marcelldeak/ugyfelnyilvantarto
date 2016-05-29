package hu.clientbase.exception;

import java.io.Serializable;
import javax.ejb.ApplicationException;


@ApplicationException
public class ValidationException extends Exception implements Serializable{

    public ValidationException() {
        super();
    }

    public ValidationException(String string) {
        super(string);
    }
}
