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
public class TipPlesa extends AbstractDomainObject implements Serializable {

    private Long tipPlesaID;
    private String nazivPlesa;

    public TipPlesa() {
    }

    public TipPlesa(Long tipPlesaID, String nazivPlesa) {
        this.tipPlesaID = tipPlesaID;
        this.nazivPlesa = nazivPlesa;
    }
    
    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM TIPPLESA";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            TipPlesa tp = new TipPlesa(rs.getLong("TipPlesaID"), 
                                            rs.getString("NazivPlesa"));
            
            lista.add(tp);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getTipPlesaID() {
        return tipPlesaID;
    }

    public void setTipPlesaID(Long tipPlesaID) {
        this.tipPlesaID = tipPlesaID;
    }

    public String getNazivPlesa() {
        return nazivPlesa;
    }

    public void setNazivPlesa(String nazivPlesa) {
        this.nazivPlesa = nazivPlesa;
    }

    @Override
    public String toString() {
        return nazivPlesa;
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
        final TipPlesa other = (TipPlesa) obj;
        if (!Objects.equals(this.nazivPlesa, other.nazivPlesa)) {
            return false;
        }
        return true;
    }
    
    
    
}
