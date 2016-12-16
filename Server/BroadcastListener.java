
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
            String bMessage= null;
            String ref=null;
            int posGP;
        
            BroadcastListener(PrintWriter o, String ref,int posGP){
                this.o=o;
                this.ref=ref;
                this.posGP=posGP;
                
            }
        
        public void run(){
            if(ref.equals("")){
                    while(true){
                        if(bMessage.equals(ServerTestoMultiThreaded.Broadcast)){
                            o.println(ServerTestoMultiThreaded.Broadcast);
                            bMessage=ServerTestoMultiThreaded.Broadcast;
                        }
                    }
                }else{
                    while(true){
                        if(bMessage.equals(ServerTestoMultiThreaded.Gruppi.get(posGP).getBroadcastMessage())){
                            o.println(ServerTestoMultiThreaded.Gruppi.get(posGP).getBroadcastMessage());
                            bMessage=ServerTestoMultiThreaded.Gruppi.get(posGP).getBroadcastMessage();
                        }
                    }
                }
        }
    }
