/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.DnevniRaspored;
import domain.Plesac;
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
public class TableModelDnevniRaspored extends AbstractTableModel implements Runnable{

    private List<DnevniRaspored> lista;
    private String[] kolone = {"ID", "Datum"};
    private String parametar = "";
    
    public TableModelDnevniRaspored() {
        try {
            lista = ClientController.getInstance().getAllDnevniRaspored();
        } catch (Exception ex) {
            Logger.getLogger(TableModelDnevniRaspored.class.getName()).log(Level.SEVERE, null, ex);
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
        DnevniRaspored dr = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch(column){
            case 0: return dr.getDnevniRasporedID();
            case 1: return sdf.format(dr.getDatum());
            
            default: return null;
        }
    }
    
    public DnevniRaspored getSelectedDnevniRaspored(int row){
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()){
                    Thread.sleep(5000);
                    refreshTable();
            }
        } catch (InterruptedException ex) {
                Logger.getLogger(TableModelDnevniRaspored.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllDnevniRaspored();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelAdministratori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

