package main;

import java.time.LocalTime;

public class Time {
    static int  time ;
    public static int getTime(){
        return time ;
    }

    static String  Time_Handling( String TIME1 , String TIME2 ){
        LocalTime time1 = LocalTime.parse(TIME1);
        LocalTime time2 = LocalTime.parse(TIME2);
        String res;
        time = Math.abs ( (time1.getHour() * 60 + time1.getMinute()*60 + time1.getSecond() )
                - ( time2.getHour() * 60 + time2.getMinute()*60 + time2.getSecond() ) )  ;

        int second = time%60 ;
        int minute = time/60 ;
        String seconds ;
        String minutes ;
        if (second >= 10){
            seconds = second + "";
        }
        else seconds = "0"+ "" + second ;


        if (minute >= 10){
            minutes = minute + "";
        }
        else minutes = "0"+ ""+ minute ;
        res =minutes +" - " + seconds;
        return res ;
    }
}