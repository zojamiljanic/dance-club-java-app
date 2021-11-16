/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.DnevniRaspored;
import domain.Plesac;
import domain.TipPlesa;
import domain.Trener;
import domain.Trening;
import java.util.ArrayList;
import so.AbstractSO;
import so.administrator.SOAddAdministrator;
import so.administrator.SODeleteAdministrator;
import so.administrator.SOEditAdministrator;
import so.administrator.SOGetAllAdministrator;
import so.dnevniraspored.SOAddDnevniRaspored;
import so.dnevniraspored.SODeleteDnevniRaspored;
import so.dnevniraspored.SOEditDnevniRaspored;
import so.dnevniraspored.SOGetAllDnevniRaspored;
import so.plesac.SOAddPlesac;
import so.plesac.SODeletePlesac;
import so.plesac.SOEditPlesac;
import so.plesac.SOGetAllPlesac;
import so.tipplesa.SOGetAllTipPlesa;
import so.trener.SOAddTrener;
import so.trener.SODeleteTrener;
import so.trener.SOEditTrener;
import so.trener.SOGetAllTrener;
import so.trening.SOAddTrening;
import so.trening.SODeleteTrening;
import so.trening.SOGetAllTrening;


/**
 *
 * @author PC
 */
public class ServerController {
    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if(instance==null)
            instance=new ServerController();
        return instance;
    }

    public void addAdministrator(Administrator administrator) throws Exception{
        AbstractSO aso=new SOAddAdministrator();
        aso.templateExecute(administrator);
    }

    public void addPlesac(Plesac plesac) throws Exception{
        AbstractSO aso=new SOAddPlesac();
        aso.templateExecute(plesac);
    }

    public void addDnevniRaspored(DnevniRaspored dnevniRaspored) throws Exception{
        AbstractSO aso=new SOAddDnevniRaspored();
        aso.templateExecute(dnevniRaspored);
    }

    public void addTrener(Trener trener) throws Exception{
        AbstractSO aso=new SOAddTrener();
        aso.templateExecute(trener);
    }

    public void addTrening(Trening trening) throws Exception{
        AbstractSO aso=new SOAddTrening();
        aso.templateExecute(trening);
    }

    public void deleteAdministrator(Administrator administrator) throws Exception{
        AbstractSO aso=new SODeleteAdministrator();
        aso.templateExecute(administrator);
    }

    public void deletePlesac(Plesac plesac) throws Exception{
        AbstractSO aso=new SODeletePlesac();
        aso.templateExecute(plesac);
    }

    public void deleteDnevniRaspored(DnevniRaspored dnevniRaspored) throws Exception{
        AbstractSO aso=new SODeleteDnevniRaspored();
        aso.templateExecute(dnevniRaspored);
    }

    public void deleteTrener(Trener trener) throws Exception{
        AbstractSO aso=new SODeleteTrener();
        aso.templateExecute(trener);
    }

    public void deleteTrening(Trening trening) throws Exception{
        AbstractSO aso=new SODeleteTrening();
        aso.templateExecute(trening);
    }

    public void editAdministrator(Administrator administrator) throws Exception{
        AbstractSO aso=new SOEditAdministrator();
        aso.templateExecute(administrator);
    }

    public void editPlesac(Plesac plesac) throws Exception{
        AbstractSO aso=new SOEditPlesac();
        aso.templateExecute(plesac);
    }

    public void editDnevniRaspored(DnevniRaspored dnevniRaspored) throws Exception{
        AbstractSO aso=new SOEditDnevniRaspored();
        aso.templateExecute(dnevniRaspored);
    }

    public void editTrener(Trener trener) throws Exception{
        AbstractSO aso=new SOEditTrener();
        aso.templateExecute(trener);
    }
    
    public ArrayList<Administrator> getAllAdministrator() throws Exception{
        SOGetAllAdministrator so=new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Plesac> getAllPlesac() throws Exception{
        SOGetAllPlesac so=new SOGetAllPlesac();
        so.templateExecute(new Plesac());
        return so.getLista();
    }

    public ArrayList<DnevniRaspored> getAllDnevniRaspored() throws Exception{
        SOGetAllDnevniRaspored so=new SOGetAllDnevniRaspored();
        so.templateExecute(new DnevniRaspored());
        return so.getLista();
    }

    public ArrayList<Trener> getAllTrener() throws Exception{
        SOGetAllTrener so=new SOGetAllTrener();
        so.templateExecute(new Trener());
        return so.getLista();
    }

    public ArrayList<Trening> getAllTrening() throws Exception{
        SOGetAllTrening so=new SOGetAllTrening();
        so.templateExecute(new Trening());
        return so.getLista();
    }
    
    public ArrayList<TipPlesa> getAllTipPlesa() throws Exception{
        SOGetAllTipPlesa so=new SOGetAllTipPlesa();
        so.templateExecute(new TipPlesa());
        return so.getLista();
    }
    
}
