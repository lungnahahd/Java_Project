package findcommontime;

import java.io.*;
import java.util.Scanner;


class Maketime{
    static int g_size;
    String get[] = new String[g_size];

    Maketime(String[] get){
        this.get = get;
    }
    public int start(){
        int chtime[] = new int[g_size];
        int stime = 0;
        for(int i = 0; i < g_size; i++){
            String temp = get[i].substring(0,2) + get[i].substring(3,5);
            chtime[i] = Integer.parseInt(temp);
            if(stime < chtime[i]){
                stime = chtime[i];
            }
        }
        return stime;
    }

    public int end(){
        int chtime[] = new int[g_size];
        int etime = 2401;
        for(int i = 0; i < g_size; i++){
            String temp = get[i].substring(8,10) + get[i].substring(11);
            chtime[i] = Integer.parseInt(temp);
            if(etime > chtime[i]){
                etime = chtime[i];
            }
        }
        return etime;
    }

}




class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = scan.nextInt();
        scan.nextLine();
        Maketime.g_size = count;
        String gettime[] = new String[count];
        int numtime[] = new int[count];
        for(int i = 0; i<count; i++){
            gettime[i] = scan.nextLine();
        }

        Maketime maketime = new Maketime(gettime);
        int start = maketime.start();
        int end = maketime.end();

        if(start >= end){
            System.out.println("-1");
        }else{
            String stemp ="";
            String etemp ="";
            if(start<10){
                stemp = "000" + Integer.toString(start);
                stemp = stemp.substring(0,2) + ":" + stemp.substring(2);

            }else if(start < 100){
                stemp = "00" + Integer.toString(start);
                stemp = stemp.substring(0,2) + ":" + stemp.substring(2);
            }else if(start < 1000){
                stemp = "0" + Integer.toString(start);
                stemp = stemp.substring(0,2) + ":" + stemp.substring(2);
            }else{
                stemp = Integer.toString(start);
                stemp = stemp.substring(0,2) + ":" + stemp.substring(2);
            }
            if(end<10){
                etemp = "000" + Integer.toString(end);
                etemp = etemp.substring(0,2) + ":" + etemp.substring(2);
            }else if(end < 100){
                etemp = "00" + Integer.toString(end);
                etemp = etemp.substring(0,2) + ":" + etemp.substring(2);
            }else if(end < 1000){
                etemp = "0" + Integer.toString(end);
                etemp = etemp.substring(0,2) + ":" + etemp.substring(2);
            }else{
                etemp = Integer.toString(end);
                etemp = etemp.substring(0,2) + ":" + etemp.substring(2);
            }
            String result = stemp + " ~ " + etemp;
            System.out.println(result);


        }


    }
}