public class Employee {

    private String idNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String emailAddress;
    private String telephoneNumber;
    private int areaCode;
    private int prefix;
    private int serialNumber;
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
        areaCode = 0;
        prefix = 0;
        serialNumber = 0;
    }

    //Initialization of Getters
    public String getIdNumber() {
        return idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public double getAge() {
        return age;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public int getPrefix() {
        return prefix;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getDepartment() {
        return department;
    }

    //Initialization of Setters
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void getGender(String gender) {
        this.gender = gender;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
