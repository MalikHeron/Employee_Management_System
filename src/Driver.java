import javax.swing.*;
import java.io.File;

public class Driver {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//match system feel
        } catch (Exception e) {
            e.printStackTrace();
        }

        InitializeFiles();
        new MainScreen().setVisible(true);//display window
    }

    //Check if files exist, if not they are created
    private static void InitializeFiles() {
        try {
            File EmployeeFile = new File("files/Employees.txt");

            if (EmployeeFile.exists()) {
                System.out.println(EmployeeFile + " Exists.");
            } else {
                if (EmployeeFile.createNewFile()) {
                    System.out.println(EmployeeFile + " Created.");
                } else {
                    System.err.println(EmployeeFile + " Creation Failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
