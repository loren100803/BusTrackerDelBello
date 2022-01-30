
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class main extends HttpServlet
{
    public static void main (String[] args) throws ServletException, IOException
    {
    
    
       Connection connection=null;
       
       try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
       try 
       {
           connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/loren/Downloads/apache-tomcat-8.5.72/webapps/BusPosition/BusPositionDB.accdb"); //sistemare per trovare sempre il DB
           System.out.println("tutto ok");
           //C:/Users/loren/Downloads/apache-tomcat-8.5.72/webapps/BusPosition/BusPositionDB.accdb
           //C:\java\apache-tomcat-8.5.72\webapps\BusPosition 
           String query = "SELECT codice, linea, partenza, bus FROM Corsa";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           String codice=null;
           String linea=null;
           String partenza=null;
           String bus=null;
           int numeroThread=0;
           while(resultSet.next())
           {
                codice=resultSet.getString(1);
                linea=resultSet.getString(2);
                partenza=resultSet.getString(3);
                bus=resultSet.getString(4);
                numeroThread++;
                Corsa corsa=new Corsa(codice, linea, partenza, bus, numeroThread);
                corsa.start();
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
