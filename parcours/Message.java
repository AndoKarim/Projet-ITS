/****************************************************/
/* Nom de classe : Message                          */
/* Description   : Message emis par un utilisateur  */
/* Version       : 1.0                              */ 
/* Date          : 15/10/2014                       */
/* Auteurs       : Perrel Ali                       */
/****************************************************/

package parcours;

import java.util.Date;

public class Message {
    private Date date;
    private String user;
    private String contenu;
    
    public Message(String u, String c){
        this.date=new Date();
        this.user=u;
        this.contenu=c;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public String getUser(){
        return this.user;
    }
    
    public String getContenu(){
        return this.contenu;
    }
}
