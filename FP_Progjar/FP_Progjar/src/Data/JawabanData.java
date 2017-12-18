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
    private String idUser;
    private ArrayList<String> jawabans;
    private int nilai;

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public JawabanData(String test) {
        this.idUser = "a@a.com";
        this.jawabans.add("A");
    }
    
    public JawabanData(String idUser, ArrayList<String> jawabans) {
        this.idUser = idUser;
        this.jawabans = jawabans;
    }

    public String getIdUser() {
        return idUser;
    }

    
    public ArrayList<String> getJawabans() {
        return jawabans;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setJawabans(ArrayList<String> jawabans) {
        this.jawabans = jawabans;
    }
        
}
