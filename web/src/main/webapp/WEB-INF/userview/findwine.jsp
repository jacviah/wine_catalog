<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Find Wine Page</title>
	</head>

	<body>
		<form action="findwine" method="post">
            <div>
                <div class="field">
                    Please enter winery
                    <input type="text" name="winery"/><br>
                </div>
                <div class="field">
                     Please enter wine name
                     <input type="text" name="wine"/><br>
                </div>
			    <input type="submit" value="submit">
			</div>
		</form>
		<c:if test="${not empty Error_Message}">
             <c:out value='${requestScope.Error_Message}'/>
        </c:if>
        <c:if test="${not empty wine}">
        <tr>
            <td><c:out value="${wine.name}" /></td>
            <td><c:out value="${wine.winery}" /></td>
            <td><c:out value="${wine.rate}" /></td>
        </tr>
        </c:if>
	</body>
</html>