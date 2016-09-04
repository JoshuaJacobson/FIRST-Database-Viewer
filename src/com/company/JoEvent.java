package com.company;

/**
 * Created by josh on 8/29/16.
 */
public class JoEvent {

    public String comments;
    public int matchNumber;
    public int teamNumber;
    public String alliance;
    public int total_points = 0;
    public int goal_points = 0;
    public int high_goals = 0;
    public int high_points = 0;
    public int high_misses = 0;
    public int high_ratio = 0;
    public int low_goals = 0;
    public int low_points = 0;
    public int low_misses = 0;
    public int low_ratio = 0;
    public int auto_goal_points = 0;
    public int auto_high_goals = 0;
    public int auto_high_points = 0;
    public int auto_high_misses = 0;
    public int auto_high_ratio = 0;
    public int auto_low_goals = 0;
    public int auto_low_points = 0;
    public int auto_low_misses = 0;
    public int auto_low_ratio = 0;
    public int teleop_goal_points = 0;
    public int teleop_high_points = 0;
    public int teleop_high_goals = 0;
    public int teleop_high_misses = 0;
    public int teleop_high_ratio = 0;
    public int teleop_low_points = 0;
    public int teleop_low_goals = 0;
    public int teleop_low_misses = 0;
    public int teleop_low_ratio = 0;
    public int defence_points = 0;
    public int portcullis = 0;
    public int portcullis_points = 0;
    public int cheval_de_frise = 0;
    public int cheval_de_frise_points = 0;
    public int ramparts = 0;
    public int ramparts_points = 0;
    public int moat = 0;
    public int moat_points = 0;
    public int drawbridge = 0;
    public int drawbridge_points = 0;
    public int sally_port = 0;
    public int sally_port_points = 0;
    public int rock_wall = 0;
    public int rock_wall_points = 0;
    public int rough_terrain = 0;
    public int rough_terrain_points = 0;
    public int low_bar = 0;
    public int low_bar_points = 0;
    public int auto_defence_points = 0;
    public int auto_portcullis = 0;
    public int auto_portcullis_points = 0;
    public int auto_cheval_de_frise = 0;
    public int auto_cheval_de_frise_points = 0;
    public int auto_ramparts = 0;
    public int auto_ramparts_points = 0;
    public int auto_moat = 0;
    public int auto_moat_points = 0;
    public int auto_drawbridge = 0;
    public int auto_drawbridge_points = 0;
    public int auto_sally_port = 0;
    public int auto_sally_port_points = 0;
    public int auto_rock_wall = 0;
    public int auto_rock_wall_points = 0;
    public int auto_rough_terrain = 0;
    public int auto_rough_terrain_points = 0;
    public int auto_low_bar = 0;
    public int auto_low_bar_points = 0;
    public int teleop_defence_points = 0;
    public int teleop_portcullis = 0;
    public int teleop_portcullis_points = 0;
    public int teleop_cheval_de_frise = 0;
    public int teleop_cheval_de_frise_points = 0;
    public int teleop_ramparts = 0;
    public int teleop_ramparts_points = 0;
    public int teleop_moat = 0;
    public int teleop_moat_points = 0;
    public int teleop_drawbridge = 0;
    public int teleop_drawbridge_points = 0;
    public int teleop_sally_port = 0;
    public int teleop_sally_port_points = 0;
    public int teleop_rock_wall = 0;
    public int teleop_rock_wall_points = 0;
    public int teleop_rough_terrain = 0;
    public int teleop_rough_terrain_points = 0;
    public int teleop_low_bar = 0;
    public int teleop_low_bar_points = 0;

    public JoEvent(String csds) throws Exception {
        String[] i = new String[1000];
        matchNumber = Integer.parseInt(csds.substring(0, 2)); //Match Number
        teamNumber = Integer.parseInt(csds.substring(2,6));  //Team Number

        String allianceorigin = csds.substring(6, 7);
        if (allianceorigin == "R") {
            alliance = "RED";
        }
        else if (allianceorigin == "B") {
            alliance = "BLUE";
        }

        String preEvents = csds.substring(8, csds.length()); //Gets the string starting with the events
        String events[] = preEvents.split("-", 2); //Splits into events and comments
        comments = events[1]; //Comments
        String eventsString = events[0]; //Events
        int j;

        for(j = 0; j<(eventsString.length()/4); j++) {
            String pre = eventsString.substring((j*4) + 2, (j*4) + 4); //Gets the second two digits of every four to get event codes
            i[j] = pre; //Event codes
        }

        //Trims the array to the minimum size.
        String[] k = new String[10+j];
        for (int m = 0; m < 10+j; m++) {
            k[m] = i[m];
        }

    }

    void eventsToJoEvent(String[] events) {

        //Converts the data from the original String array to an Integer array for easier processing and readability.
        Integer[] source = new Integer[events.length];
        for (int n = 0; n < events.length; n++) {
            source[n] = Integer.parseInt(events[n+10]);
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
                    total_points = total_points + auto_high;

                    goal_points = goal_points + auto_high;
                    high_goals++;
                    high_points = high_points + auto_high;

                    auto_goal_points = auto_goal_points + auto_high;
                    auto_high_goals++;
                    auto_high_points = auto_high_points + auto_high;

                    updateRatios();
                    break;
                case 11: //High Miss
                    high_misses++;
                    auto_high_misses++;

                    updateRatios();
                    break;
                case 12: //Low Goal
                    total_points = total_points + auto_low;

                    goal_points =goal_points + auto_low;
                    low_goals++;
                    low_points = goal_points + auto_low;

                    auto_goal_points = auto_goal_points + auto_low;
                    auto_low_goals++;
                    auto_low_points = auto_low_points + auto_low;

                    updateRatios();
                    break;
                case 13: //Low Miss
                    low_misses++;
                    auto_low_misses++;

                    updateRatios();
                    break;
                case 20: //Portcullis
                    total_points = total_points + auto_defence_cross;


            }
        }
    }

    private void updateRatios() {
        //High
        high_ratio = (high_goals) / (high_goals + high_misses);
        auto_high_ratio = (auto_high_goals) / (auto_high_goals + auto_high_misses);
        teleop_high_ratio = (teleop_high_goals) / (teleop_high_goals + teleop_high_misses);

        //Low
        low_ratio = (low_goals) / (low_goals + low_misses);
        auto_low_ratio = (auto_low_goals) / (auto_low_goals + auto_low_misses);
        teleop_low_ratio = (teleop_low_goals) / (teleop_low_goals + teleop_low_misses);
    }

}
