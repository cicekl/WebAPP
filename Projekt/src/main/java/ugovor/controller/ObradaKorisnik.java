/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ugovor.controller;

import java.util.List;
import ugovor.model.Korisnik;
import ugovor.util.DovlacenjeOIBA;
import ugovor.util.OfirException;

/**
 *
 * @author Korisnik
 */
public class ObradaKorisnik extends Obrada<Korisnik> {

    @Override
    public List<Korisnik> čitanje() {
        return session.createQuery("from Korisnik", Korisnik.class).list();
    }

    @Override
    protected void kontrolaUnos() throws OfirException {
        kontrolaPrazno();
        kontrolaIme();
        kontrolaPrezime();
        konrolaOib();
        kontrolaIban();

    }

    @Override
    protected void kontrolaPromjena() throws OfirException {
        kontrolaPrazno();
        kontrolaIme();
        kontrolaPrezime();
        konrolaOib();
        kontrolaIban();
    }

    @Override
    protected void kontrolaBrisanje() throws OfirException {

    }

    private void konrolaOib() throws OfirException {
        if (!DovlacenjeOIBA.kontrolaOIB((entitet.getOIB()))) {
            throw new OfirException("OIB nije u dobrom formatu");

        }
        kontrolaOIBDupli();
    }

    private void kontrolaIme() throws OfirException {
        kontrolaImeNull();
        kontrolaImeBroj();

    }

    private void kontrolaPrezime() throws OfirException {
        kontrolaPrezimeNull();
        kontrolaPrezimeBroj();

    }

    private void kontrolaImeNull() throws OfirException {
        if (entitet.getIme() == null) {
            throw new OfirException("Ime ne smije biti prazno");
        }

    }

    private void kontrolaPrazno() throws OfirException {

        String[] provjera = {entitet.getIme(), entitet.getPrezime(), entitet.getEmail(), entitet.getOIB(), entitet.getIBAN(), entitet.getUlica()};
        for (int i = 0; i < provjera.length; i++) {
            if (provjera[i].isEmpty()) {
                throw new OfirException("Sva polja moraju biti popunjena!");
            }
        }

        if (entitet.getKucniBroj() == null) {
            throw new OfirException("Sva polja moraju biti popunjena!");
        }

    }

    private void kontrolaImeBroj() throws OfirException {
        boolean broj = false;

        try {
            Double.parseDouble(entitet.getIme());

            broj = true;
        } catch (Exception e) {

        }
        if (broj) {
            throw new OfirException("Ime ne moze biti broj");
        }
    }

    private void kontrolaPrezimeNull() throws OfirException {
        if (entitet.getPrezime() == null) {
            throw new OfirException("Prezime ne smije biti prazno");
        }
    }

    private void kontrolaPrezimeBroj() throws OfirException {
        boolean broj = false;

        try {
            Double.parseDouble(entitet.getPrezime());

            broj = true;
        } catch (Exception e) {

        }
        if (broj) {
            throw new OfirException("Prezime ne moze biti broj");
        }

    }

    private void kontrolaOIBDupli() throws OfirException {
        List<Korisnik> korisnici = null;
        try {
            korisnici = session.createQuery("from Korisnik k "
                    + " where k.OIB=:OIB",
                    Korisnik.class)
                    .setParameter("OIB", entitet.getOIB())
                    .list();
        } catch (Exception e) {
        }
        if (korisnici != null && !korisnici.isEmpty()) {
            throw new OfirException("OIB postoji u bazi!");
        }
    }

    private void kontrolaIban() throws OfirException {
        boolean provjera = false;
        String iban = entitet.getIBAN();
        if (iban.length() == 21) {
            if (Character.compare(iban.charAt(0), 'H') == 0) {
                if (Character.compare(iban.charAt(1), 'R') == 0) {

                    for (int i = 2; i < iban.length(); i++) {
                        if (Character.isDigit(iban.charAt(i))) {
                            provjera = true;
                        }
                    }
                }
            }
        }
        if (!provjera) {
            throw new OfirException("IBAN nije u dobrom formatu!");
        }

    }

}