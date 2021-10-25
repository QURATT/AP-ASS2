/**
 * @author ASUS
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Passenger Class inherited from Passenger's Personal Information Class
 * Having Attributes such as the Seat Numbers in Flight.
 * Also reserves a seat for the Passenger.
 */
public final class Passengers extends PassengerPersonalInfo{
	private static final String NULL = null;


	/**
	 * 
	 */
	public Passengers() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    private int rows;                        //stores Row of flight seats
    public char cols;  						//stores Columns of flight seats
    
    
    /**
     * 
     * @param ReservedFlight
     */
    Passengers(Flights ReservedFlight) {
        ReservedFlight.printSeats();            //prints the seat plan of flight to console. '_' represents vacancy.
                                                //'R' represents Reserved.
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Enter Seat you want to Reserve(row-column(1-A)): ");
            String character = input.nextLine();
            try{
                String[] seat = character.split("-");            
                rows = Integer.parseInt(seat[0]);
                cols = seat[1].charAt(0);
                if(rows < 1 || rows > 10 || Character.toLowerCase(cols) < 'a' || Character.toLowerCase(cols) > 'j')
                    throw new Exception();
                rows--;
                break;
            }
            catch(Exception e) {
                System.err.println("Error! Invalid Seat Format!");
            }
        }
        
    	ReservedFlight.reserveSeats(rows, cols);
        
    	/*if(ReservedFlight.reserveSeats(rows, cols)==character)
        {
        	//ReservedFlight.printSeats();            //prints the seat plan of flight to console. '_' represents vacancy.
            //'R' represents Reserved.
			Scanner input1 = new Scanner(System.in);
			while(true) {
				System.out.println("Enter Seat again you want to Reserve(row-column(1-A)): ");
				String character = input1.nextLine();
				try{
				String[] seat = character.split("-");            
				rows = Integer.parseInt(seat[0]);
				cols = seat[1].charAt(0);
				if(rows < 1 || rows > 10 || Character.toLowerCase(cols) < 'a' || Character.toLowerCase(cols) > 'j')
				throw new Exception();
				rows--;
				break;
				}
				catch(Exception e) {
				System.err.println("Error! Invalid Seat Format!");
				}
			}
        }*/
        System.out.println("Your Seat has been Reserved.");
        System.out.println("Also your Ticket for Flight has been generated!!");
        generateTicket(ReservedFlight);           //calls a function which creates a ticket in the form of a text file
        
    }
 
    
    
    /**
     * This function generates Economy ticket for the user in the form of 
     * text file which contains relevant flight information
     * @param ReservedFlight 
     */
    public void generateTicket(Flights reservedFlight) {
        File ticket = new File("EconomyTicketGenerated.txt");
        try {
            PrintWriter cout = new PrintWriter(ticket, "UTF-8");
            cout.println("#################################################################################");
            String tempString = reservedFlight.getAirlines().toUpperCase();
            int fieldWidth1 = 40+tempString.length()/2;
            int fieldWidth2 = 40-tempString.length()/2;
            String arg = "|%" + Integer.toString(fieldWidth1) + "s%" + Integer.toString(fieldWidth2) + "s|%n";
            cout.printf(arg, tempString, "");
            tempString = "FLIGHT TICKET";
            fieldWidth1 = 40+tempString.length()/2;
            fieldWidth2 = 40-tempString.length()/2;
            arg = "|%" + Integer.toString(fieldWidth1) + "s%" + Integer.toString(fieldWidth2) + "s|%n";
            cout.printf(arg, tempString, "");
                                                                                      
            cout.println("|                                                                                |");
            tempString = this.lastName.toUpperCase() + ", " + this.firstName.toUpperCase();
            cout.printf("|%-15s%-65s|%n","NAME:", tempString);
            cout.printf("|%-15s%-49s%Ta %<td %<Tb %<tY |%n","FLIGHT:", reservedFlight.getAirlines().toUpperCase(), reservedFlight.getDepartureDate());
            cout.printf("|%-15s%-50s  %td %<Tb %5s |%n","DEPARTURE:",  reservedFlight.getFrom().toUpperCase(), reservedFlight.getDepartureDate(), reservedFlight.getDepartureTime());
            cout.printf("|%-15s%-50s  %td %<Tb %5s |%n","ARRIVAL:",  reservedFlight.getTo().toUpperCase(), reservedFlight.getArrivalDate(), reservedFlight.getArrivalTime());
            cout.printf("|%-15s%02d-%s%-60s |%n","SEAT: ", (rows+1), Character.toString(cols).toUpperCase(), "");
            cout.printf("|%-15s%-65s|%n"," ", "RESERVATION CONFIRMED, ECONOMY");
            cout.println("|                                                                                |");
            cout.println("#################################################################################");
            
            cout.close();
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(AirlineReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(AirlineReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * This function generates a Business ticket for the user in the form of 
     * text file which contains relevant flight information
     * @param ReservedFlight 
     */
    public void generateBusinessTicket(Flights reservedFlight) {
        File ticket = new File("BusinessTicketGenerated.txt");
        try {
            PrintWriter cout = new PrintWriter(ticket, "UTF-8");
            cout.println("#################################################################################");
            String tempString = reservedFlight.getAirlines().toUpperCase();
            int fieldWidth1 = 40+tempString.length()/2;
            int fieldWidth2 = 40-tempString.length()/2;
            String arg = "|%" + Integer.toString(fieldWidth1) + "s%" + Integer.toString(fieldWidth2) + "s|%n";
            cout.printf(arg, tempString, "");
            tempString = "FLIGHT TICKET";
            fieldWidth1 = 40+tempString.length()/2;
            fieldWidth2 = 40-tempString.length()/2;
            arg = "|%" + Integer.toString(fieldWidth1) + "s%" + Integer.toString(fieldWidth2) + "s|%n";
            cout.printf(arg, tempString, "");
                                                                                      
            cout.println("|                                                                                |");
            tempString = this.lastName.toUpperCase() + ", " + this.firstName.toUpperCase();
            cout.printf("|%-15s%-65s|%n","NAME:", tempString);
            cout.printf("|%-15s%-49s%Ta %<td %<Tb %<tY |%n","FLIGHT:", reservedFlight.getAirlines().toUpperCase(), reservedFlight.getDepartureDate());
            cout.printf("|%-15s%-50s  %td %<Tb %5s |%n","DEPARTURE:",  reservedFlight.getFrom().toUpperCase(), reservedFlight.getDepartureDate(), reservedFlight.getDepartureTime());
            cout.printf("|%-15s%-50s  %td %<Tb %5s |%n","ARRIVAL:",  reservedFlight.getTo().toUpperCase(), reservedFlight.getArrivalDate(), reservedFlight.getArrivalTime());
            cout.printf("|%-15s%02d-%s%-60s |%n","SEAT: ", (rows+1), Character.toString(cols).toUpperCase(), "");
            cout.printf("|%-15s%-65s|%n"," ", "RESERVATION CONFIRMED, ECONOMY");
            cout.println("|                                                                                |");
            cout.println("#################################################################################");
            
            cout.close();
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(AirlineReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(AirlineReservationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
