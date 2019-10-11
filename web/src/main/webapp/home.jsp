<%@ page language="java"
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Home Page</title>
	</head>
	<body
        <p>Hello ${cookie._user.value}. You are login</p>
	</body>
</html>
