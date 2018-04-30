import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class MongoDBImplementation implements ProductDAO {
    private ArrayList<Product> productArrayList;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public MongoDBImplementation(){
        this.productArrayList = new ArrayList<>();
        this.mongoClient = new MongoClient();
        this.database = mongoClient.getDatabase("productsDB");
        this.collection = database.getCollection("products");

    }

    @Override
    public void createProduct(Product product) {
        Document productDoc = new Document("name", product.getName())
                .append("price", product.getPrice());
        collection.insertOne(productDoc);
        System.out.println("Product was inserted successfully");
    }

    @Override
    public ArrayList<Product> getAllProducts() {

        Block<Document> printBlock = document -> this.productArrayList.add(
                new Product(document.getString("name"), document.getDouble("price"))
        );
        collection.find().forEach(printBlock);

        return this.productArrayList;
    }
}
