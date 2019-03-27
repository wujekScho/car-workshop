<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 27.03.2019
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj klienta</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Klienci</h2>
<form method="post" action="AddCustomer">
    <label>Imię <input name="name" type="text" required/></label><br>
    <label>Nazwisko <input name="surname" type="text" required/></label><br>
    <label>Data urodzenia <input name="birthDate" type="date"/></label><br>
    <button id="btn1" type="submit">Dodaj pracownika</button>
    <br>
</form>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>
