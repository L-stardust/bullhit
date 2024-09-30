package com.example.bullhit;

import java.util.Random;

public class Bullhit {
     private int number[];

     public Bullhit() {
         this.number = new int[4];
         Random rand = new Random();
         int [] arrg = new int[10];

         for (int j=0; j<arrg.length; j++){
             arrg[j]=0;
         }
         for (int i=0; i<4; i++){
             int generate = rand.nextInt(10);
             while (arrg[generate]>0){
                 generate = rand.nextInt(10);
             }
             this.number[i]=generate;
             arrg[generate]++;
        }
    }

    public int Bull(String guess){
         int counter=0;
         for( int i =0; i<this.number.length; i++){
             if (this.number[i]==guess.charAt(i)-'0'){
                 counter++;
             }
         }
         return counter;
    }

    public int Hit(String guess){
         int counterhit=0;
         for (int i=0; i<guess.length(); i++){
             for (int j=0; j<this.number.length; j++){
                 if (this.number[j]==guess.charAt(i)-'0'){
                     if(this.number[j]!=guess.charAt(j)-'0') {
                         counterhit++;
                     }
                 }
             }
         }
         return counterhit;
    }

    public String numberH(){
         return this.number[0] +"" + this.number[1]+ "" + this.number[2]+ ""+this.number[3];
    }
}
