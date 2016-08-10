<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <div align="center">
            <fieldset style="width: 500px">
                <h2 style="text-align: center">Classroom Scheduling and Analysis System</h2>
                <form name="loginForm" method="post" action="LoginServlet">
                    Username:
                    <input type="text" name="username"/><br/>
                    Password:
                    <input type="text" name="password"/><br/>
                    <input type="submit" value="Login"/>
                </form>
            </fieldset>
        </div>
    </body>
</html>