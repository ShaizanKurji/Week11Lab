<%-- 
    Document   : forgot
    Created on : Nov 21, 2017, 11:24:22 AM
    Author     : 715060
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        
        <p>Please enter your email address to retrieve your password.</p>
        <form action ="forgot"method = "post">
            Email Address: <input type ="text" name ="emailAddress">
            <input type="submit" name ="Submit" value ="Submit"> 
        </form>
           ${emailStatus}
    </body>
</html>
