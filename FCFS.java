package com.company.os;
import java.util.*;

class Process{
    int at, bt, pid;
    Process(int at, int bt, int pid){
        this.at = at;
        this.bt = bt;
        this.pid = pid;
    }
}

public class FCFS {
    public static void main(String[] args) {
        PriorityQueue<Process> pq = new PriorityQueue<>((x,y) -> x.at - y.at);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter No. of Processes");
        int n = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<n; i++){
            System.out.println("Enter Arrival Time & Burst Time of P"+(i+1)+" (Separated By Space)");
            String[] input = sc.nextLine().split(" ");
            if(input.length != 2){
                System.out.println("Wrong Input");
                System.exit(0);
            }
            Process P = new Process(Integer.parseInt(input[0]), Integer.parseInt(input[1]), i+1);
            pq.add(P);
        }
        ArrayList<Integer> at = new ArrayList<>();
        ArrayList<Integer> bt = new ArrayList<>();
        ArrayList<Integer> ct = new ArrayList<>();
        ArrayList<Integer> pid = new ArrayList<>();
        ArrayList<Integer> wt = new ArrayList<>();
        ArrayList<Integer> tat = new ArrayList<>();
        int st = 0, awt=0, atat=0;
        while(pq.size() != 0){
            if(pq.peek().at > st){
                st++;
                continue;
            }
            Process P = pq.poll();
            at.add((P.at));
            bt.add((P.bt));
            pid.add((P.pid));
            st += P.bt;
            ct.add(st);
            tat.add(st - P.at);
            wt.add(st - P.bt - P.at);
            atat += st - P.at;
            awt += st - P.bt - P.at;
        }
        System.out.println("\nPID  Arrival  Burst  TAT  Waiting");
        for(int i=0; i<n; i++){
            System.out.printf(" %d \t\t %d \t\t%d \t  %d \t\t%d \n", pid.get(i), at.get(i), bt.get(i), tat.get(i), wt.get(i));
        }
        System.out.println("Average TAT = "+atat);
        System.out.println("Average WT = "+awt);
    }
}
