/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
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
public class TableModelPlesaci extends AbstractTableModel implements Runnable{

    private List<Plesac> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Broj telefona", "Pol"};
    private String parametar = "";
    
    public TableModelPlesaci() {
        try {
            lista = ClientController.getInstance().getAllPlesac();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPlesaci.class.getName()).log(Level.SEVERE, null, ex);
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
        Plesac p = lista.get(row);
        
        switch(column){
            case 0: return p.getPlesacID();
            case 1: return p.getIme();
            case 2: return p.getPrezime();
            case 3: return p.getBrojTelefona();
            case 4: return p.getPol();
            
            default: return null;
        }
    }
    
    public Plesac getSelectedPlesac(int row){
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()){
                    Thread.sleep(5000);
                    osveziTabelu();
            }
        } catch (InterruptedException ex) {
                Logger.getLogger(TableModelPlesaci.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    private void osveziTabelu() {
        try{
            lista = ClientController.getInstance().getAllPlesac();
            if(!parametar.equals("")){
                List<Plesac> novaLista=new ArrayList<>();
                for(Plesac p: lista)
                    if(p.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || p.getPrezime().toLowerCase().contains(parametar.toLowerCase())
                            || p.getBrojTelefona().toLowerCase().contains(parametar.toLowerCase()))
                        novaLista.add(p);
                lista=novaLista;
            }else{
                
            }
            fireTableDataChanged();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllPlesac();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelAdministratori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

