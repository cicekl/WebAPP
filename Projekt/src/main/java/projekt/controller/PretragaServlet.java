package projekt.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ugovor.model.Korisnik;

public class PretragaServlet extends HttpServlet {

    private final String jdbcUrl = "jdbc:mariadb://localhost/ugovor";
    private final String user = "root";
    private final String password = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String searchQuery = request.getParameter("searchQuery");

    try {
        Class.forName("org.mariadb.jdbc.Driver"); // Load the driver

        Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
      //  System.out.println("Connected to database lol");
        String sql = "SELECT * FROM korisnik WHERE ime LIKE ? OR prezime LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + searchQuery + "%");
        statement.setString(2, "%" + searchQuery + "%");
      //  System.out.println("Executing query: " + statement.toString());
        ResultSet resultSet = statement.executeQuery();

        List<Korisnik> korisnici = new ArrayList<>();
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No rows found for search query: " + searchQuery);
        } else {
            while (resultSet.next()) {
                Korisnik korisnik = new Korisnik();
                korisnik.setIme(resultSet.getString("ime"));
                korisnik.setPrezime(resultSet.getString("prezime"));
                korisnik.setOIB(resultSet.getString("OIB"));
                korisnici.add(korisnik);
            //    System.out.println(korisnik.getIme());
             //   System.out.println(korisnici.size());
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(korisnici));

    } catch (SQLException ex) {
        ex.printStackTrace();
        response.getWriter().println("Error: " + ex.getMessage());
    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
    }
}
}
