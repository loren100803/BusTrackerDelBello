package delbello;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class scegliLinea extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
       Connection connection=null;
       HttpSession session = req.getSession();
       PrintWriter printwriter = res.getWriter();
       //RequestDispatcher rd = req.getRequestDispatcher("protetto");
       res.setContentType("text/html");
       String[] linee = new String[100];
       
       int dim=0;
       
       try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
       try 
       {
           connection = DriverManager.getConnection("jdbc:ucanaccess://" + req.getServletContext().getRealPath("/") + "BusPositionDB.accdb");
           
           String username=(String)session.getAttribute("nome");
            
           String query = "SELECT DISTINCT linea FROM Corsa;";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           String risultato=null;
           
           printwriter.println("<h2 align='center'>Benvenuto "+username+"</h2>");
           printwriter.println("<h3>Scegli la linea da visualizzare: </h3>");
           
           for(int i=0; resultSet.next(); i++)
           {
               
               risultato=resultSet.getString(1);
               linee[i]=risultato;
               
           }
           
           printwriter.println("<form action='visualizzaLinea.jsp' method='post' id='form'>");
           printwriter.println("<select id='linea' name='linea'>");
           for(int i=0; linee[i]!=null; i++)
           {
                printwriter.println("<option value='"+linee[i]+"'>"+linee[i]+"</option>");
           }
           printwriter.println("<input type='submit' id='sub' name='sub' value='Scegli'>");
           printwriter.println("</form>");
           
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


