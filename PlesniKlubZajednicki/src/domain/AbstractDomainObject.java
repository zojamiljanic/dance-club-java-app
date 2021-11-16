/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public abstract class AbstractDomainObject implements Serializable {
    
    public abstract String vratiUpitZaSve();
    public abstract ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;
    public abstract PreparedStatement vratiUpitZaUbacivanje(Connection con) throws SQLException;
    public abstract PreparedStatement vratiUpitZaIzmenu(Connection con) throws SQLException;
    public abstract PreparedStatement vratiUpitZaBrisanje(Connection con) throws SQLException;
    
}
