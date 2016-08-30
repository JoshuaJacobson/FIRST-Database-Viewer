package com.company;

/**
 * Created by josh on 8/29/16.
 */
public class JoEvent {

    public int matchNumber;

    public JoEvent(String[] data) throws Exception {
        if(data.length < 95) {
            throw new Exception("ERROR: DATA IS INVALID");
        }

        matchNumber = Integer.parseInt(data[0]);


    }

}
