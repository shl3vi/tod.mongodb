package io.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;

public class Mongo {

    MongoClient mongo;
    MongoDatabase db;
    MongoCollection<Document> usersTable;
    MongoCollection<Document> ticketsTable;

    public void connect() throws UnknownHostException {
        mongo = new MongoClient("localhost", 27017);
    }

    public void createDB() {
        db = mongo.getDatabase("secondHand");
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

    private void createTicketsTable() {
        ticketsTable = db.getCollection("tickets");
    }

    public void addUser(User user) {
        /**** Insert ****/
        // create a document to store key and value
        Document document = new Document();
        document.put("first_name", user.getFirstName());
        document.put("last_name", user.getLastName());
        document.put("email", user.getEmail()); // primary key
        usersTable.insertOne(document);
        System.out.println("user was added to users collection successfully");
    }

    public void addTicket(Ticket ticket) {
        Document document = new Document();
        document.put("publisherEmail", ticket.getPublisherEmail());
        document.put("title", ticket.getTitle());
        document.put("date", ticket.getDate());
        ticketsTable.insertOne(document);
        System.out.println("ticket was added to tickets collection successfully");
    }

    public void updateUsersDocument(String primaryKey, String key, String newVal){
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append(key, newVal));
        BasicDBObject searchQuery = new BasicDBObject().append("email", primaryKey);
        usersTable.updateOne(searchQuery, newDocument);
        System.out.println("user was updated with new record: " + newVal);
    }

    public void updateTicketDocument(String primaryKey, String key, String newVal){
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append(key, newVal));
        BasicDBObject searchQuery = new BasicDBObject().append("publisherEmail",primaryKey);
        usersTable.updateOne(searchQuery, newDocument);
        System.out.println("ticket was updated with new record: " + newVal);
    }

    public void deleteTicket(String publisherEmail) {
        BasicDBObject document = new BasicDBObject();
        document.put("publisherEmail",publisherEmail);
        ticketsTable.deleteOne(document);
        System.out.println("ticket with publisher email " + publisherEmail + " was deleted");
    }

    public void deleteUser(String email) {
        BasicDBObject document = new BasicDBObject();
        document.put("email", email);
        usersTable.deleteOne(document);
        System.out.println("user " + email + " was deleted");
    }

    public void dropExistingDB(){
        usersTable.drop();
        ticketsTable.drop();
    }

    public Mongo() throws UnknownHostException {
    }
}
