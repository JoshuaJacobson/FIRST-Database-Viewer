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
    public int bonus_points = 0;
    public int breach = 0;
    public int challenge = 0;
    public int capture = 0;
    public String result = "";
    private boolean challenged = false;

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

                    defence_points = defence_points + auto_defence_cross;
                    portcullis++;
                    portcullis_points = portcullis_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_portcullis++;
                    auto_portcullis_points = auto_portcullis_points + auto_defence_cross;
                    break;
                case 21: //Cheval De Frise
                    total_points = total_points + auto_defence_cross;

                    defence_points = defence_points + auto_defence_cross;
                    cheval_de_frise++;
                    cheval_de_frise_points = cheval_de_frise_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_cheval_de_frise++;
                    auto_cheval_de_frise_points = auto_cheval_de_frise_points + auto_defence_cross;
                    break;
                case 22: //Ramparts
                    total_points = total_points + auto_defence_cross;

                    defence_points = defence_points + auto_defence_cross;
                    ramparts++;
                    ramparts_points = ramparts_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_ramparts++;
                    auto_ramparts_points = auto_ramparts_points + auto_defence_cross;
                    break;
                case 23: //Moat
                    total_points = total_points + auto_defence_cross;

                    defence_points = defence_points + auto_defence_cross;
                    moat++;
                    moat_points = moat_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_moat++;
                    auto_moat_points = auto_moat_points + auto_defence_cross;
                    break;
                case 24: //Drawbridge
                    total_points = total_points + auto_defence_cross;

                    defence_points = defence_points + auto_defence_cross;
                    drawbridge++;
                    drawbridge_points = drawbridge_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_drawbridge++;
                    auto_drawbridge_points = auto_drawbridge_points + auto_defence_cross;
                    break;
                case 25: //Sally Port
                    total_points = total_points + auto_defence_cross;

                    defence_points = defence_points + auto_defence_cross;
                    sally_port++;
                    sally_port_points = sally_port_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_sally_port++;
                    auto_sally_port_points = auto_sally_port_points + auto_defence_cross;
                    break;
                case 26: //Rock Wall
                    total_points = total_points + auto_defence_cross;

                    defence_points = defence_points + auto_defence_cross;
                    rock_wall++;
                    rock_wall_points = rock_wall_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_rock_wall++;
                    auto_rock_wall_points = auto_rock_wall_points + auto_defence_cross;
                    break;
                case 27: //Rough Terrain
                    total_points = total_points + auto_defence_cross;

                    defence_points = defence_points + auto_defence_cross;
                    rough_terrain++;
                    rough_terrain_points = rough_terrain_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_rough_terrain++;
                    auto_rough_terrain_points = auto_rough_terrain_points + auto_defence_cross;
                    break;
                case 28: //Low Bar
                    total_points = total_points + auto_defence_cross;

                    defence_points = defence_points + auto_defence_cross;
                    low_bar++;
                    low_bar_points = low_bar_points + auto_defence_cross;

                    auto_defence_points = auto_defence_points + auto_defence_cross;
                    auto_low_bar++;
                    auto_low_bar_points = auto_low_bar_points + auto_defence_cross;
                    break;
                case 29: //Reach Defence
                    total_points = total_points + auto_defence_reach;
                    defence_points = defence_points + auto_defence_reach;
                    auto_defence_points = auto_defence_points + auto_defence_reach;
                    break;
                case 30: //Outer Works Breach
                    total_points = total_points + outer_works_breach;
                    bonus_points = bonus_points + outer_works_breach;
                    breach = breach + outer_works_breach;
                    break;
                case 31: //Challenge
                    challenged = true;
                    total_points = total_points + challenge_points;
                    bonus_points = bonus_points + challenge_points;
                    challenge = challenge_points;
                    break;
                case 32: //Scale
                    if (challenged = true) {
                        total_points = total_points - challenge_points;
                        bonus_points = bonus_points - challenge_points;
                    }
                    total_points = total_points + scaling;
                    bonus_points = bonus_points + scaling;
                    challenge = scaling;
                    break;
                case 33: //Capture
                    total_points = total_points + tower_capture;
                    bonus_points = bonus_points + tower_capture;
                    capture = capture + tower_capture;
                    break;
                case 40: //Win
                    result = "WIN";
                    break;
                case 41: //Loss
                    result = "LOSS";
                    break;
                case 42: //Tie
                    result = "TIE";
                    break;
                default:
                    break;
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
