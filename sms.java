package schoolmanagementsystems;

import java.util.Scanner;

public class SchoolManagementSystems {

    // Admin login
    static final String ADMIN_USER = "admin";
    static final String ADMIN_PASS = "admin123";

    // Student arrays
    static int[] id = new int[200];
    static String[] name = new String[200];
    static int[] age = new int[100];
    static String[] gender = new String[200];
    static String[] studentClass = new String[200];
    static String[] teacher = new String[200];

    // Marks
    static int[] math = new int[100];
    static int[] english = new int[100];
    static int[] science = new int[100];

    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!login(sc)) {
            System.out.println("Access denied!");
            return;
        }

        int choice;
        do {
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Remove Student");
            System.out.println("6. View Report Card");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchStudent(sc);
                case 4 -> updateStudent(sc);
                case 5 -> removeStudent(sc);
                case 6 -> reportCard(sc);
                case 7 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 7);

        sc.close();
    }

    // 🔐 Login
    static boolean login(Scanner sc) {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();
        return u.equals(ADMIN_USER) && p.equals(ADMIN_PASS);
    }

    // ➕ Add student
    static void addStudent(Scanner sc) {
        if (count >= 100) {
            System.out.println("Student limit reached!");
            return;
        }

        id[count] = count + 1;

        System.out.print("Name: ");
        name[count] = sc.nextLine();

        System.out.print("Age: ");
        age[count] = sc.nextInt();
        sc.nextLine();

        System.out.print("Gender: ");
        gender[count] = sc.nextLine();

        System.out.print("Class: ");
        studentClass[count] = sc.nextLine();

        System.out.print("Class Teacher: ");
        teacher[count] = sc.nextLine();

        System.out.println("Enter Marks (0–100)");
        System.out.print("Math: ");
        math[count] = sc.nextInt();
        System.out.print("English: ");
        english[count] = sc.nextInt();
        System.out.print("Science: ");
        science[count] = sc.nextInt();
        sc.nextLine();

        count++;
        System.out.println("Student added successfully!");
    }

    // 📋 View students
    static void viewStudents() {
        if (count == 0) {
            System.out.println("No students found!");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("\nID: " + id[i] +
                    "\nName: " + name[i] +
                    "\nClass: " + studentClass[i] +
                    "\nTeacher: " + teacher[i]);
        }
    }

    // 🔍 Search
    static void searchStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int searchId = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (id[i] == searchId) {
                displayFullStudent(i);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // ✏️ Update
    static void updateStudent(Scanner sc) {
        System.out.print("Enter Student ID to update: ");
        int sid = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (id[i] == sid) {
                System.out.print("New Name: ");
                name[i] = sc.nextLine();
                System.out.print("New Class: ");
                studentClass[i] = sc.nextLine();
                System.out.print("New Teacher: ");
                teacher[i] = sc.nextLine();
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // ❌ Remove
    static void removeStudent(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int sid = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (id[i] == sid) {
                for (int j = i; j < count - 1; j++) {
                    id[j] = id[j + 1];
                    name[j] = name[j + 1];
                    age[j] = age[j + 1];
                    gender[j] = gender[j + 1];
                    studentClass[j] = studentClass[j + 1];
                    teacher[j] = teacher[j + 1];
                    math[j] = math[j + 1];
                    english[j] = english[j + 1];
                    science[j] = science[j + 1];
                }
                count--;
                System.out.println("Student removed!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // 📊 Report Card
    static void reportCard(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int sid = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (id[i] == sid) {
                displayFullStudent(i);
                double avg = (math[i] + english[i] + science[i]) / 3.0;
                System.out.println("Average: " + avg);
                System.out.println("Grade: " + grade(avg));
                return;
            }
        }
        System.out.println("Student not found!");
    }

    static void displayFullStudent(int i) {
        System.out.println("\n--- STUDENT DETAILS ---");
        System.out.println("ID: " + id[i]);
        System.out.println("Name: " + name[i]);
        System.out.println("Age: " + age[i]);
        System.out.println("Gender: " + gender[i]);
        System.out.println("Class: " + studentClass[i]);
        System.out.println("Teacher: " + teacher[i]);
        System.out.println("Math: " + math[i]);
        System.out.println("English: " + english[i]);
        System.out.println("Science: " + science[i]);
    }

    static String grade(double avg) {
        if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }
}
