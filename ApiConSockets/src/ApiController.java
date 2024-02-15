import java.io.*;
import java.net.Socket;
import java.sql.*;

public class ApiController implements Runnable {
    Connection conn;
    Socket client;

    public ApiController(Socket client) throws SQLException {
        this.client = client;
        this.conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/w3schools", "root", "");
    }

    private String getProducts() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM products");
        String result = "";
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            result += rs.getString(2) + ";";
        }
        return result;
    }

    private String getSuppliers() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM suppliers");
        String result = "";
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            result += rs.getString(2) + ";";
        }
        return result;
    }

    private String getCustomer(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM customers WHERE CustomerID = ?");
        statement.setInt(1, id);
        String result = "";
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            result += rs.getString(2) + ";";
        }
        return result;
    }

    private String deleteProduct(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM products WHERE ProductID = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
        return "Producto eliminado";
    }

    @Override
    public void run() {
        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            String url = bfr.readLine();
            String api = url.split("/")[url.split("/").length - 1];
            if (api.contains("getProducts")) {
                bfw.write(getProducts());
                bfw.newLine();
                bfw.flush();
            } else if (api.contains("getSuppliers")) {
                bfw.write(getSuppliers());
                bfw.newLine();
                bfw.flush();
            } else if (api.contains("getCustomer")) {
                String idCustomer = api.split(":")[1];
                bfw.write(getCustomer(Integer.parseInt(idCustomer)));
                bfw.newLine();
                bfw.flush();
            } else if (api.contains("deleteProduct")) {
                String idProduct = api.split(":")[1];
                bfw.write(deleteProduct(Integer.parseInt(idProduct)));
                bfw.newLine();
                bfw.flush();
            } else {
                System.out.println("ERROR 404 NOT FOUND");
            }
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}