package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Festmeny> Festmenyek = new ArrayList<>();

    public static void main(String[] args) {
        Festmeny f1 = new Festmeny("A kacsa", "En", "Barna stilusu");
        Festmenyek.add(f1);
        Festmeny f2 = new Festmeny("A lo", "Megint En", "Zold stilusu");
        Festmenyek.add(f2);

        f1.licit();
        System.out.println(f1);
    }
}
