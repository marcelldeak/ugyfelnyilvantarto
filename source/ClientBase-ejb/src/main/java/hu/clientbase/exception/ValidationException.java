package hu.clientbase.exception;

import java.io.Serializable;
import javax.ejb.ApplicationException;


@ApplicationException
public class ValidationException extends IllegalArgumentException implements Serializable{

    public ValidationException() {
        super();
    }

    public ValidationException(String string) {
        super(string);
    }
}
