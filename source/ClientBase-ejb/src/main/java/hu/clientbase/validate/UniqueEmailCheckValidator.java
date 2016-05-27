package hu.clientbase.validate;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailCheckValidator implements ConstraintValidator<UniqueEmailCheckValidate, UserDTO> {

    private UserService userService;

    @Override
    public void initialize(UniqueEmailCheckValidate constraintAnnotation) {
        //init
    }

    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        return userService.isEmailExist(value.getEmail());
    }

}
