<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
   int attempts = Integer.parseInt(request.getAttribute("attempts").toString());
%>
<!DOCTYPE html>
<html>
<head>
<style>
	@import url("css/style.css");
</style>
<meta charset="ISO-8859-1">
<title>Hangman</title>
</head>
<body>
<form action="Control" method="post">
	<table>
		<tr>
			<td>type a letter: </td>
			<td><input type="text" name="pValue" maxlength=1 required="required" pattern="[A-Za-z]" title="no numbers or special characters" id=pValue >
				<input type=submit value="Try" name="sAction">
			</td> 
		</tr>
		<tr>
			<td>attempts: <%=request.getAttribute("attempts")%>/7</td>
		</tr>
		<tr>
			<td colspan=2>
				<div style="opacity:<%=(attempts > 0?"1":"0") %>" class=head></div>
				<div style="opacity:<%=(attempts > 1?"1":"0") %>" class=body></div>
				<div style="opacity:<%=(attempts > 2?"1":"0") %>" class=righthand></div>
				<div style="opacity:<%=(attempts > 3?"1":"0") %>" class=lefthand></div>
				<div style="opacity:<%=(attempts > 4?"1":"0") %>" class=leftleg></div>
				<div style="opacity:<%=(attempts > 5?"1":"0") %>" class=rightleg></div>
				<div style="opacity:<%=(attempts > 6?"1":"0") %>" class=hang></div>
			</td>
		</tr>
	</table>
	<div class=foot>
	<% 
	   out.print((String)request.getAttribute("letters"));
	   if(attempts == 7){
		   out.print("<script>alert('You Lost, but you can keep trying!');</script>");
	   }
	%>
	
	</div>
	<input type=hidden name="pAttempts" value=<%=request.getAttribute("attempts")%>>
</form>
<form action="Control" method="post">
	<input type=submit value="New Game" name="sAction">
</form>
<script>document.forms[0].pValue.focus();</script>
</body>
</html>