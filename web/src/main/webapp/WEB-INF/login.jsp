<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
			    <input type="submit" value="submit">
			</div>
            <c:if test="${not empty Error_Message}">
                <c:out value='${requestScope.Error_Message}'/>
            </c:if>
		</form>
	</body>
</html>
