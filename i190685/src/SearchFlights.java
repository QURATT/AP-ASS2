/**
 * 
 */
//package i190685;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
 
/**
 * @author ASUS
 *
 */

/**
 * The following class is a class for query objects for searching flights
 * It has source, destination, and flight date as its data members
 * The class has member functions which get data from user, search flights 
 * based on that information, store it in an arraylist, and display the results.
 */
public class SearchFlights {
    String from;                            //departure airport
    String to;                              //arrival flight
    Date departureDate;                     //departure date
    ArrayList<Flights> searchResults;        //This arrayList stores flights from the user search
        
    //returns arrival city
    public String getTo() {
        return to;
    }
    //returns departure city
    public String getFrom() {
        return from;
    }
    /**
     * returns the date of flight Departure
     * @return 
     */
    public Date getDepartureDate() {
        return departureDate;
    }
    /**
     * returns the list of flight results from the user search
     * @return 
     */
    public ArrayList<Flights> getList() {
        return searchResults;
    }
    /**
     * traverses the list containing data for all the flights
     * and displays it to the console
     */
    public SearchFlights() {
        System.out.println("\nList of all the flights:\n");
        System.out.println("AIRLINES             | FROM   DEPARTURE DATE/TIME       -->     TO     ARRIVAL DATE/TIME        PRICE PER ADULT");
    System.out.println("------------------------------------------------------------------------------------------------------------");
        
    for(int i = 0; i < flightList.size(); i++)
        flightList.get(i).display();        
    }
    /**
     * Mutator function to set Departure city
     * @param from 
     */
    public void setFrom(String from) {
        this.from = from;
    }
    /**
     * Mutator function to set arrival city
     * @param to 
     */
    public void setTo(String to) {
        this.to = to;
    }
    /**
     * Mutator function to set date of departure.
     * @param departureDateText 
     */
    public boolean setDepartureDate(String departureDateText) {
        SimpleDateFormat sdt = new SimpleDateFormat("MM-dd-yyyy");
        try { 
            this.departureDate = sdt.parse(departureDateText);
            return true;
        } 
        catch (ParseException e) { 
            System.err.println("ERROR: Invalid Date Format!");
            return false;
        }
    }
    /**
     * Prompts user for City of Departure and Arrival and
     * date on which the user wishes to travel.
     * Then the function traverses the arrayList containing flight data
     * and creates a new list which match the user's search parameters.
     */
    public void getSearchData() {
        Scanner cin = new Scanner(System.in);               //iniatilizing scanner
        System.out.println("\n\n\t\tSEARCH FLIGHTS");
        System.out.print("FROM: ");                         //prompts user for city of departure
        from = cin.next();
        System.out.print("TO: ");                           //prompts user for city of arrival
        to = cin.next();
        System.out.print("DEPARTURE(MM-DD-YYYY): ");        //prompts user for Date of departure
        String tempDate = cin.next();
        while(!setDepartureDate(tempDate)) {
            System.out.print("DEPARTURE(MM-DD-YYYY): ");        //prompts user for Date of departure
            tempDate = cin.next();            
        }           
    }
    
    static ArrayList<Flights> flightList= new ArrayList<>();
    
    /**
     * returns a list of flight objects containing information of flights
     * which match the user's search.
     * @return 
     */
    public boolean getSearchResults() {
        searchResults = new ArrayList<>();           
        
        for(int i = 0; i < flightList.size(); i++) {
            if(flightList.get(i).getFrom().equalsIgnoreCase(from) && flightList.get(i).getTo().equalsIgnoreCase(to)) {
                if(flightList.get(i).getDepartureDate().equals(departureDate)) {
                    searchResults.add(flightList.get(i));               
                }
            }
        }
        
        if(searchResults.isEmpty())                
            return false;
        else
            return true;
    }

    /**
     * Prints the results of the user's search on to console
     */
    public void displayResults() {
        for(int i = 0; i < searchResults.size(); i++) {
                System.out.print((i+1) + ". ");
                searchResults.get(i).display();                //calls the display function of flight object to print info
        }                
    }
    
    static void populateEconomyFlightList() {
        String fileName = "C:\\Users\\ASUS\\eclipse-workspace\\i190685\\src\\economyflights.txt";                     //file containg flight data
        File file = new File(fileName);
        try {
            Scanner fin = new Scanner(file);                //connects the scanner to the file flight.txt
            
            /*the following loop reads from the file and parses data into flight objects
            by creating a new object in each iteration and adding it to an arraylist*/
            while(fin.hasNext()) {
                int number = fin.nextInt();                 //flight numer
                String from = fin.next();                   //Departure airport
                String to = fin.next();                     //Arrival airport

                String departureDateText = fin.next();      //departure date as STring
                String departureTime = fin.next();          //Departure Time as String
                String arrivalDateText = fin.next();        //Arrival Date as String
                String arrivalTime = fin.next();            //Arrival Time as String

                double cost = fin.nextDouble();             //cost of the flight
                String airlines = fin.next();               //name of airlines

                int capacity = fin.nextInt();               //maximum capacity of the flight
                
                /*the following statement passes the data read from file as arguments into
                parameterised constructor of the flight object*/
                Flights flight = new Flights(number, from, to, departureDateText, 
                        departureTime, arrivalDateText, arrivalTime, cost, airlines, capacity);
                flightList.add(flight);                     //appends the flight object to ArrayList
            }
            
        } catch (FileNotFoundException ex) {                //If the file could not be found
            //Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Flight data file not found!");
        }
         
    }
    
    static void populateBusinessFlightList() {
        String fileName = "C:\\Users\\ASUS\\eclipse-workspace\\i190685\\src\\businessflights.txt";                     //file containg flight data
        File file = new File(fileName);
        try {
            Scanner fin = new Scanner(file);                //connects the scanner to the file flight.txt
            
            /*the following loop reads from the file and parses data into flight objects
            by creating a new object in each iteration and adding it to an arraylist*/
            while(fin.hasNext()) {
                int number = fin.nextInt();                 //flight numer
                String from = fin.next();                   //Departure airport
                String to = fin.next();                     //Arrival airport

                String departureDateText = fin.next();      //departure date as STring
                String departureTime = fin.next();          //Departure Time as String
                String arrivalDateText = fin.next();        //Arrival Date as String
                String arrivalTime = fin.next();            //Arrival Time as String

                double cost = fin.nextDouble();             //cost of the flight
                String airlines = fin.next();               //name of airlines

                int capacity = fin.nextInt();               //maximum capacity of the flight
                
                /*the following statement passes the data read from file as arguments into
                parameterised constructor of the flight object*/
                Flights flight = new Flights(number, from, to, departureDateText, 
                        departureTime, arrivalDateText, arrivalTime, cost, airlines, capacity);
                flightList.add(flight);                     //appends the flight object to ArrayList
            }
            
        } catch (FileNotFoundException ex) {                //If the file could not be found
            //Logger.getLogger(FlightReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Flight data file not found!");
        }
         
    }
    
}

 