/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package projekt.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ugovor.model.Korisnik;
import static ugovor.util.SearchDatabase.searchDatabase;

/**
 *
 * @author Lorena
 */
@WebServlet(name = "PohraniUDokumentServlet", urlPatterns = {"/PohraniUDokumentServlet"})
public class PohraniUDokumentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        // Get the selected value and document name from the request parameters
        String selectedValue = request.getParameter("searchQuery");
        String documentName = request.getParameter("document");

        // Retrieve the document content from the server
        
        // Document document = new Document("C:\\Users\\" + System.getProperty("user.name") + "\\Ugovori\\ugovoriTemplate\\" + documentName);

        // Populate the document with the selected value
        String[] selectedValues = selectedValue.split(" ");
        selectedValue = selectedValues[2];
        System.out.println(selectedValue.toString());

        String upit = "OIB";
        ArrayList<Korisnik> odabrano = new ArrayList<Korisnik>(searchDatabase(selectedValue, upit));
        
        
        if (!odabrano.isEmpty()) {
            Document document = new Document("C:\\Users\\" + System.getProperty("user.name") + "\\Ugovori\\ugovoriTemplate\\" + documentName.toString() + ".docx");
            System.out.println(document.toString());
            Korisnik k = odabrano.get(0);
            System.out.println(k.getIme());
            document.replace("<ime>", k.getIme(), false, true);
            document.replace("<prezime>", k.getPrezime(), false, true);
            document.replace("<adresa>", k.getUlica(), false, true);
            document.replace("<iban>", k.getIBAN(), false, true);
            document.replace("<email>", k.getEmail(), false, true);
            document.replace("<kucniBroj>", k.getKucniBroj(), false, true);
            document.replace("<oib>", k.getOIB(), false, true);
            
            document.saveToFile("C:\\Users\\" + System.getProperty("user.name") + "\\Ugovori\\" + k.getIme() + k.getPrezime() + ".docx", FileFormat.Docx);
        }

        // Save the updated document
       // String updatedDocumentPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Ugovori\\ugovori\\" + documentName;
      //  document.saveToFile(updatedDocumentPath, FileFormat.Docx);
        // Return a success message to the client
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(jsonObject));
        out.flush();
    }

}
