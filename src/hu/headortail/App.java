package hu.headortail;

import hu.headortail.controller.HeadOrTailService;
import hu.headortail.domain.service.Console;
import hu.headortail.domain.service.DataFileHandler;
import hu.headortail.domain.service.FileWriter;
import hu.headortail.domain.service.Simulate;

import java.util.Random;
import java.util.Scanner;

public class App {

    private final HeadOrTailService service;
    private final Console console;
    private final FileWriter fileWriter;

    private App() {
        Simulate simulate = new Simulate(new Random());
        DataFileHandler dataFileHandler = new DataFileHandler("kiserlet.txt");
        service = new HeadOrTailService(simulate, dataFileHandler);
        console = new Console(new Scanner(System.in));
        fileWriter = new FileWriter("dobasok.txt");
    }

    public static void main(String[] args) {
        new App().run();

    }

    private void run() {
        System.out.println("1. feladat");
        System.out.println("A pénzfeldobás eredménye: " + service.getSimulationResult());
        System.out.println("2. feladat");
        System.out.print("Tippeljen! (F/I)= ");
        String bet = console.read();
        System.out.println(service.getBetResult(bet));
        System.out.println("3. feladat");
        System.out.println(service.getCount());
        System.out.println("4.feladat");
        System.out.println(service.getHeadPercent());
        System.out.println("5.feladat");
        System.out.println(service.getDoubleHeadCount());
        System.out.println("6.feladat");
        System.out.println(service.getLongestHeadSequenceDetails());
        fileWriter.write(service.simulation());
    }


}
