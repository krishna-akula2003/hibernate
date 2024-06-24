package org.cred;
//import org.cred.Student;
//import org.StudentName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class App {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        // Configure Hibernate
        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class);
        sessionFactory = con.buildSessionFactory();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose operation:");
            System.out.println("1. Create Student");
            System.out.println("2. Read Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createStudent(scanner);
                    break;
                case 2:
                    readStudent(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    sessionFactory.close();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void createStudent(Scanner scanner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student();
        System.out.println("Enter Student ID:");
        student.setId(scanner.nextInt());
        scanner.nextLine(); // consume newline

        StudentName name = new StudentName();
        System.out.println("Enter First Name:");
        name.setFirstName(scanner.nextLine());
        System.out.println("Enter Middle Name:");
        name.setMiddleName(scanner.nextLine());
        System.out.println("Enter Last Name:");
        name.setLastName(scanner.nextLine());
        student.setName(name);

        System.out.println("Enter Branch:");
        student.setBranch(scanner.nextLine());
        System.out.println("Enter Mobile Number:");
        student.setMobileNumber(scanner.nextLine());

        session.persist(student);
        transaction.commit();
        session.close();

        System.out.println("Student created successfully");
    }

    private static void readStudent(Scanner scanner) {
        Session session = sessionFactory.openSession();

        System.out.println("Enter Student ID:");
        int id = scanner.nextInt();

        Student student = session.get(Student.class, id);
        if (student != null) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("First Name: " + student.getName().getFirstName());
            System.out.println("Middle Name: " + student.getName().getMiddleName());
            System.out.println("Last Name: " + student.getName().getLastName());
            System.out.println("Branch: " + student.getBranch());
            System.out.println("Mobile Number: " + student.getMobileNumber());
        } else {
            System.out.println("Student not found");
        }

        session.close();
    }

    private static void updateStudent(Scanner scanner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Enter Student ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Student student = session.get(Student.class, id);
        if (student != null) {
            System.out.println("Enter new First Name (current: " + student.getName().getFirstName() + "):");
            student.getName().setFirstName(scanner.nextLine());
            System.out.println("Enter new Middle Name (current: " + student.getName().getMiddleName() + "):");
            student.getName().setMiddleName(scanner.nextLine());
            System.out.println("Enter new Last Name (current: " + student.getName().getLastName() + "):");
            student.getName().setLastName(scanner.nextLine());
            System.out.println("Enter new Branch (current: " + student.getBranch() + "):");
            student.setBranch(scanner.nextLine());
            System.out.println("Enter new Mobile Number (current: " + student.getMobileNumber() + "):");
            student.setMobileNumber(scanner.nextLine());

            session.update(student);
            transaction.commit();

            System.out.println("Student updated successfully");
        } else {
            System.out.println("Student not found");
        }

        session.close();
    }

    private static void deleteStudent(Scanner scanner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Enter Student ID:");
        int id = scanner.nextInt();

        Student student = session.get(Student.class, id);
        if (student != null) {
            session.delete(student);
            transaction.commit();

            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }

        session.close();
    }
}
