package hu.headortail.controller;

import hu.headortail.domain.service.DataFileHandler;
import hu.headortail.domain.service.Simulate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeadOrTailService {

    private final Simulate simulate;
    private final DataFileHandler dataFileHandler;

    public HeadOrTailService(Simulate simulate, DataFileHandler dataFileHandler) {
        this.simulate = simulate;
        this.dataFileHandler = dataFileHandler;
    }

    public String getSimulationResult() {
        return simulate.getResult();
    }

    public String getBetResult(String bet) {
        String result = getSimulationResult();
        String match = result.equals(bet) ? "Ön eltalálta" : "Ön nem találta el";
        return String.format("A tipp %s volt, a dobás eredménye %s volt%n%s!",bet, result, match);
    }

    public String getCount() {
        return  String.format("A kisérlet %d dobásból állt. ", dataFileHandler.getCount());
    }

    public String getHeadPercent() {
        return String.format("A kisérlet során a fej relatív gyakorisága %5.2f%% volt.", dataFileHandler.getHeadPercent());
    }

    public String getDoubleHeadCount() {
        return String.format("A kisérlet során %d alkalommal dobtak pontosan két fejet egymás után. ", dataFileHandler.getDoubleHeadCount());
    }

    public String getLongestHeadSequenceDetails() {
        return String.format("A leghosszabb tisztafej sorozat %d tagból áll, kezdete az %d. dobás", getLongestHeadCount(), getLongestHeadPosition());
    }

    private int getLongestHeadCount() {
        return dataFileHandler.getLongestHeadCount();
    }

    private int getLongestHeadPosition() {
        return dataFileHandler.getLongestHeadPosition();
    }

    public List<String> simulation() {
        int headCounter = 0, tailCounter = 0;
        List<String> simulations = getSimulations();
        for (var sim : simulations) {
            if ("FFFF".equals(sim)) {
                headCounter++;
            }
            if ("FFFI".equals(sim)) {
                tailCounter++;
            }
        }
        String firstItem = String.format("FFFF: %d, FFFI: %d", headCounter, tailCounter);
        String secondItem = simulations.stream()
                .collect(Collectors.joining(" "));
        return List.of(firstItem, secondItem);
    }

    private List<String> getSimulations() {
        return IntStream.range(0,1000)
                .mapToObj(i -> simulate.getSequence())
                .collect(Collectors.toList());
    }
}
