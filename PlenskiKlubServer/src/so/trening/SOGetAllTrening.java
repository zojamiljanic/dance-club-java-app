/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trening;

import so.dnevniraspored.*;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.DnevniRaspored;
import domain.Trening;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Win 7
 */
public class SOGetAllTrening extends AbstractSO{
    
    ArrayList<Trening> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trening)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trening!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        String upitZaSve = ado.vratiUpitZaSve();
        
        Statement stat = DBBroker.getInstance().getConnection().createStatement();
        ResultSet rs = stat.executeQuery(upitZaSve);
        
        ArrayList<AbstractDomainObject> listaTreninga = ado.vratiListu(rs);
        lista = (ArrayList<Trening>) (ArrayList<?>) listaTreninga;
    }

    public ArrayList<Trening> getLista() {
        return lista;
    }
    
    
    
}
