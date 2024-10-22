package KanBanAplication;

/*
             @RegistrationDialog  uses the @Registration class to create user objects. When a user submits their information, 
             a new instance of Registration is created with the provided details, which is then stored in the @UserDatabase.

*/



import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class RegistrationDialog extends JDialog {
    private JTextField usernameField;
    private JTextField nameField;
    private JTextField surnameField;
    private JPasswordField passwordField;
    private UserDatabase userDatabase;

    public RegistrationDialog(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
        setTitle("Register User");
        setSize(300, 250);
        setLayout(new GridLayout(5, 2)); // GridLayout for form-like structure

        // Create form fields
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Surname:"));
        surnameField = new JTextField();
        add(surnameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> registerUser ());
        add(submitButton);

        setModal(true); // Modal dialog
        setLocationRelativeTo(null); // Center dialog
    }

    private void registerUser () {
        String username = usernameField.getText();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            validateUsername(username);
            validatePassword(password);

            // Create a new Registration object and add it to the database
            Registration newUser  = new Registration(username, name, surname, password);
            userDatabase.addUser (newUser );

            JOptionPane.showMessageDialog(this, "User  registered successfully!");
            dispose(); // Close dialog
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateUsername(String username) {
        if (username.length() > 5 || !username.contains("_")) {
            throw new IllegalArgumentException("Username must be 5 characters or less and contain an underscore.");
        }
    }

    private void validatePassword(String password) {
        // Example password complexity: at least 8 characters, must include a digit and a special character
        if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*].*")) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and include at least one digit and one special character.");
        }
    }
}