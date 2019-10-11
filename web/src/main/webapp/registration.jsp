<%@ page language="java"
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Registration Page</title>
	</head>

	<body>
		 <form action="registration" method="post">
		    <div>
                <div class="field">
                    Please enter your username
                    <input type="text" name="username"/><br>
                </div>
                <div class="field">
                    Please enter your username
                    <input type="text" name="pass1"/><br>
                </div>
                <div class="field">
                    Please enter your username
                    <input type="text" name="pass2"/><br>
                </div>
                <input type="submit" value="submit">
	        </div>
            <c:if test="${not empty Error_Message}">
                <c:out value='${requestScope.Error_Message}'/>
            </c:if>
		</form>
	</body>
</html>
