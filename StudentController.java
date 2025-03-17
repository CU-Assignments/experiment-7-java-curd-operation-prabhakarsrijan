import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    static final String URL = "jdbc:mysql://localhost:3306/your_database";
    static final String USER = "Aditya";
    static final String PASSWORD = "Aditya@1234";

    public void insertStudent(Student student) throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, student.getStudentID());
        pstmt.setString(2, student.getName());
        pstmt.setString(3, student.getDepartment());
        pstmt.setDouble(4, student.getMarks());
        pstmt.executeUpdate();
        con.close();
    }

    public List<Student> getAllStudents() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

        List<Student> students = new ArrayList<>();
        while (rs.next()) {
            students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"),
                    rs.getString("Department"), rs.getDouble("Marks")));
        }
        con.close();
        return students;
    }
}
