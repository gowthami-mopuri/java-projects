import java.io.*;
import java.util.*;

public class LogAnalyzer {

    static ArrayList<String> logs = new ArrayList<>();
    static HashMap<String, Integer> countMap = new HashMap<>();

    // Read Log File
    static void readLogs(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                logs.add(line);

                // Count ERROR and WARNING
                if (line.contains("ERROR")) {
                    countMap.put("ERROR", countMap.getOrDefault("ERROR", 0) + 1);
                }
                if (line.contains("WARNING")) {
                    countMap.put("WARNING", countMap.getOrDefault("WARNING", 0) + 1);
                }
            }

            br.close();
            System.out.println("Logs loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error reading file!");
        }
    }

    // Display Summary Report
    static void displaySummary() {
        System.out.println("\n--- Summary Report ---");
        System.out.println("Total Logs: " + logs.size());
        System.out.println("Errors: " + countMap.getOrDefault("ERROR", 0));
        System.out.println("Warnings: " + countMap.getOrDefault("WARNING", 0));
    }

    // Filter by Keyword
    static void filterByKeyword(String keyword) {
        System.out.println("\n--- Filtered Logs ---");
        for (String log : logs) {
            if (log.contains(keyword)) {
                System.out.println(log);
            }
        }
    }

    // Search by Date
    static void searchByDate(String date) {
        System.out.println("\n--- Logs on " + date + " ---");
        for (String log : logs) {
            if (log.startsWith(date)) {
                System.out.println(log);
            }
        }
    }

    // Search by Type (ERROR / WARNING)
    static void searchByType(String type) {
        System.out.println("\n--- " + type + " Logs ---");
        for (String log : logs) {
            if (log.contains(type)) {
                System.out.println(log);
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Log Analyzer ---");
            System.out.println("1. Load Log File");
            System.out.println("2. Summary Report");
            System.out.println("3. Filter by Keyword");
            System.out.println("4. Search by Date");
            System.out.println("5. Search by Type");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String file = sc.nextLine();
                    readLogs(file);
                    break;

                case 2:
                    displaySummary();
                    break;

                case 3:
                    System.out.print("Enter keyword: ");
                    filterByKeyword(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    searchByDate(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Enter type (ERROR/WARNING): ");
                    searchByType(sc.nextLine());
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