package com.company.os;
import java.util.*;

public class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter No. of Processes");
        int n = sc.nextInt();
        sc.nextLine();
        int[] pid = new int[n]; // process ids
        int[] at = new int[n]; // arrival times
        int[] bt = new int[n]; // burst or execution times
        int[] ct = new int[n]; // completion times
        int[] tat = new int[n]; // turn around times
        int[] wt = new int[n]; // waiting times
        int f[] = new int[n]; // f means it is flag it checks process is completed or not
        int st = 0, completed = 0;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time & Burst Time of P"+(i+1)+" (Separated By Space)");
            String[] input = sc.nextLine().split(" ");
            if(input.length != 2){
                System.out.println("Wrong Input");
                System.exit(0);
            }
            at[i] = Integer.parseInt(input[0]);
            bt[i] = Integer.parseInt(input[1]);
            pid[i] = i + 1;
            f[i] = 0;
        }

        while (true){
            int c = n, min = 999;
            if(completed==n)
                break;
            for(int i=0; i<n; i++){
                if(at[i]<=st && bt[i]<min && f[i] == 0){
                    min = bt[i];
                    c = i;
                }
            }
            if(c==n) {
                st++;
            }
            else {
                ct[c] = st + bt[c];
                st += bt[c];
                tat[c] = ct[c] - at[c];
                wt[c] = tat[c] - bt[c];
                f[c] = 1;
                completed++;
            }
        }

//        while (true) {
//            int c = n, min = 999;
//            if (completed == n) // total no of process = completed process loop will be terminated
//                break;
//            for (int i = 0; i < n; i++) {
//                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
//                    min = bt[i];
//                    c = i;
//                }
//            }
//            /*
//             * If c==n means c value can not updated because no process arrival time< system
//             * time so we increase the system time
//             */
//            if (c == n)
//                st++;
//            else {
//                ct[c] = st + bt[c];
//                st += bt[c];
//                tat[c] = ct[c] - at[c];
//                wt[c] = tat[c] - bt[c];
//                f[c] = 1;
//                completed++;
//            }
//        }
        System.out.println("\nPid  Arrival  Brust  Complete TAT Waiting");
        for (int i = 0; i < n; i++) {
            avgwt += wt[i];
            avgta += tat[i];
            System.out.println(pid[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }
        System.out.println("\naverage tat is " + (float) (avgta / n));
        System.out.println("average wt is " + (float) (avgwt / n));
    }
}