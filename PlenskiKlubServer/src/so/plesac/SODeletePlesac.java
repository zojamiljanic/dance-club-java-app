/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.plesac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Plesac;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author Win 7
 */
public class SODeletePlesac extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Plesac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Plesac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ado.vratiUpitZaBrisanje(DBBroker.getInstance().getConnection()).execute();
    }
    
}
