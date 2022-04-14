import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Verification {

    //Verify email address
    public static boolean verifyEmail(String email) {
        String emailRegex = "^[a-zA-Z\\d_+&*-]+(?:\\.[a-zA-Z\\d+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        if (email == null) {
            return false;
        } else {
            return pattern.matcher(email).matches();
        }
    }

    //Verify Field Lengths when adding a customer
    public boolean verifyFieldLengths(String idNumber, String firstName, String lastName,
                                      String gender, String age, String emailAddress, String telephoneNumber) {
        boolean valid = false;
        int length = idNumber.length();
        int length2 = firstName.length();
        int length3 = lastName.length();
        int length5 = emailAddress.length();
        int length6 = telephoneNumber.length();
        int length7 = age.length();

        if (length > 0 && length2 > 0 && length3 > 0 && !gender.equals("") &&
                length5 > 0 && length6 > 0 && length7 > 0) {
            valid = true;
        }

        return valid;
    }

    //Verify idNumber, Last Name and Phone Number when adding a Customer
    public int verifyFields(String idNumber, String firstName, String lastName,
                            int age, String emailAddress, String telephone) {
        int valid = 0;
        String areaCode = telephone.substring(0, 3);

        if (idNumber.length() >= 1) {
            valid = 1;

            //Check if idNumber is only digits
            for (int i = 0; i < idNumber.length(); i++) {
                boolean Flag = Character.isDigit(idNumber.charAt(i));

                if (!Flag) {
                    valid = -1;
                }
            }

            if (telephone.length() == 10 && valid == 1) {
                valid = 2;

                //Check if Telephone Number is only digits
                for (int i = 0; i < telephone.length(); i++) {
                    boolean Flag = Character.isDigit(telephone.charAt(i));

                    if (!Flag) {
                        valid = -2;
                    }
                }

                if (valid == 2) {
                    valid = 3;

                    //Check if First Name is only Letters
                    for (int i = 0; i < firstName.length(); i++) {
                        boolean Flag = Character.isLetter(firstName.charAt(i));

                        if (!Flag) {
                            valid = -3;
                        }
                    }

                    if (valid == 3) {
                        valid = 4;

                        //Check if Last Name is only Letters
                        for (int i = 0; i < lastName.length(); i++) {
                            boolean Flag = Character.isLetter(lastName.charAt(i));

                            if (!Flag) {
                                valid = -4;
                            }
                        }

                        //Check if area code is for jamaica
                        if (areaCode.equals("876") && valid == 4) {
                            int prefix = Integer.parseInt(telephone.substring(3, 6));

                            System.out.println("Prefix: " + prefix);

                            //check if phone number exists
                            int exists = checkPhoneNumber(telephone);

                            if (exists == 0) {
                                //verify email
                                boolean validEmail = verifyEmail(emailAddress);

                                if (validEmail) {
                                    //check if employee is an adult
                                    if (age >= 18) {
                                        exists = checkIdNumber(idNumber);

                                        if (exists == 1) {
                                            valid = -8;
                                        } else {
                                            valid = 5;
                                        }
                                    } else {
                                        valid = -7;
                                    }
                                } else {
                                    valid = -6;
                                }
                            } else {
                                valid = -5;
                            }
                        }
                    }
                }
            }
        }

        return valid;
    }

    //Check if Employee TelephoneNumber already exists
    public int checkPhoneNumber(String telephoneNumber) {
        int exists = 0;

        String idNumber;
        String firstName;
        String lastName;
        String gender;
        String age;
        String emailAddress;
        String telephone;
        String department;

        //Open Files
        Scanner read;
        try {
            read = new Scanner(new File("files/Employees.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (read.hasNext()) {
            idNumber = read.next();
            firstName = read.next();
            lastName = read.next();
            gender = read.next();
            age = read.next();
            emailAddress = read.next();
            telephone = read.next();
            department = read.nextLine();

            System.out.println(idNumber + " " + firstName + " " + lastName + " " + gender + age);
            System.out.println(emailAddress + " " + telephone + department);

            if (telephone.equals(telephoneNumber)) {
                System.out.println("Phone Number already exists.");
                exists = 1;
                read.close();
                break;
            }
        }

        //Close file
        read.close();

        return exists;
    }

    //Check if Employee TelephoneNumber already exists
    public int checkIdNumber(String IDNumber) {
        int exists = 0;

        String idNumber;
        String firstName;
        String lastName;
        String gender;
        String age;
        String emailAddress;
        String telephone;
        String department;

        //Open Files
        Scanner read;
        try {
            read = new Scanner(new File("files/Employees.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (read.hasNext()) {
            idNumber = read.next();
            firstName = read.next();
            lastName = read.next();
            gender = read.next();
            age = read.next();
            emailAddress = read.next();
            telephone = read.next();
            department = read.nextLine();

            System.out.println(idNumber + " " + firstName + " " + lastName + " " + gender + age);
            System.out.println(emailAddress + " " + telephone + department);

            if (idNumber.equals(IDNumber)) {
                System.out.println("ID Number already exists.");
                exists = 1;
                read.close();
                break;
            }
        }

        //Close file
        read.close();

        return exists;
    }
}