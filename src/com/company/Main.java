package com.company;


public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.csdsConverter("cvjhvghvjhjvh");
    }

    public Integer[] csdsConverter(String csds) {
        Integer[] i = new Integer[1000];
        String output;
        output = csds.substring(0, 1);
        i[0] = Integer.parseInt(output);

        return i;
    }
}
