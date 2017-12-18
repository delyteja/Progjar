/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Raca
 */
public class JawabanData implements Serializable {
    private int nomerSoal;
    private int idUser;
    private ArrayList<Integer> jawabans;

    public JawabanData(String test) {
        this.nomerSoal = 12;
        this.idUser = 13;
        this.jawabans.add(12);
    }
    
    public JawabanData(int nomerSoal, int idUser, ArrayList<Integer> jawabans) {
        this.nomerSoal = nomerSoal;
        this.idUser = idUser;
        this.jawabans = jawabans;
    }
    
    public int getNomerSoal() {
        return nomerSoal;
    }

    public int getIdUser() {
        return idUser;
    }

    
    public ArrayList<Integer> getJawabans() {
        return jawabans;
    }

    public void setNomerSoal(int _nomerSoal) {
        this.nomerSoal = _nomerSoal;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setJawabans(ArrayList<Integer> jawabans) {
        this.jawabans = jawabans;
    }
        
}
