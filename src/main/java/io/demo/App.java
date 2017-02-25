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
        deleteTickets(mongo,"rotem@gmail.com");
        deleteUser(mongo,"shahar@gmail.com");
    }

    public static void addUsers(Mongo mongo){
        mongo.addUser(new User("rotem", "zaig", "rotem@gmail.com", "1234"));
        mongo.addUser(new User("shahar", "levi", "shahar@gmail.com", "1234"));
        mongo.addUser(new User("blabla", "levi", "shahar@gmail.com", "1234"));
    }

    public static void addTickets(Mongo mongo){
        Date date = new Date();
        mongo.addTicket(new Ticket("rotem@gmail.com", "ticket number 1", date));
        mongo.addTicket(new Ticket("shahar@gmail.com", "ticket number 2", date));
    }

    public static void deleteTickets(Mongo mongo, String email){
        mongo.deleteTicket(email);
    }

    public static void deleteUser(Mongo mongo,String email){
        mongo.deleteUser(email);
    }
}