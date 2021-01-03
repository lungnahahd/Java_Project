package test;

import java.util.*;


public class Main{
    public static void main(String[] args){
        boolean[] check = new boolean[10001];
        check[0] = false;
        for(int i = 1; i < 10001; i++){
            check[i] = true;
        }
        for(int i = 1; i < 10001; i++){
            int result = self_num(i);
            if(result < 10001) {
                check[result] = false;
            }
        }
        for(int i = 1; i< check.length; i++){
            if(check[i]== true){
                System.out.println(i);
            }
        }
    }
    
    public static int self_num(int num){
        String[] array;
        String snum = Integer.toString(num);
        array = snum.split("");
        int sum = num;
       
       	for(int i = 0; i< array.length; i++){
       		sum = sum + Integer.parseInt(array[i]);
        }
        
        return sum;
    }
}