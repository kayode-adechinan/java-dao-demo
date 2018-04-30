import java.util.ArrayList;

public interface ProductDAO {

    void createProduct(Product product);
    ArrayList<Product> getAllProducts();

}
