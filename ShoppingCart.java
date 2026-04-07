import java.util.*;
import java.io.*;


class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotal() {
        return price * quantity;
    }
}


public class ShoppingCart {

    static ArrayList<Product> cart = new ArrayList<>();

    
    static void addProduct(String name, double price, int quantity) {
        cart.add(new Product(name, price, quantity));
        System.out.println("Product added!");
    }

   
    static void removeProduct(String name) {
        for (Product p : cart) {
            if (p.name.equalsIgnoreCase(name)) {
                cart.remove(p);
                System.out.println("Product removed!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    
    static void updateProduct(String name, int quantity) {
        for (Product p : cart) {
            if (p.name.equalsIgnoreCase(name)) {
                p.quantity = quantity;
                System.out.println("Quantity updated!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

   
    static double calculateTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.getTotal();
        }
        return total;
    }

    
    static void generateBill() {
        System.out.println("\n--- BILL ---");
        for (Product p : cart) {
            System.out.println(p.name + " | " + p.price + " x " + p.quantity + " = " + p.getTotal());
        }
        System.out.println("Total Price: " + calculateTotal());
    }

   
    static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("bill.txt"));

            for (Product p : cart) {
                bw.write(p.name + "," + p.price + "," + p.quantity);
                bw.newLine();
            }

            bw.write("Total:" + calculateTotal());
            bw.close();

            System.out.println("Purchase saved to file!");

        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Shopping Cart ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Quantity");
            System.out.println("4. Generate Bill");
            System.out.println("5. Save Bill");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    addProduct(name, price, qty);
                    break;

                case 2:
                    System.out.print("Enter product name: ");
                    removeProduct(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter product name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQty = sc.nextInt();
                    updateProduct(pname, newQty);
                    break;

                case 4:
                    generateBill();
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