package pl.bieniek;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        SYSTEM os = new SYSTEM();
        System.out.println("----------OS-----------");
        System.out.println(os.manufacturer+" "+os.family+" "+ os.ver);
        CPU cpu1 = new CPU();
        System.out.println("----------CPU----------");
        System.out.println(cpu1.name);
        System.out.println("Częstotliwość: "+cpu1.freq+" GHz");
        GPU gpu1 = new GPU();
        System.out.println("----------GPU----------");
        System.out.println("Nazwa: "+gpu1.Name);
        System.out.println("VRAM: "+gpu1.VRAM+" GB");
        RAM ram1 = new RAM();
        System.out.println("----------RAM----------");
        System.out.println("Ilość RAMu: "+ram1.total+" GB");
    }

}
