<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 24.03.2019
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klienci</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Klienci</h2>
<table>
    <tr>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>Data urodzenia</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.name}</td>
            <td>${customer.surname}</td>
            <td>${customer.birthDate}</td>
            <td><a href="DeleteCustomer?id=${customer.id}">Usuń</a> <a href="EditCustomer?id=${customer.id}">Edytuj</a>
                <a href="ShowCustomerVehicles?id=${customer.id}">Pojazdy</a> <a
                        href="ShowCustomerServices?id=${customer.id}">Zlecenia</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="AddCustomer">Dodaj pracownika</a>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>

