import java.util.*;
import java.io.*;
class Candidate {
    String name;
    int votes;
    Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }
    void addVote() {
        votes++;
    }
}
public class votingsystem {
    static HashMap<String, Candidate> candidates = new HashMap<>();
    static HashSet<String> voters = new HashSet<>(); 
    static void addCandidate(String name) {
        if (candidates.containsKey(name)) {
            System.out.println("Candidate already exists!");
        } else {
            candidates.put(name, new Candidate(name));
            System.out.println("Candidate added!");
        }
    }
    static void castVote(String voterId, String candidateName) {
        if (voters.contains(voterId)) {
            System.out.println("You have already voted!");
            return;
        }
        if (!candidates.containsKey(candidateName)) {
            System.out.println("Candidate not found!");
            return;
        }
        candidates.get(candidateName).addVote();
        voters.add(voterId);
        System.out.println("Vote cast successfully!");
    }
    static void displayResults() {
        System.out.println("\n--- Voting Results ---");
        for (Candidate c : candidates.values()) {
            System.out.println(c.name + " : " + c.votes + " votes");
        }
    }
    static void announceWinner() {
        Candidate winner = null;
        for (Candidate c : candidates.values()) {
            if (winner == null || c.votes > winner.votes) {
                winner = c;
            }
        }
        if (winner != null) {
            System.out.println("\nWinner is: " + winner.name + " with " + winner.votes + " votes");
        } else {
            System.out.println("No votes yet.");
        }
    } 
    static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("results.txt"));

            for (Candidate c : candidates.values()) {
                bw.write(c.name + "," + c.votes);
                bw.newLine();
            }
            bw.close();
            System.out.println("Results saved to file!");

        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Voting System ---");
            System.out.println("1. Add Candidate");
            System.out.println("2. Cast Vote");
            System.out.println("3. Display Results");
            System.out.println("4. Announce Winner");
            System.out.println("5. Save Results");
            System.out.println("6. Exit");
            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("Invalid input!");
                sc.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter candidate name: ");
                    String cname = sc.nextLine();
                    addCandidate(cname);
                    break;
                case 2:
                    System.out.print("Enter voter ID: ");
                    String voterId = sc.nextLine();
                    System.out.print("Enter candidate name: ");
                    String voteTo = sc.nextLine();
                    castVote(voterId, voteTo);
                    break;
                case 3:
                    displayResults();
                    break;
                case 4:
                    announceWinner();
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
