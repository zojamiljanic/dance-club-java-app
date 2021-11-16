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
 * @author 38160
 */
public class Administrator extends AbstractDomainObject implements Serializable {
    
    private Long administratorID;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
        this.administratorID = administratorID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM ADMINISTRATOR";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"), 
                    rs.getString("Username"), rs.getString("Password"));
            
            lista.add(a);
        }
        
        rs.close();
        return lista;
        
    }

    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO ADMINISTRATOR("
                        + "IME, PREZIME, USERNAME,PASSWORD) VALUES (?,?,?,?)");
        
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, username);
        ps.setString(4, password);
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("UPDATE ADMINISTRATOR "
                        + "SET Ime = ?, Prezime = ?, Username = ?, Password = ?"
                        + " WHERE administratorid = ?");
        
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, username);
        ps.setString(4, password);
        ps.setLong(5, administratorID);
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM ADMINISTRATOR WHERE ADMINISTRATORID = ?");
        
        ps.setLong(1, administratorID);
        
        return ps;
    }

    public Long getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(Long administratorID) {
        this.administratorID = administratorID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
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
    
}
