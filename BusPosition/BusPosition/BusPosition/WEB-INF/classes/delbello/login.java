package delbello;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class login extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
       Connection connection=null;
       HttpSession session = req.getSession(true);
       PrintWriter printwriter = res.getWriter();
       RequestDispatcher rd = req.getRequestDispatcher("scegliLinea");
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
            
           String query = "SELECT username, tipo FROM Utenti WHERE username= '"+ username +"' AND password= '"+ password +"'";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           String risultato=null;
           String tipo=null;
           while(resultSet.next())
           {
                risultato=resultSet.getString(1);
                tipo=resultSet.getString(2); //prede tipo che è il secondo nella query, dopo username
           }
           if(risultato==null)
           {
             printwriter.println("<p align='center'>Errore di login, credenziali errate</p> <br> <p>Torna alla schermata di <a href='index.html'>login</a></p>");
           
           }
           else
                {
                    
                    //printwriter.println("Benvenuto"+risultato);
                    session.setAttribute("nome",username);
                    if(tipo.equals("admin"))
                        res.sendRedirect(req.getContextPath()+"/AdminPage.html");  
                        else
                            rd.forward(req,res); 
                    
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


