/*
This makes a Diolog box apear that prompts usert to put in
Deatails captured, in @RegistrationDialog, 
*/


package KanBanAplication;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDatabase userDatabase;

    public LoginDialog(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
        setTitle("Log In");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        // Create form fields
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser ();
            }
        });
        add(loginButton);

        setModal(true);
        setLocationRelativeTo(null);
    }

    private void loginUser () {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Check if the username and password match any user in the database
        if (userDatabase.validateUser (username, password)) {
            // Retrieve user details
            Registration user = userDatabase.getUser (username);
            String name = user.getName();
            String surname = user.getSurname();

            // Open the TaskManager
            new TaskManager(name, surname); // Pass name and surname to TaskManager
            dispose(); // Close the login dialog
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}