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
public class Plesac extends AbstractDomainObject implements Serializable{

    private Long plesacID;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String pol;

    public Plesac() {
    }

    public Plesac(Long plesacID, String ime, String prezime, String brojTelefona, String pol) {
        this.plesacID = plesacID;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.pol = pol;
    }
    
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM PLESAC";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Plesac p = new Plesac(rs.getLong("PlesacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("BrojTelefona"), rs.getString("Pol"));
            
            lista.add(p);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO PLESAC("
                        + "IME, PREZIME, BROJTELEFONA, POL) VALUES (?,?,?,?)");
        
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, brojTelefona);
        ps.setString(4, pol);
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("UPDATE PLESAC "
                        + "SET BrojTelefona = ? "
                        + " WHERE plesacid = ?");
        
        ps.setString(1, brojTelefona);
        ps.setLong(2, plesacID);
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM PLESAC WHERE PLESACID = ?");
        
        ps.setLong(1, plesacID);
        
        return ps;
    }

    public Long getPlesacID() {
        return plesacID;
    }

    public void setPlesacID(Long plesacID) {
        this.plesacID = plesacID;
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

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
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
        final Plesac other = (Plesac) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        if (!Objects.equals(this.pol, other.pol)) {
            return false;
        }
        return true;
    }
    
    
    
}
