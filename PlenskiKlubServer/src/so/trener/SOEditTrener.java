/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trener;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Trener;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author 38160
 */
public class SOEditTrener extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trener)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trener!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ado.vratiUpitZaIzmenu(DBBroker.getInstance().getConnection()).execute();
    }
    
    
}
