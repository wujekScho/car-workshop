<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 27.03.2019
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edytuj klienta</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Klienci</h2>
<form method="post" action="EditCustomer">
    <label>Imię <input name="name" type="text" value="${customer.name}" required/></label><br>
    <label>Nazwisko <input name="surname" type="text" value="${customer.surname}" required/></label><br>
    <label>Data urodzenia <input name="birthDate" type="date" value="${customer.birthDate}"/></label><br>
    <input type="hidden" name="id" value="${customer.id}">
    <button type="submit">Edytuj dane klienta</button>
    <br>
</form>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
</html>

