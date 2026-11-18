import java.util.*;
import java.io.*;

interface StudentOperations {
    void displayDetails();
    String calculateGrade();
}

// base class
abstract class Student implements StudentOperations {
    protected int id;
    protected String name;
    protected int age;
    protected String course;
    protected double marks;

    public Student(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    public int getId() { return id; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public void displayDetails() {
        System.out.println("\n------------------------------");
        System.out.println("Student ID : " + id);
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
        System.out.println("Course : " + course);
        if (marks == -1) {
            System.out.println("Marks : Not assigned yet");
            System.out.println("Grade : Not available");
        } else {
            System.out.println("Marks : " + marks);
            System.out.println("Grade : " + calculateGrade());
        }
        System.out.println("------------------------------");
    }

    public abstract String calculateGrade();
}

// regular student
class RegularStudent extends Student {
    public RegularStudent(int id, String name, int age, String course, double marks) {
        super(id, name, age, course, marks);
    }

    public String calculateGrade() {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else if (marks >= 0) return "F";
        else return "Not Assigned";
    }
}

// file ka kaam yaha hoga
class FileHandler {
    static String FILE_NAME = "students.txt";

    public static void saveStudents(ArrayList<Student> students) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            PrintWriter pw = new PrintWriter(fw);
            for (Student s : students) {
                // comma se alag karke save kar rahe hai
                pw.println(s.id + "," + s.name + "," + s.age + "," + s.course + "," + s.marks);
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    public static void loadStudents(ArrayList<Student> students) {
        try {
            File f = new File(FILE_NAME);
            if (!f.exists()) return;
            Scanner fileSc = new Scanner(f);
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                if (line.trim().equals("")) continue;
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String course = parts[3];
                double marks = Double.parseDouble(parts[4]);
                students.add(new RegularStudent(id, name, age, course, marks));
            }
            fileSc.close();
        } catch (Exception e) {
            System.out.println("Error loading file, starting fresh");
        }
    }
}

class StudentManager {
    ArrayList<Student> students = new ArrayList<>();

    public StudentManager() {
        FileHandler.loadStudents(students);
    }

    public void enrolStudent(int id, String name, int age, String course) {
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("ID already exists!");
                return;
            }
        }
        students.add(new RegularStudent(id, name, age, course, -1));
        FileHandler.saveStudents(students);
        System.out.println("Student enrolled successfully!");
    }

    public void assignGrade(int id, double marks) {
        Student found = searchStudent(id);
        if (found == null) {
            System.out.println("Student not found!");
            return;
        }
        found.setMarks(marks);
        FileHandler.saveStudents(students);
        System.out.println("Marks assigned! Grade is: " + found.calculateGrade());
    }

    public Student searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.size() == 0) {
            System.out.println("No student records found.");
            return;
        }
        for (Student s : students) {
            s.displayDetails();
        }
    }

    public void generateReport() {
        if (students.size() == 0) {
            System.out.println("No records available.");
            return;
        }
        int graded = 0, passed = 0;
        double total = 0;
        for (Student s : students) {
            if (s.getMarks() != -1) {
                graded++;
                total = total + s.getMarks();
                if (s.getMarks() >= 50) passed++;
            }
        }
        System.out.println("\n======= STUDENT REPORT =======");
        System.out.println("Total Students: " + students.size());
        System.out.println("Students Graded: " + graded);
        System.out.println("Students Passed: " + passed);
        System.out.println("Students Failed: " + (graded - passed));
        if (graded > 0) {
            System.out.println("Class Average: " + (total / graded));
        } else {
            System.out.println("Class Average: Not available");
        }
        System.out.println("==============================");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        int choice = -1;

        System.out.println("==================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");

        while (choice != 0) {
            System.out.println("\n------------ MAIN MENU ------------");
            System.out.println("1. Enrol New Student");
            System.out.println("2. Assign Marks and Grade");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Generate Student Report");
            System.out.println("0. Exit");
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice: ");

            // agar user number ki jagah kuch aur daal de to
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Student ID: ");
                int id = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Student Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Age: ");
                int age = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Course: ");
                String course = sc.nextLine();
                manager.enrolStudent(id, name, age, course);
            }
            else if (choice == 2) {
                System.out.print("Enter Student ID: ");
                int sid = sc.nextInt();
                System.out.print("Enter Marks (0-100): ");
                double marks = sc.nextDouble();
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                } else {
                    manager.assignGrade(sid, marks);
                }
            }
            else if (choice == 3) {
                System.out.print("Enter Student ID to search: ");
                int sid = sc.nextInt();
                Student st = manager.searchStudent(sid);
                if (st != null) {
                    System.out.println("Student found!");
                    st.displayDetails();
                } else {
                    System.out.println("Student not found!");
                }
            }
            else if (choice == 4) {
                manager.displayAllStudents();
            }
            else if (choice == 5) {
                manager.generateReport();
            }
            else if (choice == 0) {
                System.out.println("Thank you for using the system!");
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}
