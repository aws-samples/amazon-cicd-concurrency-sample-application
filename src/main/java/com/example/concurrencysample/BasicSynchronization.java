package com.example.concurrencysample;
 
public class BasicSynchronization extends Thread{
    private String mMessage;
    int tname = 0;
    int count = 0;
    int loop_count = 0;
    public BasicSynchronization(int var){
        loop_count = var;
        count = 0;
    }
    private void callMe() {
        try { 
            int key = loop_count > 0 ? (int) (Math.random() * loop_count) : (int) (Math.random() * 100000000);
//            int key = (int) (Math.random() * 1000);
            Concurrency result = SingletonRepo.getInstance().putKey(key);
            if( result == null)
                count++;

            long sleep = (long) (Math.random() * 1); 
       
            Thread.sleep(sleep); 
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        }
    }
    public int getCount(){
        return count;
    }
    public void setCount(int var){
         count = var;
    }
    public void run() {
        if( loop_count > 0)
            for( int i =0 ; i < loop_count ; i++)
                callMe();
        else
            while( true )
                callMe();

    }
    public void setLoopCount( int var ){
        loop_count = var;
    }
    public void setTName(int var){
        tname  = var;
    }
    public int getTName(){
        return tname;
    }
    public String getmMessage(){
        return mMessage;
    }    
}