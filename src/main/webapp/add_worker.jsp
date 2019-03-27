<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 24.03.2019
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dodaj pracownika</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/header.jsp"/>
<h2>Pracownicy</h2>
<form action="AddWorker" method="post">
    <label>Imię <input name="name" type="text" required/></label><br>
    <label>Nazwisko <input name="surname" type="text" required/></label><br>
    <label>Adres <input name="address" type="text" required/></label><br>
    <label>Numer telefonu <input name="phoneNumber" type="text" required/></label><br>
    <p id="phoneWarning" class="hidden">Zły format numeru telefonu</p>
    <label>Notatka <input name="note" type="text"/></label><br>
    <label>Stawka godzinowa <input name="ratePerHour" type="number" min="10" step="0.25" required/></label><br>
    <button id="btn1" type="submit">Dodaj pracownika</button>
    <br>
</form>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
<script src="js/checkPhoneNumber.js"></script>
</html>
