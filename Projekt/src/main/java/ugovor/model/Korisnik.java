/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ugovor.model;

import jakarta.persistence.Entity;

/**
 *
 * @author Lorena
 */

@Entity
public class Korisnik extends Entitet {
    
    private String ime;
    private String prezime;
    private String OIB;
    private String ulica;
    private String kucniBroj;
    private String email;
    private String IBAN;

    public Korisnik(String string, String string0, String string1) {
        this.ime = string;
        this.prezime=string0;
        this.OIB=string1;
    }
    

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOIB() {
        return OIB;
    }

    public void setOIB(String OIB) {
        this.OIB = OIB;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKucniBroj() {
        return kucniBroj;
    }

    public void setKucniBroj(String kucniBroj) {
        this.kucniBroj = kucniBroj;
    }

    public Korisnik() {
    }
    
    

    public Korisnik(String ime, String prezime, String OIB, String ulica, String kucniBroj, Integer ID, String email, String IBAN) {
        super(ID);
        this.ime = ime;
        this.prezime = prezime;
        this.OIB = OIB;
        this.ulica = ulica;
        this.kucniBroj = kucniBroj;
        this.email = email;
        this.IBAN = IBAN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public String toString() {
        return ime+ " " +prezime;
    }
    
    
    
}
