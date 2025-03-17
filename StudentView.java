import java.sql.SQLException;
import java.util.List;

public class StudentView {
    public static void main(String[] args) {
        StudentController controller = new StudentController();

        try {
            // Insert a new student
            Student student = new Student(1, "Alice", "CS", 85.5);
            controller.insertStudent(student);
            System.out.println("Student inserted successfully!");

            // Display all students
            List<Student> students = controller.getAllStudents();
            for (Student s : students) {
                System.out.println(s.getStudentID() + " | " + s.getName() + " | " + s.getDepartment() + " | " + s.getMarks());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
