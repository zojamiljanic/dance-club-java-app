/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dnevniraspored;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.DnevniRaspored;
import domain.Trening;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author Win 7
 */
public class SOAddDnevniRaspored extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof DnevniRaspored)) {
            throw new Exception("Prosledjeni objekat nije instanca klase DnevniRaspored!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        PreparedStatement ps = ado.vratiUpitZaUbacivanje(DBBroker.getInstance().getConnection());
        
        ps.execute();
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long id = tableKeys.getLong(1);
        
        DnevniRaspored dr = (DnevniRaspored) ado;
        dr.setDnevniRasporedID(id);
        
        for (Trening trening : dr.getListaTreninga()) {
            trening.setDr(dr);
            trening.vratiUpitZaUbacivanje(DBBroker.getInstance().getConnection()).execute();
        }
        
    }
    
}
