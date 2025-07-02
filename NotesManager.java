import java.io.*;
import java.util.Scanner;

public class NotesManager {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Notes Manager =====");
            System.out.println("1. Create New Note");
            System.out.println("2. View Note");
            System.out.println("3. Append to Note");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createNote();
                    break;
                case 2:
                    viewNote();
                    break;
                case 3:
                    appendToNote();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // 1. Create new note using FileWriter
    public static void createNote() {
        System.out.print("Enter note title (filename): ");
        String filename = scanner.nextLine();

        try (FileWriter writer = new FileWriter(filename + ".txt")) {
            System.out.println("Enter note content (type 'EOF' to finish):");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("EOF")) break;
                writer.write(line + System.lineSeparator());
            }
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error while writing note: " + e.getMessage());
        }
    }

    // 2. View note using FileReader + BufferedReader
    public static void viewNote() {
        System.out.print("Enter note title (filename): ");
        String filename = scanner.nextLine();

        File file = new File(filename + ".txt");
        if (!file.exists()) {
            System.out.println("Note not found.");
            return;
        }

        System.out.println("\n--- Note Content ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error while reading note: " + e.getMessage());
        }
    }

    // 3. Append to note using FileWriter in append mode
    public static void appendToNote() {
        System.out.print("Enter note title (filename): ");
        String filename = scanner.nextLine();

        File file = new File(filename + ".txt");
        if (!file.exists()) {
            System.out.println("Note not found.");
            return;
        }

        try (FileWriter writer = new FileWriter(file, true)) {
            System.out.println("Enter content to append (type 'EOF' to finish):");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("EOF")) break;
                writer.write(line + System.lineSeparator());
            }
            System.out.println("Note updated successfully!");
        } catch (IOException e) {
            System.out.println("Error while appending note: " + e.getMessage());
        }
    }
}

