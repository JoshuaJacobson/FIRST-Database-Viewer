package com.company;


public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        String s[] = m.csdsConverter("015086-0A110F018C02-This team sucked.");
    }

    public String[] csdsConverter(String csds) {
        String[] i = new String[1000];
        i[1] = csds.substring(0, 2);
        i[2] = csds.substring(2,6);

        String preEvents = csds.substring(7, csds.length());
        String events[] = preEvents.split("-", 2);
        i[0] = events[1];
        String eventsString = events[0];
        int j;

        for(j = 0; j<(eventsString.length()/4); j++) {
            String pre = eventsString.substring((j*4) + 2, (j*4) + 4);
            i[10 + j] = pre;
        }

        String[] k = new String[10+j];
        for (int m = 0; m < 10+j; m++) {
            k[m] = i[m];
        }

        return k;
    }
}
