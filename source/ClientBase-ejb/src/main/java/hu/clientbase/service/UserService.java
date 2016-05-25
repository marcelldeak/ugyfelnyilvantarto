package hu.clientbase.service;

import hu.clientbase.dto.BasicUserDTO;
import hu.clientbase.entity.PendingRegistration;
import hu.clientbase.entity.Role;
import hu.clientbase.entity.User;
import hu.clientbase.facade.EntityFacade;
import hu.clientbase.facade.UserFacade;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private EntityFacade entityFacade;

    @Inject
    private UserFacade userFacade;

    public void create(BasicUserDTO dto) {
        User u = new User(dto);
        u.setActive(false);
        PendingRegistration p = new PendingRegistration(u);
        entityFacade.create(u);
        entityFacade.create(p);
    }

    public void delete(BasicUserDTO dto) {
        User u = entityFacade.find(User.class, dto.getId());
        entityFacade.delete(u);
    }

    public void deletePendingRegistration(BasicUserDTO dto) {
        User u = entityFacade.find(User.class, dto.getId());
        PendingRegistration p = userFacade.getPendingRegistrationByUser(u);
        entityFacade.delete(p);
        entityFacade.delete(u);
    }

    public void acceptPendingRegistration(BasicUserDTO dto, String role) {
        User u = entityFacade.find(User.class, dto.getId());
        PendingRegistration p = userFacade.getPendingRegistrationByUser(u);
        entityFacade.delete(p);

        Role r = userFacade.getRoleByName(role);
        u.setActive(true);
        u.getRoles().add(r);

        entityFacade.update(u);
    }

    public List<BasicUserDTO> getAdministratorsExceptOne(String email) {

        List<User> users = userFacade.getUsers();

        List<BasicUserDTO> ret = new LinkedList<>();

        for (User u : users) {
            for (Role r : u.getRoles()) {
                if (r.getName().toLowerCase().equals("admin") && !u.getEmail().equals(email)) {
                    ret.add(new BasicUserDTO(u));
                    break;
                }
            }
        }

        return ret;
    }

    public List<BasicUserDTO> getUsers() {
        List<User> users = userFacade.getUsers();

        List<BasicUserDTO> ret = new LinkedList<>();

        for (User u : users) {
            for (Role r : u.getRoles()) {
                if (r.getName().toLowerCase().equals("user")) {
                    ret.add(new BasicUserDTO(u));
                    break;
                }
            }
        }
        return ret;
    }

    public boolean isEmailExist(String email) {
        return entityFacade.findAll(User.class).stream().anyMatch((user) -> (user.getEmail().equals(email)));
    }

    public List<BasicUserDTO> getPendingRegistrations() {
        List<PendingRegistration> pendingRegistrations = userFacade.getPendingRegistrations();

        List<BasicUserDTO> ret = new LinkedList<>();

        pendingRegistrations.stream().forEach(p -> ret.add(new BasicUserDTO(p.getUser())));

        return ret;
    }

}
