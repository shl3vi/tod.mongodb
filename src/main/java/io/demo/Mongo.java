package io.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

public class Mongo {

    MongoClient mongo;
    DB db;
    DBCollection usersTable;
    DBCollection ticketsTable;

    public void connect() throws UnknownHostException {
        mongo = new MongoClient("localhost", 27017);
    }

    public void createDB() {
        db = mongo.getDB("secondHand");
    }

    public void createTables() {
        createUsersTable();
        createTicketsTable();
    }

    private void createUsersTable() {
        /**** Get collection / usersTable from 'secondHand' ****/
        // if collection doesn't exists, MongoDB will create it for you
        usersTable = db.getCollection("users");
    }

    private void createTicketsTable() {ticketsTable = db.getCollection("tickets");}

    public void addUser(User user) {
        /**** Insert ****/
        // create a document to store key and value
        BasicDBObject document = new BasicDBObject();
        document.put("first_name", user.getFirstName());
        document.put("last_name", user.getLastName());
        document.put("email", user.getEmail()); // primary key
        usersTable.insert(document);
        System.out.println("user was added to users document successfully");
    }

    public void addTicket(Ticket ticket) {
        BasicDBObject document = new BasicDBObject();
        document.put("publisherId", ticket.getPublisherEmail());
        document.put("title", ticket.getTitle());
        document.put("date", ticket.getDate());
        ticketsTable.insert(document);
        System.out.println("ticket was added to tickets document successfully");
    }

    public void updateUsersTable(String email, String key, String newVal){
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append(key, newVal));
        BasicDBObject searchQuery = new BasicDBObject().append("email", email);
        usersTable.update(searchQuery, newDocument);
        System.out.println("user was updated with new record: " + newVal);
    }

    public void updateTicketsTable(String publisherEmail, String key, String newVal){
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append(key, newVal));
        BasicDBObject searchQuery = new BasicDBObject().append("publisherEmail",publisherEmail);
        usersTable.update(searchQuery, newDocument);
        System.out.println("ticket was updated with new record: " + newVal);
    }

    public void deleteTicket(String publisherEmail) {
        BasicDBObject document = new BasicDBObject();
        document.put("publisherEmail",publisherEmail);
        ticketsTable.remove(document);
        System.out.println("ticket with publisher email " +publisherEmail + " was deleted");
    }

    public void deleteUser(String email) {
        BasicDBObject document = new BasicDBObject();
        document.put("email", email);
        usersTable.remove(document);
        System.out.println("user " + email + " was deleted");
    }

    public void dropExistingDB(){
        usersTable.drop();
        ticketsTable.drop();
    }

    public Mongo() throws UnknownHostException {
    }
}
