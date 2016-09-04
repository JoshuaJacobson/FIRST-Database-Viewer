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

        //Index values for data in the l[] array.
        int match_number = 0;
        int team_number = 1;
        int total_points = 2;
        int goal_points = 3;
        int high_goals = 4;
        int high_points = 5;
        int high_misses = 6;
        int high_ratio = 7;
        int low_goals = 8;
        int low_points = 9;
        int low_misses = 10;
        int low_ratio = 11;
        int auto_goal_points = 12;
        int auto_high_goals = 13;
        int auto_high_points = 14;
        int auto_high_misses = 15;
        int auto_high_ratio = 16;
        int auto_low_goals = 17;
        int auto_low_points = 18;
        int auto_low_misses = 19;
        int auto_low_ratio = 20;
        int teleop_goal_points = 21;
        int teleop_high_points = 22;
        int teleop_high_goals = 23;
        int teleop_high_misses = 24;
        int teleop_high_ratio = 25;
        int teleop_low_points = 26;
        int teleop_low_goals = 27;
        int teleop_low_misses = 28;
        int teleop_low_ratio = 29;
        int defence_points = 30;
        int portcullis = 31;
        int portcullis_points = 32;
        int cheval_de_frise = 33;
        int cheval_de_frise_points = 34;
        int ramparts = 35;
        int ramparts_points = 36;
        int moat = 37;
        int moat_points = 38;
        int drawbridge = 39;

        //Point values
        int auto_defence_reach = 2;
        int auto_defence_cross = 10;
        int auto_low = 5;
        int auto_high = 10;
        int teleop_defence_cross = 5;
        int teleop_low = 2;
        int teleop_high = 5;
        int challenge_points = 5;
        int scaling = 15;
        int outer_works_breach = 25;
        int tower_capture = 25;


        for(int i = 0; i < auto.length; i++) {
            Boolean challenge_bool = false;

            //If this code is not self-documenting, may God help your soul, I ain't commenting it.

            switch(auto[i]) {
                case 10: //High Goal
                    l[total_points] = l[total_points] + auto_high;

                    l[goal_points] = l[goal_points] + auto_high;
                    l[high_goals]++;
                    l[high_points] = l[high_points] + auto_high;
                    l[high_ratio] = (l[high_goals]) / (l[high_goals] + l[high_misses]);

                    l[auto_goal_points] = l[auto_goal_points] + auto_high;
                    l[auto_high_goals]++;
                    l[auto_high_points] = l[auto_high_points] + auto_high;
                    l[auto_high_ratio] = (l[auto_high_goals]) / (l[auto_high_goals] + l[auto_high_misses]);
                    break;
                case 11: //High Miss
                    l[high_misses]++;
                    l[high_ratio] = (l[high_goals]) / (l[high_goals] + l[high_misses]);

                    l[auto_high_misses]++;
                    l[auto_high_ratio] = (l[auto_high_goals]) / (l[auto_high_goals] + l[auto_high_misses]);
                    break;
                case 12: //Low Goal
                    l[total_points] = l[total_points] + auto_low;

                    l[goal_points] =l[goal_points] + auto_low;
                    l[low_goals]++;
                    l[low_points] = l[goal_points] + auto_low;
                    l[low_ratio] = (l[low_goals]) / (l[low_goals + l[low_misses]]);

                    l[auto_goal_points] = l[auto_goal_points] + auto_low;
                    l[auto_low_goals]++;
                    l[auto_low_points] = l[auto_low_points] + auto_low;
                    l[auto_low_ratio] = (l[auto_low_goals]) / (l[auto_low_goals] + l[auto_low_misses]);
                    break;
                case 13: //Low Miss
                    l[low_misses]++;
                    l[low_ratio] = (l[low_goals]) / (l[low_goals] + l[low_misses]);

                    l[auto_low_misses]++;
                    l[auto_low_ratio] = (l[auto_low_goals]) / (l[auto_low_goals] + l[auto_low_misses]);
                    break;
                case 20:
                    l[total_points] = l[total_points] + auto_defence_cross;


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
