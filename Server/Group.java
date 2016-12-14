
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gasperini Luca
 */
 import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
 
public class Group {
    public String t;	//Titolo
    public String d;	//Descrizione
    public List<User> u;	//Lista utenti nel gruppo
    
    public User(String t, String d){		//Costruttore completo
        this.t=t;
        this.t=t;
        u= new ArrayList();
    }
    
    public void setTitle(String t){		//Get e set per le variabili
    this.t =t;
}
    public String getTitle(){
        return t;
    }
    
    public void setDescription(String d){
        this.d=d;
    }
    
    public String getDescription(){
        return d;
    }

    public boolean getUserList(){
	return u.toString();
	}

	public void setNewUser(User n){
		u.add(n);
	}
  
  public void removeUser(User o){
    for(int i=0;i<u.size();i++){
      if(u.get(i).equals(o)){
        u.remove(i);
      }
    }
  }
 
}
