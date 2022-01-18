import java.util.Random;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;

class Corsa extends Thread{
    private String codice;
    private String linea;
    private String partenza;
    private String bus;
    public Corsa(String codice, String linea, String partenza, String bus){
        this.codice=codice;
        this.linea=linea;
        this.partenza=partenza;
        this.bus=bus;
        
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
           connection = DriverManager.getConnection("jdbc:ucanaccess://C:/java/apache-tomcat-8.5.72/webapps/BusPosition/BusPositionDB.accdb"); //sistemare per trovare sempre il DB
           //System.out.println("tutto ok");
           
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
           
           numero_fermate = Integer.parseInt(nfermate);
           Random rnd = new Random();
           double tempo=0;
           for(int fermata=0; i<numero_fermate; fermata++)
           {
               String queryFermata = "INSERT INTO Corsa(fermata) VALUES('"+fermata+"') WHERE codice= '"+ codice +"';";
               statement = connection.createStatement();
               statement.executeUpdate(queryFermata);
               
               //tempo=rng.nextDouble(5)+1;
               //TimeUnit.MINUTES.sleep(1);
               
           }
           
           
           
           
       } catch (Exception e) {
            System.out.println("Errore: Impossibile Connettersi al DB");
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
                   System.out.println("Errore nella chiusura connessione");
               }
           }
       
    }
      
    }
}

