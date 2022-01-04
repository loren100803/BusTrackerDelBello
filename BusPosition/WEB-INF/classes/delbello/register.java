package delbello;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class register extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
       Connection connection=null;
       HttpSession session = req.getSession(true);
       PrintWriter printwriter = res.getWriter();
       res.setContentType("text/html");

       
       try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
       try 
       {
           connection = DriverManager.getConnection("jdbc:ucanaccess://" + req.getServletContext().getRealPath("/") + "BusPositionDB.accdb");
           //printwriter.println("tutto ok");
           
           String username=req.getParameter("username");
            //printwriter.println(username);
            
           String password=req.getParameter("password");
            //printwriter.println(password);
            
           String mail=req.getParameter("mail");
            //printwriter.println(mail);
            
           String query = "INSERT INTO Utenti VALUES('"+ username +"' , '"+ password +"' , '"+ mail +"', 'utente');";
           Statement statement = connection.createStatement();
           statement.executeUpdate(query);
           
           printwriter.println("<p align='center'>Registrazione effettuata!</p> <br> <p>Torna alla schermata di <a href='index.html'>login</a></p>");
           
           
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


