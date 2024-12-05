package com.example.konyvtar_kezelo;

import java.io.Serializable;

public class konyvadatok implements Serializable {
    private String cim;
    private String szerzo;
    private int oldalszam;
    private int ev;

    public konyvadatok(String cim, String szerzo, int oldalszam) {
        this.cim = cim;
        this.szerzo = szerzo;
        this.oldalszam = oldalszam;
        this.ev = (1900 + (int)(Math.random() * 125));
    }

    public String getTitle() {
        return cim;
    }

    public String getAuthor() {
        return szerzo;
    }

    public int getPagesCount() {
        return oldalszam;
    }

    public int getYear() {
        return ev;
    }
}