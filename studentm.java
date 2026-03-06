import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.ArrayList;

class Student {
 String name, department, id; int age, sem; long contact;

Student(String name, String department, String id, int age, int sem, long contact) {
    this.name = name;
    this.department = department;
    this.id = id;
    this.age = age;
    this.sem = sem;
    this.contact = contact;
}

public String toString() {
    return "Name: " + name + " | ID: " + id + " | Age: " + age + " | Dept: " + department + " | Sem: " + sem + " | Contact: " + contact;
}

}

public class studentm{

private static ArrayList<Student> students = new ArrayList<>();

public static void main(String[] args) {
    JFrame frame = new JFrame("Student Management System");
    frame.setSize(600, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    // Display area
    JTextArea displayArea = new JTextArea();
    displayArea.setEditable(false);
    JScrollPane scroll = new JScrollPane(displayArea);
    frame.add(scroll, BorderLayout.CENTER);

    // Buttons panel
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 5));

    JButton addBtn = new JButton("Add");
    JButton viewBtn = new JButton("View");
    JButton searchBtn = new JButton("Search");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");

    panel.add(addBtn);
    panel.add(viewBtn);
    panel.add(searchBtn);
    panel.add(updateBtn);
    panel.add(deleteBtn);

    frame.add(panel, BorderLayout.SOUTH);

    // Add button action
    addBtn.addActionListener(e -> addStudent());

    // View students
    viewBtn.addActionListener(e -> {
        displayArea.setText("");
        for (Student s : students) displayArea.append(s + "\n");
    });

    // Search student
    searchBtn.addActionListener(e -> {
        String id = JOptionPane.showInputDialog(frame, "Enter ID to Search:");
        if (id == null) return;

        Student found = null;
        for (Student s : students) {
            if (s.id.equals(id)) found = s;
        }

        if (found != null)
            displayArea.setText(found.toString());
        else
            JOptionPane.showMessageDialog(frame, "Student Not Found");
    });

    // Update student
    updateBtn.addActionListener(e -> updateStudent());

    // Delete student
    deleteBtn.addActionListener(e -> {
        String id = JOptionPane.showInputDialog(frame, "Enter ID to Delete:");
        if (id == null) return;

        Student toRemove = null;
        for (Student s : students) {
            if (s.id.equals(id)) toRemove = s;
        }

        if (toRemove != null) {
            students.remove(toRemove);
            JOptionPane.showMessageDialog(frame, "Student Deleted!");
        } else {
            JOptionPane.showMessageDialog(frame, "Student Not Found");
        }
    });

    frame.setVisible(true);
}

// Method to add student
private static void addStudent() {
    JTextField name = new JTextField();
    JTextField id = new JTextField();
    JTextField age = new JTextField();
    JTextField dept = new JTextField();
    JTextField sem = new JTextField();
    JTextField contact = new JTextField();

    Object[] fields = {
            "Name:", name,
            "ID:", id,
            "Age:", age,
            "Department:", dept,
            "Semester:", sem,
            "Contact:", contact
    };

    int option = JOptionPane.showConfirmDialog(null, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        students.add(new Student(
                name.getText(),
                dept.getText(),
                id.getText(),
                Integer.parseInt(age.getText()),
                Integer.parseInt(sem.getText()),
                Long.parseLong(contact.getText())
        ));
        JOptionPane.showMessageDialog(null, "Student Added Successfully!");
    }
}

// Method to update student
private static void updateStudent() {
    String id = JOptionPane.showInputDialog("Enter ID to Update:");
    if (id == null) return;

    Student found = null;
    for (Student s : students) {
        if (s.id.equals(id)) found = s;
    }

    if (found == null) {
        JOptionPane.showMessageDialog(null, "Student Not Found");
        return;
    }

    JTextField name = new JTextField(found.name);
    JTextField age = new JTextField(String.valueOf(found.age));
    JTextField dept = new JTextField(found.department);
    JTextField sem = new JTextField(String.valueOf(found.sem));
    JTextField contact = new JTextField(String.valueOf(found.contact));

    Object[] fields = {
            "Name:", name,
            "Age:", age,
            "Department:", dept,
            "Semester:", sem,
            "Contact:", contact
    };

    int option = JOptionPane.showConfirmDialog(null, fields, "Update Student", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        found.name = name.getText();
        found.age = Integer.parseInt(age.getText());
        found.department = dept.getText();
        found.sem = Integer.parseInt(sem.getText());
        found.contact = Long.parseLong(contact.getText());

        JOptionPane.showMessageDialog(null, "Student Updated Successfully!");
    }
}

}