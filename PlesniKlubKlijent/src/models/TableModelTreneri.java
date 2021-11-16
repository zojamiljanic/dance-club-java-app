/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Trener;
import FormTrener.FormPretragaTrenera;
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
public class TableModelTreneri extends AbstractTableModel implements Runnable{

    private List<Trener> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Broj telefona", "Email"};
    private String parametar = "";
    
    public TableModelTreneri() {
        try {
            lista = ClientController.getInstance().getAllTrener();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTreneri.class.getName()).log(Level.SEVERE, null, ex);
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
        Trener t = lista.get(row);
        
        switch(column){
            case 0: return t.getTrenerID();
            case 1: return t.getIme();
            case 2: return t.getPrezime();
            case 3: return t.getBrojTelefona();
            case 4: return t.getEmail();
            
            default: return null;
        }
    }
    
    public Trener getSelectedTrener(int row){
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
                Logger.getLogger(TableModelTreneri.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        osveziTabelu();
    }

    private void osveziTabelu() {
        try{
            lista = ClientController.getInstance().getAllTrener();
            if(!parametar.equals("")){
                List<Trener> novaLista=new ArrayList<>();
                for(Trener t: lista)
                    if(t.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || t.getPrezime().toLowerCase().contains(parametar.toLowerCase())
                            || t.getBrojTelefona().toLowerCase().contains(parametar.toLowerCase()))
                        novaLista.add(t);
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
            lista = ClientController.getInstance().getAllTrener();
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelAdministratori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

