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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Win 7
 */
public class DnevniRaspored extends AbstractDomainObject implements Serializable {

    private Long dnevniRasporedID;
    private Date datum;
    private ArrayList<Trening> listaTreninga;

    public DnevniRaspored() {
    }

    public DnevniRaspored(Long dnevniRasporedID, Date datum, ArrayList<Trening> listaTreninga) {
        this.dnevniRasporedID = dnevniRasporedID;
        this.datum = datum;
        this.listaTreninga = listaTreninga;
    }

    @Override
    public String vratiUpitZaSve() {
        return "SELECT * FROM DNEVNIRASPORED dr "
                + "JOIN trening tr ON (dr.dnevnirasporedid = tr.dnevnirasporedid) "
                + "JOIN trener t ON (t.trenerid = tr.trenerid) "
                + "JOIN tipplesa tp ON (tp.tipplesaid = tr.tipplesaid) "
                + "GROUP BY dr.dnevnirasporedid";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            DnevniRaspored dnevniRaspored = 
                    new DnevniRaspored(rs.getLong("DnevniRasporedID"),
                    rs.getDate("Datum"), null);

            Trener t = new Trener(rs.getLong("TrenerID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("BrojTelefona"), rs.getString("Email"));

            TipPlesa tp = new TipPlesa(rs.getLong("TipPlesaID"),
                    rs.getString("NazivPlesa"));

            Trening trening = new Trening(dnevniRaspored, rs.getInt("RedniBroj"), 
                    rs.getString("Vreme"), t, tp);
            
            ArrayList<Trening> treninzi = new ArrayList<>();
            treninzi.add(trening);
            dnevniRaspored.setListaTreninga(treninzi);
            
            if(lista.contains(dnevniRaspored)){
                int index = lista.indexOf(dnevniRaspored);
                DnevniRaspored dr = (DnevniRaspored) lista.get(index);
                trening.setDr(dr);
                dr.getListaTreninga().add(trening);
            }else{
                lista.add(dnevniRaspored);
            }
        }

        rs.close();
        return lista;
    }

    @Override
    public PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException {
        PreparedStatement ps
                = con.prepareStatement("INSERT INTO DNEVNIRASPORED("
                        + "DATUM) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

        ps.setDate(1, new java.sql.Date(datum.getTime()));

        return ps;
    }

    @Override
    public PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException {
        PreparedStatement ps
                = con.prepareStatement("DELETE FROM DNEVNIRASPORED WHERE DNEVNIRASPOREDID = ?");

        ps.setLong(1, dnevniRasporedID);

        return ps;
    }

    public Long getDnevniRasporedID() {
        return dnevniRasporedID;
    }

    public void setDnevniRasporedID(Long dnevniRasporedID) {
        this.dnevniRasporedID = dnevniRasporedID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public ArrayList<Trening> getListaTreninga() {
        return listaTreninga;
    }

    public void setListaTreninga(ArrayList<Trening> listaTreninga) {
        this.listaTreninga = listaTreninga;
    }

}
