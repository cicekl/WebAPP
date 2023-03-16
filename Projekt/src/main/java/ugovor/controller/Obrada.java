/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ugovor.controller;
import java.util.List;
import org.hibernate.Session;
import ugovor.model.Entitet;
import ugovor.util.HibernateUtil;
import ugovor.util.OfirException;
/**
 *
 * @author Lorena
 */
public abstract class Obrada<T extends Entitet> {
    
    protected T entitet;
    protected Session session;
    public abstract List<T> čitanje();
    protected abstract void kontrolaUnos() throws OfirException;
    protected abstract void kontrolaPromjena() throws OfirException;
    protected abstract void kontrolaBrisanje() throws OfirException;

    public Obrada() {
        this.session = HibernateUtil.getSession();
    }

    public void create() throws OfirException {
        if(entitet==null){
            throw new OfirException("Entitet ne postoji.");
        }
        kontrolaUnos();
        persist();
    }

    public void update() throws OfirException {
        kontrolaPromjena();
        persist();
    }

    public void delete() throws OfirException {
        kontrolaBrisanje();
        session.beginTransaction();
        session.remove(entitet);
        session.getTransaction().commit();
    }

    private void persist() {
        session.beginTransaction();
        session.persist(entitet);
        session.getTransaction().commit();
    }

    public T getEntitet() {
        return entitet;
    }

    public void setEntitet(T entitet) {
        this.entitet = entitet;
    }
        
        
    
    
}
