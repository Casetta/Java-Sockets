/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gasperini Luca
 */
public class User {
    private String i;	//Indirizzo IP
    private String n;	//Nickname
    private boolean s;	//Status utente(online, offline)
    
    public User(String i, String n, boolean s){		//Costruttore completo
        this.i=i;
        this.n=n;
	this.s=s;
    }
    
    public void setAddress(String i){		//Get e set per le variabili
    this.i =i;
}
    public String getAddress(){
        return i;
    }
    
    public void setNickname(String n){
        this.n=n;
    }
    
    public String getNickname(){
        return n;
    }

    public boolean getStatus(){
	return s;
	}

	public void setStatus(boolean o){
		this.s=s;
	}
}
