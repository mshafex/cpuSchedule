package cpm;

import java.util.ArrayList;

public class GranttChart {
    String name;
    int end;
    int start;
    static int Time;

    public GranttChart(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.Time = end;
    }

     public static void Table(ArrayList<GranttChart> array) {
        for (int i = 0; i < Time + array.size() * 3; i++)
            System.out.print("_");
        System.out.println();

        System.out.print("|");
        for (GranttChart cG : array) {

            for (int k = 0; k < cG.end - cG.start + 2; k++)
                System.out.print(" ");
            System.out.print("|");
        }
        System.out.println();

        System.out.print("|");
        for (GranttChart cG : array) {

            for (int k = 0; k < cG.end - cG.start + 1; k++)
                if (k != (cG.end - cG.start) / 2)
                    System.out.print(" ");
                else
                    System.out.print(cG.name);
            System.out.print("|");
        }
        System.out.println();

        System.out.print("|");
        for (GranttChart cG : array) {

            for (int k = 0; k < cG.end - cG.start + 2; k++)
                System.out.print(" ");
            System.out.print("|");
        }
        System.out.println();
        for (int i = 0; i < Time + array.size() * 3; i++)
            System.out.print("_");
        System.out.println();

        System.out.print(0);
        for (GranttChart cG : array) {
            int spaces = 0;
            int end = cG.end / 10;
            while (end != 0) {
                spaces += 1;
                end /= 10;
            }
            for (int i = 0; i < cG.end - cG.start + 2 - spaces; i++)
                System.out.print(" ");
            System.out.print(cG.end);
        }

        System.out.println();
        System.out.println();
    }

}