import java.sql.*;
import java.util.ArrayList;

public class MysqlImplementation implements ProductDAO {

    private ArrayList<Product> productArrayList;
    private Connection connection;

    public MysqlImplementation(){
        this.productArrayList = new ArrayList<>();
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/productsDB","root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createProduct(Product product) {

        try{
            String sqlQuery = "INSERT INTO products (name, price) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Product was inserted successfully");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public ArrayList<Product> getAllProducts() {

        try{
            String sqlQuery = "SELECT * FROM products";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                this.productArrayList.add(
                        new Product(resultSet.getString("name"), resultSet.getDouble("price")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }



        return this.productArrayList;
    }
}
