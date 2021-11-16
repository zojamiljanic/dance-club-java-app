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
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author Win 7
 */
public class SODeleteDnevniRaspored extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof DnevniRaspored)) {
            throw new Exception("Prosledjeni objekat nije instanca klase DnevniRaspored!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        
        DnevniRaspored dnevniRaspored = (DnevniRaspored) ado;
        
        for (Trening trening : dnevniRaspored.getListaTreninga()) {
            trening.vratiUpitZaBrisanje(DBBroker.getInstance().getConnection()).execute();
        }
        
        ado.vratiUpitZaBrisanje(DBBroker.getInstance().getConnection()).execute();
    }
    
}
