/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.arsw.eata.persistence.impl;

import eci.arsw.eata.model.FreeTime;
import eci.arsw.eata.model.Group;
import eci.arsw.eata.model.Location;
import eci.arsw.eata.model.Meeting;
import eci.arsw.eata.model.User;
import eci.arsw.eata.persistence.EataNotFoundException;
import eci.arsw.eata.persistence.EataPersistence;
import eci.arsw.eata.persistence.EataPersistenceException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2101751
 */
@Service
public class LocalEataPersistence implements EataPersistence{
    
    private final Map<Integer,User> users=new HashMap<>();
    private final Map<Integer,Group> groups=new HashMap<>();
    private final Map<Integer,Meeting> meetings=new HashMap<>();
    
    public LocalEataPersistence() {
        
        //Creacion de usuarios QUEMADOS
        User u1 = new User("Alejandro Villarraga", 2101751, "1234" , "asdvasd@mail.com", "macho");
        User u2 = new User("Jairo Gonzalez", 2090540, "1234" , "aaaa@mail.com", "macho");
        User u3 = new User("Miguel Rojas", 2099444, "1234" , "bbbbbb@mail.com", "macho");
        User u4 = new User("Juan Carlos", 2098165, "1234" , "pollo@mail.com", "macho");
        User u5 = new User("User Prueba",1 , "1234" , "prueba@mail.com", "macho");
        User u6 = new User("Camila Martinez",4341324 , "1234" , "prfdsdueba@mail.com", "mujer");
        User u7 = new User("Andrea Rojas",134342 , "1234" , "prufasddeba@mail.com", "mujer");
        User u8 = new User("Carlos Torres",3243 , "1234" , "pruasdfsdeba@mail.com", "macho");
        User u9 = new User("Santiago Diaz",543435, "1234" , "prsdfufseba@mail.com", "macho");
        User u10 = new User("Sara Corrles",543534, "1234" , "pruasdeba@mail.com", "mujer");
        
        //Se agregan amigos a todos
        u1.addFriend(2090540);u1.addFriend(2099444);u1.addFriend(1);
        u2.addFriend(2101751);u2.addFriend(2099444);u2.addFriend(2098165);u2.addFriend(1);
        u3.addFriend(2101751);u3.addFriend(2090540);u3.addFriend(2098165);u3.addFriend(1);
        u4.addFriend(2090540);u4.addFriend(2099444);u4.addFriend(1);
        u5.addFriend(2101751);u5.addFriend(2090540);u5.addFriend(2099444);u5.addFriend(2098165);
   
        
        //Se crean horas libres
        FreeTime hl1 = new FreeTime("lunes", 8, 10);
        FreeTime hl2 = new FreeTime("martes", 7, 12);
        FreeTime hl3 = new FreeTime("martes", 10, 15);
        FreeTime hl4 = new FreeTime("jueves", 10, 11);
        FreeTime hl5 = new FreeTime("miercoles", 10, 11);
        FreeTime hl6 = new FreeTime("viernes", 13, 19);
        FreeTime hl7 = new FreeTime("lunes", 8, 16);
        FreeTime hl8 = new FreeTime("sabado", 7, 8);
        FreeTime hl9 = new FreeTime("martes", 9, 15);
        FreeTime hl10 = new FreeTime("miercoles", 7, 14);
        FreeTime hl11 = new FreeTime("viernes", 10, 12);
        FreeTime hl12 = new FreeTime("jueves", 12, 16);
        FreeTime hl13 = new FreeTime("lunes",13, 18);
        FreeTime hl14 = new FreeTime("sabado", 14, 16);
        FreeTime hl15 = new FreeTime("viernes", 8, 15);
        FreeTime hl16 = new FreeTime("jueves", 9, 11);
        FreeTime hl17 = new FreeTime("viernes", 10, 11);
        FreeTime hl18 = new FreeTime("jueves", 10, 11);
        
        //Se agregan horas libre
        u1.addFreeTime(hl1);
        u1.addFreeTime(hl2);
        u3.addFreeTime(hl3);
        u3.addFreeTime(hl4);
        u3.addFreeTime(hl5);
        u3.addFreeTime(hl6);
        u2.addFreeTime(hl7);
        u2.addFreeTime(hl8);
        u2.addFreeTime(hl9);
        u2.addFreeTime(hl10);
        u3.addFreeTime(hl11);
        u3.addFreeTime(hl12);
        u1.addFreeTime(hl13);
        u1.addFreeTime(hl14);
        u1.addFreeTime(hl15);
        u3.addFreeTime(hl16);
        u3.addFreeTime(hl17);
        u3.addFreeTime(hl18);
        
        //se agregan los usuarios al conjunto
        users.put(2101751, u1);
        users.put(2090540, u2);
        users.put(2099444, u3);
        users.put(2098165, u4);
        users.put(1, u5);
        users.put(4341324, u6);
        users.put(134342, u7);
        users.put(3243, u8);
        users.put(543435, u9);
        users.put(543534, u10);
        
        
        
        // Creacion de grupos QUEMADOS 
        ArrayList<Integer> members1 = new ArrayList<Integer>();
        members1.add(2101751);
        members1.add(2090540);
        members1.add(1);
        
        ArrayList<Integer> members2 = new ArrayList<Integer>();
        members2.add(2101751);
        members2.add(2090540);
        members2.add(2099444);
        members2.add(2098165);
        
        ArrayList<Integer> members3 = new ArrayList<Integer>();
        members3.add(2098165);
        members3.add(2099444);
        
        
        
        //creacion de grupos
        
        Group g1 = new Group(members1, 1, "arsw trabajo", "Este grupo es para hacer lab de arsw");
        Group g2 = new Group(members2, 2, "segi trabajo", "seminariop de segi");
        Group g3 = new Group(members3, 3, "PGR1", "pgr1 no me funciona nada");
        
        groups.put(1, g1);
        groups.put(2, g2);
        groups.put(3, g3);
        
        //Creacion de reuniones
        Date fecha = new Date(2017,10,22);
        
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        Meeting m1 = new Meeting(0, fechaSQL, "estudio", "estudiaremos para examenes");
        Meeting m2 = new Meeting(1, fechaSQL, "reunion pgr", "adelanto de pgr");
        Meeting m3 = new Meeting(2, fechaSQL, "almuerzo", "reunamonos para almorzar");
        
        meetings.put(0, m1);
        meetings.put(1, m2);
        meetings.put(2, m3);
        
        //Se agregan las reuniones a los grupos
        g1.addMeeting(0);
        g2.addMeeting(1);
        g2.addMeeting(2);
        
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
        
        if (users.containsKey(user.getIdUser())){
            throw new EataPersistenceException("El usuario dado ya existe: "+user);
        }
        else{
            users.put(user.getIdUser(), user);
        } 
    }
    
    @Override
    public Set<Group> getMyGroups(int idUser) throws EataNotFoundException {
        Set<Group> allGroups = new HashSet<>();
        
        for (Map.Entry<Integer, Group> entry : groups.entrySet()) {
            Integer key = entry.getKey();
            Group value = entry.getValue();
            ArrayList<Integer> miembros = value.getMembers();
            if(miembros.contains(idUser)){https://github.com/Proyectoarsw2017/ARSW-Proyecto-EncuentraATusAmigos-2017-2.git
                allGroups.add(value);
            }
            
        }
        return allGroups;
    }
    
    @Override
    public void saveGroup(Group group) throws EataPersistenceException {
        System.out.println("esta en el save");
        if (groups.containsKey(group.getId())){
            throw new EataPersistenceException("El usuario dado ya existe: "+group);
        }
        else{
            groups.put(groups.size()+1, group);
        }
    }
    @Override
    public void saveMeeting(Meeting meeting) throws EataPersistenceException {
        System.out.println("esta en el save");
        if (meetings.containsKey(meeting.getId())){
            throw new EataPersistenceException("La reunion ya existe: "+meeting);
        }
        else{
            meetings.put(meetings.size()+1, meeting);
        }
    }
    
    
    @Override
    public Set<User> getMyFriends(int idUser) throws EataNotFoundException {
        Set<User> myFriends = new HashSet<>();
        User currentUser = users.get(idUser);
        ArrayList<Integer> idFriends = currentUser.getFriends();
        
        for (int i=0; i<idFriends.size(); i++) {
            int idnew = idFriends.get(i);
            myFriends.add(users.get(idnew));
        }
        
        return myFriends;
    }
    
    @Override
    public void addMeeting(int idMetting, int idGroup) throws EataPersistenceException {
        groups.get(idGroup).addMeeting(idMetting);
        
    }

    @Override
    public void addFriend(int friendDocument, int idUser) throws EataPersistenceException {
        users.get(idUser).addFriend(friendDocument);
    }

    @Override
    public User getUserByDocument(int idUser) throws EataNotFoundException {
        return users.get(idUser);
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
    public ArrayList<FreeTime> getFreeTimebyUser(int idUser) throws EataNotFoundException {
        return users.get(idUser).getFreeTime();
    }
    
    //Este metodo calcula el tiepo libre comun de los estudiantes
    @Override
    public ArrayList<FreeTime> getCommonFreeTimebyGroup(int idGroup) throws EataNotFoundException {
        ArrayList<FreeTime> commonFreeTime = new ArrayList<FreeTime>();
        
        Set<User> usersByGroup = this.getUsersByGroup(idGroup);
        Iterator<User> itus = usersByGroup.iterator();
        ArrayList<Integer> revisados = new ArrayList<Integer>();
        
        for (Iterator<User> iterator = usersByGroup.iterator(); iterator.hasNext();) {
            User next = iterator.next();
            
            ArrayList<FreeTime> ft = next.getFreeTime();
            for (int e = 0; e < ft.size(); e++) {
                FreeTime ftini = ft.get(e);
                
                for (Iterator<User> iterator1 = usersByGroup.iterator(); iterator1.hasNext();) {
                    User next1 = iterator1.next();
                    
                    if (next.getIdUser() != next1.getIdUser()) {
                        ArrayList<FreeTime> ftother = next1.getFreeTime();
                        
                        for (int j = 0; j < ftother.size(); j++) {
                            FreeTime ftcurrent = ftother.get(j);
                            if (ftcurrent.getDay() == ftini.getDay()) {
                                if (revisados.contains(next1.getIdUser())) {
                                    System.out.println("true");
                                    
                                } else {
                                    System.out.println("false");
                                    //Primer caso
                                    if (ftini.getStart() == ftcurrent.getStart() && ftini.getEnd() == ftcurrent.getEnd()) {
                                        FreeTime temp = new FreeTime(ftini.getDay(), ftini.getStart(), ftini.getEnd());

                                        commonFreeTime.add(temp);
                                    }
                                    //segundo caso
                                    if (ftini.getStart() > ftcurrent.getStart() && ftini.getEnd() > ftcurrent.getEnd() && ftini.getStart() < ftcurrent.getEnd()) {
                                        FreeTime temp = new FreeTime(ftini.getDay(), ftini.getStart(), ftcurrent.getEnd());

                                        commonFreeTime.add(temp);
                                    }
                                    //tercer caso
                                    if (ftini.getStart() < ftcurrent.getStart() && ftini.getEnd() < ftcurrent.getEnd() && ftini.getEnd() > ftcurrent.getStart()) {
                                        FreeTime temp = new FreeTime(ftini.getDay(), ftcurrent.getStart(), ftini.getEnd());

                                        commonFreeTime.add(temp);
                                    }
                                    //cuarto caso
                                    if (ftini.getStart() > ftcurrent.getStart() && ftini.getEnd() < ftcurrent.getEnd()) {
                                        FreeTime temp = new FreeTime(ftini.getDay(), ftini.getStart(), ftini.getEnd());

                                        commonFreeTime.add(temp);
                                    }
                                    //quinto caso
                                    if (ftini.getStart() < ftcurrent.getStart() && ftini.getEnd() > ftcurrent.getEnd()) {
                                        FreeTime temp = new FreeTime(ftini.getDay(), ftcurrent.getStart(), ftcurrent.getEnd());

                                        commonFreeTime.add(temp);
                                    }
                                }
                                
                                
                            }
                            
                        }
                        revisados.add(next.getIdUser());
                    }
            }
            }
            
        }
     
        return commonFreeTime;
    }

    @Override
    public Set<Meeting> getMeetingsByGroup(int idGroup) throws EataNotFoundException {
        Set<Meeting> meetingsByGroup = new HashSet<>();
        Group currentGroup = groups.get(idGroup);
        ArrayList<Integer> idMeetings = currentGroup.getMeetings();
        
        for (int i=0; i<idMeetings.size(); i++) {
            int idnew = idMeetings.get(i);
            meetingsByGroup.add(meetings.get(idnew));
        }
        
        return meetingsByGroup;
    }

    @Override
    public void addNewUserConected(int idUser) {
        users.get(idUser).setOnline(true);
    }

    @Override
    public ArrayList<User> getMyFriendsConected(int idUser) {
        
        Set<User> misAmigos = null;
        try {
            misAmigos = this.getMyFriends(idUser);
        } catch (EataNotFoundException ex) {
            Logger.getLogger(LocalEataPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<User> myFriendsOnline = new ArrayList<User>();
        
        for (Iterator<User> iterator = misAmigos.iterator(); iterator.hasNext();) {
            User next = iterator.next();
            if(next.getOnline()){
                myFriendsOnline.add(next);
            }
        }
        
        return myFriendsOnline;
    }

    @Override
    public void addNewUserPosition(int idUser, double  lat, double  lon) {
        Location pos = new Location(lat, lon);
        users.get(idUser).setLocation(pos);
    }

    @Override
    public void disconectUser(int idUser) {
        users.get(idUser).setOnline(false);
    }

    @Override
    public Map<Integer, Integer> porcentajeDePersonasConectadasPorGrupo() {
        Map<Integer, Integer> resp =new HashMap<>();
        
        for (Map.Entry<Integer, Group> entry : groups.entrySet()) {
            int cantOnline = 0;
            Integer key = entry.getKey();
            Group gr = entry.getValue();
            ArrayList<Integer> miembros = gr.getMembers();
            for(int i=0; i<miembros.size(); i++){
                System.out.println("Prueba booleaana: "+users.get(miembros.get(i)).getOnline());
                if(users.get(miembros.get(i)).getOnline()){
                    cantOnline = cantOnline+1;
                }
            }
            
            int porcentaje = (cantOnline*100)/miembros.size();
            System.out.println("cantOnline: "+cantOnline+" numero de miembros: "+miembros.size());
            System.out.println("Porcentaje: "+porcentaje+" del grupo: "+key);
            resp.put(key, porcentaje);
        }
        
        return resp;
    }

    @Override
    public boolean sonAmigos(int idUser1, int idUser2) {
        boolean sonAmigoslosDos = false;
        ArrayList<Integer> a1 = users.get(idUser1).getFriends();
        ArrayList<Integer> a2 = users.get(idUser2).getFriends();
        if(a1.contains(idUser2)&& a2.contains(idUser1)){
            sonAmigoslosDos=true;
        }
        return sonAmigoslosDos;
    }

    @Override
    public Group getGroupById(int idGroup) {
        return groups.get(idGroup);
    }

    @Override
    public void addMeetingByGroup(int idMeeting, int idGroup) {
        groups.get(idGroup).addMeeting(idMeeting);
    }

    @Override
    public ArrayList<Meeting> getAllMeetings() {
        
        ArrayList<Meeting> reuniones = new ArrayList<Meeting>();
        for (Map.Entry<Integer, Meeting> entry : meetings.entrySet()) {
            int cantOnline = 0;
            Integer key = entry.getKey();
            Meeting gr = entry.getValue();
            reuniones.add(gr);
            
        }
        return reuniones;
    }

    @Override
    public boolean perteneceAlGrupo(int idUser, int idGroup) {
        Boolean resp = false;
        
        ArrayList<Integer> miembros = groups.get(idGroup).getMembers();
            for(int i=0; i<miembros.size(); i++){
                if(miembros.get(i)==idUser){
                    resp=true;
                }
            }
           
        return resp;
    }


    
    
}
