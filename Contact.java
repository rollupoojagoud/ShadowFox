import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phone;
    String email;

    Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("-------------------------");
    }
}

public class ContactManager {
    static ArrayList<Contact> contactList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n----- Contact Management System -----");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: addContact(); break;
                case 2: viewContacts(); break;
                case 3: updateContact(); break;
                case 4: deleteContact(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    static void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        contactList.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    static void viewContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        System.out.println("\n--- Contact List ---");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println("Contact #" + (i + 1));
            contactList.get(i).display();
        }
    }

    static void updateContact() {
        viewContacts();
        if (contactList.isEmpty()) return;

        System.out.print("Enter contact number to update: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < contactList.size()) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            contactList.set(index, new Contact(name, phone, email));
            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Invalid contact number.");
        }
    }

    static void deleteContact() {
        viewContacts();
        if (contactList.isEmpty()) return;

        System.out.print("Enter contact number to delete: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index >= 0 && index < contactList.size()) {
            contactList.remove(index);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Invalid contact number.");
        }
    }
}
