package KanBanAplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManager extends JFrame {
    private JLabel welcomeLabel;

    public TaskManager(String name, String surname) {
        // Set the title of the JFrame
        setTitle("Task Manager");

        // Set the layout manager
        setLayout(new BorderLayout());

        // Create welcome label with a larger font
        welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to Easy Kanban, " + name + " " + surname);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
        add(welcomeLabel, BorderLayout.NORTH); // Add welcome label to the top of the frame

        // Create buttons
        JButton addTaskButton = new JButton("1. Add Task");
        JButton showReportButton = new JButton("2. Show Report");
        JButton exitButton = new JButton("3. Exit");
        
        
// Action listener for Add Task button
addTaskButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int numberOfTasks = 0;
        boolean isValidInput = false;

        // Continuously prompt the user until a valid input is provided or they cancel
        while (!isValidInput) {
            String input = JOptionPane.showInputDialog("Enter the number of tasks you want to add:");

            // Check if the user pressed Cancel
            if (input == null) {
                JOptionPane.showMessageDialog(null, "canceled.");
                return; // Exit the action listener
            }

            try {
                // Parse the input to an integer
                numberOfTasks = Integer.parseInt(input);

                // Check if the number of tasks is positive
                if (numberOfTasks > 0) {
                    isValidInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                }
            } catch (NumberFormatException ex) {
                // Handle the case where the input is not a valid integer
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }

        // Logic to add tasks
        for (int i = 0; i < numberOfTasks; i++) {
            new AddTask(); // Open a new AddTask form for each task
        }
    }
});



        showReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for showing report
                JOptionPane.showMessageDialog(null, "Show Report functionality coming soon!");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        // Create a panel for buttons and add buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addTaskButton);
        buttonPanel.add(showReportButton);
        buttonPanel.add(exitButton);

        // Add button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the JFrame
        setSize(350, 150);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Set the JFrame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Simulate user login (replace with actual login logic)
        String username = "johndoe"; // Example username
        String password = "password"; // Example password

        UserDatabase userDatabase = new UserDatabase();
        // Example of adding a user (for testing purposes)
        userDatabase.addUser (new Registration(username, "John", "Doe", password));

        // Validate user (replace with actual validation logic)
        if (userDatabase.validateUser (username, password)) {
            Registration user = userDatabase.getUser (username);
            String name = user.getName();
            String surname = user.getSurname();
            new TaskManager(name, surname); // Pass name and surname to TaskManager
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password.");
        }
    }
}