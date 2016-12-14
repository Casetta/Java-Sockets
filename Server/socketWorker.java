/*
 * socketWorker.java ha il compito di gestire la connessione al socket da parte di un Client.
 * Elabora il testo ricevuto che in questo caso viene semplicemente mandato indietro con l'aggiunta 
 * di una indicazione che e' il testo che viene dal Server.
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Gasperini Luca, Casetta Lorenzo
 */
class SocketWorker implements Runnable {
  private Socket client;

    //Constructor: inizializza le variabili
    SocketWorker(Socket client) {
        this.client = client;
        System.out.println("Connesso con: " + client);
    }

    // Questa e' la funzione che viene lanciata quando il nuovo "Thread" viene generato
    public void run(){
        
        BufferedReader in = null;
        PrintWriter out = null;
        boolean firstTime =true;
        
        try{
          // connessione con il socket per ricevere (in) e mandare(out) il testo
          in = new BufferedReader(new InputStreamReader(client.getInputStream()));
          out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
          System.out.println("Errore: in|out fallito");
          System.exit(-1);
        }

        String line = "";
        String nameClient="";
        int _lenghtList=0, posClient=0;
        int clientPort = client.getPort(); //il "nome" del mittente (client)
        while(line != null){
          try{
                         
            if(firstTime || ServerTestoMultiThreaded.Utenti.isEmpty()){         //client al primo accesso inserito nella lista
                String ClientAddress = client.getInetAddress().toString();      //registra l'indirizzo del client
                _lenghtList = ServerTestoMultiThreaded.Utenti.size();
                //System.out.println(_lenghtList);
                int i=0;
                boolean exists =false;
            if(!ServerTestoMultiThreaded.Utenti.isEmpty()){                     //inserimento client nella lista del server
                while(_lenghtList < i && exists == false){
                    //System.out.println(i);
                    if(ServerTestoMultiThreaded.Utenti.get(i).getAddress().equals(ClientAddress)){
                        exists = true;
                        posClient=i;
                    }
                    i++;
                    
                }
            }
            if(exists){
                nameClient = ServerTestoMultiThreaded.Utenti.get(posClient).getNickname();
                ServerTestoMultiThreaded.Utenti.get(posClient).setStatus(true);
            }else{
                out.println("Nickname : ");
                line =in.readLine();
                nameClient =line;
                ServerTestoMultiThreaded.Utenti.add(new User(ClientAddress, nameClient, true));
               
            }
            firstTime=false;
            BroadcastListener w= new BroadcastListener(out);
            Thread t = new Thread(w);
            t.start();
            }
            
            
            
            line = in.readLine();

		if(line.equals("!Users")){
                    _lenghtList = ServerTestoMultiThreaded.Utenti.size();
			for(int j=0;j<_lenghtList;j++){
				if(ServerTestoMultiThreaded.Utenti.get(j).getStatus()) out.println(ServerTestoMultiThreaded.Utenti.get(j).getNickname());
			}
		}
            //Manda lo stesso messaggio appena ricevuto con in aggiunta il "nome" del client
            out.println("Server---> " + nameClient + ">> " + line);
            //scrivi messaggio ricevuto su terminale
            System.out.println(nameClient + ": " + line);
            
            ServerTestoMultiThreaded.Broadcast=nameClient + ": " + line;
           } catch (IOException e) {
            System.out.println("lettura da socket fallito");
            System.exit(-1);
           }
        }
        //avviso client uscito disconnesso dalla chat
        try {   
            client.close();
            System.out.println( client + " ha abbandonato la chat!");
        } catch (IOException e) {
            System.out.println("Errore connessione con client: " + client);
        }
	ServerTestoMultiThreaded.Utenti.get(posClient).setStatus(false);
    }
    
}
