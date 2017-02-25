package io.demo;

import java.net.UnknownHostException;
import java.util.Date;

/**
 * Java + MongoDB Hello world Example
 */
public class App {
    public static void main(String[] args) throws UnknownHostException {
        Mongo mongo = new Mongo();
        mongo.connect();
        mongo.createDB();
        mongo.createTables();
        addUsers(mongo);
        addTickets(mongo);

       // deleteUser(mongo,"rotem@gmail.com");
       // deleteTickets(mongo,"rotem@gmail.com");

        updateTicket(mongo,"rotem@gmail.com","publisherEmail" ,"aaa@aaa.com");
    }

    public static void addUsers(Mongo mongo){
        mongo.addUser(new User("rotem", "zaig", "rotem@gmail.com", "1234"));
        //mongo.addUser(new User("shahar", "levi", "shahar@gmail.com", "1234"));
        //mongo.addUser(new User("blabla", "levi", "shahar@gmail.com", "1234"));
    }

    public static void addTickets(Mongo mongo){
        Date date = new Date();
        mongo.addTicket(new Ticket("rotem@gmail.com", "ticket number 1", date));
        //mongo.addTicket(new Ticket("shahar@gmail.com", "ticket number 2", date));
    }

    public static void deleteTickets(Mongo mongo, String email){
        mongo.deleteTicket(email);
    }

    public static void deleteUser(Mongo mongo,String email){
        mongo.deleteUser(email);
    }

    public static void updateTicket(Mongo mongo, String publisherEmail,String key, String newVal){
        mongo.updateTicketDocument(publisherEmail,key,newVal);
    }
}