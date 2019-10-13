<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Set user as Sommelier Page</title>
	</head>

	<body>
		<form action="setassomm" method="post">
            <div>
                <div class="field">
                    Please enter username
                    <input type="text" name="name"/><br>
                </div>
			    <input type="submit" value="submit">
			</div>
		</form>
		<c:if test="${not empty Error_Message}">
             <c:out value='${requestScope.Error_Message}'/>
        </c:if>
		<c:if test="${not empty sommelier}">
             <c:out value='${requestScope.sommelier}'/>
        </c:if>
	</body>
</html>