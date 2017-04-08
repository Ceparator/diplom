/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Myuser;
import Model.MyuserRole;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 *
 * @author Ceparator
 */
@Stateless
public class MyuserDAO implements MyuserDAOInterface {

    @PersistenceContext(unitName = "Kyrsach-ejbPU2")
    private EntityManager em2;

    @Override
    public void addUser(String username, String password, String role) throws Exception {
        Myuser myuser = new Myuser();
        myuser.setUsername(username);
        MessageDigest md = MessageDigest.getInstance("MD5");
        password = (new HexBinaryAdapter()).marshal(md.digest(password.getBytes(Charset.forName("UTF-8"))));
        myuser.setPassword(password);
        MyuserRole myuserRole = new MyuserRole();
        myuserRole.setUsername(myuser);
        myuserRole.setRole(role);
        em2.persist(myuser);
        em2.flush();
        em2.persist(myuserRole);
        em2.flush();
        List<MyuserRole> myuserRoleList = new ArrayList<>();
        myuserRoleList.add(myuserRole);
        myuser.setMyuserRoleList(myuserRoleList);
        em2.merge(myuser);
    }

    @Override
    public List<Myuser> getAllUsers() {
        Query query = em2.createQuery("SELECT u FROM Myuser u", Myuser.class);
        List<Myuser> myuserList = query.getResultList();
        List<Myuser> userList = new ArrayList<>();
        Iterator<Myuser> iter = myuserList.iterator();
        while (iter.hasNext()) {
            Myuser item = iter.next();
            Myuser someMyuser = new Myuser(item.getUsername(), item.getMyuserRoleList().get(0).getRole());
            userList.add(someMyuser);
        }
        return userList;
    }

    @Override
    public void editUser(String username, String role, String oldUsername) throws Exception {
        Myuser myuser = em2.find(Myuser.class, oldUsername);
        myuser.setUsername(username);
        em2.merge(myuser);
        em2.flush();
        MyuserRole newRole = myuser.getMyuserRoleList().get(0);
        newRole.setRole(role);
        newRole.setUsername(myuser);
        em2.merge(newRole);
    }

    @Override
    public String getUserRole(String username) {
        Query query = em2.createQuery("SELECT u FROM Myuser u WHERE u.username = ?1", Myuser.class);
        query.setParameter(1, username);
        Myuser us = (Myuser) query.getSingleResult();
        return us.getMyuserRoleList().get(0).getRole();
    }

    @Override
    public void deleteUser(String username) {
        em2.remove(em2.find(Myuser.class, username));
    }

    @Override
    public Myuser findUserById(String username) {
        return em2.find(Myuser.class, username);
    }

    @Override
    public boolean exist(String username) {
        Query query = em2.createQuery("SELECT u FROM Myuser u WHERE u.username = ?1", Myuser.class);
        query.setParameter(1, username);
        List<Myuser> myuserList = query.getResultList();
        if (myuserList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean correctOldPassword(String username, String password) throws Exception {
        Myuser user = em2.find(Myuser.class, username);
        MessageDigest md = MessageDigest.getInstance("MD5");
        password = (new HexBinaryAdapter()).marshal(md.digest(password.getBytes(Charset.forName("UTF-8"))));
        if (user.getPassword().toLowerCase().equals(password.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changePassword(String usernm, String password) throws Exception {
        Myuser user = em2.find(Myuser.class, usernm);
        MessageDigest md = MessageDigest.getInstance("MD5");
        password = (new HexBinaryAdapter()).marshal(md.digest(password.getBytes(Charset.forName("UTF-8"))));
        user.setPassword(password);
        em2.merge(user);
    }
    
    @Override
    public List<MyuserRole> getUsersByRole(String userrole){
        Query query = em2.createQuery("SELECT u FROM MyuserRole u WHERE u.role = ?1", MyuserRole.class);
        query.setParameter(1, userrole);
        return query.getResultList();
    }
}
