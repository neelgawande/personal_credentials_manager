import people.Admin;
import people.User;
import utility.Authentication;
import java.sql.SQLException;
import java.util.*;

//TODO: PUT EVERYTHING IN while(true) {...}

public class IOTerminal {
    public static void start() throws SQLException {
        System.out.println("=========================================================================================");
        System.out.println("WELCOME TO PERSONAL CREDENTIALS MANAGER\n");
        System.out.println("Please select your login type:");
        System.out.println("(1) people.Admin utility.Login");
        System.out.println("(2) people.User utility.Login");
        System.out.println("(3) Exit");
        System.out.println("=========================================================================================");
        int choice = 0;
        try {
            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println(ime);
        }
        switch (choice) {
            case 1: {
                adminLogin();
                break;
            }
            case 2: {
                userLogin();
            }
            case 3: {
                System.exit(0);
            }
            default:
                System.out.println("Please enter a valid option!");
        }
    }
    public static void adminLogin() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scan.next();
        // verify admin
        if(!Authentication.verifyAdmin(username)){
            System.out.println("Incorrect username. people.Admin not found.");
            return;
        }
        System.out.println("Enter your password: ");
        String password = scan.next();
        if(!Authentication.verifyAdminPassword(username, password)){
            System.out.println("Incorrect username. people.Admin not found.");
            return;
        }
        // ACTIVATE THE ADMIN SESSION for an existing admin
        Admin admin = new Admin(username, password);
        adminSession(admin);
    }
    public static void userLogin() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your id: ");
        int id = 0;
        try {
            id = scan.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println(ime);
        }
        // verify user
        if (!Authentication.verifyUser(id)) {
            System.out.println("Incorrect username. people.User not found.");
            return;
        }
        System.out.println("Enter your password: ");
        String password = scan.next();
        if (!Authentication.verifyUserPassword(id, password)) {
            System.out.println("Incorrect password.");
            return;
        }
        // ACTIVATE THE USER SESSION for an existing user
        User user = new User(id, password);
        userSession(user);
    }

    public static void adminSession(Admin admin) throws SQLException {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("================= WELCOME TO PERSONAL CREDENTIALS MANAGER - ADMIN SESSION =================\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("\n=========================================================================================\n");
            System.out.println("What would you like to do?:");
            System.out.println("(1) Add a new user");
            System.out.println("(2) Add a new admin");
            System.out.println("(3) View all users");
            System.out.println("(4) View all admins");
            System.out.println("(5) Exit");
            System.out.println("\n=========================================================================================\n");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: // Add a new user
                {
                    while(true) {
                        System.out.println("Set a userId: ");
                        try {
                            int userId = scan.nextInt();
                            System.out.println("Set a userName: ");
                            String userName = scan.next();
                            System.out.println("Set a password: ");
                            String passwordIter1 = scan.next();
                            System.out.println("confirm password: ");
                            String passwordIter2 = scan.next();
                            if(!passwordIter1.equals(passwordIter2)) {
                                System.out.println("Passwords do not match. Please try again.");
                            }
                            else{
                                Admin.createNewUser(userId, userName, passwordIter1);
                                System.out.println("Successfully added new user with id: "+userId+" and name: "+userName);
                                break;
                            }
                        }
                        catch(InputMismatchException ime) {
                            System.out.println(ime);
                        }

                    }
                } // end of case 1
                break;

                case 2: // Add a new admin
                {
                    while(true) {
                        try {
                            System.out.println("Set a userId: ");
                            int userId = scan.nextInt();
                            System.out.println("Set a userName: ");
                            String userName = scan.next();
                            System.out.println("Set a password: ");
                            String passwordIter1 = scan.next();
                            System.out.println("confirm password: ");
                            String passwordIter2 = scan.next();
                            if (!passwordIter1.equals(passwordIter2)) {
                                System.out.println("Passwords do not match. Please try again.");
                            } else {
                                Admin.createNewAdmin(userId, userName, passwordIter1);
                                System.out.println("Successfully added new user with id: " + userId + " and name: " + userName);
                                break;
                            }
                        }
                        catch(InputMismatchException ime) {
                            System.out.println(ime);
                        }
                    }
                } // end of case 2
                break;
                case 3: // view all users
                {
                    admin.displayAllUsers();
                    break;
                }
                case 4:
                {
                    admin.displayAllAdmins();
                    break;
                }
                case 5:
                {
                    keepRunning = false;
                    break;
                }
            }
        }


    }
    public static void userSession(User user) throws SQLException {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("================= WELCOME TO PERSONAL CREDENTIALS MANAGER - ADMIN SESSION =================\n");
            Scanner scan = new Scanner(System.in);
            System.out.println("\n=========================================================================================\n");
            System.out.println("What would you like to do?:");
            System.out.println("(1) Add a new credential");
            System.out.println("(2) Retrieve a credential");
            System.out.println("(3) View your information");
            System.out.println("(4) View all your credentials");
            System.out.println("(5) Exit");
            System.out.println("\n=========================================================================================\n");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: // Add a new credential
                {
                    while(true) {
                        System.out.println("Set a credential id: ");
                        int credId = 0;
                        try {
                            credId = scan.nextInt();
                        } catch (InputMismatchException ime) {
                            System.out.println(ime);
                            continue;
                        }
                        System.out.println("Set a credential name: ");
                        String credName = scan.next();
                        System.out.println("Set a value: ");
                        String value = scan.next();
                        System.out.println("Enter your password");
                        String passwordIter1 = scan.next();
                        user.addCredential(credId, credName, value, passwordIter1);
                        System.out.println("Successfully added new credential with id: " + credId + " and name: " + credName + " for user " + user.getUserName());
                        System.out.println("Do you want to add another credential? (Press 'N' to stop)");
                        if (scan.next().equalsIgnoreCase("n")) {
                            break;
                        }
                        break;
                    }
                } // end of case 1
                break;

                case 2: // Retrieve a credential
                {
                    try {
                        System.out.println("SELECT ONE OF THE FOLLOWING:");
                        System.out.println("(1) Search by credential name");
                        System.out.println("(2) Search by credential id");
                        int ch = 0;
                        try {
                            ch = scan.nextInt();
                        } catch (InputMismatchException ime) {
                            System.out.println(ime);
                            continue;
                        }
                        if (ch == 1) {
                            System.out.println("Enter your password");
                            String password = scan.next();
                            System.out.println("Enter your username");
                            String username = scan.next();
                            String requestedCredential = user.retrieveCredential(username, password);
                            System.out.println(requestedCredential);
                        } else if (ch == 2) {
                            System.out.println("Enter credential id");
                            int id = scan.nextInt();
                            System.out.println("Enter your password");
                            String password = scan.next();
                            if (!Authentication.verifyUserPassword(user.getUserId(), password)) {
                                System.out.println("Incorrect password.");
                                return;
                            }
                            String requestedCredential = user.retrieveCredential(id, password);
                            System.out.println(requestedCredential);
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Some error occurred, try again");
                    }
                } // end of case 2
                break;
                case 3: // view user information
                {
                    System.out.println("Enter your password");
                    String password = scan.next();
                    user.displayUserInformation(password);
                }
                break;
                case 4: // view all credentials
                {
                    System.out.println("Enter your password");
                    String password = scan.next();
                    user.displayAllCredentials(password);
                }
                break;
                case 5:
                {
                    keepRunning = false;
                }
                break;
            }
        }
    }
}
