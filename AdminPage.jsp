<!DOCTYPE HTML>
<html>
  <head>
    <title>Admin Page</title>
  </head>
  <body>
	<h1>ADMIN</h1>
	<h2>Benvenuto amministratore</h2>
	
	<%
		
    
       if(session.getAttribute("nome")==null)
           res.sendRedirect(req.getContextPath()+"/");
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