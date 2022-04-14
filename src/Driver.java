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
            File employeeFile = new File("files/Employees.txt");

            if (employeeFile.exists()) {
                System.out.println(employeeFile + " Exists.");
            } else {
                if (employeeFile.createNewFile()) {
                    System.out.println(employeeFile + " Created.");
                } else {
                    System.err.println(employeeFile + " Creation Failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
