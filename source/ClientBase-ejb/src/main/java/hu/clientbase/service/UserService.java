package hu.clientbase.service;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.entity.PendingRegistration;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.UserFacade;
import hu.clientbase.validate.LoggerInterceptor;
import hu.clientbase.validate.ValidatorInterceptor;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors({LoggerInterceptor.class})
public class UserService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private UserFacade userFacade;

    @Interceptors({ValidatorInterceptor.class})
    public void create(UserDTO dto) {
        User u = new User(dto);
        u.setActive(false);
        PendingRegistration p = new PendingRegistration(u);
        entityFacade.create(u);
        entityFacade.create(p);
    }

    public void delete(UserDTO dto) {
        User u = entityFacade.find(User.class, dto.getId());
        entityFacade.delete(u);
    }

    public void deletePendingRegistration(UserDTO dto) {
        User u = entityFacade.find(User.class, dto.getId());
        PendingRegistration p = userFacade.getPendingRegistrationByUser(u);
        entityFacade.delete(p);
        entityFacade.delete(u);
    }

    public void acceptPendingRegistration(UserDTO dto, String role) {
        User u = entityFacade.find(User.class, dto.getId());
        PendingRegistration p = userFacade.getPendingRegistrationByUser(u);
        entityFacade.delete(p);

        Role r = userFacade.getRoleByName(role);
        u.setActive(true);
        u.getRoles().add(r);

        entityFacade.update(u);
    }

    public List<UserDTO> getAdministratorsExceptOne(String email) {

        List<User> users = userFacade.getUsers();

        List<UserDTO> ret = new LinkedList<>();

        for (User u : users) {
            for (Role r : u.getRoles()) {
                if ("admin".equalsIgnoreCase(r.getName()) && !u.getEmail().equals(email)) {
                    ret.add(new UserDTO(u));
                    break;
                }
            }
        }

        return ret;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userFacade.getUsers();

        List<UserDTO> ret = new LinkedList<>();

        for (User u : users) {
            for (Role r : u.getRoles()) {
                if ("user".equalsIgnoreCase(r.getName().toLowerCase())) {
                    ret.add(new UserDTO(u));
                    break;
                }
            }
        }
        return ret;
    }

    public boolean isEmailExist(String email) {

        return entityFacade.findAll(User.class).stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public List<UserDTO> getPendingRegistrations() {
        List<PendingRegistration> pendingRegistrations = userFacade.getPendingRegistrations();

        List<UserDTO> ret = new LinkedList<>();

        pendingRegistrations.stream().forEach(p -> ret.add(new UserDTO(p.getUser())));

        return ret;
    }

}
