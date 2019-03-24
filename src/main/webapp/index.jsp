<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: schodziak
  Date: 15.03.19
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Aktualne naprawy</h2>
<table>
    <tr>
        <th>Brand</th>
        <th>Model</th>
        <th>Received</th>
        <th>Problem description</th>
        <th>Status</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${services}" var="service">
        <tr>
            <td>${service.vehicleBrand}</td>
            <td>${service.vehicleModel}</td>
            <td>${service.recived}</td>
            <td>${service.problemDescription}</td>
            <td>${service.status}</td>
            <td></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>
