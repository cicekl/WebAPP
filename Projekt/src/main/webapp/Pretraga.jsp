<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search</title>
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
            #pretrazi {
                background-color: lightcyan;
                border-radius: 12px;
                border-width: 0;
                color: #000000;
                cursor: pointer;
                display: inline-block;
                font-family: "Haas Grot Text R Web", "Helvetica Neue", Helvetica, Arial, sans-serif;
                font-size: 20px;
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
                margin-left: 0px;
            }
            #populateBtn {
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
            label {
                color: black;
                padding: 10px;
                text-align:left;
                line-height:150%;
                display: block;
                margin-left: 290px;
                font-size: 25px;

            }
            input[type=text] {
                padding:10px;
                border:0;
                box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
                justify-content: center;
                margin-left: 300px;
            }
            select {


                border: none;
                padding: 5px;
                margin-left: 300px;
                width: 20%;
                font-family: inherit;
                font-size: inherit;
                cursor: inherit;
                line-height: inherit;
            }
            span {
                margin-left: 300px;
                font-size: 25px;
            }
            p {
                margin-left: 300px;
                font-size: 25px;
            }

        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <h1>Pretraga 🔎</h1>
        <form id="searchForm" method="get">
            <label for="searchQuery">Pretraga:</label>
            <input type="text" id="searchQuery" name="searchQuery">
            <button id="pretrazi" type="submit">Pretraži</button>
        </form>
        <br>
        <span style="display: inline-block;">Odabrani korisnici:</span> <br>
        <div id="searchResults"> <br>
            <p>Pretražite korisnika.</p>
        </div>
        <br>
        <form>
            <label for="document-select">Odaberite dokument:</label>
            <select id="document-select" name="document" onchange="getSelectedDocument()">
                <option value="ugovorPotpora">ugovorPotpora</option>
                <option value="ugovorSport">ugovorSport</option>
            </select>
        </form>
        <br>
        <br>
        <button id="populateBtn" type="button" onclick="populateDocument()">Pohrani u ugovor</button>

        <script>
            $(document).ready(function () {
                $("#searchForm").submit(function (event) {
                    event.preventDefault();
                    $.ajax({
                        type: "GET",
                        url: "${pageContext.request.contextPath}/PretragaServlet",
                        data: {
                            searchQuery: $("#searchQuery").val(),
                            document: $("#document-select").val()
                        },
                        dataType: "json",
                        success: function (result) {
                            if (result.length === 0) {
                                $("#searchResults").html("<p>Nema rezultata.</p>");
                            } else {

                                var resultsHtml = "<select id='searchResultsSelect'>";
                                $.each(result, function (index, value) {

                                    resultsHtml += "<option value='" + value.ime + " " + value.prezime + " " + value.OIB + "'>" + value.ime + " " + value.prezime + " " + value.OIB + "</option>";
                                });
                                resultsHtml += "</select>";
                                $("#searchResults").html(resultsHtml);

                                // Set up event listener for selecting an option
//                                $("#searchResultsSelect").on("change", function () {
//                                    var selectedValue = $(this).val();
//                                    alert("Odabrali ste: " + selectedValue);
//                                    // You can perform any other action with the selected value here
//                                });
                            }
                        },
                        error: function () {
                            $("#searchResults").html("<p>Dogodila se pogreška.</p>");
                        }
                    });
                });
            });
            function populateDocument() {
                // Get the selected value and document name
                var selectedValue = $("#searchResultsSelect").val();
                var documentName = $("#document-select").val();

                // Make an AJAX request to the PohraniUDokumentServlet to populate the document
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/PohraniUDokumentServlet",
                    data: {
                        searchQuery: selectedValue,
                        document: documentName
                    },
                    dataType: "json",
                    success: function (result) {
                        // Display a success message to the user
                        alert("Dokument je uspješno popunjen.😄");
                    },
                    error: function () {
                        // Display an error message to the user
                        alert("Dogodila se greška prilikom popunjavanja dokumenta.😬");
                    }
                });
            }
        </script>
    </body>
</html>