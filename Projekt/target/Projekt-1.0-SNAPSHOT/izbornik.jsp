<%-- 
    Document   : izbornik
    Created on : 13. ožu 2023., 11:50:12
    Author     : Lorena
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/izgled.css">
        <meta charset="UTF-8">
        <title>Izbornik</title>
        <style>
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

            form {
                display: flex;
                justify-content: center;
            }
            button {
                background-color: lightcyan;
                border-radius: 12px;
                border-width: 0;
                color: #000000;
                cursor: pointer;
                display: inline-block;
                font-family: "Haas Grot Text R Web", "Helvetica Neue", Helvetica, Arial, sans-serif;
                font-size: 24px;
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
            }
            input[type=submit] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                box-sizing: border-box;
                width: 40%;
                border: solid #5B6DCD 5px;
                padding: 5px;
            }
        </style>
    </head>
    <div id="container">
        <body>
            <h1>Izbornik 📃</h1>
            <form action="Prozor.jsp">
                <!--        <input type="submit" value="Kreiranje korisnika" />-->
                <button class=\"button" role=\"button\">Kreiranje korisnika</button>
            </form>
            <br>
            <form action="Pretraga.jsp">
                <button class=\"button" role=\"button\">Pretraga i upisivanje na ugovor</button>
            </form>
        </body>
    </div>
</html>
