import java.util.*;
import java.io.*;


class Patient {
    int id;
    String name;
    int age;
    String disease;

    Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Disease: " + disease);
        System.out.println("----------------------");
    }
}


public class PatientSystem {

    static ArrayList<Patient> patients = new ArrayList<>();

   
    static void addPatient(int id, String name, int age, String disease) {
        patients.add(new Patient(id, name, age, disease));
        System.out.println("Patient added!");
    }

    static void searchPatient(int id) {
        for (Patient p : patients) {
            if (p.id == id) {
                p.display();
                return;
            }
        }
        System.out.println("Patient not found!");
    }

    
    static void updatePatient(int id, String disease) {
        for (Patient p : patients) {
            if (p.id == id) {
                p.disease = disease;
                System.out.println("Record updated!");
                return;
            }
        }
        System.out.println("Patient not found!");
    }

    
    static void deletePatient(int id) {
        for (Patient p : patients) {
            if (p.id == id) {
                patients.remove(p);
                System.out.println("Patient deleted!");
                return;
            }
        }
        System.out.println("Patient not found!");
    }

    
    static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("patients.txt"));

            for (Patient p : patients) {
                bw.write(p.id + "," + p.name + "," + p.age + "," + p.disease);
                bw.newLine();
            }

            bw.close();
            System.out.println("Data saved!");

        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Patient Record System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Record");
            System.out.println("4. Delete Patient");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");

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
                    System.out.print("Enter Disease: ");
                    String disease = sc.nextLine();

                    addPatient(id, name, age, disease);
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    searchPatient(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Disease: ");
                    updatePatient(uid, sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    deletePatient(sc.nextInt());
                    break;

                case 5:
                    saveToFile();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}