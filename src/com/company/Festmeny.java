package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Festmeny {
    //<editor-fold desc="variables">
    private String cim;
    private String festo;
    private String stilus;
    private int licitekSzama = 0;
    private int legmagasabbLicit = 0;
    private LocalDateTime utolsoLicitIdeje;
    private boolean elkelt = false;
    //</editor-fold>

    //<editor-fold desc="getters">
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
    //</editor-fold>

    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
    }

    public void elad() {
        elkelt = true;
    }

    public void licit() {
        licit(10);
    }

    public void licit(int mertek) {
        double ideiglenesTarolo;
        if (elkelt) System.out.println("Sajnalom, a festmeny mar elkelt");
        else if (legmagasabbLicit == 0) {
            legmagasabbLicit = 100;
            licitekSzama++;
            utolsoLicitIdeje = LocalDateTime.now();
        } else if (!(mertek >= 10 && mertek <= 100)) {
            System.out.println("10 es 100 kozott adjon meg erteket");
        } else {
            ideiglenesTarolo = ((double)legmagasabbLicit * (100 + (double)mertek)) / 100;
            legmagasabbLicit = (int)(ideiglenesTarolo - (ideiglenesTarolo % (Math.pow(10, Math.floor(Math.log10(ideiglenesTarolo)) - 1))));
            licitekSzama++;
            utolsoLicitIdeje = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        String elkelte;
        if (elkelt) elkelte = "Elkelt\n";
        else elkelte = "";
        if (licitekSzama < 1) {
            return String.format("%s: %s\n%s", festo, cim, elkelte);
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = utolsoLicitIdeje.format(formatter);
            return String.format("%s: %s\n%s%s$ - %s (osszesen: %s)", festo, cim, elkelte, legmagasabbLicit, formattedDate, licitekSzama);
        }
    }

    public String toSimpleLine() {
        return String.format("%s;%s;%s\n", festo, cim, stilus);
    }
}
