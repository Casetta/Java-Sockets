
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
            String bMessage= "";
            int posGP;
        
            BroadcastListener(PrintWriter o, int posGP){
                this.o=o;
                this.posGP=posGP;
                
            }
        
        public void run(){
            if(posGP<0){
                    while(true){
                        if(!ServerTestoMultiThreaded.Broadcast.isEmpty()){
                            if(!bMessage.equals(ServerTestoMultiThreaded.Broadcast)){
                                o.println(ServerTestoMultiThreaded.Broadcast);
                                bMessage=ServerTestoMultiThreaded.Broadcast;
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
