<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 24.03.2019
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Klient</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Właściciel</h2>
<table>
    <tr>
        <td>Imię</td>
        <td>${customer.name}</td>
    </tr>
    <tr>
        <td>Nazwisko</td>
        <td>${customer.surname}</td>
    </tr>
    <tr>
        <td>Data urodzenia</td>
        <td>${customer.birthDate}</td>
    </tr>
</table>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>

