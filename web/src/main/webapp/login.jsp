<%@ page language="java"
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
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

                <%if(request.getAttribute("errorMessage")!=null) {
                    out.println((String)request.getAttribute("errorMessage"));
                }%>
		</form>
	</body>
</html>
