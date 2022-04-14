public class Employee {

    private String idNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String emailAddress;
    private String telephoneNumber;
    private String department;

    // Primary Constructor
    public Employee() {
        //Initialize variables
        idNumber = "";
        firstName = "";
        lastName = "";
        gender = "";
        telephoneNumber = "";
        emailAddress = "";
        age = 0;
    }

    //Initialization of Getters
    public String getIdNumber() {
        return idNumber;
    }

    //Initialization of Setters
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public double getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
