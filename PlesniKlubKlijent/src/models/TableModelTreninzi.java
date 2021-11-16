/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.DnevniRaspored;
import domain.Trener;
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
public class TableModelTreninzi extends AbstractTableModel {

    private List<Trening> lista;
    private String[] kolone = {"Redni broj", "Vreme", "Trener", "Tip plesa"};
    DnevniRaspored dr;

    public TableModelTreninzi(DnevniRaspored dr) {
        this.dr = dr;
        try {
            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTreninzi.class.getName()).log(Level.SEVERE, null, ex);
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
                return t.getRb();
            case 1:
                return t.getVreme();
            case 2:
                return t.getTrener();
            case 3:
                return t.getTipPlesa();

            default:
                return null;
        }
    }

    public Trening getSelectedTrening(int row) {
        return lista.get(row);
    }

    public void obrisiTrening(int selectedRow) {
        lista.remove(selectedRow);
        int rb = 0;
        
        for (Trening trening : lista) {
            trening.setRb(++rb);
        }
        
        fireTableDataChanged();
    }

    public void dodajTrening(Trening t) {
        int rb = 0;
        
        for (Trening trening : lista) {
            rb++;
        }
        
        t.setRb(++rb);
        
        lista.add(t);
        
        fireTableDataChanged();
        
    }

    public List<Trening> getLista() {
        return lista;
    }

    public boolean trenerVecDrzi(String vreme, Trener trener) {
        for (Trening trening : lista) {
            if(trening.getTrener().getTrenerID().equals(trener.getTrenerID())
                    && trening.getVreme().equals(vreme)){
                return true;
            }
        }
        return false;
    }

    
    
}
