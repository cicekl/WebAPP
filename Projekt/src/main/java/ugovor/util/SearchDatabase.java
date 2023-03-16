/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ugovor.util;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;
import ugovor.model.Korisnik;

/**
 *
 * @author Lorena
 */
public class SearchDatabase {
    
    public static List<Korisnik> searchDatabase(String query, String upit) {
    List<Korisnik> results = new ArrayList<Korisnik>();
    try {
        Connection conn = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost/ugovor", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Korisnik WHERE " + upit + " LIKE '%" + query + "%'");
        while (rs.next()) {
            Korisnik korisnik = new Korisnik(rs.getString("ime"), rs.getString("prezime"), rs.getString("OIB"), rs.getString("ulica"), rs.getString("kucniBroj"),rs.getInt("ID"),rs.getString("email"),rs.getString("IBAN"));
            results.add(korisnik);
        }
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error searching database: " + e.getMessage());
    }
    return results;
}
    
  public static List<Korisnik> searchDatabase(String query) {
    List<Korisnik> results = new ArrayList<Korisnik>();
    try {
        Connection conn = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost/ugovor", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Korisnik WHERE ime LIKE '%" + query + "%' or prezime LIKE '%" + query + "%'");
        while (rs.next()) {
            Korisnik korisnik = new Korisnik(rs.getString("ime"), rs.getString("prezime"), rs.getString("OIB"), rs.getString("ulica"), rs.getString("kucniBroj"),rs.getInt("ID"),rs.getString("email"),rs.getString("IBAN"));
            results.add(korisnik);
        }
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error searching database: " + e.getMessage());
    }
    return results;
}

    
    
    
}
