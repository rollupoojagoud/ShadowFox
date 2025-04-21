import java.sql.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:library.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initDatabase() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String createUsers = "CREATE TABLE IF NOT EXISTS users (" +
                                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                 "username TEXT UNIQUE NOT NULL," +
                                 "password TEXT NOT NULL);";

            String createBooks = "CREATE TABLE IF NOT EXISTS books (" +
                                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                 "title TEXT NOT NULL," +
                                 "author TEXT NOT NULL," +
                                 "available INTEGER DEFAULT 1);";

            stmt.execute(createUsers);
            stmt.execute(createBooks);
        } catch (SQLException e) {
            System.out.println("DB Initialization error: " + e.getMessage());
        }
    }
}


import java.sql.*;

public class User {

    public static boolean register(String username, String password) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean login(String username, String password) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // returns true if match found
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }
}


import java.sql.*;

public class Book {

    public static void addBook(String title, String author) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO books (title, author) VALUES (?, ?)")) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println("Add book error: " + e.getMessage());
        }
    }

    public static void viewBooks() {
        try (Connection conn = DBHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            System.out.println("\n--- Book List ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Title: %s | Author: %s | Available: %s\n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("available") == 1 ? "Yes" : "No");
            }
        } catch (SQLException e) {
            System.out.println("View books error: " + e.getMessage());
        }
    }

    public static void borrowBook(int bookId) {
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE books SET available = 0 WHERE id = ? AND available = 1")) {
            pstmt.setInt(1, bookId);
            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Book not available or doesn't exist.");
            }
        } catch (SQLException e) {
            System.out.println("Borrow book error: " + e.getMessage());
        }
    }
}


import java.util.Scanner;

public class LibraryMain {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DBHelper.initDatabase();
        boolean loggedIn = false;

        System.out.println("Welcome to Library Management System");

        while (!loggedIn) {
            System.out.println("\n1. Register\n2. Login\nChoose option:");
            int choice = sc.nextInt();
            sc.nextLine();

            System.out.print("Username: ");
            String user = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            if (choice == 1) {
                loggedIn = User.register(user, pass);
            } else if (choice == 2) {
                loggedIn = User.login(user, pass);
                if (!loggedIn) System.out.println("Invalid credentials.");
            }
        }

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Exit");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    Book.addBook(title, author);
                    break;
                case 2:
                    Book.viewBooks();
                    break;
                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    int id = sc.nextInt();
                    Book.borrowBook(id);
                    break;
                case 4:
                    System.out.println("Thank you for using the system.");
                    System.exit(0);
            }
        }
    }
}
