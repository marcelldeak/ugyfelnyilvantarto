package hu.clientbase.backingbean;

import hu.clientbase.dto.UserDTO;
import hu.clientbase.service.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "adminBean")
@SessionScoped
public class AdminBackingBean implements Serializable{
    
    @Inject
    private UserService userService;
    
    private List<UserDTO> users = new ArrayList<>();
    
    private List<UserDTO> filteredUsers;

    public AdminBackingBean() {
        // default constructor
    }

    @PostConstruct
    private void init(){
        users = userService.findAllUser();
    }
    
    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public List<UserDTO> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<UserDTO> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }
    
    public List<UserDTO> getAdminUsers(){
        return userService.findAllAdmin();
    }
    
    public List<UserDTO> getOrdinaryUsers(){
        return userService.findAllOrdinaryUser();
    }
    
    public List<UserDTO> getPendingRegistrations(){
        return userService.findAllPendingRegistration();
    }
    public void acceptRegistration(UserDTO user){
        user.setActive(true);
        userService.updateUser(user);
    }
    
    public void declineRegistration(UserDTO user){
        users.remove(user);
        userService.deleteUser(user);
    }
    
    public void deleteUser(UserDTO user){
        users.remove(user);
        userService.deleteUser(user);
    }
}
