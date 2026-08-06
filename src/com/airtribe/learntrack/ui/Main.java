package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Trainer;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   Welcome to LearnTrack");
        System.out.println("   Student & Course Management System");
        System.out.println("==============================================");

        demonstratePolymorphism();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntInput();

            switch (choice) {
                case 1:
                    handleStudentMenu();
                    break;
                case 2:
                    handleCourseMenu();
                    break;
                case 3:
                    handleEnrollmentMenu();
                    break;
                case 4:
                    running = false;
                    System.out.println("\nThank you for using LearnTrack. Goodbye!");
                    break;
                default:
                    System.out.println("\n[!] Invalid option. Please choose 1-4.");
            }
        }
        scanner.close();
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n--- Polymorphism Demo ---");

        Person student = new Student(0, "Jane", "Doe", "jane@example.com", "Batch-A");
        Person trainer = new Trainer(0, "John", "Smith", "john@example.com", "Java");

        // Same method call, different behavior based on actual object type
        System.out.println("Student display: " + student.getDisplayName());
        System.out.println("Trainer display: " + trainer.getDisplayName());
        System.out.println("-------------------------\n");
    }

    // Main Menu
    private static void printMainMenu() {
        System.out.println("\n============ MAIN MENU ============");
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Enrollment Management");
        System.out.println("  4. Exit");
        System.out.println("===================================");
        System.out.print("Choose an option: ");
    }

    // Student Menu
    private static void handleStudentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            printStudentMenu();
            int choice = readIntInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    deactivateStudent();
                    break;
                case 5:
                    inMenu = false;
                    break;
                default:
                    System.out.println("\n[!] Invalid option. Please choose 1-5.");
            }
        }
    }

    private static void printStudentMenu() {
        System.out.println("\n-------- STUDENT MANAGEMENT --------");
        System.out.println("  1. Add New Student");
        System.out.println("  2. View All Students");
        System.out.println("  3. Search Student by ID");
        System.out.println("  4. Deactivate a Student");
        System.out.println("  5. Back to Main Menu");
        System.out.println("------------------------------------");
        System.out.print("Choose an option: ");
    }

    private static void addStudent() {
        try {
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine().trim();
            if (!InputValidator.isNonEmptyString(firstName)) {
                throw new InvalidInputException("First name cannot be empty.");
            }

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine().trim();
            if (!InputValidator.isNonEmptyString(lastName)) {
                throw new InvalidInputException("Last name cannot be empty.");
            }

            System.out.print("Enter Email (or press Enter to skip): ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter Batch: ");
            String batch = scanner.nextLine().trim();
            if (!InputValidator.isNonEmptyString(batch)) {
                throw new InvalidInputException("Batch cannot be empty.");
            }

            Student student = studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("\n[✓] Student added successfully!");
            System.out.println("    " + student);

        } catch (InvalidInputException e) {
            System.out.println("\n[!] Invalid input: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        ArrayList<Student> students = studentService.listAllStudents();
        if (students.isEmpty()) {
            System.out.println("\n[i] No students found. Add a student first.");
            return;
        }
        System.out.println("\n-------- ALL STUDENTS --------");
        for (Student student : students) {
            System.out.println("  " + student);
        }
        System.out.println("------------------------------");
    }

    private static void searchStudentById() {
        System.out.print("Enter Student ID: ");
        int id = readIntInput();
        try {
            Student student = studentService.findStudentById(id);
            System.out.println("\n[✓] Student found:");
            System.out.println("    ID:         " + student.getId());
            System.out.println("    Name:       " + student.getDisplayName());
            System.out.println("    Email:      " + student.getEmail());
            System.out.println("    Batch:      " + student.getBatch());
            System.out.println("    Active:     " + student.isActive());
        } catch (EntityNotFoundException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    private static void deactivateStudent() {
        System.out.print("Enter Student ID to deactivate: ");
        int id = readIntInput();
        try {
            studentService.deactivateStudent(id);
            System.out.println("\n[✓] Student with ID " + id + " has been deactivated.");
        } catch (EntityNotFoundException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    // Course Menu
    private static void handleCourseMenu() {
        boolean inMenu = true;
        while (inMenu) {
            printCourseMenu();
            int choice = readIntInput();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    toggleCourseStatus();
                    break;
                case 4:
                    inMenu = false;
                    break;
                default:
                    System.out.println("\n[!] Invalid option. Please choose 1-4.");
            }
        }
    }

    private static void printCourseMenu() {
        System.out.println("\n-------- COURSE MANAGEMENT --------");
        System.out.println("  1. Add New Course");
        System.out.println("  2. View All Courses");
        System.out.println("  3. Activate/Deactivate a Course");
        System.out.println("  4. Back to Main Menu");
        System.out.println("-----------------------------------");
        System.out.print("Choose an option: ");
    }

    private static void addCourse() {
        try {
            System.out.print("Enter Course Name: ");
            String courseName = scanner.nextLine().trim();
            if (!InputValidator.isNonEmptyString(courseName)) {
                throw new InvalidInputException("Course name cannot be empty.");
            }

            System.out.print("Enter Description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter Duration (in weeks): ");
            int duration = readIntInput();
            if (!InputValidator.isPositiveInt(duration)) {
                throw new InvalidInputException("Duration must be a positive number.");
            }

            Course course = courseService.addCourse(courseName, description, duration);
            System.out.println("\n[✓] Course added successfully!");
            System.out.println("    " + course);

        } catch (InvalidInputException e) {
            System.out.println("\n[!] Invalid input: " + e.getMessage());
        }
    }

    private static void viewAllCourses() {
        ArrayList<Course> courses = courseService.listAllCourses();
        if (courses.isEmpty()) {
            System.out.println("\n[i] No courses found. Add a course first.");
            return;
        }
        System.out.println("\n-------- ALL COURSES --------");
        for (Course course : courses) {
            System.out.println("  " + course);
        }
        System.out.println("-----------------------------");
    }

    private static void toggleCourseStatus() {
        System.out.print("Enter Course ID: ");
        int id = readIntInput();
        try {
            Course course = courseService.findCourseById(id);
            System.out.println("Current status: " + (course.isActive() ? "ACTIVE" : "INACTIVE"));
            System.out.print("Do you want to toggle? (y/n): ");
            String confirm = scanner.nextLine().trim();

            if (confirm.equalsIgnoreCase("y")) {
                if (course.isActive()) {
                    courseService.deactivateCourse(id);
                    System.out.println("\n[✓] Course deactivated.");
                } else {
                    courseService.activateCourse(id);
                    System.out.println("\n[✓] Course activated.");
                }
            } else {
                System.out.println("\n[i] No changes made.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    // Enrollment Menu
    private static void handleEnrollmentMenu() {
        boolean inMenu = true;
        while (inMenu) {
            printEnrollmentMenu();
            int choice = readIntInput();

            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    viewEnrollmentsForStudent();
                    break;
                case 3:
                    updateEnrollmentStatus();
                    break;
                case 4:
                    inMenu = false;
                    break;
                default:
                    System.out.println("\n[!] Invalid option. Please choose 1-4.");
            }
        }
    }

    private static void printEnrollmentMenu() {
        System.out.println("\n-------- ENROLLMENT MANAGEMENT --------");
        System.out.println("  1. Enroll Student in a Course");
        System.out.println("  2. View Enrollments for a Student");
        System.out.println("  3. Mark Enrollment Completed/Cancelled");
        System.out.println("  4. Back to Main Menu");
        System.out.println("---------------------------------------");
        System.out.print("Choose an option: ");
    }

    private static void enrollStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = readIntInput();
        System.out.print("Enter Course ID: ");
        int courseId = readIntInput();

        try {
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            System.out.println("\n[✓] Enrollment created successfully!");
            System.out.println("    " + enrollment);
        } catch (EntityNotFoundException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    private static void viewEnrollmentsForStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = readIntInput();

        ArrayList<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("\n[i] No enrollments found for Student ID " + studentId + ".");
            return;
        }

        System.out.println("\n-------- ENROLLMENTS FOR STUDENT " + studentId + " --------");
        for (Enrollment enrollment : enrollments) {
            // Look up the course name for a better display
            String courseName = "Unknown";
            try {
                courseName = courseService.findCourseById(enrollment.getCourseId()).getCourseName();
            } catch (EntityNotFoundException ignored) {
                // Course might have been removed; show "Unknown"
            }
            System.out.println("  Enrollment ID: " + enrollment.getId()
                    + " | Course: " + courseName
                    + " | Date: " + enrollment.getEnrollmentDate()
                    + " | Status: " + enrollment.getStatus());
        }
        System.out.println("------------------------------------------------");
    }

    private static void updateEnrollmentStatus() {
        System.out.print("Enter Enrollment ID: ");
        int enrollmentId = readIntInput();

        System.out.println("Select new status:");
        System.out.println("  1. COMPLETED");
        System.out.println("  2. CANCELLED");
        System.out.print("Choose: ");
        int statusChoice = readIntInput();

        EnrollmentStatus newStatus;
        switch (statusChoice) {
            case 1:
                newStatus = EnrollmentStatus.COMPLETED;
                break;
            case 2:
                newStatus = EnrollmentStatus.CANCELLED;
                break;
            default:
                System.out.println("\n[!] Invalid status choice.");
                return;
        }

        try {
            enrollmentService.updateEnrollmentStatus(enrollmentId, newStatus);
            System.out.println("\n[✓] Enrollment status updated to " + newStatus + ".");
        } catch (EntityNotFoundException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    // Input Helpers
    private static int readIntInput() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("\n[!] Please enter a valid number.");
            return -1;
        }
    }
}
