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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gasperini Luca, Casetta Lorenzo
 */
class SocketWorker implements Runnable {
  public Socket client;
    BufferedReader in = null;
        PrintWriter out = null;
  private String lastMSG=null;
  private int posGP;
    //Constructor: inizializza le variabili
    SocketWorker(Socket client) {
        this.client = client;
        System.out.println("Connesso con: " + client);
    }

    // Questa e' la funzione che viene lanciata quando il nuovo "Thread" viene generato
    public void run(){
        
        
        boolean firstTime =true;
        posGP=-1;
        
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
            
            if(ServerTestoMultiThreaded.Gruppi.isEmpty()){
                out.println("Non esistono chat attive");
            }else{
                out.println("Group chat attive:");
                for(int j=0;j<ServerTestoMultiThreaded.Gruppi.size();j++){
                    if(ServerTestoMultiThreaded.Gruppi.get(i).userExists(nameClient)){
                        out.println(ServerTestoMultiThreaded.Gruppi.get(j).getTitle());
                    }
                }
            }
            out.println("Comandi disponibili: !Users, !CreateGP, !InviteUser, !JoinGP, !ExitGP, !Help");
            }
            
            
            for(int i=0;i<ServerTestoMultiThreaded.Gruppi.size();i++){
                if(ServerTestoMultiThreaded.Gruppi.get(i).userExists(nameClient)){
                    if(posGP<0){
                        out.println("Sei stato invitato al gruppo "+ServerTestoMultiThreaded.Gruppi.get(i).getTitle()+", scrivi !joinGP e il nome del gruppo per entrare");
                    }else if(posGP!=i)
                        out.println("Sei stato invitato al gruppo "+ServerTestoMultiThreaded.Gruppi.get(i).getTitle()+", scrivi !joinGP e il nome del gruppo per entrare");
                }
            }
		  if(posGP<0){
			  out.print(">");
		  }
		  else{
			  out.print(ServerTestoMultiThreaded.Gruppi.get(posGP).getTitle()+">")
		  }
		  
            
            
            line = in.readLine();
            
            if(line!=null){
		if(line.equals("!Users")){                                                     //mostra lista utenti online
			System.out.println(nameClient+" ha richiesto il comando !Users");
                    _lenghtList = ServerTestoMultiThreaded.Utenti.size();
			for(int j=0;j<_lenghtList;j++){
				if(ServerTestoMultiThreaded.Utenti.get(j).getStatus()) out.println(ServerTestoMultiThreaded.Utenti.get(j).getNickname());
			}
		}else if(line.equals("!CreateGP")){                                           //crea group chat
			System.out.println(nameClient+" ha richiesto il comando !CreateGP");
                    out.println("Nome del gruppo ");
                    String _title = in.readLine();
                    out.println("Descrizione del gruppo ");
                    ServerTestoMultiThreaded.Gruppi.add(new Group(_title, in.readLine(), ServerTestoMultiThreaded.Utenti.get(posClient)));
                    posGP=ServerTestoMultiThreaded.Gruppi.size()-1;
                }else if(line.equals("!InviteUser")){                                       //invita utente
                    if(posGP<0){
                        out.println("Non sei all'interno di un group chat");
                    }else{
                    out.println("Utente da invitare : ");
                    _lenghtList = ServerTestoMultiThreaded.Utenti.size();
			for(int j=0;j<_lenghtList;j++){
				if(ServerTestoMultiThreaded.Utenti.get(j).getStatus()&&ServerTestoMultiThreaded.Utenti.get(j).getNickname().equals(in.readLine())){
                                    out.println(ServerTestoMultiThreaded.Utenti.get(j).getNickname() + " invitato");
                                    ServerTestoMultiThreaded.Gruppi.get(posGP).setNewUser(ServerTestoMultiThreaded.Utenti.get(j));
                                }
                        }
                    }
                }else if(line.equals("!JoinGP")){                           //entra in una group chat
			System.out.println(nameClient+" ha richiesto il comando !JoinGP");
                    out.println("Scrivi il nome del gruppo");
                    for(int i=0;i<ServerTestoMultiThreaded.Gruppi.size();i++){
                        if(ServerTestoMultiThreaded.Gruppi.get(i).userExists(nameClient)&& ServerTestoMultiThreaded.Gruppi.get(i).getTitle().equals(in.readLine())){
                            posGP=i;
                            out.println("benvenuto");
                        }
                    }
                }else if(line.equals("!ExitGP")){                                   //esci dalla chat di gruppo
			System.out.println(nameClient+" ha richiesto il comando !ExitGP");
                    if(posGP<0){
                        out.println("Non sei all'interno di una group chat");
                    }else{
                        ServerTestoMultiThreaded.Gruppi.get(posGP).removeUser(ServerTestoMultiThreaded.Utenti.get(posClient));
                        posGP=-1;
                        out.println("Hai abbandonato la chat");
                    
                    }
                    
                }else if(line.equals("!ListGP")){
			System.out.println(nameClient+" ha richiesto il comando !ListGP");
			for(int i=0;i<ServerTestoMultiThreaded.Gruppi.size();i++){
                        	out.println("Nome: "+ServerTestoMultiThreaded.Gruppi.get(posGP).getTitle());
				out.println("Descrizione: "+ServerTestoMultiThreaded.Gruppi.get(posGP).getDescription());
                    }
		}
		    else if(line.equals("!Help")){
			    System.out.println(nameClient+" ha richiesto il comando !Help");
			out.println("!Users -> comando per visualizzare gli utenti attualmente online sul server");
			out.println("!CreateGP -> comando per creare una stanza di chat privata");
			out.println("!InviteUser -> comando per invitare un utente online sul server in una stanza di chat privata");
			out.println("!JoinGP -> dopo aver ricevuto un invito, si può usare questo comando per accedere alla stanza di chat privata");
			out.println("!ExitGP -> comando per uscire dalla stanza di chat privata");
			out.println("!ListGP -> elenca tutte le stanze di chat private esistenti sul server");
		}
                else{
                     //Manda lo stesso messaggio appena ricevuto con in aggiunta il "nome" del client
                    out.print("      -Inviato");
			out.println("");
                    //scrivi messaggio ricevuto su terminale
                    System.out.println("-Ricevuto messaggio da "nameClient + " : " + line);
                }
            }
                
                
           lastMSG= nameClient + ": " + line;
            ServerTestoMultiThreaded.Update(nameClient + ": " + line, posGP);
            
           
           } catch (IOException e) {
           
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
    
    public void sendMessage(String text, int GP){
        if(!text.equals(lastMSG) && GP==posGP){
            out.println(text);
        }
    }    
}
