/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Administrator;
import domain.DnevniRaspored;
import domain.Trening;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelTreninziDnevnog extends AbstractTableModel implements Runnable {

    private List<Trening> lista;
    private String[] kolone = {"Vreme", "Trener", "Tip plesa"};
    private String parametar = "";
    DnevniRaspored dr;

    public TableModelTreninziDnevnog(DnevniRaspored dr) {
        this.dr = dr;
        try {
            ArrayList<Trening> sviTreninzi = ClientController.getInstance().getAllTrening();
            ArrayList<Trening> treninziDnevnog = new ArrayList<>();
            
            for (Trening trening : sviTreninzi) {
                if(trening.getDr().getDnevniRasporedID().equals(dr.getDnevniRasporedID()))
                    treninziDnevnog.add(trening);
            }
            
            lista = treninziDnevnog;
            
        } catch (Exception ex) {
            Logger.getLogger(TableModelTreninziDnevnog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Trening t = lista.get(row);
        switch (column) {
            case 0:
                return t.getVreme();
            case 1:
                return t.getTrener();
            case 2:
                return t.getTipPlesa();

            default:
                return null;
        }
    }

    public Trening getSelectedTrening(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                osveziTabelu();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelTreninziDnevnog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    public void osveziTabelu() {
        try {
            ArrayList<Trening> sviTreninzi = ClientController.getInstance().getAllTrening();
            ArrayList<Trening> treninziDnevnog = new ArrayList<>();
            
            for (Trening trening : sviTreninzi) {
                if(trening.getDr().getDnevniRasporedID().equals(dr.getDnevniRasporedID()))
                    treninziDnevnog.add(trening);
            }
            
            lista = treninziDnevnog;
            
            if (!parametar.equals("")) {
                List<Trening> novaLista = new ArrayList<>();
                for (Trening trening : lista) {
                    if (trening.getTrener().getIme().toLowerCase().contains(parametar.toLowerCase())
                            || trening.getTrener().getPrezime().toLowerCase().contains(parametar.toLowerCase())
                            || trening.getTipPlesa().getNazivPlesa().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(trening);
                    }
                }
                lista = novaLista;
            } else {

            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void refreshTable() {
        try {
            ArrayList<Trening> sviTreninzi = ClientController.getInstance().getAllTrening();
            ArrayList<Trening> treninziDnevnog = new ArrayList<>();
            
            for (Trening trening : sviTreninzi) {
                if(trening.getDr().getDnevniRasporedID().equals(dr.getDnevniRasporedID()))
                    treninziDnevnog.add(trening);
            }
            
            lista = treninziDnevnog;
            
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTreninziDnevnog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Trening> getLista() {
        return lista;
    }
    
}
