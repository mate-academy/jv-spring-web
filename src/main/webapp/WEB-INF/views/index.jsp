<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index page</title>
</head>
<body>
<form method="post" id="redirect"></form>
<h1>Welcome to spring service</h1>
<table>
    <thead>
    <th>Id</th>
    <th>First name</th>
    <th>Last name</th>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}"/>
            </td>
            <td>
                <c:out value="${user.firstName}"/>
            </td>
            <td>
                <c:out value="${user.lastName}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/users/delete?id=${user.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div style="color: brown">Count users with service: ${count}</div>
</body>
</html>
