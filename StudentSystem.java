import java.util.*;
import java.io.*;
class Student {
    int id;
    String name;
    int age;
    String course;
    Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }
    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
        System.out.println("----------------------");
    }
}
public class StudentSystem {
    static ArrayList<Student> students = new ArrayList<>(); 
    static void addStudent(int id, String name, int age, String course) {
        students.add(new Student(id, name, age, course));
        System.out.println("Student added!");
    } 
    static void searchStudent(int id) {
        for (Student s : students) {
            if (s.id == id) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found!");
    }
    static void updateStudent(int id, String name, int age, String course) {
        for (Student s : students) {
            if (s.id == id) {
                s.name = name;
                s.age = age;
                s.course = course;
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    } 
    static void deleteStudent(int id) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.id == id) {
                it.remove();
                System.out.println("Student deleted!");
                return;
            }
        }
        System.out.println("Student not found!");
    }
    static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No students available!");
            return;
        }
        for (Student s : students) {
            s.display();
        }
    }
    static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"));

            for (Student s : students) {
                bw.write(s.id + "," + s.name + "," + s.age + "," + s.course);
                bw.newLine();
            }
            bw.close();
            System.out.println("Data saved successfully!");

        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    } 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Student Record System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    addStudent(id, name, age, course);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    searchStudent(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String n = sc.nextLine();
                    System.out.print("Enter New Age: ");
                    int a = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Course: ");
                    String c = sc.nextLine();
                    updateStudent(uid, n, a, c);
                    break;
                case 4:
                    System.out.print("Enter ID: ");
                    deleteStudent(sc.nextInt());
                    break;
                case 5:
                    displayAll();
                    break;
                case 6:
                    saveToFile();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
