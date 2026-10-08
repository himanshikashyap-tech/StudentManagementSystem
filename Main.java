import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        scanner.close();
    }

    // Add Student
    public static void addStudent() {

        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Course: ");
            String course = scanner.nextLine();

            System.out.print("Enter Marks: ");
            double marks = scanner.nextDouble();

            String sql = "INSERT INTO students (id, name, course, marks) VALUES (?, ?, ?, ?)";

            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, course);
            statement.setDouble(4, marks);

            statement.executeUpdate();

            System.out.println("Student added successfully!");

            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // View Students
    public static void viewStudents() {

        try {
            String sql = "SELECT * FROM students";

            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            System.out.println("\n----- Student List -----");

            while (result.next()) {

                System.out.println(
                    "ID: " + result.getInt("id") +
                    " | Name: " + result.getString("name") +
                    " | Course: " + result.getString("course") +
                    " | Marks: " + result.getDouble("marks")
                );
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
    }

    // Search Student
    public static void searchStudent() {

        try {
            System.out.print("Enter Student ID to search: ");
            int id = scanner.nextInt();

            String sql = "SELECT * FROM students WHERE id = ?";

            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                System.out.println("\nStudent Found:");
                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Course: " + result.getString("course"));
                System.out.println("Marks: " + result.getDouble("marks"));

            } else {
                System.out.println("Student not found.");
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
    }

    // Update Student
    public static void updateStudent() {

        try {
            System.out.print("Enter Student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter New Course: ");
            String course = scanner.nextLine();

            System.out.print("Enter New Marks: ");
            double marks = scanner.nextDouble();

            String sql = "UPDATE students SET name = ?, course = ?, marks = ? WHERE id = ?";

            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, course);
            statement.setDouble(3, marks);
            statement.setInt(4, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found.");
            }

            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    // Delete Student
    public static void deleteStudent() {

        try {
            System.out.print("Enter Student ID to delete: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM students WHERE id = ?";

            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found.");
            }

            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}