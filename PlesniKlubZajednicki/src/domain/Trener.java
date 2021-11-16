/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Win 7
 */
public class Trener extends AbstractDomainObject implements Serializable {

    private Long trenerID;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String email;

    public Trener() {
    }

    public Trener(Long trenerID, String ime, String prezime, String brojTelefona, String email) {
        this.trenerID = trenerID;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
    }
    
    
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM TRENER";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Trener t = new Trener(rs.getLong("TrenerID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("BrojTelefona"), rs.getString("Email"));
            
            lista.add(t);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO TRENER("
                        + "IME, PREZIME, BROJTELEFONA, EMAIL) VALUES (?,?,?,?)");
        
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, brojTelefona);
        ps.setString(4, email);
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("UPDATE TRENER "
                        + "SET Ime = ?, Prezime = ?, BrojTelefona = ?, Email = ?"
                        + " WHERE trenerid = ?");
        
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, brojTelefona);
        ps.setString(4, email);
        ps.setLong(5, trenerID);
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM TRENER WHERE TRENERID = ?");
        
        ps.setLong(1, trenerID);
        
        return ps;
    }

    public Long getTrenerID() {
        return trenerID;
    }

    public void setTrenerID(Long trenerID) {
        this.trenerID = trenerID;
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

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ime + " " + prezime; 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trener other = (Trener) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    
    
}
