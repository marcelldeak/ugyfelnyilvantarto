package hu.clientbase.exception;

import hu.clientbase.dto.ErrorDTO;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>{

    @Override
    public Response toResponse(ValidationException exception) {
        System.out.println("--------------- EXCEPTION ÉRKEZETT -----------------");
       return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(exception.getLocalizedMessage())).build();
    }
}
