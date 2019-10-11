<%@ page language="java"
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Login Page</title>
	</head>

	<body>

		<form action="login" method="post">
            <div>
                <div class="field">
                    Please enter your username
                    <input type="text" name="username"/><br>
                </div>
                <div class="field">
                     Please enter your password
                     <input type="text" name="pass"/><br>
                </div>
            </div>
			<input type="submit" value="submit">
            <c:if test="${not empty Error_Message}">
                <c:out value='${requestScope.Error_Message}'/>
            </c:if>
		</form>
	</body>
</html>
