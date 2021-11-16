/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {
    
    public static final int LOGIN = 0;

    public static final int ADD_ADMINISTRATOR = 1;
    public static final int DELETE_ADMINISTRATOR = 2;
    public static final int EDIT_ADMINISTRATOR = 3;
    public static final int GET_ALL_ADMINISTRATOR = 4;
    
    public static final int ADD_TRENER = 5;
    public static final int DELETE_TRENER = 6;
    public static final int EDIT_TRENER = 7;
    public static final int GET_ALL_TRENER = 8;
    
    public static final int ADD_PLESAC = 9;
    public static final int DELETE_PLESAC = 10;
    public static final int EDIT_PLESAC = 11;
    public static final int GET_ALL_PLESAC = 12;
    
    public static final int ADD_DNEVNI_RASPORED = 13;
    public static final int DELETE_DNEVNI_RASPORED = 14;
    public static final int EDIT_DNEVNI_RASPORED = 15;
    public static final int GET_ALL_DNEVNI_RASPORED = 16;
    
    public static final int ADD_TRENING = 17;
    public static final int DELETE_TRENING = 18;
    public static final int GET_ALL_TRENING = 20;
    
    public static final int GET_ALL_TIP_PLESA = 21;

}
