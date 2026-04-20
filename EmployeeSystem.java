import java.util.*;
import java.io.*;
class Employee {
    int id;
    String name;
    double basicSalary;
    double bonus;
    Employee(int id, String name, double basicSalary, double bonus) {
        this.id = id;
        this.name = name;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
    }
    double calculateSalary() {
        return basicSalary + bonus;
    }
    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Bonus: " + bonus);
        System.out.println("Total Salary: " + calculateSalary());
        System.out.println("------------------------");
    }
}
public class EmployeeSystem {

    static HashMap<Integer, Employee> employees = new HashMap<>();
    static void addEmployee(int id, String name, double salary, double bonus) {
        if (employees.containsKey(id)) {
            System.out.println("Employee already exists!");
            return;
        }
        employees.put(id, new Employee(id, name, salary, bonus));
        System.out.println("Employee added!");
    }
    static void searchEmployee(int id) {
        if (employees.containsKey(id)) {
            employees.get(id).display();
        } else {
            System.out.println("Employee not found!");
        }
    }
    static void updateEmployee(int id, String name, double salary, double bonus) {
        if (employees.containsKey(id)) {
            Employee e = employees.get(id);
            e.name = name;
            e.basicSalary = salary;
            e.bonus = bonus;
            System.out.println("Employee updated!");
        } else {
            System.out.println("Employee not found!");
        }
    }
    static void deleteEmployee(int id) {
        if (employees.remove(id) != null) {
            System.out.println("Employee deleted!");
        } else {
            System.out.println("Employee not found!");
        }
    }
    static void displayAll() {
        if (employees.isEmpty()) {
            System.out.println("No employees available!");
            return;
        }
        for (Employee e : employees.values()) {
            e.display();
        }
    }
    static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt"));
            for (Employee e : employees.values()) {
                bw.write(e.id + "," + e.name + "," + e.basicSalary + "," + e.bonus);
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
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Display All Employees");
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
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    System.out.print("Enter Bonus: ");
                    double bonus = sc.nextDouble();
                    addEmployee(id, name, salary, bonus);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    searchEmployee(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String n = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double s = sc.nextDouble();
                    System.out.print("Enter New Bonus: ");
                    double b = sc.nextDouble();
                    updateEmployee(uid, n, s, b);
                    break;
                case 4:
                    System.out.print("Enter ID: ");
                    deleteEmployee(sc.nextInt());
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
