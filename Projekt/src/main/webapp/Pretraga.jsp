<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <h1>Pretraga</h1>
        <form id="searchForm" method="get">
            <label for="searchQuery">Pretraga:</label>
            <input type="text" id="searchQuery" name="searchQuery">
            <button type="submit">Pretraži</button>
        </form>
        <br>
        <span style="display: inline-block;">Odabrani korisnici:</span>
        <div id="searchResults">
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
                        alert("Dokument je uspješno popunjen.");
                    },
                    error: function () {
                        // Display an error message to the user
                        alert("Dogodila se greška prilikom popunjavanja dokumenta.");
                    }
                });
            }
        </script>
    </body>
</html>