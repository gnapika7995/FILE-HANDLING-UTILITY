import java.io.*;
import java.util.Scanner;

public class FileHandlerUtility {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String fileName = "myfile.txt";  // Default file
        int choice;

        do {
            System.out.println("\n=== File Handling Utility ===");
            System.out.println("1. Create File");
            System.out.println("2. Write to File");
            System.out.println("3. Read from File");
            System.out.println("4. Append to File");
            System.out.println("5. Replace Content in File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> createFile(fileName);
                case 2 -> writeToFile(fileName);
                case 3 -> readFile(fileName);
                case 4 -> appendToFile(fileName);
                case 5 -> replaceContent(fileName);
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);
    }

    public static void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void writeToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            System.out.println("Enter text to write to the file:");
            String content = scanner.nextLine();
            writer.write(content);
            System.out.println("Successfully written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing.");
        }
    }

    public static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("\n--- File Content ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("---------------------");
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }
    }

    public static void appendToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            System.out.println("Enter text to append to the file:");
            String content = scanner.nextLine();
            writer.write("\n" + content);
            System.out.println("Successfully appended to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while appending.");
        }
    }

    public static void replaceContent(String fileName) {
        try {
            System.out.print("Enter text to find: ");
            String oldText = scanner.nextLine();
            System.out.print("Enter replacement text: ");
            String newText = scanner.nextLine();

            // Read existing content
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line.replaceAll(oldText, newText)).append("\n");
            }
            reader.close();

            // Write modified content back
            FileWriter writer = new FileWriter(fileName);
            writer.write(content.toString());
            writer.close();

            System.out.println("Content replaced successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while replacing content.");
        }
    }
}
