import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Appointment {
    String doctor;
    String patientName;
    String time;
    int age;
    long contactNo;
    String symptoms;

    Appointment(String doctor, String patientName, String time, int age, long contactNo, String symptoms) {
        this.doctor = doctor;
        this.patientName = patientName;
        this.time = time;
        this.age = age;
        this.contactNo = contactNo;
        this.symptoms = symptoms;
    }
}

class Staff {
    String name;
    int age;
    long salary;
    long contactNo;
    String position;

    Staff(String name, int age, long salary, long contactNo, String position) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.contactNo = contactNo;
        this.position = position;
    }
}

class User {
    private Map<String, String> credentials;
    protected int countAttempt = 0;
    protected boolean isLoggedIn = false;
    protected String loggedInUser = "";

    User() {
        credentials = new HashMap<>();
        credentials.put("admin", "admin"); // default credentials
    }

    void login(JFrame frame) {
        if (isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "Already logged in");
            return;
        }

        if (countAttempt == 3) {
            JOptionPane.showMessageDialog(frame, "No more attempts");
            System.exit(0);
        }

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);

        int option = JOptionPane.showConfirmDialog(frame, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String userName = userField.getText();
            String password = new String(passField.getPassword());

            if (credentials.containsKey(userName) && credentials.get(userName).equals(password)) {
                loggedInUser = userName;
                JOptionPane.showMessageDialog(frame, "Login Successful\nWelcome Admin");
                isLoggedIn = true;
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect Username or Password\nTry again");
                countAttempt++;
                login(frame);
            }
        }
    }

    void logout(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Logout Successful");
        isLoggedIn = false;
        loggedInUser = "";
    }
}

class Hospital extends User {
    private ArrayList<Appointment> appointments;
    private ArrayList<Staff> staffs;

    Hospital() {
        appointments = new ArrayList<>();
        staffs = new ArrayList<>();
    }

    void allAppointments(JFrame frame) {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "Please Login");
            return;
        }

        StringBuilder appointmentDetails = new StringBuilder("Appointments for today are:\n");
        for (Appointment appointment : appointments) {
            appointmentDetails.append("Doctor: ").append(appointment.doctor).append("\n")
                    .append("Patient Name: ").append(appointment.patientName).append("\n")
                    .append("Appointment Time: ").append(appointment.time).append("\n")
                    .append("Age: ").append(appointment.age).append("\n")
                    .append("Contact No: ").append(appointment.contactNo).append("\n")
                    .append("Symptoms: ").append(appointment.symptoms).append("\n\n");
        }

        JTextArea textArea = new JTextArea(appointmentDetails.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Appointments", JOptionPane.INFORMATION_MESSAGE);
    }

    void addAppointment(JFrame frame) {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "Please Login");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(7, 2));
        JTextField docField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField symptomsField = new JTextField();

        panel.add(new JLabel("Enter Doctor Name:"));
        panel.add(docField);
        panel.add(new JLabel("Enter Patient Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Enter Appointment Time:"));
        panel.add(timeField);
        panel.add(new JLabel("Enter Patient Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Enter Patient Contact No:"));
        panel.add(contactField);
        panel.add(new JLabel("Enter Patient Symptoms:"));
        panel.add(symptomsField);

        int option = JOptionPane.showConfirmDialog(frame, panel, "Add Appointment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String doc = docField.getText();
            String name = nameField.getText();
            String time = timeField.getText();
            int age = Integer.parseInt(ageField.getText());
            long contactNo = Long.parseLong(contactField.getText());
            String symptoms = symptomsField.getText();

            Appointment appointment = new Appointment(doc, name, time, age, contactNo, symptoms);
            appointments.add(appointment);
            JOptionPane.showMessageDialog(frame, "Appointment added Successfully!");
        }
    }

    void addStaff(JFrame frame) {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "Please Login");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField positionField = new JTextField();

        panel.add(new JLabel("Enter Staff Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Enter Staff Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Enter Staff Salary:"));
        panel.add(salaryField);
        panel.add(new JLabel("Enter Staff Contact No:"));
        panel.add(contactField);
        panel.add(new JLabel("Enter Staff Position:"));
        panel.add(positionField);

        int option = JOptionPane.showConfirmDialog(frame, panel, "Add Staff", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            long salary = Long.parseLong(salaryField.getText());
            long contactNo = Long.parseLong(contactField.getText());
            String position = positionField.getText();

            Staff staff = new Staff(name, age, salary, contactNo, position);
            staffs.add(staff);
            JOptionPane.showMessageDialog(frame, "Staff Information added Successfully!");
        }
    }

    void allStaffs(JFrame frame) {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "Please Login");
            return;
        }

        StringBuilder staffDetails = new StringBuilder("All Staffs Details:\n");
        for (Staff staff : staffs) {
            staffDetails.append("Name: ").append(staff.name).append("\n")
                    .append("Age: ").append(staff.age).append("\n")
                    .append("Salary: ").append(staff.salary).append("\n")
                    .append("Contact No: ").append(staff.contactNo).append("\n")
                    .append("Position: ").append(staff.position).append("\n\n");
        }

        JTextArea textArea = new JTextArea(staffDetails.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Staffs", JOptionPane.INFORMATION_MESSAGE);
    }

    void checkBeds(JFrame frame) {
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(frame, "Please Login");
            return;
        }

        Random rand = new Random();
        int randomNoOfBeds = rand.nextInt(200); // this will keep the random number within the range of 200

        JOptionPane.showMessageDialog(frame, "Number of beds available today are: " + randomNoOfBeds);
    }

    void showDoctorsDetails(JFrame frame) {
        String doctorDetails = "<html><body>";
        doctorDetails += "<h2>Dr. Waqar</h2>";
        doctorDetails += "<p>Timing: Monday To Friday 9AM - 5PM<br>Saturday 10AM - 1PM<br>Sunday OFF</p>";
        doctorDetails += "<h2>Dr. Shankar</h2>";
        doctorDetails += "<p>Timing: Monday To Friday 1PM - 9PM<br>Saturday 1PM - 5PM<br>Sunday OFF</p>";
        doctorDetails += "<h2>Dr. Krishna</h2>";
        doctorDetails += "<p>Timing: Monday To Friday 8AM - 6PM<br>Saturday 10AM - 1PM<br>Sunday 3PM - 5PM</p>";
        doctorDetails += "<h2>Dr. Roy</h2>";
        doctorDetails += "<p>Timing: Monday To Friday 9AM - 5PM<br>Saturday 10AM - 1PM<br>Sunday 7PM - 9PM</p>";
        doctorDetails += "</body></html>";

        JLabel label = new JLabel(doctorDetails);
        JOptionPane.showMessageDialog(frame, label, "Doctors Details", JOptionPane.INFORMATION_MESSAGE);
    }
}

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();

        JFrame frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JButton btnLogin = new JButton("Login");
        JButton btnAllAppointments = new JButton("Check All Appointments for Today");
        JButton btnAddAppointment = new JButton("Add Appointments");
        JButton btnAddStaff = new JButton("Add Staff Information");
        JButton btnAllStaffs = new JButton("Check All Staffs Details");
        JButton btnShowDoctors = new JButton("Check Doctors Availability");
        JButton btnCheckBeds = new JButton("Check Bed Availability");
        JButton btnLogout = new JButton("Logout");

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hospital.login(frame);
            }
        });

        btnAllAppointments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hospital.allAppointments(frame);
            }
        });

        btnAddAppointment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hospital.addAppointment(frame);
            }
        });

        btnAddStaff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hospital.addStaff(frame);
            }
        });

        btnAllStaffs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hospital.allStaffs(frame);
            }
        });

        btnShowDoctors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hospital.showDoctorsDetails(frame);
            }
        });

        btnCheckBeds.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hospital.checkBeds(frame);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hospital.logout(frame);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1));
        panel.add(btnLogin);
        panel.add(btnAllAppointments);
        panel.add(btnAddAppointment);
        panel.add(btnAddStaff);
        panel.add(btnAllStaffs);
        panel.add(btnShowDoctors);
        panel.add(btnCheckBeds);
        panel.add(btnLogout);

        frame.add(panel);
        frame.setVisible(true);
    }
}
