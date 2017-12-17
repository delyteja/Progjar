/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp_progjar;

import java.io.Serializable;

/**
 *
 * @author Raca
 */
public class JawabanData implements Serializable {
    private int nomerSoal;
    private int idUser;
    private int jawaban;

    public JawabanData() {
        this.nomerSoal = 12;
        this.idUser = 13;
        this.jawaban = 12;
    }
    
    public JawabanData(int nomerSoal, int idUser, int jawaban) {
        this.nomerSoal = nomerSoal;
        this.idUser = idUser;
        this.jawaban = jawaban;
    }
    
    public int getNomerSoal() {
        return nomerSoal;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getJawaban() {
        return jawaban;
    }
    
    public void setNomerSoal(int _nomerSoal) {
        this.nomerSoal = _nomerSoal;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setJawaban(int jawaban) {
        this.jawaban = jawaban;
    }
    
    
}
