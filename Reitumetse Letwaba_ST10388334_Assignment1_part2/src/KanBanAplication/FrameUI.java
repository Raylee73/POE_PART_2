package KanBanAplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameUI extends JFrame {
    private UserDatabase database; // User database instance

    public FrameUI() {
        database = new UserDatabase(); // Initialize user database

        // Set up the frame
        setTitle("Login Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Log in to Easy Kanban", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Buttons
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Log In");

        // Action listeners for buttons
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationDialog dialog = new RegistrationDialog(database);
                dialog.setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginDialog loginDialog = new LoginDialog(database);
                loginDialog.setVisible(true);
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        // Add button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);
        
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Create and display the frame
        FrameUI frame = new FrameUI();
        frame.setVisible(true);
    }
}