package com.company;


public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        String s[] = m.csdsConverter("015086-0A110F018C02-This team sucked.");
    }

    /**
     * A method to convert a Compressed Scouting Data String into a String Array
     * @param csds The data string to be converted.
     * @return An array with the following values 0:Comments 1:Match Number 2:Team Number 3-9:null 10+:Events
     */
    public String[] csdsConverter(String csds) {
        String[] i = new String[1000];
        i[1] = csds.substring(0, 2); //Match Number
        i[2] = csds.substring(2,6);  //Team Number

        String preEvents = csds.substring(7, csds.length()); //Gets the string starting with the events
        String events[] = preEvents.split("-", 2); //Splits into events and comments
        i[0] = events[1]; //Comments
        String eventsString = events[0]; //Events
        int j;

        for(j = 0; j<(eventsString.length()/4); j++) {
            String pre = eventsString.substring((j*4) + 2, (j*4) + 4); //Gets the second two digits of every four to get event codes
            i[10 + j] = pre; //Event codes
        }

        //Trims the array to the minimum size.
        String[] k = new String[10+j];
        for (int m = 0; m < 10+j; m++) {
            k[m] = i[m];
        }

        //Note that I keep 3-9 as null so that the event 0 = k[10] and 1 = k[11] instead of 0 = k[3] and 1 = k[4]
        return k;
    }
}
