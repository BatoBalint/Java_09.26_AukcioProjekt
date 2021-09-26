package com.company;

import java.time.LocalDateTime;

public class Festmeny {
    private String cim;
    private String festo;
    private String stilus;
    private int licitekSzama = 0;
    private int legmagasabbLicit = 0;
    private LocalDateTime utolsoLicitIdeje;
    private boolean elkelt;

    public String getStilus() {
        return stilus;
    }

    public String getFesto() {
        return festo;
    }

    public int getLicitekSzama() {
        return licitekSzama;
    }

    public int getLegmagasabbLicit() {
        return legmagasabbLicit;
    }

    public LocalDateTime getUtolsoLicitIdeje() {
        return utolsoLicitIdeje;
    }

    public boolean getElkelt() {
        return elkelt;
    }

    public void setElkelt(boolean elkelt) {
        this.elkelt = elkelt;
    }

    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
    }

    public void licit() {
        if (elkelt) System.out.println("Sajnalom, a festmeny mar elkelt");
        else if (legmagasabbLicit == 0) {
            legmagasabbLicit = 100;
            licitekSzama++;
            utolsoLicitIdeje = LocalDateTime.now();
        } else {
            legmagasabbLicit *= 1.1;
            utolsoLicitIdeje = LocalDateTime.now();
        }
    }

    public void licit(int mertek) {

    }
}
