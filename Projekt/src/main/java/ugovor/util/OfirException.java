/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ugovor.util;

/**
 *
 * @author Korisnik
 */
public class OfirException extends Exception {
    private String poruka;
    
    public OfirException(String poruka){
    super();
    this.poruka = poruka;
    }
    
    public String getPoruka(){
    return poruka;
    }
}
