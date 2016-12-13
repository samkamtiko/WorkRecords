package model;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;

public class UserHandlerMongoDB implements UserHandler {

    private static UserHandlerMongoDB instance = new UserHandlerMongoDB();

    public static UserHandlerMongoDB getInstance() {
        return instance;
    }

    private MongoCollection<BasicDBObject> mUserCollection;
    private final String DB_HOSTNAME = "localhost";
    private final String DB_NAME = "tmp";

    public UserHandlerMongoDB() {
        mUserCollection = new MongoClient(DB_HOSTNAME)
                .getDatabase(DB_NAME)
                .getCollection("Users", BasicDBObject.class);
    }

    private User decodeUser(BasicDBObject dbObject) {
        User user = new User();
        user.setId(dbObject.getString("_id"));
        user.setGroup(UserGroup.getFromInt(dbObject.getInt("group")));
        user.setLogin(dbObject.getString("login"));
        user.setName(dbObject.getString("name"));
        user.setPassword(dbObject.getString("password"));
//        user.setSalary(dbObject.getInt("salary"));
        return user;
    }

    @Override
    public User getById(String id) {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        BasicDBObject result = mUserCollection.find(query).first();
        if (result == null) {
            return null;
        }
        return decodeUser(result);
    }

    @Override
    public ArrayList<User> getListOfUsert() {
        ArrayList<User> listOfUsers = new ArrayList<>();
        for(BasicDBObject obj: mUserCollection.find()) {
            listOfUsers.add(decodeUser(obj));
        }
        return listOfUsers;
    }

    @Override
    public void addUser(User user) {
        BasicDBObject doc = new BasicDBObject()
            .append("name", user.getName())
            .append("login", user.getLogin())
            .append("password", user.getPassword())
            .append("salary", 0)
            .append("group", 0);
        mUserCollection.insertOne(doc);
        user.setId(doc.getString("_id"));
    }

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost");
        MongoDatabase db = client.getDatabase("tmp");

        for(String name: db.listCollectionNames()) {
            System.out.println(name);
        }

        MongoCollection<BasicDBObject> col = db.getCollection("tasks", BasicDBObject.class);
        BasicDBObject doc = new BasicDBObject("x", 1);
        col.insertOne(doc);
        doc.append("x", 2).append("y", 3);
        col.replaceOne(Filters.eq("_id", doc.get("_id")), doc);
    }
}
