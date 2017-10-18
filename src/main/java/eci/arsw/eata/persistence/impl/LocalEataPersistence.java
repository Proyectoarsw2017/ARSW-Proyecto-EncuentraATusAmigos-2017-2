/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.arsw.eata.persistence.impl;

import eci.arsw.eata.model.FreeTime;
import eci.arsw.eata.model.Group;
import eci.arsw.eata.model.Meeting;
import eci.arsw.eata.model.User;
import eci.arsw.eata.persistence.EataNotFoundException;
import eci.arsw.eata.persistence.EataPersistence;
import eci.arsw.eata.persistence.EataPersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2101751
 */
@Service
public class LocalEataPersistence implements EataPersistence{
    
    private final Map<Integer,User> users=new HashMap<>();
    private final Map<Integer,Group> groups=new HashMap<>();
    
    public LocalEataPersistence() {
        
        //Creacion de usuarios QUEMADOS
        User u1 = new User("Alejandro Villarraga", 2101751, "1234" , "asdvasd@mail.com", "macho");
        User u2 = new User("Jairo Gonzalez", 2090540, "1234" , "aaaa@mail.com", "macho");
        User u3 = new User("Miguel Rojas", 2099444, "1234" , "bbbbbb@mail.com", "macho");
        User u4 = new User("Juan Carlos", 2098165, "1234" , "pollo@mail.com", "macho");
        
        //Se agregan amigos a u1
        u1.addFriend(2090540);
        u1.addFriend(2099444);
        u1.addFriend(2098165);
        
        //se agregan amigos a u4
        u4.addFriend(2099444);
        u4.addFriend(2101751);
        
        //Se crean horas libres
        FreeTime hl1 = new FreeTime("lunes", 8, 10);
        FreeTime hl2 = new FreeTime("martes", 7, 12);
        FreeTime hl3 = new FreeTime("martes", 10, 11);
        FreeTime hl4 = new FreeTime("miercoles", 10, 11);
        FreeTime hl5 = new FreeTime("miercoles", 10, 11);
        FreeTime hl6 = new FreeTime("martes", 10, 11);
        
        //Se agregan horas libre
        u1.addFreeTime(hl1);
        u1.addFreeTime(hl2);
        u3.addFreeTime(hl3);
        
        //se agregan los usuarios al conjunto
        users.put(2101751, u1);
        users.put(2090540, u2);
        users.put(2099444, u3);
        users.put(2098165, u4);
        
        // Creacion de grupos QUEMADOS 
        ArrayList<Integer> members1 = new ArrayList<Integer>();
        members1.add(2101751);
        members1.add(2099444);
        
        ArrayList<Integer> members2 = new ArrayList<Integer>();
        members2.add(2101751);
        members2.add(2090540);
        members2.add(2099444);
        
        ArrayList<Integer> members3 = new ArrayList<Integer>();
        members3.add(2098165);
        
        
        Group g1 = new Group(members1, 1, "arsw trabajo", "Este grupo es para hacer lab de arsw");
        Group g2 = new Group(members2, 2, "segi trabajo", "seminariop de segi");
        Group g3 = new Group(members3, 3, "PGR1", "pgr1 no me funciona nada");
        
        groups.put(1, g1);
        groups.put(2, g2);
        groups.put(3, g3);
        
    }

    @Override
    public Set<User> getAllUsers() throws EataNotFoundException {
        Set<User> allUsers = new HashSet<>();
        
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            Integer key = entry.getKey();
            User value = entry.getValue();
            allUsers.add(value);
        }
        return allUsers;
    }
    
    @Override
    public Set<Group> getAllGroups() throws EataNotFoundException {
        Set<Group> allGroups = new HashSet<>();
        
        for (Map.Entry<Integer, Group> entry : groups.entrySet()) {
            Integer key = entry.getKey();
            Group value = entry.getValue();
            allGroups.add(value);
        }
        return allGroups;
    }
    
    @Override
    public void saveUser(User user) throws EataPersistenceException {
        
        if (users.containsKey(user.getDocument())){
            throw new EataPersistenceException("El usuario dado ya existe: "+user);
        }
        else{
            users.put(user.getDocument(), user);
        } 
    }
    
    @Override
    public Set<Group> getMyGroups(int document) throws EataNotFoundException {
        Set<Group> allGroups = new HashSet<>();
        
        for (Map.Entry<Integer, Group> entry : groups.entrySet()) {
            Integer key = entry.getKey();
            Group value = entry.getValue();
            ArrayList<Integer> miembros = value.getMembers();
            if(miembros.contains(document)){
                allGroups.add(value);
            }
            
        }
        return allGroups;
    }
    
    @Override
    public void saveGroup(Group group) throws EataPersistenceException {
        if (groups.containsKey(group.getId())){
            throw new EataPersistenceException("El usuario dado ya existe: "+group);
        }
        else{
            groups.put(group.getId(), group);
        }
    }
    
    @Override
    public Set<User> getMyFriends(int document) throws EataNotFoundException {
        Set<User> myFriends = new HashSet<>();
        User currentUser = users.get(document);
        ArrayList<Integer> idFriends = currentUser.getFriends();
        
        for (int i=0; i<idFriends.size(); i++) {
            int idnew = idFriends.get(i);
            myFriends.add(users.get(idnew));
        }
        
        return myFriends;
    }
    
    @Override
    public void addMeeting(Meeting metting, int idGroup) throws EataPersistenceException {
        groups.get(idGroup).addMeeting(metting);
        
    }

    @Override
    public void addFriend(int friendDocument, int document) throws EataPersistenceException {
        users.get(document).addFriend(friendDocument);
    }

    @Override
    public User getUserByDocument(int document) throws EataNotFoundException {
        return users.get(document);
    }

    @Override
    public Set<User> getUsersByGroup(int idGroup) throws EataNotFoundException {
        
        Set<User> usersGroup = new HashSet<>();
        ArrayList<Integer> idUsersGroup = groups.get(idGroup).getMembers();
        
        for (int i=0; i<idUsersGroup.size(); i++) {
            int idnew = idUsersGroup.get(i);
            usersGroup.add(users.get(idnew));
        }
        
        return usersGroup;
    }

    @Override
    public void deleteGroup(int idGroup) throws EataNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<FreeTime> getFreeTimebyUser(int document) throws EataNotFoundException {
        return users.get(document).getFreeTime();
    }

    @Override
    public ArrayList<FreeTime> getCommonFreeTimebyGroup(int idGroup) throws EataNotFoundException {
        ArrayList<FreeTime> commonFreeTime = new ArrayList<FreeTime>();
 
        
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            Integer key = entry.getKey();
            User value = entry.getValue();
            
            ArrayList<FreeTime> ft = value.getFreeTime();
            for (int e = 0; e < ft.size(); e++) {
                FreeTime ftini = ft.get(e);
                
                for (Map.Entry<Integer, User> i : users.entrySet()) {
                    
                    if (value.getDocument() != i.getValue().getDocument()) {
                        ArrayList<FreeTime> ftother = i.getValue().getFreeTime();
                        
                        for (int j = 0; j < ftother.size(); j++) {
                            FreeTime ftcurrent = ftother.get(j);
                            if (ftcurrent.getDay() == ftini.getDay()) {
                                //Primer caso
                                if(ftini.getStart()==ftcurrent.getStart() && ftini.getEnd()==ftcurrent.getEnd()){
                                    FreeTime temp = new FreeTime(ftini.getDay(),ftini.getStart(),ftini.getEnd());
                                    commonFreeTime.add(temp);
                                }
                                //segundo caso
                                if(ftini.getStart()>ftcurrent.getStart() && ftini.getEnd()>ftcurrent.getEnd()){
                                    FreeTime temp = new FreeTime(ftini.getDay(),ftini.getStart(),ftcurrent.getEnd());
                                    commonFreeTime.add(temp);
                                }
                                //tercer caso
                                if(ftini.getStart()<ftcurrent.getStart() && ftini.getEnd()<ftcurrent.getEnd()){
                                    FreeTime temp = new FreeTime(ftini.getDay(),ftcurrent.getStart(),ftini.getEnd());
                                    commonFreeTime.add(temp);
                                }
                                //cuarto caso
                                if(ftini.getStart()>ftcurrent.getStart() && ftini.getEnd()<ftcurrent.getEnd()){
                                    FreeTime temp = new FreeTime(ftini.getDay(),ftini.getStart(),ftini.getEnd());
                                    commonFreeTime.add(temp);
                                }
                                //quinto caso
                                if(ftini.getStart()<ftcurrent.getStart() && ftini.getEnd()>ftcurrent.getEnd()){
                                    FreeTime temp = new FreeTime(ftini.getDay(),ftcurrent.getStart(),ftcurrent.getEnd());
                                    commonFreeTime.add(temp);
                                }
                            }
                            
                        }
                    }

            }
            }
            
            
        }
        
        return commonFreeTime;
    }

    
}
