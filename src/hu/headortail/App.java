package hu.headortail;

import hu.headortail.controller.HeadOrTailService;
import hu.headortail.domain.service.Console;
import hu.headortail.domain.service.Simulate;

import java.util.Random;
import java.util.Scanner;

public class App {

    private final HeadOrTailService service;
    private final Console console;

    private App() {
        Simulate simulate = new Simulate(new Random());
        service = new HeadOrTailService(simulate);
        console = new Console(new Scanner(System.in));
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
    }


}
