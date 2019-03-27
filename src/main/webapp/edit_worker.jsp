<%--
  Created by IntelliJ IDEA.
  User: Scho
  Date: 27.03.2019
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edycja pracownika</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>Pracownicy</h2>
<form action="EditWorker" method="post">
    <label>Imię <input name="name" type="text" value="${worker.name}" required/></label><br>
    <label>Nazwisko <input name="surname" type="text" value="${worker.surname}" required/></label><br>
    <label>Adres <input name="address" type="text" value="${worker.address}" required/></label><br>
    <label>Numer telefonu <input name="phoneNumber" type="text" value="${worker.phoneNumber}" required/></label><br>
    <p id="phoneWarning" class="hidden">Zły format numeru telefonu</p>
    <label>Notatka <input name="note" type="text" value="${worker.note}"/></label><br>
    <label>Stawka godzinowa <input name="ratePerHour" type="number" min="10" step="0.25" value="${worker.ratePerHour}"
                                   required/></label><br>
    <input type="hidden" value="${worker.id}" name="id">
    <button id="btn1" type="submit">Edytuj dane pracownika</button>
    <br>
    <br>
</form>
<jsp:include page="WEB-INF/footer.jsp"/>
</body>
<script src="js/checkPhoneNumber.js"></script>
</html>
