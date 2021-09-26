package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    static List<Festmeny> Festmenyek = new ArrayList<>();
    static List<Festmeny> Festmenyek2 = new ArrayList<>();

    public static void main(String[] args) {
        Festmeny f1 = new Festmeny("A kacsa", "En", "Barna stilusu");
        Festmenyek.add(f1);
        Festmeny f2 = new Festmeny("A lo", "Megint En", "Zold stilusu");
        Festmenyek.add(f2);
        f2.licit();
        Beolvasas();
        //Kiiratas();
        RandomLicit();
        //FelhasznaloLicit();
        Kiiratas();
        FestmenyekListaRendezese();
        System.out.println("\n\n");
        Kiiratas();
    }

    static void FestmenyHozzaadas() {
        System.out.print("Kerem adja meg hany kepet szeretne hozzaadni: ");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        try {
            int mennyiseg = Integer.parseInt(line);
            int i = 0;
            while (mennyiseg > i) {
                i++;
                System.out.print("Kerem adjon meg a festot: ");
                String nev = sc.nextLine();
                System.out.print("Kerem adjon meg a kep cimet: ");
                String cim = sc.nextLine();
                System.out.print("Kerem adjon meg a kep stilusat: ");
                String stilus = sc.nextLine();
                Festmenyek2.add(new Festmeny(nev, cim, stilus));
            }
        } catch (Exception e) {
            System.out.println("Nem jo szamot adott meg");
        }
    }

    static void Beolvasas() {
        try {
            FileReader fr = new FileReader("festmenyek.csv");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] data;
            while (line != null) {
                data = line.split(";");
                Festmenyek2.add(new Festmeny(data[1], data[0], data[2]));
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Bocsi, kaga van a levesben");
        }
    }

    static void Kiiratas() {
        for (Festmeny a: Festmenyek2) {
            System.out.println(a);
        }
    }

    static void RandomLicit() {
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            Festmenyek2.get(rnd.nextInt(Festmenyek2.size())).licit();
        }
    }

    static void FelhasznaloLicit() {
        Scanner sc = new Scanner(System.in);
        String userAnsw;
        try {
            List<Integer> licitaltFestmenyek = new ArrayList<>();
            boolean again = true;
            while(again) {
                System.out.printf("\nMelyik festmenyre szeretne licitalni (0-kilepes, kepek: %s): ", Festmenyek2.size());
                userAnsw = sc.nextLine();
                int index = Integer.parseInt(userAnsw) - 1;
                if (index == -1) {
                    again = false;
                } else if (index < Festmenyek2.size() && index > -1) {
                    if (TwoMinutesPast(index)) System.out.println("Sajnalom, ez a festmeny mar elkelt");
                    else {
                        System.out.printf("A festmeny adatai:\n%s", Festmenyek2.get(index));
                        boolean bidRepeat = true;
                        while (bidRepeat) {
                            System.out.print("\n\nMennyivel szeretne licitalni +(10%-100%)? ");
                            userAnsw = sc.nextLine();
                            if (userAnsw == "") {
                                Festmenyek2.get(index).licit();
                                bidRepeat = false;
                                System.out.printf("Sikeresen licitalt\nA festmeny uj adatai\n%s\n", Festmenyek2.get(index));
                                licitaltFestmenyek.add(index);
                            } else {
                                try {
                                    int licitMerteke = Integer.parseInt(userAnsw);
                                    if (licitMerteke > 9 && licitMerteke < 101) {
                                        Festmenyek2.get(index).licit(licitMerteke);
                                        bidRepeat = false;
                                        System.out.printf("Sikeresen licitalt\nA festmeny uj adatai\n%s\n", Festmenyek2.get(index));
                                        licitaltFestmenyek.add(index);
                                    } else {
                                        System.out.println("A szam nincs a taromanyban");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Nem jo szamot adott meg");
                                    again = false;
                                    bidRepeat = false;
                                }
                            }
                        }
                    }
                } else {
                    System.out.printf("Kerem adjon meg egy szamot 0 es %d kozott (0-kilepes)", Festmenyek2.size());
                }
            }
            Set<Integer> egyediLicitaltFestmenyek = new HashSet<>(licitaltFestmenyek);
            for (int a: egyediLicitaltFestmenyek) {
                Festmenyek2.get(a).elad();
                System.out.printf("\nFestmeny sikeresen eladva:\n%s\n", Festmenyek2.get(a));
            }
        } catch (Exception e) {
            System.out.println("Ez nem egy szam *facepalm*");
            System.out.println(e.getMessage());
        }
    }

    static boolean TwoMinutesPast(int index) {
        int elteltMasodpperc = (int)ChronoUnit.SECONDS.between(Festmenyek2.get(index).getUtolsoLicitIdeje(), LocalDateTime.now());
        boolean elteltKetPerc = elteltMasodpperc > 120;
        return elteltKetPerc;
    }

    static void LegdragabbFestmeny() {
        Festmeny legdreagabb = null;
        for (Festmeny a: Festmenyek2) {
            if (a.getElkelt()) {
                if (legdreagabb == null) legdreagabb = a;
                else if(a.getLegmagasabbLicit() < legdreagabb.getLegmagasabbLicit()) legdreagabb = a;
            }
        }
        if (legdreagabb == null) System.out.println("Nincs eladott festmeny");
        else System.out.printf("A legdragabb elkelt festmeny: \n\n%s", legdreagabb);
    }
    
    static void TiznelTobbLicit() {
        boolean nincs = true;
        int i = 0;
        while (nincs && i < Festmenyek2.size()) {
            if (Festmenyek2.get(i).getLicitekSzama() > 10) nincs = false;
        }
        if (!nincs) System.out.println("Van olyan festmeny amire 10nel tobben licitaltak");
        else System.out.println("Nincs olyan festmeny amire 10nel tobben licitaltak");
    }

    static void NemEladottFestmenyekSzama() {
        int db = 0;
        for (Festmeny a: Festmenyek2) {
            if (!a.getElkelt()) db++;
        }
        System.out.printf("%d db festmeny van ami nem kelt el.");
    }

    static void FestmenyekListaRendezese() {
        Collections.sort(Festmenyek2, new Comparator<Festmeny>() {
            @Override
            public int compare(Festmeny o1, Festmeny o2) {
                if (o1.getLegmagasabbLicit() > o2.getLegmagasabbLicit()) return -1;
                if (o1.getLegmagasabbLicit() < o2.getLegmagasabbLicit()) return 1;
                return 0;
            }
        });
    }
}
