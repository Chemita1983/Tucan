package main.java;


import infraestructure.MatchService;
import infraestructure.utils.IOUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        MatchService matchService = new MatchService();

        try {
            List<String> matchStats = IOUtils.readFile("input/basketballMatch.txt");
            System.out.println(matchService.getMvp(matchStats));
        } catch (Exception e) {
            throw new Exception("File incorrect MVP cant be calculated");
        }
    }



}