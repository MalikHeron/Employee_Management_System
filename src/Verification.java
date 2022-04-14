import java.io.File;
import java.util.Scanner;

public class Verification {

    //Verify Field Lengths when adding a customer
    public boolean verifyFieldLengths(String TRN, String LastName, String Address, String TelephoneNumber) {
        boolean Valid = false;
        int Length = TRN.length();
        int Length2 = LastName.length();
        int Length3 = Address.length();
        int Length4 = TelephoneNumber.length();

        if (Length > 0 && Length2 > 0 && Length3 > 0 && Length4 > 0) {
            Valid = true;
        }

        return Valid;
    }

    //Verify idNumber, Last Name and Phone Number when adding a Customer
    public int verifyFields(String idNumber, String firstName, String lastName,
                            String gender, String age, String telephone, String departmentName) {
        int Valid = 0;
        int Index = 0;
        String AreaCode = telephone.substring(0, 3);

        if (idNumber.length() == 9) {
            Valid = 1;

            //Check if idNumber is only digits
            for (int i = 0; i < idNumber.length(); i++) {
                boolean Flag = Character.isDigit(idNumber.charAt(i));

                if (!Flag) {
                    Valid = -1;
                }
            }

            if (telephone.length() == 10 && Valid == 1) {
                Valid = 2;

                //Check if Telephone Number is only digits
                for (int i = 0; i < telephone.length(); i++) {
                    boolean Flag = Character.isDigit(telephone.charAt(i));

                    if (!Flag) {
                        Valid = -2;
                    }
                }

                if (Valid == 2) {
                    Valid = 3;

                    //Check if Last Name is only Letters
                    for (int i = 0; i < lastName.length(); i++) {
                        boolean Flag = Character.isLetter(lastName.charAt(i));

                        if (!Flag) {
                            Valid = -3;
                        }
                    }

                    if (AreaCode.equals("876") && Valid == 3) {
                        Valid = 4;

                        int Prefix = Integer.parseInt(telephone.substring(3, 6));

                        System.out.println("Prefix: " + Prefix);

                        while (Index < 4) {
                            System.out.println("Index: " + Index);
/*
                            if (CompanyPrefix[Index] == Prefix && CompanyID.equals("Digicel")) {
                                System.out.println("Valid Digicel Number");

                                int Exists = verifyPhoneNumber(Telephone, Prefix);

                                if (Exists == 0) {
                                    Valid = 5;
                                } else {
                                    Valid = -4;
                                }

                                break;
                            } else if (CompanyPrefix[Index] == Prefix && CompanyID.equals("Flow")) {
                                System.out.println("Valid Flow Number");

                                int Exists = verifyPhoneNumber(Telephone, Prefix);

                                if (Exists == 0) {
                                    Valid = 5;
                                } else {
                                    Valid = -4;
                                }

                                break;
                            }*/

                            Index++;
                        }
                    }
                }
            }
        }

        return Valid;
    }

    //Verify Customer TelephoneNumber when trying to Add Credit
    /*public int verifyPhoneNumber(String TelephoneNumber) {
        int Exists = 0;

        try {
            int Index = 0;
            String idNumber;
            String firstName;
            String lastName;
            String emailAddress;
            int age;
            String telephone;

            while (Index < 4) {
                System.out.println("Index: " + Index);

                    //Open Files
                    Scanner Read = new Scanner(new File("files/Employees.txt"));

                    while (Read.hasNext()) {
                        idNumber = Read.next();
                        firstName = Read.nextLine();
                        Address = Read.nextLine();
                        Telephone = Read.next();
                        CreditBal = Read.nextDouble();

                        System.out.println(TRN + LName);
                        System.out.println(Address);
                        System.out.println(Telephone + " " + CreditBal);

                        if (Telephone.equals(TelephoneNumber)) {
                            System.out.println("Phone Number Exists for Digicel.");
                            Exists = 1;
                            break;
                        }
                    }

                    //Close file
                    Read.close();
                }

                Index++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Exists;
    }*/

}