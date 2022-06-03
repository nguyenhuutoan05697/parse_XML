<%-- 
    Document   : createStudent
    Created on : Jun 3, 2022, 8:59:56 AM
    Author     : HuuToan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>Create Student</h1>
        <form action="DispatchController" method="POST">
            Id <input type="text" name="txtId" value=""/> <br/>
            Last Name <input type="text" name="txtLastname" value=""/> <br/>
            Middle Name<input type="text" name="txtMiddlename" value=""/> <br/>
            First Name<input type="text" name="txtFirstname" value=""/> <br/>
            Male <input type="checkbox" name="chkMale" value=""/> <br/>
            Address <input type="text" name="txtAddress" value=""/> <br/>  
            Class <input type="text" name="txtClass" value=""/> <br/> 
            <input type="submit" value="Create Student" name="btAction" />          
        </form>
    </body>
</html>
