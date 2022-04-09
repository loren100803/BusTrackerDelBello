<html>
	
	<script>
	function loadDoc() {
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		document.getElementById("demo").innerHTML = this.responseText;
	}
	xhttp.open("GET", "infoLinea.txt");
	xhttp.send();
}



</script>
	
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
		<%@ page import="java.util.concurrent.TimeUnit"%>
		
		

		<h1>Autobus in transito: </h1>

		<%

			String linea=request.getParameter("linea");
			Random rnd=new Random();
			SimpleDateFormat formato=new SimpleDateFormat("HH:mm");
			String ora;
			
		
	
			ora=formato.format(new Date());
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
			FileWriter myWriter = new FileWriter(request.getServletContext().getRealPath("/") +"tabLinea.txt", false);	
			BufferedWriter bf = new BufferedWriter(myWriter);	
           
		   //usa il bufferedWriter
		   
           out.println("<table border=1><th>codice</th><th>linea</th><th>partenza</th><th>bus</th><th>fermata</th>");
		  bf.write("<table border=1><th>codice</th><th>linea</th><th>partenza</th><th>bus</th><th>fermata</th>");
           while(resultSet.next())
           {
           		//int fermata=rnd.nextInt(11);
           		
                out.println("<tr>");
				
				bf.write("<tr>");
				
				String r1=resultSet.getString(1);
				String r2=resultSet.getString(2);
				String r3=resultSet.getString(3);
				String r4=resultSet.getString(4);
                int fermata= resultSet.getInt(5);
				
				out.println("<td>"+r1+"</td>");
					
						bf.write("<td>"+r1+"</td>");
					
                	out.println("<td>"+r2+"</td>");
					
						bf.write("<td>"+r2+"</td>");
					
                	out.println("<td>"+r3+"</td>");
					
						bf.write("<td>"+r3+"</td>");
					
                	out.println("<td>"+r4+"</td>");
					
						bf.write("<td>"+r4+"</td>");
                	
                	out.println("<td>"+fermata+"</td>");
					
						bf.write("<td>"+fermata+"</td>");

                out.println("</tr>");
				bf.write("</tr>");
           }
            out.println("</table>");
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
	   
	   
	   TimeUnit.SECONDS.sleep(3);
	   
	   
	//chiusura while(true)

		%>
	</body>
</html>