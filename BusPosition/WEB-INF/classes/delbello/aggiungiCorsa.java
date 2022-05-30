package delbello;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class aggiungiCorsa extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
       Connection connection=null;
       HttpSession session = req.getSession();
       PrintWriter printwriter = res.getWriter();
       res.setContentType("text/html");

       if(session.getAttribute("nome")==null)
           res.sendRedirect(req.getContextPath()+"/");
       try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
       try 
       {
           connection = DriverManager.getConnection("jdbc:ucanaccess://" + req.getServletContext().getRealPath("/") + "BusPositionDB.accdb");
           //printwriter.println("tutto ok");
           
           String codice=req.getParameter("codice");
            //printwriter.println(username);
            
           String linea=req.getParameter("linea");
            //printwriter.println(password);
            
           String partenza=req.getParameter("partenza");
            //printwriter.println(mail);
            
           String bus=req.getParameter("bus");
           
           int ferm=0;
            
           String query = "INSERT INTO Corsa VALUES('"+ codice +"' , '"+ linea +"' , '"+ partenza +"', '"+ bus +"', '"+ferm+"');";
           Statement statement = connection.createStatement();
           statement.executeUpdate(query);
           
           printwriter.println("<p align='center'>Corsa aggiunta!</p> <br> <p>Torna alla schermata di <a href='AdminPage.jsp'>amministrazione</a></p>");
           
           
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
                   System.out.println("Errore nella chiusura connessione");
               }
           }
       }
    }
}
