package hu.clientbase.service;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import hu.clientbase.facade.UserFacade;
import java.io.Serializable;
>>>>>>> develop
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
<<<<<<< HEAD
public class UserService {

    @Inject
    private EntityFacade entityFacade;

    public UserDTO createUser(UserDTO userDTO) {
        return new UserDTO(entityFacade.create(new User(userDTO)));
    }

    public UserDTO findUser(Long id) {
        return new UserDTO(entityFacade.find(User.class, id));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        return new UserDTO(entityFacade.update(new User(userDTO)));
    }

    public void deleteUser(UserDTO userDTO) {
        entityFacade.delete(new User(userDTO));
    }

    public List<UserDTO> findAllUser() {
        List<UserDTO> result = new ArrayList<>();

        for (User user : entityFacade.findAll(User.class)) {
            result.add(new UserDTO(user));
        }

        return result;
    }

    public List<UserDTO> findAllPendingRegistration(){
        List<UserDTO> result = new ArrayList<>();
        
        for(User user : entityFacade.findAll(User.class)){
            if(!user.isActive()){
                result.add(new UserDTO(user));
            }
        }
        
        return result;
    }
    
    public List<UserDTO> findAllAdmin() {
        List<UserDTO> result = new ArrayList<>();

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

    public List<UserDTO> findAllOrdinaryUser() {
        List<UserDTO> result = new ArrayList<>();

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
=======
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

>>>>>>> develop
}
