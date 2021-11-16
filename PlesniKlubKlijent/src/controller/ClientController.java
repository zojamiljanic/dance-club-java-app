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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ClientController {
    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if(instance == null)
            instance = new ClientController();
        return instance;
    }
    
    public Administrator login(Administrator administrator) throws Exception{
        return (Administrator)sendRequest(Operation.LOGIN, administrator);
    }
    
    public void addPlesac(Plesac plesac) throws Exception{
         sendRequest(Operation.ADD_PLESAC, plesac);
     }

     public void addDnevniRaspored(DnevniRaspored dnevniRaspored) throws Exception{
         sendRequest(Operation.ADD_DNEVNI_RASPORED, dnevniRaspored);
     }

     public void addTrener(Trener trener) throws Exception{
         sendRequest(Operation.ADD_TRENER, trener);
     }

     public void addTrening(Trening trening) throws Exception{
         sendRequest(Operation.ADD_TRENING, trening);
     }

     public void addAdministrator(Administrator administrator) throws Exception{
         sendRequest(Operation.ADD_ADMINISTRATOR, administrator);
     }

     public void deletePlesac(Plesac plesac) throws Exception{
         sendRequest(Operation.DELETE_PLESAC, plesac);
     }

     public void deleteDnevniRaspored(DnevniRaspored dnevniRaspored) throws Exception{
         sendRequest(Operation.DELETE_DNEVNI_RASPORED, dnevniRaspored);
     }

     public void deleteTrener(Trener trener) throws Exception{
         sendRequest(Operation.DELETE_TRENER, trener);
     }

     public void deleteTrening(Trening trening) throws Exception{
         sendRequest(Operation.DELETE_TRENING, trening);
     }

     public void deleteAdministrator(Administrator administrator) throws Exception{
         sendRequest(Operation.DELETE_ADMINISTRATOR, administrator);
     }

     public void editPlesac(Plesac plesac) throws Exception{
         sendRequest(Operation.EDIT_PLESAC, plesac);
     }

     public void editDnevniRaspored(DnevniRaspored dnevniRaspored) throws Exception{
         sendRequest(Operation.EDIT_DNEVNI_RASPORED, dnevniRaspored);
     }

     public void editTrener(Trener trener) throws Exception{
         sendRequest(Operation.EDIT_TRENER, trener);
     }

     public void editAdministrator(Administrator administrator) throws Exception{
         sendRequest(Operation.EDIT_ADMINISTRATOR, administrator);
     }

     public ArrayList<Plesac> getAllPlesac() throws Exception{
         return (ArrayList<Plesac>)sendRequest(Operation.GET_ALL_PLESAC, null);
     }

     public ArrayList<DnevniRaspored> getAllDnevniRaspored() throws Exception{
         return (ArrayList<DnevniRaspored>)sendRequest(Operation.GET_ALL_DNEVNI_RASPORED, null);
     }

     public ArrayList<Trener> getAllTrener() throws Exception{
         return (ArrayList<Trener>)sendRequest(Operation.GET_ALL_TRENER, null);
     }

     public ArrayList<Trening> getAllTrening() throws Exception{
         return (ArrayList<Trening>)sendRequest(Operation.GET_ALL_TRENING, null);
     }

     public ArrayList<Administrator> getAllAdministrator() throws Exception{
         return (ArrayList<Administrator>)sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
     }
     
     public ArrayList<TipPlesa> getAllTipPlesa() throws Exception{
         return (ArrayList<TipPlesa>)sendRequest(Operation.GET_ALL_TIP_PLESA, null);
     }
    
    private Object sendRequest(int operation,Object data) throws Exception{
        Request req = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res = (Response)in.readObject();
        if(res.getResponseStatus().equals(ResponseStatus.Error))
            throw res.getError();
        else 
            return res.getData();
    }
}
