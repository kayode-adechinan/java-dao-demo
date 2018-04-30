import java.util.ArrayList;

public class DemoDAO {
    public static void main(String[] args) {

        //ProductDAO productDAO = new MysqlImplementation();

        ProductDAO productDAO = new MongoDBImplementation();

        // create product
        productDAO.createProduct(new Product("Hat", 2000));
        productDAO.createProduct(new Product("Shirt", 1500));
        productDAO.createProduct(new Product("Shoes", 3000));

        // get products

        ArrayList<Product> products = productDAO.getAllProducts();

        // display our products
        products.forEach(System.out::println);


    }
}
