<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Add Wine Page</title>
	</head>

	<body>
		<form action="addwine" method="post">
            <div>
                <div class="field">
                    Please enter winery
                    <input type="text" name="winery"/><br>
                </div>
                <div class="field">
                     Please enter wine name
                     <input type="text" name="wine"/><br>
                </div>
                <div class="field">
                    Please enter country
                    <input type="text" name="country"/><br>
                </div>
                <div class="field">
                    Please enter region
                    <input type="text" name="region"/><br>
                </div>
                <div class="field">
                     Please enter grape
                     <input type="text" name="grape"/><br>
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
            <td><c:out value="${wine.region}" /></td>
        </tr>
        </c:if>
	</body>
</html>