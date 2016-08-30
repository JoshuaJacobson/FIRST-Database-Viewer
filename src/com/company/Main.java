package com.company;


public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        String s[] = {"015086-0A110F018C02-This team sucked.", "020666-0A140B011B02-Nope."};
        String g[][] = m.batchCsdsConverter(s);
        for (int z = 0; z < g.length; z++) {
            String x[] = g[z];
            for(int y = 0; y < x.length; y++) {
                System.out.println(x[y]);
            }
        }
    }

    public String[] eventsToJoEvent(String[] events) {
        String[] r = new String[95]; //String to return

        r[0] = events[1]; //Match Number
        r[1] = events[2]; //Team Number

        //Converts the data from the original String array to an Integer array for easier processing and readability.
        Integer[] source = new Integer[events.length];
        for (int n = 0; n < events.length; n++) {
            source[n] = Integer.parseInt(events[n+10]);
        }

        //Creates a temporary Integer array to be used as a buffer that flows into r[] to be returned
        Integer[] l = new Integer[95];
        for(int i = 2; i < 95; i++) {
            l[i] = 0;
        }

        //Calculates where the to Teleop event is located
        int split = 0;
        for(int p = 0; p < source.length; p++) {
            if (source[p] == 01) {
                split = p;
                break;
            }
        }

        //Takes the events before the to Teleop event
        Integer[] auto = new Integer[split];
        for(int q = 0; q < split; q++) {
            auto[q] = source[q];
        }

        //Takes the events after the to Teleop event
        Integer[] tele = new Integer[split];
        for(int w = split+1; w < source.length; w++) {
            tele[w] = source[w];
        }

        for(int i = 0; i < auto.length; i++) {
            switch(auto[i]) {
                case 10: //High Goal
                    l[2] = l[2] + 10; //Total Points
                    l[3] = l[3] + 10; //Goal Points
                    l[4]++; //High Goals
                    l[5] = l[5] + 10; //High Points
                    l[7] = ((l[4]) / (l[4] + l[6])); //High Ratio
                    l[12] = l[12] + 1; //Autonomous Points
                    l[13]++; //Auto High Goals
                    l[14] = l[14] + 10; //Auto High Points
                    l[16] = ((l[13]) / (l[13] + l[15])); //Auto High Ratio
                    break;
                case 11: //High Miss
            }
        }

        //Converts the temporary Integer array to a the string array that will be returned
        for(int s = 2; s < 95; s++) {
            r[s] = l[s].toString();
        }

        return r;
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

    public String[][] batchCsdsConverter(String[] csds) {
        String[][] r = new String[csds.length][];

        for(int i = 0; i < csds.length; i++) {
            r[i] = csdsConverter(csds[i]);
        }

        return r;
    }
}
