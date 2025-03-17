import java.sql.*;
import java.util.Scanner;

public class MediumLevelJDBC {
    static final String URL = "jdbc:mysql://localhost:3306/Student_2";
    static final String USER = "Aditya";
    static final String PASSWORD = "Aditya@1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            int choice;
            do {
                System.out.println("\n1. Insert Product\n2. View Products\n3. Update Product\n4. Delete Product\n5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> insertProduct(con);
                    case 2 -> viewProducts(con);
                    case 3 -> updateProduct(con);
                    case 4 -> deleteProduct(con);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    private static void insertProduct(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setDouble(2, price);
        pstmt.setInt(3, quantity);
        pstmt.executeUpdate();
        System.out.println("Product inserted successfully!");
    }

    private static void viewProducts(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

        System.out.println("ProductID | ProductName | Price | Quantity");
        while (rs.next()) {
            System.out.println(rs.getInt("ProductID") + " | " + rs.getString("ProductName") + " | " +
                               rs.getDouble("Price") + " | " + rs.getInt("Quantity"));
        }
    }

    private static void updateProduct(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ProductID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new Price: ");
        double price = scanner.nextDouble();

        String sql = "UPDATE Product SET Price = ? WHERE ProductID = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setDouble(1, price);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
        System.out.println("Product updated successfully!");
    }

    private static void deleteProduct(Connection con) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ProductID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM Product WHERE ProductID = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Product deleted successfully!");
    }
}
