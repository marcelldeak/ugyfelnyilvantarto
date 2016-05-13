package hu.clientbase.service;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
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
}
