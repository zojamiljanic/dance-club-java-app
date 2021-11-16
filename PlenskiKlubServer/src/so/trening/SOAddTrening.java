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
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author Win 7
 */
public class SOAddTrening extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trening)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trening!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ado.vratiUpitZaUbacivanje(DBBroker.getInstance().getConnection()).execute();
    }
    
}
