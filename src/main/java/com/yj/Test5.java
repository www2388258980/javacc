package com.yj;

public class Test5 {

    public static void main(String[] args) {
        Thread t = new Thread() {

            public void run() {
                my360DW();
            }
        };


        t.run();
        System.out.print("DW");

        Object obj = new Object() {
            public String toString() {
                my360DW();
                return null;
            }
        };

        obj.toString();

    }

    static void my360DW() {

        System.out.print("360");

    }

}
