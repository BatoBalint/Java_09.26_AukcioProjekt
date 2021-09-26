package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Festmeny {
    private String cim;
    private String festo;
    private String stilus;
    private int licitekSzama = 0;
    private int legmagasabbLicit = 0;
    private LocalDateTime utolsoLicitIdeje;
    private boolean elkelt = false;

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
        else {
            licit(10);
        }
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
            System.out.println(ideiglenesTarolo);
            legmagasabbLicit = (int)(ideiglenesTarolo - (ideiglenesTarolo % (Math.pow(10, Math.floor(Math.log10(ideiglenesTarolo)) - 1))));
            licitekSzama++;
            utolsoLicitIdeje = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = utolsoLicitIdeje.format(formatter);
        String elkelte;
        if (elkelt) elkelte = "Elkelt";
        else elkelte = "Nem kelt el";
        return String.format("%s: %s\n%s\n%s - %s (osszesen: %s)", festo, cim, elkelte, legmagasabbLicit, formattedDate, licitekSzama);
    }
}
