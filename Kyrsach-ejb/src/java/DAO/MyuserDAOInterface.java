/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Myuser;
import Model.MyuserRole;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ceparator
 */
@Local
public interface MyuserDAOInterface {

    void addUser(String username, String password, String role) throws Exception;

    List<Myuser> getAllUsers();

    public void changePassword(String usernm, String password) throws Exception;

    public boolean correctOldPassword(String username, String password) throws Exception;

    public boolean exist(String username);

    public Myuser findUserById(String username);

    public void deleteUser(String username);

    public String getUserRole(String username);

    public void editUser(String username, String role, String oldUsername) throws Exception;

    public List<MyuserRole> getUsersByRole(String userrole);
    
}
