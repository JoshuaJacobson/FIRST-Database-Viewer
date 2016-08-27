package com.company;


public class Main {

    public String install = System.getProperty("user.dir");
    public void setInstall(String install) {
        this.install = install;
    }
    public String getInstall() {
        return install;
    }

    public String interpreter = "2016_1.0.0";
    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
    }
    public String getInterpreter() {
        return interpreter;
    }

    public static void main(String[] args) {
        for (int i = 0; i< args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
