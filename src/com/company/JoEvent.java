package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //Regex to parse CSDS strings
        Pattern p = Pattern.compile("(\\w{2})(\\w{4})(\\w)(\\w)\\-(\\w+)\\-(.+)"); //Oh God I'm so sorry.
        Matcher m = p.matcher(csds);
        if (m.find()) {
            this.matchNumber = Integer.parseInt(m.group(1));
            this.teamNumber = Integer.parseInt(m.group(2));
            gameStates(m.group(3), m.group(4));
            System.out.println(m.group(5));
            this.result = m.group(6);
        }
    }

    void eventsToJoEvent(String events) {

        String regex = "";

        int halfLength = events.length() / 2;
        for (int i = 0; i < halfLength; i++) {
            regex += "(\\w{2})";
        }
        Integer[] source = new Integer[halfLength];
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(events);
        if (m.find()) {
            for (int i = 0; i < halfLength; i++) {
                source[i] = Integer.parseInt(m.group(i));
            }
        }

        //Calculates where the to Teleop event is located
        int split = 0;
        for(int i = 0; i < source.length; i++) {
            if (source[i] == 01) {
                split = i;
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
                    total_points += auto_high;

                    goal_points += auto_high;
                    high_goals++;
                    high_points += auto_high;

                    auto_goal_points += auto_high;
                    auto_high_goals++;
                    auto_high_points += auto_high;

                    updateRatios();
                    break;
                case 11: //High Miss
                    high_misses++;
                    auto_high_misses++;

                    updateRatios();
                    break;
                case 12: //Low Goal
                    total_points += auto_low;

                    goal_points += auto_low;
                    low_goals++;
                    low_points += auto_low;

                    auto_goal_points += auto_low;
                    auto_low_goals++;
                    auto_low_points += auto_low;

                    updateRatios();
                    break;
                case 13: //Low Miss
                    low_misses++;
                    auto_low_misses++;

                    updateRatios();
                    break;
                case 20: //Portcullis
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    portcullis++;
                    portcullis_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_portcullis++;
                    auto_portcullis_points += auto_defence_cross;
                    break;
                case 21: //Cheval De Frise
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    cheval_de_frise++;
                    cheval_de_frise_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_cheval_de_frise++;
                    auto_cheval_de_frise_points += auto_defence_cross;
                    break;
                case 22: //Ramparts
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    ramparts++;
                    ramparts_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_ramparts++;
                    auto_ramparts_points += auto_defence_cross;
                    break;
                case 23: //Moat
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    moat++;
                    moat_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_moat++;
                    auto_moat_points += auto_defence_cross;
                    break;
                case 24: //Drawbridge
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    drawbridge++;
                    drawbridge_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_drawbridge++;
                    auto_drawbridge_points += auto_defence_cross;
                    break;
                case 25: //Sally Port
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    sally_port++;
                    sally_port_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_sally_port++;
                    auto_sally_port_points += auto_defence_cross;
                    break;
                case 26: //Rock Wall
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    rock_wall++;
                    rock_wall_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_rock_wall++;
                    auto_rock_wall_points += auto_defence_cross;
                    break;
                case 27: //Rough Terrain
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    rough_terrain++;
                    rough_terrain_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_rough_terrain++;
                    auto_rough_terrain_points += auto_defence_cross;
                    break;
                case 28: //Low Bar
                    total_points += auto_defence_cross;

                    defence_points += auto_defence_cross;
                    low_bar++;
                    low_bar_points += auto_defence_cross;

                    auto_defence_points += auto_defence_cross;
                    auto_low_bar++;
                    auto_low_bar_points += auto_defence_cross;
                    break;
                case 29: //Reach Defence
                    total_points += auto_defence_reach;
                    defence_points += auto_defence_reach;
                    auto_defence_points += auto_defence_reach;
                    break;
                case 30: //Outer Works Breach
                    total_points += outer_works_breach;
                    bonus_points += outer_works_breach;
                    breach = 1;
                    break;
                case 31: //Challenge
                    challenged = true;
                    total_points += challenge_points;
                    bonus_points += challenge_points;
                    challenge = challenge_points;
                    break;
                case 32: //Scale
                    if (challenged = true) {
                        total_points -= challenge_points;
                        bonus_points -= challenge_points;
                    }
                    total_points += scaling;
                    bonus_points += scaling;
                    challenge = scaling;
                    break;
                case 33: //Capture
                    total_points += tower_capture;
                    bonus_points += tower_capture;
                    capture = 1;
                    break;
                default:
                    break;
            }
        }

        for(int i = 0; i < tele.length; i++) {
            Boolean challenge_bool = false;

            //If this code is not self-documenting, may God help your soul, I ain't commenting it.

            switch(tele[i]) {
                case 10: //High Goal
                    total_points += teleop_high;

                    goal_points += teleop_high;
                    high_goals++;
                    high_points += teleop_high;

                    teleop_goal_points += teleop_high;
                    teleop_high_goals++;
                    teleop_high_points += teleop_high;

                    updateRatios();
                    break;
                case 11: //High Miss
                    high_misses++;
                    teleop_high_misses++;

                    updateRatios();
                    break;
                case 12: //Low Goal
                    total_points += teleop_low;

                    goal_points += teleop_low;
                    low_goals++;
                    low_points += teleop_low;

                    teleop_goal_points += teleop_low;
                    teleop_low_goals++;
                    teleop_low_points += teleop_low;

                    updateRatios();
                    break;
                case 13: //Low Miss
                    low_misses++;
                    teleop_low_misses++;

                    updateRatios();
                    break;
                case 20: //Portcullis
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    portcullis++;
                    portcullis_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_portcullis++;
                    teleop_portcullis_points += teleop_defence_cross;
                    break;
                case 21: //Cheval De Frise
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    cheval_de_frise++;
                    cheval_de_frise_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_cheval_de_frise++;
                    teleop_cheval_de_frise_points += teleop_defence_cross;
                    break;
                case 22: //Ramparts
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    ramparts++;
                    ramparts_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_ramparts++;
                    teleop_ramparts_points += teleop_defence_cross;
                    break;
                case 23: //Moat
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    moat++;
                    moat_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_moat++;
                    teleop_moat_points += teleop_defence_cross;
                    break;
                case 24: //Drawbridge
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    drawbridge++;
                    drawbridge_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_drawbridge++;
                    teleop_drawbridge_points += teleop_defence_cross;
                    break;
                case 25: //Sally Port
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    sally_port++;
                    sally_port_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_sally_port++;
                    teleop_sally_port_points += teleop_defence_cross;
                    break;
                case 26: //Rock Wall
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    rock_wall++;
                    rock_wall_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_rock_wall++;
                    teleop_rock_wall_points += teleop_defence_cross;
                    break;
                case 27: //Rough Terrain
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    rough_terrain++;
                    rough_terrain_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_rough_terrain++;
                    teleop_rough_terrain_points += teleop_defence_cross;
                    break;
                case 28: //Low Bar
                    total_points += teleop_defence_cross;

                    defence_points += teleop_defence_cross;
                    low_bar++;
                    low_bar_points += teleop_defence_cross;

                    teleop_defence_points += teleop_defence_cross;
                    teleop_low_bar++;
                    teleop_low_bar_points += teleop_defence_cross;
                    break;
                case 30: //Outer Works Breach
                    total_points += outer_works_breach;
                    bonus_points += outer_works_breach;
                    breach = 1;
                    break;
                case 31: //Challenge
                    challenged = true;
                    total_points += challenge_points;
                    bonus_points += challenge_points;
                    challenge = challenge_points;
                    break;
                case 32: //Scale
                    if (challenged = true) {
                        total_points -= challenge_points;
                        bonus_points -= challenge_points;
                    }
                    total_points += scaling;
                    bonus_points += scaling;
                    challenge = scaling;
                    break;
                case 33: //Capture
                    total_points += tower_capture;
                    bonus_points += tower_capture;
                    capture = 1;
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


    private void gameStates(String alliance, String result) throws Exception {
        if (alliance.equals("B")) {
            this.alliance = "BLUE";
        }
        else if (alliance.equals("R")) {
            this.alliance = "RED";
        }
        else {
            throw new Exception("INVALID GAME STATE AT CHARACTER 6 (Zero Based csds system");
        }

        if (result.equals("W")) {
            this.result = "WIN";
        }
        else if (result.equals("L")) {
            this.result = "LOSS";
        }
        else if (result.equals("T")) {
            this.result = "TIE";
        }
        else {
            throw new Exception("INVALID GAME STATE AT CHARACTER 7 (Zero Based csds system");
        }
    }

    private int length(int i) {
        String s = String.valueOf(i);
        return s.length();
    }
}
