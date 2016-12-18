package model;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class TaskHandlerMongoDB implements TaskHandler {

    private static TaskHandlerMongoDB instance = new TaskHandlerMongoDB();
    private MongoCollection<BasicDBObject> mTaskCollection;
    private final String DB_HOSTNAME = "localhost";
    private final String DB_NAME = "tmp";

    public TaskHandlerMongoDB() {
        mTaskCollection = new MongoClient(DB_HOSTNAME)
                .getDatabase(DB_NAME)
                .getCollection("Tasks", BasicDBObject.class);
    }

    public static TaskHandlerMongoDB getInstance() {
        return instance;
    }

    @Override
    public Task getById(String id) {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        BasicDBObject result = mTaskCollection.find(query).first();
        if (result == null) {
            return null;
        }
        return decodeTask(result);
    }

    private Task decodeTask(BasicDBObject obj) {
        Task task = new Task();
        task.setId(obj.getString("_id"));
        task.setName(obj.getString("name"));
        task.setDescription(obj.getString("desctiption"));
        return task;
    }

    @Override
    public ArrayList<Task> getListForUser(User user) {
        throw new NotImplementedException();
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for(BasicDBObject task: mTaskCollection.find()) {
            tasks.add(decodeTask(task));
        }
        return tasks;
    }

    @Override
    public void addTask(Task task) {
        BasicDBObject doc = new BasicDBObject()
                .append("name", task.getName())
                .append("description", task.getDescription());
        mTaskCollection.insertOne(doc);
        task.setId(doc.getString("_id"));
    }

    @Override
    public void updateTask(Task task) {
        String id = task.getId();
        BasicDBObject update = new BasicDBObject();
            update.put("$set", new BasicDBObject()
                    .append("name", task.getName())
                    .append("description", task.getDescription()));

        BasicDBObject searchItem = new BasicDBObject().append("_id", new ObjectId(id));
        mTaskCollection.updateOne(searchItem, update);
        task.setId(id);
    }


}
