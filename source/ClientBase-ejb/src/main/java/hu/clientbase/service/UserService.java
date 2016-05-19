package hu.clientbase.service;

import hu.clientbase.dto.BasicUserDTO;
import hu.clientbase.entity.PendingRegistration;
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

    public void create(BasicUserDTO user) {
        User tempUser = new User(user);
        tempUser.setActive(false);
        PendingRegistration p = new PendingRegistration(tempUser);
        entityFacade.create(tempUser);
        entityFacade.create(p);
    }
    
    public void deletePendingRegistration(BasicUserDTO user)
    {
        User u = entityFacade.find(User.class, user.getId());
        PendingRegistration p = userFacade.getPendingRegistrationByUser(u);
        entityFacade.delete(p);
        entityFacade.delete(u);
    }
    
    public void AcceptPendingRegistration(BasicUserDTO user, String role)
    {
        User u = entityFacade.find(User.class, user.getId());
        PendingRegistration p = userFacade.getPendingRegistrationByUser(u);
        entityFacade.delete(p);
        
        Role r = userFacade.getRoleByName(role);
        u.setActive(true);
        u.getRoles().add(r);
        
        entityFacade.update(u);
    }

    public boolean isEmailExist(String email) {
        return entityFacade.findAll(User.class).stream().anyMatch((user) -> (user.getEmail().equals(email)));
    }

    public List<BasicUserDTO> getPendingRegistrations() {
        List<PendingRegistration> pendingRegistrations = userFacade.getPendingRegistrations();

        List<BasicUserDTO> userDTOs = new LinkedList<>();

        pendingRegistrations.stream().forEach(p -> userDTOs.add(new BasicUserDTO(p.getUser())));

        return userDTOs;
    }
    

}
