
/*
 * Author : Cameron Vandermeersch
 * File: Airport.java
 */
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Airport {
    private ArrayList<String> planesFlightRecord;
    private PriorityQueue<String> takeOffList;
    private Queue<String> landingList;

    public Airport() {
        planesFlightRecord = new ArrayList<String>();
        takeOffList = new PriorityQueue<String>();
        landingList = new LinkedList<String>();
    }

    // addTakeOff: It receives a flight-number as a String and updates the
    // corresponding list.
    public void addTakeOff(String flightNum) {
        takeOffList.add(flightNum);
    }

    // addLanding: It receives a flight-number as a String and updates the
    // corresponding list.
    public void addLanding(String flightNum) {
        landingList.add(flightNum);
    }

    // handleNextAction: It checks the landing and take-off lists and conducts one
    // single
    // operation. It also returns a String showing the conducting operation, as you
    // can
    // see in the expected output.
    public String handleNextAction() {
        String planeFlightNumber = "";
        String planeDirectionMotion = "";
        if (!landingList.isEmpty()) {
            planeFlightNumber = landingList.poll();
            planesFlightRecord.add("Flight " + planeFlightNumber + " Is Landed.");
            planeDirectionMotion = "Flight " + planeFlightNumber + " Is Landing.";
        } else if (!takeOffList.isEmpty()) {
            planeFlightNumber = takeOffList.poll();
            planesFlightRecord.add("Flight " + planeFlightNumber + " Is Taking Off.");
            planeDirectionMotion = "Flight " + planeFlightNumber + " Is Taking Off.";
        } else {
            planeDirectionMotion = "No Plane Is Waiting To Land Or Take Off.";
        }
        return planeDirectionMotion;
    }

    // waitingPlanes: It returns a String showing the list of all the waiting
    // airplanes for landing/take-off, as you can see in the expected output.
    public String waitingPlanes() {
        String waitingLine = "";
        if (!landingList.isEmpty()) {
            waitingLine += "Planes Waiting For Landing: \n ";
            waitingLine += "---------------------\n";
            for (String planeList : landingList) {
                waitingLine += planeList + "\n";
            }
        } else if (!takeOffList.isEmpty()) {
            waitingLine += "Planes Waiting For Take-Off: \n ";
            waitingLine += "---------------------\n";
            for (String plane : takeOffList) {
                waitingLine += plane + "\n";
            }
        } else {
            return "No Plane Is Waiting To Landing Or Take-Off Queues.";
        }
        return waitingLine;
    }

    // log: It returns a String showing the list of all the operations already
    // conducted, as you can see in the expected output.
    // Note: The sooner an airplane landed/taken off, the later it must appear in
    // the log.

    public String log() {
        if (planesFlightRecord.isEmpty()) {
            return "No Activity Exists";
        } else {
            String planeLog = "List Of The Landing/Taking-Off Activities:\n";
            planeLog += "--------------------------------------\n";
            for (int i = planesFlightRecord.size() - 1; i >= 0; i--) {
                planeLog += planesFlightRecord.get(i) + "\n";
            }
            return planeLog;
        }
    }

    // Overloaded log: It receives the name of a text file as a String and writes
    // the same output
    // of the above log function into the text file instead of showing it on the
    // screen.
    // The sooner an airplane lands/is taken off, the later it must appear in the
    // log.
    public void log(String fileName) {
        System.out.println("Writing The Airport Log To The File AirportLog.txt");
        try {
            FileWriter outputToFile = new FileWriter(fileName);
            PrintWriter writerToFile = new PrintWriter(outputToFile);
            for (int i = 0; i < planesFlightRecord.size(); i++) {
                writerToFile.println(planesFlightRecord.get(i));
            }
            System.out.println("Done.");
            outputToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}