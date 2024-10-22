/*

*/


package KanBanAplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddTask {
    private JFrame frame;
    private JTextField taskNameField;
    private JTextField taskNumberField;
    private JTextArea taskDescriptionField;
    private JSpinner taskDurationSpinner;
    private JComboBox<String> taskStateDropdown;
    private JTextField taskIDField;
    private String[] taskStates = {"To Do", "Doing", "Done"};
    
    // Array to store task data
    private String[] taskNames = new String[10];
    private String[] taskNumbers = new String[10];
    private String[] taskDescriptions = new String[10];
    private int[] taskDurations = new int[10];
    private String[] taskIDs = new String[10];
    private int taskCount = 0;

    public AddTask() {
        frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allow independent closing
        frame.setSize(500, 480);
        frame.setLayout(new GridLayout(7, 2));

        // Input fields
        frame.add(new JLabel("Task Name:"));
        taskNameField = new JTextField();
        taskNameField.addKeyListener(new TaskIDGenerator());
        frame.add(taskNameField);

        frame.add(new JLabel("Task Number:"));
        taskNumberField = new JTextField();
        taskNumberField.addKeyListener(new TaskIDGenerator());
        frame.add(taskNumberField);

        frame.add(new JLabel("Task ID:"));
        taskIDField = new JTextField();
        taskIDField.setEditable(false);
        frame.add(taskIDField);

        frame.add(new JLabel("Task Description:"));
        taskDescriptionField = new JTextArea(3, 20);
        frame.add(new JScrollPane(taskDescriptionField));

        frame.add(new JLabel("Task Duration (hours):"));
        taskDurationSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 24, 1));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) taskDurationSpinner.getEditor();
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        frame.add(taskDurationSpinner);

        frame.add(new JLabel("Task State:"));
        taskStateDropdown = new JComboBox<>(taskStates);
        frame.add(taskStateDropdown);

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new AddTaskListener());
        frame.add(addButton);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class TaskIDGenerator implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            String taskName = taskNameField.getText();
            String taskNumber = taskNumberField.getText();

            if (!taskName.isEmpty() && !taskNumber.isEmpty()) {
                // Ensure taskName has at least 2 characters
                String taskID = taskName.length() >= 2 ? taskName.substring(0, 2) : taskName + "X"; // Append 'X' if less than 2 characters
                taskID += ";" + taskNumber;
                taskIDField.setText(taskID);
            } else {
                taskIDField.setText("");
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }

 private class AddTaskListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (taskCount < 10) { // Limit to 10 tasks for simplicity
            String taskName = taskNameField.getText();
            String taskNumber = taskNumberField.getText();
            String taskDescription = taskDescriptionField.getText(); // Corrected here
            int taskDuration = (int) taskDurationSpinner.getValue();
            String taskState = (String) taskStateDropdown.getSelectedItem();

            // Validate task description length
            if (taskDescription.length() > 50) { // Corrected here
                JOptionPane.showMessageDialog(frame, "Task description cannot exceed 50 characters.");
                return;
            }

            // Store data in arrays
            taskNames[taskCount] = taskName;
            taskNumbers[taskCount] = taskNumber;
            taskDescriptions[taskCount] = taskDescription;
            taskDurations[taskCount] = taskDuration;
            taskIDs[taskCount] = taskIDField.getText();

            taskCount++;

            // Clear input fields
            taskNameField.setText("");
            taskNumberField.setText("");
            taskDescriptionField.setText("");
            taskDurationSpinner.setValue(1);
            taskStateDropdown.setSelectedIndex(0);
            taskIDField.setText("");

            JOptionPane.showMessageDialog(frame, "Task added successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "Task limit reached.");
        }
    }
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddTask::new);
    }
}