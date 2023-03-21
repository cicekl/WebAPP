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
        <style>
            title{

                margin-left: 600px;
            }

            body {
                background-color: lightgray;
                opacity: 0.8;
                background-image: radial-gradient(#444cf7 0.5px, #e5e5f7 0.5px);
                background-size: 10px 10px;
            }

            h1 {
                text-align: center;
                text-shadow: 1px 1px;
            }
            label {
                color: black;
                padding: 10px;
                text-align:left;
                line-height:150%;
                display: block;
                margin-left: 300px;

            }
            input[type=text] {
                padding:10px;
                border:0;
                box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
                justify-content: center;
                margin-left: 300px;
            }
            button {
                background-color: lightcyan;
                border-radius: 12px;
                border-width: 0;
                color: #000000;
                cursor: pointer;
                display: inline-block;
                font-family: "Haas Grot Text R Web", "Helvetica Neue", Helvetica, Arial, sans-serif;
                font-size: 25px;
                font-weight: 500;
                line-height: 20px;
                list-style: none;
                margin: 0;
                padding: 10px 12px;
                text-align: center;
                transition: all 200ms;
                vertical-align: baseline;
                white-space: nowrap;
                user-select: none;
                -webkit-user-select: none;
                touch-action: manipulation;
                margin-left: 300px;
            }
        </style>
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/ProzorServlet">
            <h1>Unos korisnika ✏️</h1>
            <c:if test="${not empty poruka}">
                <p style="color: green">${poruka}</p>
            </c:if>
            <c:if test="${not empty greska}">
                <p style="color: red">${greska}</p>
            </c:if>
            <form action="${pageContext.request.contextPath}/prozor" method="POST">
                <label for="ime">Ime:</label>
                <input type="text" id="ime" name="ime"><br> <br>
                <label for="prezime">Prezime:</label>
                <input type="text" id="prezime" name="prezime"><br><br>
                <label for="email">Email:</label>
                <input type="text" id="email" name="email"> <br> <br>
                <label for="oib">OIB:</label>
                <input type="text" id="oib" name="oib"> <br><br>
                <label for="iban">IBAN:</label>
                <input type="text" id="iban" name="iban"> <br><br>
                <label for="ulica">Ulica:</label>
                <input type="text" id="ulica" name="ulica"> <br><br>
                <label for="kucniBroj">Kucni broj:</label>
                <input type="text" id="kucniBroj" name="kucniBroj"> <br><br>
                <button type="submit">Unos korisnika</button>
            </form>
    </body>

</html>