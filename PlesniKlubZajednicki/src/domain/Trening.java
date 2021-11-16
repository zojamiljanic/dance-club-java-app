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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Win 7
 */
public class Trening extends AbstractDomainObject implements Serializable {

    private DnevniRaspored dr;
    private int rb;
    private String vreme;
    private Trener trener;
    private TipPlesa tipPlesa;

    public Trening() {
    }

    public Trening(DnevniRaspored dr, int rb, String vreme, Trener trener, TipPlesa tipPlesa) {
        this.dr = dr;
        this.rb = rb;
        this.vreme = vreme;
        this.trener = trener;
        this.tipPlesa = tipPlesa;
    }

    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM trening tr "
                + "JOIN dnevniraspored dr ON (tr.dnevnirasporedid = dr.dnevnirasporedid) "
                + "JOIN trener t ON (tr.trenerid = t.trenerid) "
                + "JOIN tipplesa tp ON (tp.tipplesaid = tr.tipplesaid)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        
        while(rs.next()){
            Trener t = new Trener(rs.getLong("TrenerID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("BrojTelefona"), rs.getString("Email"));
            
            TipPlesa tp = new TipPlesa(rs.getLong("TipPlesaID"), 
                                            rs.getString("NazivPlesa"));
            
            DnevniRaspored dr = new DnevniRaspored(rs.getLong("DnevniRasporedID"),
                                        rs.getDate("Datum"), null);
            
            Trening trening = new Trening(dr, rs.getInt("RedniBroj"), 
                    rs.getString("Vreme"), t, tp);
            
            lista.add(trening);
        }
        
        rs.close();
        return lista;
    }

    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("INSERT INTO TRENING("
                        + "DNEVNIRASPOREDID, REDNIBROJ, "
                        + "VREME, TRENERID, TIPPLESAID) VALUES (?,?,?,?,?)");
        
        ps.setLong(1, dr.getDnevniRasporedID());
        ps.setInt(2, rb);
        ps.setString(3, vreme);
        ps.setLong(4, trener.getTrenerID());
        ps.setLong(5, tipPlesa.getTipPlesaID());
        
        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps = 
                con.prepareStatement("DELETE FROM TRENING WHERE DNEVNIRASPOREDID = ? "
                        + "AND REDNIBROJ = ?");
        
        ps.setLong(1, dr.getDnevniRasporedID());
        ps.setInt(2, rb);
        
        return ps;
    }

    public DnevniRaspored getDr() {
        return dr;
    }

    public void setDr(DnevniRaspored dr) {
        this.dr = dr;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public TipPlesa getTipPlesa() {
        return tipPlesa;
    }

    public void setTipPlesa(TipPlesa tipPlesa) {
        this.tipPlesa = tipPlesa;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

}
