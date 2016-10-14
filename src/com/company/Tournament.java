package com.company;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by josh on 10/9/16.
 */
public class Tournament {
    private Connection connect = null;
    private Statement statement = null;
    private String table;
    private static String args = "" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "match_number INTEGER," +
            "team_number INTEGER," +
            "alliance TEXT," +
            "total_points INTEGER," +
            "goal_points INTEGER," +
            "high_goals INTEGER," +
            "high_points INTEGER," +
            "high_misses INTEGER," +
            "high_ratio INTEGER," +
            "low_goals INTEGER," +
            "low_points INTEGER," +
            "low_misses INTEGER," +
            "low_ratio INTEGER," +
            "auto_goal_points INTEGER," +
            "auto_high_goals INTEGER," +
            "auto_high_points INTEGER," +
            "auto_high_misses INTEGER," +
            "auto_high_ratio INTEGER," +
            "auto_low_goals INTEGER," +
            "auto_low_points INTEGER," +
            "auto_low_misses INTEGER," +
            "auto_low_ratio INTEGER," +
            "teleop_goal_points INTEGER," +
            "teleop_high_points INTEGER," +
            "teleop_high_goals INTEGER," +
            "teleop_high_misses INTEGER," +
            "teleop_high_ratio INTEGER," +
            "teleop_low_points INTEGER," +
            "teleop_low_goals INTEGER," +
            "teleop_low_misses INTEGER," +
            "teleop_low_ratio INTEGER," +
            "defence_points INTEGER," +
            "portcullis INTEGER," +
            "portcullis_points INTEGER," +
            "cheval_de_frise INTEGER," +
            "cheval_de_frise_points INTEGER," +
            "ramparts INTEGER," +
            "ramparts_points INTEGER," +
            "moat INTEGER," +
            "moat_points INTEGER," +
            "drawbridge INTEGER," +
            "drawbridge_points INTEGER," +
            "sally_port INTEGER," +
            "sally_port_points INTEGER," +
            "rock_wall INTEGER," +
            "rock_wall_points INTEGER," +
            "rough_terrain INTEGER," +
            "rough_terrain_points INTEGER," +
            "low_bar INTEGER," +
            "low_bar_points INTEGER," +
            "auto_defence_points INTEGER," +
            "auto_portcullis INTEGER," +
            "auto_portcullis_points INTEGER," +
            "auto_cheval_de_frise INTEGER," +
            "auto_cheval_de_frise_points INTEGER," +
            "auto_ramparts INTEGER," +
            "auto_ramparts_points INTEGER," +
            "auto_moat INTEGER," +
            "auto_moat_points INTEGER," +
            "auto_drawbridge INTEGER," +
            "auto_drawbridge_points INTEGER," +
            "auto_sally_port INTEGER," +
            "auto_sally_port_points INTEGER," +
            "auto_rock_wall INTEGER," +
            "auto_rock_wall_points INTEGER," +
            "auto_rough_terrain INTEGER," +
            "auto_rough_terrain_points INTEGER," +
            "auto_low_bar INTEGER," +
            "auto_low_bar_points INTEGER," +
            "teleop_defence_points INTEGER," +
            "teleop_portcullis INTEGER," +
            "teleop_portcullis_points INTEGER," +
            "teleop_cheval_de_frise INTEGER," +
            "teleop_cheval_de_frise_points INTEGER," +
            "teleop_ramparts INTEGER," +
            "teleop_ramparts_points INTEGER," +
            "teleop_moat INTEGER," +
            "teleop_moat_points INTEGER," +
            "teleop_drawbridge INTEGER," +
            "teleop_drawbridge_points INTEGER," +
            "teleop_sally_port INTEGER," +
            "teleop_sally_port_points INTEGER," +
            "teleop_rock_wall INTEGER," +
            "teleop_rock_wall_points INTEGER," +
            "teleop_rough_terrain INTEGER," +
            "teleop_rough_terrain_points INTEGER," +
            "teleop_low_bar INTEGER," +
            "teleop_low_bar_points INTEGER," +
            "bonus_points INTEGER," +
            "breach INTEGER," +
            "challenge INTEGER," +
            "capture INTEGER," +
            "result TEXT," +
            "comments TEXT";
    private static String args_short = "" +
            "id," +
            "match_number," +
            "team_number," +
            "alliance," +
            "total_points," +
            "goal_points," +
            "high_goals," +
            "high_points," +
            "high_misses," +
            "high_ratio," +
            "low_goals," +
            "low_points," +
            "low_misses," +
            "low_ratio," +
            "auto_goal_points," +
            "auto_high_goals," +
            "auto_high_points," +
            "auto_high_misses," +
            "auto_high_ratio," +
            "auto_low_goals," +
            "auto_low_points," +
            "auto_low_misses," +
            "auto_low_ratio," +
            "teleop_goal_points," +
            "teleop_high_points," +
            "teleop_high_goals," +
            "teleop_high_misses," +
            "teleop_high_ratio," +
            "teleop_low_points," +
            "teleop_low_goals," +
            "teleop_low_misses," +
            "teleop_low_ratio," +
            "defence_points," +
            "portcullis," +
            "portcullis_points," +
            "cheval_de_frise," +
            "cheval_de_frise_points," +
            "ramparts," +
            "ramparts_points," +
            "moat," +
            "moat_points," +
            "drawbridge," +
            "drawbridge_points," +
            "sally_port," +
            "sally_port_points," +
            "rock_wall," +
            "rock_wall_points," +
            "rough_terrain," +
            "rough_terrain_points," +
            "low_bar," +
            "low_bar_points," +
            "auto_defence_points," +
            "auto_portcullis," +
            "auto_portcullis_points," +
            "auto_cheval_de_frise," +
            "auto_cheval_de_frise_points," +
            "auto_ramparts," +
            "auto_ramparts_points," +
            "auto_moat," +
            "auto_moat_points," +
            "auto_drawbridge," +
            "auto_drawbridge_points," +
            "auto_sally_port," +
            "auto_sally_port_points," +
            "auto_rock_wall," +
            "auto_rock_wall_points," +
            "auto_rough_terrain," +
            "auto_rough_terrain_points," +
            "auto_low_bar," +
            "auto_low_bar_points," +
            "teleop_defence_points," +
            "teleop_portcullis," +
            "teleop_portcullis_points," +
            "teleop_cheval_de_frise," +
            "teleop_cheval_de_frise_points," +
            "teleop_ramparts," +
            "teleop_ramparts_points," +
            "teleop_moat," +
            "teleop_moat_points," +
            "teleop_drawbridge," +
            "teleop_drawbridge_points," +
            "teleop_sally_port," +
            "teleop_sally_port_points," +
            "teleop_rock_wall," +
            "teleop_rock_wall_points," +
            "teleop_rough_terrain," +
            "teleop_rough_terrain_points," +
            "teleop_low_bar," +
            "teleop_low_bar_points," +
            "bonus_points," +
            "breach," +
            "challenge," +
            "capture," +
            "result," +
            "comments";

    public Tournament(String table) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/tournaments?"
                            + "user=fdvadmin&password=fdvadminpassword&useSSL=false");
            ResultSet resultSet = connect.getMetaData().getTables(null, null, table, null);
            if (!resultSet.next()) {
                createTable(table);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.table = table;

    }

    private void createTable(String table) throws SQLException {
        statement = connect.createStatement();
        statement.executeUpdate("CREATE TABLE " + table + " (id INTEGER PRIMARY KEY, words TEXT);");
        statement = null;
    }
}
