/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package projekt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ugovor.controller.ObradaKorisnik;
import ugovor.model.Korisnik;
import ugovor.util.OfirException;

/**
 *
 * @author Lorena
 */
@WebServlet(name = "ProzorServlet", urlPatterns = {"/ProzorServlet"})
public class ProzorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ObradaKorisnik obrada;

    public ProzorServlet() {
        super();
        obrada = new ObradaKorisnik();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // retrieve input fields from request
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String email = request.getParameter("email");
        String oib = request.getParameter("oib");
        String iban = request.getParameter("iban");
        String ulica = request.getParameter("ulica");
        String kucniBroj = request.getParameter("kucniBroj");

        ObradaKorisnik ok = new ObradaKorisnik();
        Korisnik k = new Korisnik();
        k.setIme(ime);
        k.setPrezime(prezime);
        k.setEmail(email);
        k.setIBAN(iban);
        k.setKucniBroj(kucniBroj);
        k.setUlica(ulica);
        k.setOIB(oib);

        ok.setEntitet(k);
        try {
            session.removeAttribute("greska");
            ok.create();
            session.setAttribute("poruka", "Korisnik uspješno spremljen!");
        } catch (OfirException ex) {
            session.removeAttribute("poruka");
            session.setAttribute("greska", ex.getPoruka());
       
        }

        response.sendRedirect(request.getContextPath() + "/Prozor.jsp");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
