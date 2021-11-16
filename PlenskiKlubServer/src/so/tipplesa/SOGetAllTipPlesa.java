/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tipplesa;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.TipPlesa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Win 7
 */
public class SOGetAllTipPlesa extends AbstractSO{
    
    ArrayList<TipPlesa> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TipPlesa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TipPlesa!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        String upitZaSve = ado.vratiUpitZaSve();
        
        Statement stat = DBBroker.getInstance().getConnection().createStatement();
        ResultSet rs = stat.executeQuery(upitZaSve);
        
        ArrayList<AbstractDomainObject> listaTipovaPlesa = ado.vratiListu(rs);
        lista = (ArrayList<TipPlesa>) (ArrayList<?>) listaTipovaPlesa;
    }

    public ArrayList<TipPlesa> getLista() {
        return lista;
    }
    
}
