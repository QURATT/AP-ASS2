/**
 * 
 */
//package i190685;

import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.Date;

 

/**
 * @author ASUS
 *
 */
public class Flights{
	private String to;                      //Arrival airport
	private String from;                    //Departure airport
	private Date departureDate;             
	private Date arrivalDate;
	private char[][] seats;                 //array representing seat planof the plane. '_' signifies vacancy. 'R' signifies reserved
	
	//Accessor methods for flight class
	public String getAirlines() {
	    return airlines;
	}
	/*The following class is a class inside the flight class.
	It makes it easier to display/access time information*/
	private class Time {
	    private int hour;               //stores hour in 24 hour format
	    private int minutes;            //stores minutes
	    
	    //default constructor of Time class
	    public Time() {
	        hour = 0;
	        minutes = 0;
	    }
	    //parameterised constructor
	    public Time(int hour, int minutes) {
	        this.hour = hour;
	        this.minutes = minutes;
	    }
	    //returns the hour in 24 hour fromat
	    public int getHour() {
	        return hour;
	    }
	    //returns minutes
	    public int getMinutes() {
	        return minutes;
	    }
	    
	    //mutator function to set time to passed values
	    public void setTime(int hour, int minutes) {
	        this.hour = hour;
	        this.minutes = minutes;
	    }
	    //prints time in 24 hour format
	    public void printTime() {
	        System.out.printf("%02d:%02d", hour, minutes);
	    }
	    //prints time in 12 hour format
	    public void printTime12() {
	        int temp = hour%12;
	        String ch;
	        if (hour/12 == 1)
	            ch = "PM";
	        else
	            ch = "AM";
	        System.out.printf("%02d:%02d %s", temp, minutes, ch);
	    }
	    
	}
	
	private Time departureTime;             //Time object storing departure time
	private Time arrivalTime;               //time object storing arrival time of flight
	private String airlines;
	private int duration;                   //stores flight duration
	private int number;                     //stores flight number
	private int capacity;                   //stores maximum capacity of the plane
	private int bookedSeats;                //stores the number of seats booked
	private boolean cancelledSeats;                //stores the number of seats Cancelled
	private double cost;                    //stores the cost of a flight. all seats are of the same cost. No premium class
	
	//Default constructor. Stores default values as flight data
	public Flights() {
	    to = "KARACHI";
	    from = "ISLAMABAD";
	    departureDate = new Date();
	    arrivalDate = new Date();
	    departureTime = new Time();
	    arrivalTime = new Time();
	    duration = 0;
	    airlines = "PIA";
	    number = 1;
	    capacity = 100;
	    bookedSeats=0;
	    cancelledSeats= true;
	    cost = 100.0;
	    
	    seats = new char[10][10];
	    
	    for(int i = 0; i < 10; i++)
	        for(int j = 0; j < 10; j++)
	            seats[i][j] = '_';
	}
	
	public Flights(int number, String from, String to, String departureDateText, 
	        String departureTime, String arrivalDateText,String arrivalTime,
	        double cost, String airlines, int capacity) {
	    
	    this.to = to;
	    this.from = from;
	    
	    SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
	    try { 
	        this.departureDate = sdt.parse(departureDateText);
	       
	        this.arrivalDate = sdt.parse(arrivalDateText);
	    } 
	    catch (ParseException e) { 
	        System.out.println("Unparseable using " + sdt);
	        
	    }
	    
	    String[] parts = departureTime.split(":");
	    int hour = Integer.parseInt(parts[0]);
	    int minutes = Integer.parseInt(parts[1]);            
	    this.departureTime = new Time(hour, minutes);
	    
	    parts = arrivalTime.split(":");
	    hour = Integer.parseInt(parts[0]);
	    minutes = Integer.parseInt(parts[1]);
	    this.arrivalTime = new Time(hour, minutes);
	    
	    //this.duration = ;
	    this.cost = cost;
	    this.airlines = airlines;
	    this.number = number;
	    this.capacity = capacity;
	    this.bookedSeats=0;
	    
	    seats = new char[10][10];
	    
	    for(int i = 0; i < 10; i++)
	        for(int j = 0; j < 10; j++)
	            seats[i][j] = '_';
	}
	
	public String getTo() {
	    return to;
	}
	public String getFrom() {
	    return from;
	}
	public Date getDepartureDate() {
	    return departureDate;
	}
	public Date getArrivalDate() {
	    return arrivalDate;
	}
	public String getDepartureTime() {
	    String time = departureTime.getHour()+":"+departureTime.getMinutes();
	    return time;
	}
	public String getArrivalTime() {
	    String time = arrivalTime.getHour()+":"+arrivalTime.getMinutes();
	    return time;
	}
	public int getBookedSeats(){
	    return bookedSeats;
	}
	public boolean getCancelledSeats(){
	    return cancelledSeats;
	}
	public int getAvailableSeats(){
	    return capacity-bookedSeats;
	}
	public void reserveSeats(int row, char col) {
	    this.bookedSeats--;
	    if(seats[row][(int)Character.toUpperCase(col)-65]=='R')
	        System.out.println("Error: This seat is Reserved.");
	    else {
	        seats[row][(int)Character.toUpperCase(col)-65]='R';
	        printSeats();
	    }
	}
	
	public void cancelSeats(int row, char col) {
	    this.bookedSeats++;
	    if(seats[row][(int)Character.toUpperCase(col)-65]=='R')
	        System.out.println("Error: This seat is Cancelled.");
	    else {
	        seats[row][(int)Character.toUpperCase(col)-65]='R';
	        printSeats();
	    }
	}
	
	public void display() {
	    System.out.printf("%-20s | %-5s  %tB %<te, %<tY ",airlines, from, departureDate);
	    departureTime.printTime();
	    System.out.printf(" \t-->\t%-5s  %tB %<te, %<tY ", to, arrivalDate);
	    arrivalTime.printTime();
	    System.out.printf("\t$%.2f%n", cost);
	}
	
	public void printSeats() {
	    System.out.println("\n\t\tRESERVE A SEAT");
	    System.out.print("\n\t    A B C    D E F G    H I J\n\t");
	    for(int i = 0; i < 10; i++) {
	        System.out.printf("%2d  ",(i+1));
	        for(int j = 0; j < 10; j++) {
	            if(j == 3 || j == 7)
	                System.out.print("   ");
	            System.out.print(seats[i][j] + " ");
	        }
	        System.out.print("\n\t");
	    }
	    System.out.printf("%n%n");
	}
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
	
	
