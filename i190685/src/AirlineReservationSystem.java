/**
 * 
 */
//package i190685;

/**
 * @author ASUS
 *
 */  
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 
 
 																																																																																																																																																																																																																																																																																																		
public class AirlineReservationSystem {
 
	/**
	 * 
	 */
	public AirlineReservationSystem() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Print the main menu
     */
    static void printMainMenu() {
        System.out.println("\nMain Menu\n");
        System.out.println("1. Flight Schedule");
        System.out.println("2. Make a reservation");
        System.out.println("3. Cancel a reservation");
        System.out.println("4. Quit");
        System.out.println();
    }
 
    /**
     * Get the user's choice off the main menu
     */
    static int getMainMenuChoice() {
        int choice;             // choice entered
        boolean valid = false;  // is choice valid?
        Scanner cin = new Scanner(System.in); 
        do {
            System.out.print("===> ");
            choice = cin.nextInt();
 
            if (1 <= choice && choice <= 4) {
                valid = true;
            } else {
                System.out.println("Invalid choice.");
            }
        } while (!valid);
 
        return choice;
    }
    
    
    static void printClassMenu() {
        System.out.println("\nClass Menu\n");
        System.out.println("1. Business Class");
        System.out.println("2. Economy Class");
       // System.out.println("3. Cancel a reservation");
        System.out.println("4. Quit");
        System.out.println();
    }
 
    /**
     * Get the user's choice off the class menu
     */
    static int getClassChoice() {
        int choice;             // choice entered
        boolean valid = false;  // is choice valid?
        Scanner cin = new Scanner(System.in); 
        do {
            System.out.print("===> ");
            choice = cin.nextInt();
 
            if (1 <= choice && choice <= 3) {
                valid = true;
            } else {
                System.out.println("Invalid choice.");
            }
        } while (!valid);
 
        return choice;
    }
 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 boolean valid= false;
		 int choice = 0;
		 int classs = 0;
		 Scanner cin = new Scanner(System.in);              //initialising scanner
		 SearchFlights.populateEconomyFlightList();
		 
       
		System.out.println("\t\t\tWELCOME TO FLIGHT RESERVATION CENTER");
		 do {
			 	printMainMenu();
	            try {
	            	System.out.println("\t\t\tSELECT FROM MENU");
					choice = getMainMenuChoice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}       // choice off of the main menu
	         
	        switch(choice)
	        {
	        	case 1:
	        		//the following function call reads the flight data from a file and creates an arraylist of flight objects
	        	 try {
	               SearchFlights test1 = new SearchFlights();         //creates a new search object
		            test1.displayResults();                           //prompts the user to enter deails such as destination, departure date etc.
		           // boolean found1 = test1.getSearchResults();        //creates a list of flights available using search details entered by user. returns false if no flights could be found
	        	 }catch(Exception e) 
	        	 {
	        		 
	        	 }
	        	break;
	        	
		        case 2:
		        	boolean done= false;
		        	do {
		        		printClassMenu();
			            try {
			            	System.out.println("\t\t\tSELECT FROM MENU");
							classs = getClassChoice();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
		        		switch(classs)
		        		{
		        		case 1:
				        int ans = 1;                                      //used to control flow of program
				        while(ans!=0) {
				            SearchFlights test = new SearchFlights();         //creates a new search object
				            test.getSearchData();                           //prompts the user to enter deails such as destination, departure date etc.
				            boolean found = test.getSearchResults();      //creates a list of flights available using search details entered by user. returns false if no flights could be found
				            if(found) {
				                System.out.println("\n\t\t\tSEARCH RESULTS\n");
				                test.displayResults();                      //displays the results of the search to the user in the form of table
				                
				                System.out.print("Enter the index number of flight you wish to book (1-" 
				                        + (test.getList().size())+ "): ");        //prompts the user to select a flight from results
				                int index = cin.nextInt()-1;
				                
				                if(index >= 0 && index < test.getList().size()) {   //if the index entered is valid
				                    Flights reservedFlight = test.getList().get(index);      //stores the flight selected by user in flight object
			
				                    if(reservedFlight.getAvailableSeats() <= 0){            //if all the seats are occupied then
				                        System.out.println("Sorry! Not enough seats.");
				                        System.out.print("\nDo you want to search again?? (Enter 1 to continue or 0 to exit): ");
				                        ans = cin.nextInt(); 
				                    }
				                    else {                                                  
				                        System.out.print("\nSELECTED FLIGHT: "+(index+1)+". ");
				                        reservedFlight.display();                           //displays the flight selected by user
				                        System.out.println("\n\t\t\tENTER PASSENGER INFORMATION");
				                        Passengers passenger = new Passengers(reservedFlight);        //creates a passenger object to make reservation
				                        ans = 0;
				                    }
				                }
				                else {
				                    System.out.print("ERROR: invalid index");
				                    System.out.print("\nDo you want to search again?? (Enter 1 to continue or 0 to exit): ");
				                    ans = cin.nextInt();
				                }
				            }
				            else    //notifies the user that no flights were found and asks if he/she wants to perform another search
				            {
				                System.out.print("Sorry! There are no flights flying from " + test.getFrom() + " to " + test.getTo() + " on ");
				                System.out.printf("%tD%n", test.getDepartureDate());
				                System.out.print("\nDo you want to search again?? (Enter 1 to continue or 0 to exit): ");
				                ans = cin.nextInt();               
				            }
				        }
				        break;
		        	case 2:
		        		 SearchFlights.populateBusinessFlightList();
		        		int ans1 = 1;                                      //used to control flow of program
				        while(ans1!=0) {
				            SearchFlights test = new SearchFlights();         //creates a new search object
				            test.getSearchData();                           //prompts the user to enter deails such as destination, departure date etc.
				            boolean found = test.getSearchResults();      //creates a list of flights available using search details entered by user. returns false if no flights could be found
				            if(found) {
				                System.out.println("\n\t\t\tSEARCH RESULTS\n");
				                test.displayResults();                      //displays the results of the search to the user in the form of table
				                
				                System.out.print("Enter the index number of flight you wish to book (1-" 
				                        + (test.getList().size())+ "): ");        //prompts the user to select a flight from results
				                int index = cin.nextInt()-1;
				                
				                if(index >= 0 && index < test.getList().size()) {   //if the index entered is valid
				                    Flights reservedFlight = test.getList().get(index);      //stores the flight selected by user in flight object
			
				                    if(reservedFlight.getAvailableSeats() <= 0){            //if all the seats are occupied then
				                        System.out.println("Sorry! Not enough seats.");
				                        System.out.print("\nDo you want to search again?? (Enter 1 to continue or 0 to exit): ");
				                        ans1 = cin.nextInt(); 
				                    }
				                    else {                                                  
				                        System.out.print("\nSELECTED FLIGHT: "+(index+1)+". ");
				                        reservedFlight.display();                           //displays the flight selected by user
				                        System.out.println("\n\t\t\tENTER PASSENGER INFORMATION");
				                        Passengers passenger = new Passengers(reservedFlight);        //creates a passenger object to make reservation
				                        ans1 = 0;
				                    }
				                }
				                else {
				                    System.out.print("ERROR: invalid index");
				                    System.out.print("\nDo you want to search again?? (Enter 1 to continue or 0 to exit): ");
				                    ans1 = cin.nextInt();
				                }
				            }
				            else    //notifies the user that no flights were found and asks if he/she wants to perform another search
				            {
				                System.out.print("Sorry! There are no flights flying from " + test.getFrom() + " to " + test.getTo() + " on ");
				                System.out.printf("%tD%n", test.getDepartureDate());
				                System.out.print("\nDo you want to search again?? (Enter 1 to continue or 0 to exit): ");
				                ans1 = cin.nextInt();               
				            }
				        }
		        	break;
		        	case 3:
		        		done= true;
		        	
		        	default:
		        		System.out.println("INVALID!!");
		        	}
		        } while(!done);
		        System.out.println("\n\t\t\tTHANK YOU FOR USING OUR SYSTEM!\n");        //Ending message
		        break;
		        
		        case 3:
		        //SearchFlights test = new SearchFlights();         //creates a new search object
		            //test.getSearchData(); 
		        	System.out.print("Enter the index number of flight you wish to Cancel" );        //prompts the user to select a flight from results
	                int index = cin.nextInt()+1;
	                
	                /*if(index >= 0 && index < test.getList().size()) {   //if the index entered is valid*/
	                    Flights bookedSeats= new Flights();      //stores the flight selected by user in flight object
	               
	                   try {
						if(bookedSeats.getCancelledSeats())  
						{
							 
						        System.out.println("Seat Cancelled");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                        
		        break;
		        
		        case 4:
		        	valid=true;
		        break;
		        default:
		        	System.out.println("INVALID CHOICE SELECTION!!");        //Ending message
	        }
	        
		 }while(!valid); 
	 }  
	   
}
