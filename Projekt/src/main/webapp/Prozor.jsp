<%-- 
    Document   : Prozor
    Created on : 13. ožu 2023., 12:02:18
    Author     : Lorena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Unos korisnika</title>
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/ProzorServlet">
            <h1>Unos korisnika</h1>
            <c:if test="${not empty poruka}">
                <p style="color: green">${poruka}</p>
            </c:if>
            <c:if test="${not empty greska}">
                <p style="color: red">${greska}</p>
            </c:if>
                <form action="${pageContext.request.contextPath}/prozor" method="POST">
                    <label for="ime">Ime:</label>
                    <input type="text" id="ime" name="ime"><br>
                    <label for="prezime">Prezime:</label>
                    <input type="text" id="prezime" name="prezime"><br>
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email"> <br>
                    <label for="oib">OIB:</label>
                    <input type="text" id="oib" name="oib"> <br>
                    <label for="iban">IBAN:</label>
                    <input type="text" id="iban" name="iban"> <br>
                    <label for="ulica">Ulica:</label>
                    <input type="text" id="ulica" name="ulica"> <br>
                    <label for="kucniBroj">Kucni broj:</label>
                    <input type="text" id="kucniBroj" name="kucniBroj"> <br>
                    <input type="submit" value="Unos" />
                </form>
                </body>

                </html>