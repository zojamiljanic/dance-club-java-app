/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.plesac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Plesac;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Win 7
 */
public class SOGetAllPlesac extends AbstractSO {
    
    ArrayList<Plesac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Plesac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Plesac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        String upitZaSve = ado.vratiUpitZaSve();
        
        Statement stat = DBBroker.getInstance().getConnection().createStatement();
        ResultSet rs = stat.executeQuery(upitZaSve);
        
        ArrayList<AbstractDomainObject> listaPlesaca = ado.vratiListu(rs);
        lista = (ArrayList<Plesac>) (ArrayList<?>) listaPlesaca;
    }

    public ArrayList<Plesac> getLista() {
        return lista;
    }
    
}
