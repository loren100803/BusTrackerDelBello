<!DOCTYPE HTML>
<html>
  <head>
    <title>Admin Page</title>
  </head>
  <body>
		<%@ page import="java.io.*"%>
		<%@ page import="java.util.*"%>
		<%@ page import="javax.servlet.*"%>
		<%@ page import="javax.servlet.http.*"%>
		<%@ page import="java.sql.*"%>
		

	<h1>ADMIN</h1>
	<h2>Benvenuto amministratore</h2>
	
	<%
		
		//String username=session.getAttribute("nome");
	   	
		String username=(String)session.getAttribute("nome");
		
     try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
        Connection connection=null;
       try 
       {
           connection = DriverManager.getConnection("jdbc:ucanaccess://" + request.getServletContext().getRealPath("/") + "BusPositionDB.accdb");
           
           
            
           String query = "SELECT tipo FROM Utenti WHERE username = '"+username+"'";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
		   String tipo;
		   while(resultSet.next())
           {
			   tipo=resultSet.getString(1);
		   }
			
			if(username==null)
				response.sendRedirect(request.getContextPath()+"/");
			
			if(!(username.equals("admin")))
				response.sendRedirect(request.getContextPath()+"/");
	   
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
    %>

	<p>Aggiungi Corsa</p>
	<form action="aggiungiCorsa" method="post" id="form">
		<input type="text" id="codice" name="codice" placeholder="codice">
		<input type="linea" id="linea" name="linea" placeholder="linea">
		<input type="partenza" id="partenza" name="partenza" placeholder="ora partenza">
		<input type="bus" id="bus" name="bus" placeholder="bus">
		<input type="submit" id="sub" name="sub" value="Aggiungi">
	</form>

	<p>Rimuovi Corsa</p>
	<form action="rimuoviCorsa" method="post" id="form">
		<input type="text" id="codice" name="codice" placeholder="codice">
		<input type="submit" id="sub" name="sub" value="Rimuovi">
	</form>

	<p>torna alla shermata di <a href="index.html">login</a></p>

  </body>
</html>