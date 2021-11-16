/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.DnevniRaspored;
import domain.Plesac;
import domain.Trener;
import domain.Trening;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                Response res = handleRequest(req);
                //preko ove dvije linije koda saljem response
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_ADMINISTRATOR:
                    ServerController.getInstance().addAdministrator((Administrator) req.getData());
                    break;
                case Operation.ADD_PLESAC:
                    ServerController.getInstance().addPlesac((Plesac) req.getData());
                    break;
                case Operation.ADD_DNEVNI_RASPORED:
                    ServerController.getInstance().addDnevniRaspored((DnevniRaspored) req.getData());
                    break;
                case Operation.ADD_TRENER:
                    ServerController.getInstance().addTrener((Trener) req.getData());
                    break;
                case Operation.ADD_TRENING:
                    ServerController.getInstance().addTrening((Trening) req.getData());
                    break;
                case Operation.DELETE_ADMINISTRATOR:
                    ServerController.getInstance().deleteAdministrator((Administrator) req.getData());
                    break;
                case Operation.DELETE_PLESAC:
                    ServerController.getInstance().deletePlesac((Plesac) req.getData());
                    break;
                case Operation.DELETE_DNEVNI_RASPORED:
                    ServerController.getInstance().deleteDnevniRaspored((DnevniRaspored) req.getData());
                    break;
                case Operation.DELETE_TRENER:
                    ServerController.getInstance().deleteTrener((Trener) req.getData());
                    break;
                case Operation.DELETE_TRENING:
                    ServerController.getInstance().deleteTrening((Trening) req.getData());
                    break;
                case Operation.EDIT_ADMINISTRATOR:
                    ServerController.getInstance().editAdministrator((Administrator) req.getData());
                    break;
                case Operation.EDIT_PLESAC:
                    ServerController.getInstance().editPlesac((Plesac) req.getData());
                    break;
                case Operation.EDIT_DNEVNI_RASPORED:
                    ServerController.getInstance().editDnevniRaspored((DnevniRaspored) req.getData());
                    break;
                case Operation.EDIT_TRENER:
                    ServerController.getInstance().editTrener((Trener) req.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    res.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_PLESAC:
                    res.setData(ServerController.getInstance().getAllPlesac());
                    break;
                case Operation.GET_ALL_DNEVNI_RASPORED:
                    res.setData(ServerController.getInstance().getAllDnevniRaspored());
                    break;
                case Operation.GET_ALL_TRENER:
                    res.setData(ServerController.getInstance().getAllTrener());
                    break;
                case Operation.GET_ALL_TRENING:
                    res.setData(ServerController.getInstance().getAllTrening());
                    break;
                case Operation.GET_ALL_TIP_PLESA:
                    res.setData(ServerController.getInstance().getAllTipPlesa());
                    break;
                case Operation.LOGIN:
                    ArrayList<Administrator> listaAdmina =
                            ServerController.getInstance().getAllAdministrator();
                    Administrator a = (Administrator) req.getData();
                    for (Administrator administrator : listaAdmina) {
                        if (administrator.getUsername().equals(a.getUsername())
                                && administrator.getPassword().equals(a.getPassword())) {
                            res.setData(administrator);
                        }
                    }
                    if (res.getData() == null) {
                        throw new Exception("Ne postoji administrator sa tim kredencijalima.");
                    } else {
                        break;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setData(null);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
