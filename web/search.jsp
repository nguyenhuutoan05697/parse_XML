<%-- 
    Document   : search.jsp
    Created on : May 30, 2022, 9:00:40 AM
    Author     : HuuToan
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color ="red">
        Welcome, ${sessionScope.StudentDTO.fullName}
        </font>
        <h1>Search Page</h1>
        <form action="DispatchController">        
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/><br/>
            <input type="submit" value="Search" name="btAction"/>
        </form>
        <br/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>    
        <c:set var="result" value="${requestScope.STUDENT}"/>
        <c:if test="${not empty result}"> 
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Class</th>
                        <th>Full Name</th>
                        <th>Address</th>
                        <th>Sex</th>
                        <th>Status</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="DispatchController" method="POST">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                <input type="text" name="id" value="${dto.id}"  />
                            </td>
                            <td>
                                <input type="text" name="sClass" value="${dto.sClass}" />
                            </td>
                            <td>
                                <input type="text" name="fullName"  value="${dto.fullName}" />
                            </td>
                            <td>
                                <input type="text" name="address"  value="${dto.address}" />
                            </td>
                            <td>
                                <input type="text" name="sex"  value="${dto.sex}" />
                            </td>
                            <td>
                                <input type="text" name="status"  value="${dto.status}" />
                            </td>
                            <td>
                                <c:url var="deleteStudent" value="DispatchController">
                                    <c:param name="btAction" value="delete" />
                                    <c:param name="id" value="${dto.id}" />
                                    <c:param name="lastSearchValue" value="${searchValue}" />                                      
                                </c:url>
                                <a href="${deleteStudent}">Delete</a>
                            </td>


                            <td>
                                <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                <input type="submit" name="btAction" value="update"/>
                            </td> 
                        </tr>
                    </form>                               
                </c:forEach>                               
            </tbody>
        </table>
    </c:if>  
    <c:if test="${empty result}">
        <font color="red">
        No result record!!!
        </font>
    </c:if>

</body>
</html>
