/**
 * 
 */
//package i190685;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author ASUS
 *
 */

/**
 * Base class for Passenger class. Stores basic information such as
 * name, dob, age, address, contact, email etc.
 * only has getter and setter functions.
 */
public class PassengerPersonalInfo {
 
    public String firstName;
    public String lastName;
    private int age;
    private Date dateOfBirth;
    private String address;
    private String nationality;
    private long contact;
    private String email;
    
    /**
     * Prompts user for his personal information
     * and stores the information in class objects
     */
    public PassengerPersonalInfo(){
    	// TODO Auto-generated constructor stub
        Scanner cin = new Scanner(System.in);
        String tempAge;
        System.out.print("FIRST NAME: ");
        firstName = cin.nextLine();
        System.out.print("LAST NAME: ");
        lastName = cin.nextLine();
        while(true) {
            System.out.print("AGE: ");
            tempAge = cin.nextLine();
            try {
                age = Integer.parseInt(tempAge);
                if(age < 0 || age > 150) {
                    throw new Exception();
                }
                break;
            }
            catch(Exception e){
                System.err.println("ERROR: Invalid Age!");
            }                
        }    
        SimpleDateFormat sdt = new SimpleDateFormat("MM-dd-yyyy");
        while(true) {
            System.out.print("DATE OF BIRTH(mm-dd-yyyy): ");
            String tempDateOfBirth = cin.nextLine();
            try { 
                this.dateOfBirth= sdt.parse(tempDateOfBirth);
                break;
            } 
            catch (ParseException e) { 
                System.err.println("ERROR: Invalid Date Format!"); 
            }
        }
        //String ch = cin.nextLine();
        System.out.print("ADDRESS: ");
        address = cin.nextLine();
        System.out.print("NATIONALITY: ");
        nationality = cin.nextLine();
        while(true) {
            System.out.print("CONTACT NUMBER: ");
            String tempContactNumber = cin.nextLine();
            try {
            	contact = Long.parseLong(tempContactNumber);
                if(tempContactNumber.length() != 11)
                    throw new Exception();
                break;
            }
            catch(Exception e){
                System.err.println("ERROR: Invalid Contact Number!");
            }                
        }           
    }
    
    /**
     * Parameterized Constructor for Passenger's Personal Information class.
     * @param firstName
     * @param lastName
     * @param age
     * @param dateOfBirth
     * @param address
     * @param nationality
     * @param contactNumber
     * @param email 
     */
    PassengerPersonalInfo(String First_Name, String Last_Name, int Age, Date dob, 
            String Address, String Nationality, long Number, String eID) {  
        this.firstName = First_Name;
        this.lastName = Last_Name;
        this.age = Age;
        this.dateOfBirth = dob;
        this.address = Address;
        this.nationality = Nationality;
        this.contact = Number;
        this.email = eID;
    }        
    
    //Getter Functions
    public String getFirstName() {
        return firstName;            
    }
    public String getLastName() {
        return lastName;            
    }
    public int getAge() {
        return age;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public String getAddress() {
        return address;            
    }
    public String getNationality() {
        return nationality;            
    }
    public long getContact() {
        return contact;
    }
    public String getEmailID() {
        return email;            
    }
    
    //Setter Functions
    public void setFirstName(String First_Name) {
        this.firstName = First_Name;            
    }
    public void setLastName(String Last_Name) {
        this.lastName = Last_Name;            
    }
    public void setAge(int Age) {
        this.age = Age;
    }
    public void setDateOfBirth(Date dob) {
        this.dateOfBirth = dob;
    }
    public void setAddress(String Address) {
        this.address = Address;            
    }
    public void setNationality(String Nationality) {
        this.nationality = Nationality;            
    }
    public void setContact(long Number) {
        this.contact = Number;
    }
    public void setEmailID(String eID) {
        this.email = eID;            
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    
}
