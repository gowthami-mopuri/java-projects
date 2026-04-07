import java.io.*;
import java.util.*;

public class TextSearchEngine {

    static ArrayList<String> lines = new ArrayList<>();
    static HashMap<String, Integer> wordCount = new HashMap<>();

    
    static void loadFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);

                String[] words = line.toLowerCase().split(" ");
                for (String word : words) {
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }

            br.close();
            System.out.println("File loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error reading file!");
        }
    }

    
    static void searchKeyword(String keyword) {
        System.out.println("\n--- Search Results ---");
        keyword = keyword.toLowerCase();

        for (String line : lines) {
            if (line.toLowerCase().contains(keyword)) {

                
                String highlighted = line.replaceAll("(?i)" + keyword, "[" + keyword.toUpperCase() + "]");
                System.out.println(highlighted);
            }
        }
    }

   
    static void wordFrequency(String word) {
        word = word.toLowerCase();
        int count = wordCount.getOrDefault(word, 0);
        System.out.println("Frequency of '" + word + "' = " + count);
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Text Search Engine ---");
            System.out.println("1. Load File");
            System.out.println("2. Search Keyword");
            System.out.println("3. Word Frequency");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    loadFile(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Enter keyword: ");
                    searchKeyword(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter word: ");
                    wordFrequency(sc.nextLine());
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}