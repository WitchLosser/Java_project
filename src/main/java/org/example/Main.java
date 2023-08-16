package org.example;


import org.example.DAOs.UserDao;
import org.example.entities.User;
import org.example.utils.HibernateUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao(HibernateUtil.getSessionFactory());

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createUser(userDao, scanner);
                    break;
                case 2:
                    readUser(userDao, scanner);
                    break;
                case 3:
                    updateUser(userDao, scanner);
                    break;
                case 4:
                    deleteUser(userDao, scanner);
                    break;
                case 0:
                    userDao.close();
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void createUser(UserDao userDao, Scanner scanner) {
        User newUser = new User();
        System.out.print("Enter Email: ");
        newUser.setEmail(scanner.nextLine());
        System.out.print("Enter First Name: ");
        newUser.setFirstName(scanner.nextLine());
        System.out.print("Enter Last Name: ");
        newUser.setLastName(scanner.nextLine());
        System.out.print("Enter Phone: ");
        newUser.setPhone(scanner.nextLine());
        System.out.print("Enter Password: ");
        newUser.setPassword(scanner.nextLine());

        userDao.saveUser(newUser);
        System.out.println("User created successfully!");
    }

    private static void readUser(UserDao userDao, Scanner scanner) {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        User user = userDao.getUserById(userId);

        if (user != null) {
            System.out.println("User Found:");
            System.out.println("ID: " + user.getId());
            System.out.println("Email: " + user.getEmail());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Phone: " + user.getPhone());
        } else {
            System.out.println("User not found!");
        }
    }

    private static void updateUser(UserDao userDao, Scanner scanner) {
        System.out.print("Enter User ID to Update: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        User user = userDao.getUserById(userId);

        if (user != null) {
            System.out.print("Enter New First Name: ");
            user.setFirstName(scanner.nextLine());
            System.out.print("Enter New Last Name: ");
            user.setLastName(scanner.nextLine());

            userDao.updateUser(user);
            System.out.println("User updated successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    private static void deleteUser(UserDao userDao, Scanner scanner) {
        System.out.print("Enter User ID to Delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        User user = userDao.getUserById(userId);

        if (user != null) {
            userDao.deleteUser(user);
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
        }
    }
