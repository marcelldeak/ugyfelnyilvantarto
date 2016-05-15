package hu.clientbase.service;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.UserFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService implements Serializable {

    @Inject
    private EntityFacade entityManager;
    
    @Inject
    private UserFacade userFacade;

    public UserService() {
    }

    public User create(UserDTO user) {
        User tempUser = new User(user);
        tempUser.setActive(false);
        return entityManager.create(tempUser);
    }

    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    public void Delete(long id) {
        entityManager.delete(id);
    }

    public User update(User user, long id) {
        user.setId(id);
        entityManager.update(user);
        return user;
    }

    public boolean existEmail(String email) {
        for (User user : entityManager.findAll(User.class)) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    public List<Role> getUserRolesByEmail(String email)
    {
        Long userId = userFacade.getUserIdByUserName(email);
        User u = find(userId);
        return u.getRoles();
    }

}
