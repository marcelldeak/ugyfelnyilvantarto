package hu.clientbase.validator;

import hu.clientbase.service.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

@FacesValidator("uniqueEmailValidator")
public class UniqueEmailValidator implements Validator {

    @Inject
    private UserService userManagaer;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
              throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "you did not enter any caracter", null));
        }
        String email = value.toString();
        if (userManagaer.existEmail(email)) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Email is already in use.", null));
        }
    }

}
