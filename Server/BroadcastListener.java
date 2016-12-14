
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author orientamento
 */
    class BroadcastListener implements Runnable {
            PrintWriter o;
            String bMessage= ServerTestoMultiThreaded.Broadcast;
        
            BroadcastListener(PrintWriter o){
                this.o=o;
            }
        
        public void run(){
            while(true){
            if(bMessage!=ServerTestoMultiThreaded.Broadcast){
                o.println(ServerTestoMultiThreaded.Broadcast);
                bMessage=ServerTestoMultiThreaded.Broadcast;
            }
        }
        }
}
