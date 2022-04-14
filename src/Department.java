import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Department {

    public static final String[] DEPARTMENTS = {
            "Accounting",
            "Human Resources",
            "Administration",
            "Marketing"
    };
    private int numberOfEmployees;
    private final JPanel panel = new JPanel();
    private final Employee employee;
    private int departmentID;
    private String departmentName;

    public Department() {
        //Initialize variables
        departmentName = "";
        departmentID = 0;
        numberOfEmployees = 0;
        employee = new Employee();

        //Background image
        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon("images/background.jpeg");
        background.setIcon(icon);
        background.setBounds(0, 0, 600, 600);

        //Properties for Panel
        panel.setLayout(null);
        panel.setSize(600, 600);
        panel.setVisible(true);

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Main Menu");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(230, 20, 190, 40);

        //Properties for Department Button
        JButton viewDepartmentsButton = new JButton("View All Departments");
        viewDepartmentsButton.setBounds(180, 90, 250, 80);
        viewDepartmentsButton.setFocusPainted(false);
        viewDepartmentsButton.setFocusable(false);
        viewDepartmentsButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Employee Button
        JButton viewEmployeesButton = new JButton("View All Employees");
        viewEmployeesButton.setBounds(180, 175, 250, 80);
        viewEmployeesButton.setFocusPainted(false);
        viewEmployeesButton.setFocusable(false);
        viewEmployeesButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Employee Button
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBounds(180, 260, 250, 80);
        addEmployeeButton.setFocusPainted(false);
        addEmployeeButton.setFocusable(false);
        addEmployeeButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Customer Button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(180, 345, 250, 80);
        exitButton.setFocusPainted(false);
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Add components to Panel
        panel.add(MenuLabel);
        panel.add(viewDepartmentsButton);
        panel.add(viewEmployeesButton);
        panel.add(addEmployeeButton);
        panel.add(exitButton);
        panel.add(background);

        //Action when viewDepartmentsButton is Pressed
        viewDepartmentsButton.addActionListener((ActionEvent e) -> {
            System.out.println("Admin Button Pressed");
            viewDepartments();
        });

        //Action when viewEmployeesButton is Pressed
        viewEmployeesButton.addActionListener((ActionEvent e) -> {
            System.out.println("View Employees Button Pressed");
            viewEmployees();
        });

        //Action when addEmployeesButton is Pressed
        addEmployeeButton.addActionListener((ActionEvent e) -> {
            System.out.println("Add Employee Button Pressed");
            addEmployee();
        });

        //Action when exitButton is Pressed
        exitButton.addActionListener((ActionEvent e) -> {
            System.out.println("Exit Button Pressed");

            int selection = JOptionPane.showConfirmDialog(panel, "Do you want to continue?",
                    "Exit Prompt", JOptionPane.YES_NO_OPTION);

            boolean isYes = (selection == JOptionPane.YES_NO_OPTION);

            if (isYes) {
                System.out.println("Program Exiting...");
                System.exit(0);
            } else {
                System.out.println("Exit Cancelled.");
            }
        });
    }

    //Initialization of Getters
    public int getNumberOfEmployees(String departmentName) {
        int count = 0;
        String department;

        try {
            //Open Files
            Scanner read = new Scanner(new File("files/Employees.txt"));

            //while there is more text in file
            while (read.hasNext()) {
                read.next();
                read.next();
                read.next();
                read.next();
                read.nextLine();
                read.next();
                read.next();

                //remove space in front of string
                department = read.nextLine().substring(1);

                if (department.equals(departmentName)) {
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set Number of Customers to value of Records
        numberOfEmployees = count;
        System.out.println("Number of employees for " + departmentName + ": " + count);

        return numberOfEmployees;
    }

    public int getDepartmentID(String departmentName) {
        int count = 0;

        while (count < DEPARTMENTS.length) {
            if (departmentName.equals(DEPARTMENTS[count])) {
                this.departmentID = count + 1;
                this.departmentName = DEPARTMENTS[count];
            }
            count++;
        }

        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    //View all departments
    public void viewDepartments() {
        System.out.println("View Departments Button Pressed");

        try {
            //Column Names for Table
            String[] tableHead = {"Department ID", "Department Name", "Number of Employees"};

            //Properties for table
            DefaultTableModel model = new DefaultTableModel(tableHead, 0);

            //Used for counting rows
            int count = 0;

            while (count < DEPARTMENTS.length) {
                //Insert data into rows
                model.insertRow(count, new Object[]{getDepartmentID(DEPARTMENTS[count]),
                        getDepartmentName(), getNumberOfEmployees(DEPARTMENTS[count])});
                count++;
            }

            //Create new Table
            JTable Table = new JTable(model);
            Table.setDefaultEditor(Object.class, null);//Set to not editable
            Table.setAutoCreateRowSorter(true);//Enable sorting by columns
            Table.setVisible(true);
            Table.setOpaque(false);

            //Create new window
            JFrame Frame = new JFrame();
            Frame.setLayout(new GridLayout());
            Frame.setLocationRelativeTo(panel);
            Frame.setVisible(true);
            Frame.setSize(600, 400);
            Frame.setMinimumSize(new Dimension(600, 400));
            Frame.setTitle("All Departments");
            Frame.pack();

            //Add table to window
            Frame.add(new JScrollPane(Table));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a employee
    public void addEmployee() {
        //Remove previous display
        panel.removeAll();

        // Background image
        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon("images/background.jpeg");
        background.setIcon(icon);
        background.setBounds(0, 0, 600, 600);

        //Properties for Menu Label
        JLabel menuLabel = new JLabel("Add Employee");
        menuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        menuLabel.setBounds(210, 20, 250, 40);

        //Properties for ID Number Label
        JLabel idLabel = new JLabel("ID Number");
        idLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        idLabel.setBounds(10, 70, 250, 30);

        //Properties for Last Name Label
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        firstNameLabel.setBounds(10, 120, 250, 30);

        //Properties for First Name Label
        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        lastNameLabel.setBounds(10, 170, 250, 30);

        //Properties for Gender Label
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        genderLabel.setBounds(10, 220, 250, 30);

        //Properties for age Label
        JLabel ageLabel = new JLabel("Age");
        ageLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        ageLabel.setBounds(10, 270, 250, 30);

        //Properties for Email Address Label
        JLabel emailAddressLabel = new JLabel("Email Address");
        emailAddressLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        emailAddressLabel.setBounds(10, 320, 250, 30);

        //Properties for Phone Number Label
        JLabel phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        phoneNumberLabel.setBounds(10, 370, 250, 30);

        //Properties for Department Label
        JLabel departmentLabel = new JLabel("Department");
        departmentLabel.setFont(new Font("times new roman", Font.BOLD, 18));
        departmentLabel.setBounds(10, 420, 250, 30);

        //Properties for Back Button
        JButton backButton = new JButton("<");
        backButton.setBounds(10, 20, 50, 30);
        backButton.setFocusPainted(false);
        backButton.setFocusable(false);
        backButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for ID Field
        JTextField idField = new JTextField() {
            //Overrides addNotify
            public void addNotify() {
                super.addNotify();
                requestFocus();//Focus on this when displayed
            }
        };

        idField.setBounds(150, 70, 310, 40);
        idField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for First Name Field
        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(150, 120, 310, 40);
        firstNameField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Last Name Field
        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 170, 310, 40);
        lastNameField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Radio Buttons
        JRadioButton maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(150, 220, 90, 30);
        maleRadioButton.setFocusPainted(false);
        maleRadioButton.setBackground(Color.WHITE);

        JRadioButton femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(280, 220, 90, 30);
        femaleRadioButton.setFocusPainted(false);
        femaleRadioButton.setBackground(Color.WHITE);

        AtomicReference<ButtonGroup> genderButton = new AtomicReference<>(new ButtonGroup());
        genderButton.get().add(maleRadioButton);
        genderButton.get().add(femaleRadioButton);

        //Properties for Age Field
        JTextField ageField = new JTextField();
        ageField.setBounds(150, 270, 310, 40);
        ageField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Email Address Field
        JTextField emailAddressField = new JTextField();
        emailAddressField.setBounds(150, 320, 310, 40);
        emailAddressField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Phone Number Field
        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(150, 370, 310, 40);
        phoneNumberField.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for departmentComboBox
        JComboBox<String> departmentComboBox = new JComboBox<>(DEPARTMENTS);
        departmentComboBox.setBounds(150, 420, 310, 40);
        departmentComboBox.setFont(new Font("times new roman", Font.PLAIN, 18));

        //Properties for Process Button
        JButton processButton = new JButton("Process");
        processButton.setBounds(200, 470, 200, 50);
        processButton.setFocusPainted(false);
        processButton.setFocusable(false);
        processButton.setFont(new Font("times new roman", Font.PLAIN, 20));

        //Add components to Panel
        panel.add(menuLabel);
        panel.add(idLabel);
        panel.add(firstNameLabel);
        panel.add(lastNameLabel);
        panel.add(ageLabel);
        panel.add(genderLabel);
        panel.add(emailAddressLabel);
        panel.add(phoneNumberLabel);
        panel.add(departmentLabel);
        panel.add(idField);
        panel.add(firstNameField);
        panel.add(lastNameField);
        panel.add(ageField);
        panel.add(maleRadioButton);
        panel.add(femaleRadioButton);
        panel.add(emailAddressField);
        panel.add(phoneNumberField);
        panel.add(departmentComboBox);
        panel.add(backButton);
        panel.add(processButton);
        panel.add(background);

        //Show Panel
        panel.validate();
        panel.repaint();

        //Action when backButton is Pressed
        backButton.addActionListener((ActionEvent e) -> {
            //Remove previous display
            panel.removeAll();
            panel.add(new MainScreen().panel);//Recreate Main menu
            panel.validate();
            panel.repaint();

            System.out.println("Back Button Pressed");
        });

        //Action when processButton is Pressed
        processButton.addActionListener((ActionEvent e) -> {
            Verification check = new Verification();

            //Get values from TextFields
            String idNumber = idField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String gender = getSelectedGender(maleRadioButton, femaleRadioButton);
            String age = ageField.getText();
            String emailAddress = emailAddressField.getText();
            String phoneNumber = phoneNumberField.getText();
            String department = String.valueOf(departmentComboBox.getSelectedItem());

            System.out.println("Process Button Pressed");

            if (check.verifyFieldLengths(idNumber, firstName, lastName, gender, age, emailAddress, phoneNumber)) {
                int valid = check.verifyFields(idNumber, firstName, lastName, Integer.parseInt(age), emailAddress, phoneNumber);

                if (valid == 0) {
                    System.err.println("Invalid Length for idNumber");
                    JOptionPane.showMessageDialog(panel, "Invalid Length for idNumber",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == -1) {
                    System.err.println("idNumber is not numerical");
                    JOptionPane.showMessageDialog(panel, "idNumber is not numerical",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == 1) {
                    System.err.println("Invalid number of digits for Phone Number");
                    JOptionPane.showMessageDialog(panel, "Invalid number of digits for Phone Number",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == -2) {
                    System.err.println("Phone Number is not numerical");
                    JOptionPane.showMessageDialog(panel, "Phone Number is not numerical",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == -3) {
                    System.err.println("First Name is not only Letters");
                    JOptionPane.showMessageDialog(panel, "Last Name is not only Letters",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == -4) {
                    System.err.println("Last Name is not only Letters");
                    JOptionPane.showMessageDialog(panel, "Last Name is not only Letters",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == 4) {
                    System.err.println("Invalid Area Code");
                    JOptionPane.showMessageDialog(panel, "Invalid Area Code",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == -5) {
                    System.err.println("Phone Number already exists");
                    JOptionPane.showMessageDialog(panel, "Phone Number already exists",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == -6) {
                    System.err.println("Invalid email address");
                    JOptionPane.showMessageDialog(panel, "Invalid email address",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == -7) {
                    System.err.println("Invalid age, too young");
                    JOptionPane.showMessageDialog(panel, "Invalid age, too young",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == -8) {
                    System.err.println("Id number already exists");
                    JOptionPane.showMessageDialog(panel, "Id number already exists",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (valid == 5) {
                    //Set values
                    employee.setIdNumber(idNumber);
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setGender(gender);
                    employee.setAge(Integer.parseInt(age));
                    employee.setEmailAddress(emailAddress);
                    employee.setTelephoneNumber(phoneNumber);
                    employee.setDepartment(department);

                    try {
                        System.out.println("Writing to File...");

                        //Open file in append mode
                        FileWriter writer = new FileWriter("files/Employees.txt", true);
                        writer.write(employee.getIdNumber() + " ");
                        writer.write(employee.getFirstName() + " ");
                        writer.write(employee.getLastName() + " ");
                        writer.write(employee.getGender() + " ");
                        writer.write(employee.getAge() + "\n");
                        writer.write(employee.getEmailAddress() + " ");
                        writer.write(employee.getTelephoneNumber() + " ");
                        writer.write(employee.getDepartment() + "\n");
                        writer.close();

                        System.out.println("Employee Information Saved");
                        JOptionPane.showMessageDialog(panel, "Employee Added", "Information",
                                JOptionPane.INFORMATION_MESSAGE);

                        //Reset Fields
                        idField.setText("");
                        firstNameField.setText("");
                        lastNameField.setText("");
                        genderButton.set(new ButtonGroup());
                        ageField.setText("");
                        emailAddressField.setText("");
                        phoneNumberField.setText("");
                        departmentComboBox.setSelectedItem(DEPARTMENTS[0]);
                    } catch (Exception ex) {
                        System.err.println("Could not save Employee Information");
                        JOptionPane.showMessageDialog(panel, "Could not save Employee Information",
                                "Error", JOptionPane.WARNING_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            } else {
                System.err.println("One or more fields are empty");
                JOptionPane.showMessageDialog(panel, "One or more fields are empty",
                        "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    //View all Employees
    public void viewEmployees() {
        System.out.println("View Employees Button Pressed");

        try {
            //Column Names for Table
            String[] tableHead = {"ID Number", "First Name", "Last Name", "Gender",
                    "Age", "Email Address", "Telephone Number", "Department"};

            //Properties for table
            DefaultTableModel model = new DefaultTableModel(tableHead, 0);

            //Used for counting rows
            int count = 0;

            Scanner read = new Scanner(new File("files/Employees.txt"));

            while (read.hasNext()) {
                //Insert data into rows
                model.insertRow(count, new Object[]{read.next(), read.next(), read.next(), read.next(),
                        read.nextLine(), read.next(), read.next(), read.nextLine()});
                count++;
            }

            //Create new Table
            JTable Table = new JTable(model);
            Table.setDefaultEditor(Object.class, null);//Set to not editable
            Table.setAutoCreateRowSorter(true);//Enable sorting by columns
            Table.setVisible(true);
            Table.setOpaque(false);

            //Create new window
            JFrame Frame = new JFrame();
            Frame.setLayout(new GridLayout());
            Frame.setLocationRelativeTo(panel);
            Frame.setVisible(true);
            Frame.setSize(1920, 1080);
            Frame.setMinimumSize(new Dimension(1280, 720));
            Frame.setTitle("All Employees");
            Frame.pack();

            //Add table to window
            Frame.add(new JScrollPane(Table));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get selected gender
    private String getSelectedGender(JRadioButton male, JRadioButton female) {
        if (male.isSelected()) {
            return male.getText();
        } else if (female.isSelected()) {
            return female.getText();
        }
        return "";
    }

    //Return JPanel
    public JComponent getComponent() {
        return panel;
    }
}
