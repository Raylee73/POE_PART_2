package KanBanAplication;

import java.util.ArrayList;

public class UserDatabase {
    private ArrayList<Registration> users;

    // Constructor
    public UserDatabase() {
        this.users = new ArrayList<>();
    }

    // Method to add a new user
    public void addUser (Registration user) {
        this.users.add(user);
    }

    // Method to remove a user by username
    public void removeUser (String username) {
        this.users.removeIf(user -> user.getUsername().equals(username));
    }

    // Method to retrieve a user by username
    public Registration getUser (String username) {
        for (Registration user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Method to display all users
    public void displayUsers() {
        for (Registration user : this.users) {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Name: " + user.getName());
            System.out.println("Surname: " + user.getSurname());
            System.out.println("Password: " + user.getPassword());
            System.out.println();
        }
    }

    // Implementing the validateUser  method
    public boolean validateUser (String username, String password) {
        for (Registration user : this.users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return true; // User found with matching credentials
            }
        }
        return false; // No matching user found
    }
}