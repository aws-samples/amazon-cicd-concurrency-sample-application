package com.example.concurrencysample;

public class BasicCheckMap  extends Thread{
    int tname = 0;
    int count = 0;
    int loop_count = 0;
    public BasicCheckMap(int var){
        loop_count = var;
        count = 0;
    }
    private void getMe() {
        try { 
            int key = (int) (Math.random() * 500000);
            Concurrency result = SingletonRepo.getInstance().get(key);
            if( result != null )
                if( Util.getRandomDate() == result.getDate() )
                    SingletonRepo.getInstance().clearHashMap(); 
            long sleep = (long) (Math.random() * 0); 
            if( SingletonRepo.getInstance().getMapCount() > 100000){
                SingletonRepo.getInstance().clearHashMap();
            }
            Thread.sleep(sleep); 
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        }
    }
    public void run() {
        if( loop_count > 0)
            for( int i =0 ; i < loop_count ; i++)
                getMe();
        else
            while( true )
                getMe();

    }
}