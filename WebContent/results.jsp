<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.DictionaryInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>this is result page
	<%-- <%
		Book book = (Book) request.getAttribute("results");
	%>

	Book Name:
	<%=book.getBookname()%><br /> ISBN:
	<%=book.getISBN()%>
	<br /> Book Name: ${ results.bookname }
	<br /> ISBN: ${ results.ISBN } --%>
</body>
</html>