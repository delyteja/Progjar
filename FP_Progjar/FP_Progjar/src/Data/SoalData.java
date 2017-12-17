/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import fp_progjar.*;
import java.io.Serializable;

/**
 *
 * @author Raca
 */
public class SoalData implements Serializable {
    private String soal;
    private String jawaban[];
    private int nomerSoal;
    private int waktu;

    public SoalData() {
        this.soal = "contoh soal";
        this.nomerSoal = 1;
        this.waktu = 12;
    }

    public SoalData(String soal, String[] jawaban, int nomerSoal, int waktu) {
        this.soal = soal;
        this.jawaban = jawaban;
        this.nomerSoal = nomerSoal;
        this.waktu = waktu;
    }
    
    public String getSoal() {
        return soal;
    }

    public String[] getJawaban() {
        return jawaban;
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

    public void setJawaban(String[] jawaban) {
        this.jawaban = jawaban;
    }

    public void setNomerSoal(int nomerSoal) {
        this.nomerSoal = nomerSoal;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }    
}
