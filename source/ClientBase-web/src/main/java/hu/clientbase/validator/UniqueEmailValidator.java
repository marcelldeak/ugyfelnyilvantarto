package hu.clientbase.validator;

import hu.clientbase.service.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

@FacesValidator(value = "uniqueEmailValidator")
public class UniqueEmailValidator implements Validator {

    @Inject
    private UserService userManager;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        String email = (String) value;
        if (userManager.existEmail(email)) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Email is already in use.", null));
        }
    }

}
