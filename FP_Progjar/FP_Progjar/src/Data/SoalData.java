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
public class SoalData implements Serializable {
    private String soal;
    ArrayList<String> jawabans = new ArrayList<String>();
    private int nomerSoal;
    private int waktu;

    public SoalData() {
    }
    
    public SoalData(String test) {
        this.soal = "contoh soal";
        this.nomerSoal = 1;
        this.waktu = 10;
        this.jawabans.add("1");
        this.jawabans.add("2");
        this.jawabans.add("3");
        this.jawabans.add("4");
    }

    public SoalData(String soal, ArrayList<String> jawabans, int nomerSoal, int waktu) {
        this.soal = soal;
        this.jawabans = jawabans;
        this.nomerSoal = nomerSoal;
        this.waktu = waktu;
        
    }
    
    public String getSoal() {
        return soal;
    }

    public ArrayList<String> getJawabans() {
        return jawabans;
    }

    public int getNomerSoal() {
        return nomerSoal;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public void setJawabans(ArrayList<String> jawabans) {
        this.jawabans = jawabans;
    }

    public void setNomerSoal(int nomerSoal) {
        this.nomerSoal = nomerSoal;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }    
}
