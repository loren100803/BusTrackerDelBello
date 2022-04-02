<html>
	<head>
		<title>visualizza Linea</title>
	</head>
	<body>
		<%@ page import="java.io.*"%>
		<%@ page import="java.util.*"%>
		<%@ page import="javax.servlet.*"%>
		<%@ page import="javax.servlet.http.*"%>
		<%@ page import="java.sql.*"%>
		<%@ page import="java.text.SimpleDateFormat"%>
		<%@ page import="java.util.Date"%>
		<%@ page import="java.util.Random"%>
		<%@ page import="java.io.FileWriter"%>

		<h1>Autobus in transito: </h1>

		<%

			String linea=request.getParameter("linea");
			Random rnd=new Random();
			SimpleDateFormat formato=new SimpleDateFormat("HH:mm");
			String ora=formato.format(new Date());
			out.println(ora);

	    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
        Connection connection=null;
       try 
       {
           connection = DriverManager.getConnection("jdbc:ucanaccess://" + request.getServletContext().getRealPath("/") + "BusPositionDB.accdb");
           //printwriter.println("tutto ok");
           
            
           String query = "SELECT * FROM Corsa WHERE partenza<'"+ora+"' AND linea='"+linea+"'";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
		   
		   
		   
		   

		   
		try {   
			FileWriter myWriter = new FileWriter(request.getServletContext().getRealPath("/") +"infoLinea.txt", false);	
			BufferedWriter bf = new BufferedWriter(myWriter);	
           
		   //usa il bufferedWriter
		   
           //out.println("<table border=1><th>codice</th><th>linea</th><th>partenza</th><th>bus</th><th>fermata</th>");
		  bf.write("<table border=1><th>codice</th><th>linea</th><th>partenza</th><th>bus</th><th>fermata</th>");
           while(resultSet.next())
           {
           		int fermata=rnd.nextInt(11);
           		
                //out.println("<tr>");
				
				bf.write("<tr>");
                
				//out.println("<td>"+resultSet.getString(1)+"</td>");
					
						bf.write("<td>"+resultSet.getString(1)+"</td>");
					
                	//out.println("<td>"+resultSet.getString(2)+"</td>");
					
						bf.write("<td>"+resultSet.getString(2)+"</td>");
					
                	//out.println("<td>"+resultSet.getString(3)+"</td>");
					
						bf.write("<td>"+resultSet.getString(3)+"</td>");
					
                	//out.println("<td>"+resultSet.getString(4)+"</td>");
					
						bf.write("<td>"+resultSet.getString(4)+"</td>");
                	
                	if(fermata==0)
                	{
                		//out.println("<td>Non ancora partito</td>");
							bf.write("<td>Non ancora partito</td>");
                	}
                		else if(fermata==10)
                		{
                			//out.println("<td>Capolinea</td>");
							bf.write("<td>Capolinea</td>");
                		}
                		else
                			//out.println("<td>"+fermata+"</td>");
							bf.write("<td>"+fermata+"</td>");

                //out.println("</tr>");
				bf.write("</tr>");
           }
            //out.println("</table>");
			bf.write("</table>");
			
			bf.flush();
			bf.close();
			
			
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("errore");
			e.printStackTrace();
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

		%>
	</body>
</html>