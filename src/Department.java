import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Department {

    private JPanel panel = new JPanel();
    private int departmentID;
    private String departmentName;
    private static int numberOfEmployees;
    private final Employee employee;
    private final String[] DEPARTMENTS = {
            "Accounting",
            "Human Resources",
            "Administration",
            "Marketing"
    };

    public Department() {
        //Initialize variables
        departmentName = "";
        departmentID = 0;
        numberOfEmployees = 0;
        employee = new Employee();

        //Properties for Panel
        panel.setLayout(null);
        panel.setSize(600, 600);
        panel.setBackground(Color.WHITE);
        panel.setVisible(true);

        //Properties for Menu Label
        JLabel MenuLabel = new JLabel("Main Menu");
        MenuLabel.setFont(new Font("times new roman", Font.BOLD, 30));
        MenuLabel.setBounds(250, 20, 190, 40);

        //Properties for Department Button
        JButton viewDepartmentsButton = new JButton("View All Departments");
        viewDepartmentsButton.setBounds(200, 90, 250, 80);
        viewDepartmentsButton.setFocusPainted(false);
        viewDepartmentsButton.setFocusable(false);
        viewDepartmentsButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Employee Button
        JButton viewEmployeesButton = new JButton("View All Employees");
        viewEmployeesButton.setBounds(200, 175, 250, 80);
        viewEmployeesButton.setFocusPainted(false);
        viewEmployeesButton.setFocusable(false);
        viewEmployeesButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Employee Button
        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBounds(200, 260, 250, 80);
        addEmployeeButton.setFocusPainted(false);
        addEmployeeButton.setFocusable(false);
        addEmployeeButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Customer Button
        JButton ExitButton = new JButton("Exit");
        ExitButton.setBounds(200, 345, 250, 80);
        ExitButton.setFocusPainted(false);
        ExitButton.setFocusable(false);
        ExitButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Add components to Panel
        panel.add(MenuLabel);
        panel.add(viewDepartmentsButton);
        panel.add(viewEmployeesButton);
        panel.add(addEmployeeButton);
        panel.add(ExitButton);

        //Action when viewDepartmentsButton is Pressed
        viewDepartmentsButton.addActionListener((ActionEvent e) -> {
            System.out.println("Admin Button Pressed");
            viewDepartments();
        });

        //Action when viewEmployeesButton is Pressed
        viewEmployeesButton.addActionListener((ActionEvent e) -> {
            System.out.println("View Employees Button Pressed");
            //viewEmployees();
        });

        //Action when addEmployeesButton is Pressed
        addEmployeeButton.addActionListener((ActionEvent e) -> {
            System.out.println("Add Employee Button Pressed");
            addEmployee();
        });

        //Action when ExitButton is Pressed
        ExitButton.addActionListener((ActionEvent e) -> {
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
    public static int getNumberOfEmployees() {
        // Open file
        File EmployeeFile = new File("files/Employees.txt");

        int records = 0;
        int count = 0;

        /* Read file line by line (One employee takes up 3 lines in a file
        so whenever count = 3 then record increases by 1
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(EmployeeFile))) {
            while (reader.readLine() != null) {
                count++;
                if (count == 3) {
                    records++; //Count records in file
                    count = 0;
                }
            }
        } catch (Exception e) {
            System.err.println("Error Reading " + EmployeeFile);
            e.printStackTrace();
        }

        // Set Number of Customers to value of Records
        numberOfEmployees = records;

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
                        getDepartmentName(), getNumberOfEmployees()});
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
            //Frame.setIconImage(Icon.getImage());
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
        JLabel departmentLabel = new JLabel("Departments");
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

        JRadioButton femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(280, 220, 90, 30);
        femaleRadioButton.setFocusPainted(false);

        ButtonGroup genderButton = new ButtonGroup();
        genderButton.add(maleRadioButton);
        genderButton.add(femaleRadioButton);

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
        processButton.setBounds(200, 420, 200, 50);
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
            String gender = genderButton.getSelection().toString();
            String age = ageField.getText();
            String emailAddress = emailAddressField.getText();
            String phoneNumber = phoneNumberField.getText();
            String department = departmentComboBox.getSelectedItem().toString();

            System.out.println("Process Button Pressed");

            if (check.verifyFieldLengths(idNumber, lastName, emailAddress, phoneNumber)) {
                int Valid = check.verifyFields(idNumber, firstName, lastName, gender, age, phoneNumber, department);

                if (Valid == 0) {
                    System.err.println("Invalid Length for idNumber");
                    JOptionPane.showMessageDialog(panel, "Invalid Length for idNumber",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -1) {
                    System.err.println("idNumber is not numerical");
                    JOptionPane.showMessageDialog(panel, "idNumber is not numerical",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 1) {
                    System.err.println("Invalid number of digits for Phone Number");
                    JOptionPane.showMessageDialog(panel, "Invalid number of digits for Phone Number",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -2) {
                    System.err.println("Phone Number is not numerical");
                    JOptionPane.showMessageDialog(panel, "Phone Number is not numerical",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -3) {
                    System.err.println("Last Name is not only Letters");
                    JOptionPane.showMessageDialog(panel, "Last Name is not only Letters",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 3) {
                    System.err.println("Invalid Area Code");
                    JOptionPane.showMessageDialog(panel, "Invalid Area Code",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 4) {
                    System.err.println("Invalid Prefix for Phone Number");
                    JOptionPane.showMessageDialog(panel, "Invalid Prefix for Phone Number",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == -4) {
                    System.err.println("Phone Number already exists");
                    JOptionPane.showMessageDialog(panel, "Phone Number already exists",
                            "Error", JOptionPane.WARNING_MESSAGE);
                } else if (Valid == 5) {
                    //Set values
                    employee.setIdNumber(idNumber);
                    employee.setLastName(lastName);
                    employee.setEmailAddress(emailAddress);
                    employee.setTelephoneNumber(phoneNumber);
                    //employee.setDepartment(department);
                    //etCreditBalance();

                    try {
                        System.out.println("Writing to File...");

                        //Open file in append mode
                        FileWriter writer = new FileWriter("files/Digicel_Customers.txt", true);
                        writer.write(employee.getIdNumber() + " ");
                        writer.write(employee.getLastName() + "\n");
                        writer.write(employee.getEmailAddress() + "\n");
                        writer.write(employee.getTelephoneNumber() + " ");
                        writer.write(employee.getDepartment() + "\n");
                        writer.close();

                        System.out.println("Customer Information Saved");
                        JOptionPane.showMessageDialog(panel, "Customer Added", "Information",
                                JOptionPane.INFORMATION_MESSAGE);

                        //Reset Fields
                        idField.setText("");
                        lastNameField.setText("");
                        emailAddressField.setText("");
                        phoneNumberField.setText("");
                    } catch (Exception ex) {
                        System.err.println("Could not Save Customer Information");
                        JOptionPane.showMessageDialog(panel, "Could not Save Customer Information",
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

    //Return JPanel
    public JComponent getComponent() {
        return panel;
    }
}
