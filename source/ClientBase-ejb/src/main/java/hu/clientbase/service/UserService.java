package hu.clientbase.service;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.UserFacade;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService implements Serializable {

    @Inject
    private EntityFacade entityFacade;
    
    @Inject
    private UserFacade userFacade;

    public UserService() {
    }

    public User create(UserDTO user) {
        User tempUser = new User(user);
        tempUser.setActive(false);
        return entityFacade.create(tempUser);
    }

    public User find(long id) {
        return entityFacade.find(User.class, id);
    }

    public void Delete(long id) {
        entityFacade.delete(id);
    }

    public User update(User user, long id) {
        user.setId(id);
        entityFacade.update(user);
        return user;
    }

    public boolean isEmailExist(String email) {
        for (User user : entityFacade.findAll(User.class)) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
        public List<UserDTO> findAllUsers() {
        List<UserDTO> result = new LinkedList<>();

        for (User user : entityFacade.findAll(User.class)) {
            result.add(new UserDTO(user));
        }

        return result;
    }

    public List<UserDTO> findAllPendingRegistrations(){
        List<UserDTO> result = new LinkedList<>();
        
        for(User user : entityFacade.findAll(User.class)){
            if(!user.isActive()){
                result.add(new UserDTO(user));
            }
        }
        
        return result;
    }
    
    public List<UserDTO> findAllAdmin() {
        List<UserDTO> result = new LinkedList<>();

        for (User user : entityFacade.findAll(User.class)) {
            if (user.isActive()) {
                for (Role role : user.getRoles()) {
                    if (role.getName().equals("ADMIN")) {
                        result.add(new UserDTO(user));
                    }
                }
            }
        }

        return result;
    }

    public List<UserDTO> findAllOrdinaryUsers() {
        List<UserDTO> result = new LinkedList<>();

        for (User user : entityFacade.findAll(User.class)) {
            if (user.isActive()) {
                for (Role role : user.getRoles()) {
                    if (role.getName().equals("USER")) {
                        result.add(new UserDTO(user));
                    }
                }
            }
        }

        return result;
    }

}
