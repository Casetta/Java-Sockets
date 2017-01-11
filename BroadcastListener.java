
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            String bMessage= "";
            int posGP;
        
            BroadcastListener(PrintWriter o, int posGP){
                this.o=o;
                this.posGP=posGP;
                
            }
        
        public void run(){
                if(posGP<0){
                    synchronized (ServerTestoMultiThreaded.Broadcast) {
                        while(true){
                            try {
                                ServerTestoMultiThreaded.Broadcast.wait();
                                
                                System.out.println("si"+ posGP);
                                if(!ServerTestoMultiThreaded.Broadcast.isEmpty()){
                                    
                                    
                                    bMessage=ServerTestoMultiThreaded.Broadcast;
                                    o.println(bMessage);
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(BroadcastListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }else{
                    while(true){
                        if(!ServerTestoMultiThreaded.Gruppi.get(posGP).getBroadcastMessage().isEmpty()){
                            if(!bMessage.equals(ServerTestoMultiThreaded.Gruppi.get(posGP).getBroadcastMessage())){
                                o.println(ServerTestoMultiThreaded.Gruppi.get(posGP).getBroadcastMessage());
                                bMessage=ServerTestoMultiThreaded.Gruppi.get(posGP).getBroadcastMessage();
                            }
                        }
                    }
                }
        
       }
        
    }
