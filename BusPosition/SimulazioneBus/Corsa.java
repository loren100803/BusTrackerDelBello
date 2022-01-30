import java.util.Random;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.util.Date;

class Corsa extends Thread{
    private String codice;
    private String linea;
    private String partenza;
    private String bus;
    private int numeroThread;
    public Corsa(String codice, String linea, String partenza, String bus, int numeroThread){
        this.codice=codice;
        this.linea=linea;
        this.partenza=partenza;
        this.bus=bus;
        this.numeroThread=numeroThread;
    }
   public void run(){
       
      Connection connection=null;
       
       try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
       try 
       {
           SimpleDateFormat formato=new SimpleDateFormat("HH:mm");
           
           
           
           
           connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/loren/Downloads/apache-tomcat-8.5.72/webapps/BusPosition/BusPositionDB.accdb"); //sistemare per trovare sempre il DB
           System.out.println("tutto ok "+numeroThread);
           
           //C:\java\apache-tomcat-8.5.72\webapps\BusPosition 
           String query = "SELECT numero_fermate FROM Linee WHERE linea='"+ linea +"'";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           
           String nfermate=null;
          
           while(resultSet.next())
           {
                nfermate=resultSet.getString(1);
          
           }
           
           //System.out.println(numero_fermate);
           
           int numero_fermate = Integer.parseInt(nfermate);
           
           Random rnd = new Random();
           int tempo=0;
           
           
           statement = connection.createStatement();
           
           
           String queryFermata;
           while(true)
           {
                
		String ora=formato.format(new Date());
                if(ora.equals(partenza))
                {
                    for(int fermata=0; fermata<numero_fermate; fermata++)
                     {
                      queryFermata = "UPDATE Corsa SET fermata="+fermata+" WHERE codice= '"+ codice +"';";      
                      statement.executeUpdate(queryFermata);
                      System.out.println("Corsa: "+codice+" Linea: "+linea+" Fermata: "+fermata);
                      tempo=rnd.nextInt(3)+1;
                      
                      TimeUnit.MINUTES.sleep(tempo);
               
                         }
                }
           }
           
           
       } catch (Exception e) {
            System.out.println(e);
       }
       finally
       {
           if(connection!=null)
           {
               try
               {
                   connection.close(); 
               }
               catch (Exception e)
               {
                   System.out.println("Errore nella chiusura connessione al thread: "+numeroThread);
               }
           }
       
    }
      
    }
}

