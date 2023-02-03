package infraestructure;

import domain.HandballPlayer;
import domain.Match;
import domain.SportType;

import java.util.*;

/**
 * Service to calculate match data.
 */
public class MatchService {

     BasketballService basketballService = new BasketballService();
     HandballService handballService = new HandballService();

    public String getMvp(List<String> matchStats) {
        Match match = getMatchStats(matchStats);
        return calculateMpv(match);
    }

    private  Match getMatchStats(List<String> matchStats) {
        return Match.builder()
                .withSport(matchStats.get(0))
                .withPlayers(getPlayerStats(matchStats))
                .build();
    }

    private  List getPlayerStats(List<String> matchStats) {

        //Se podria implementar un patrón factory.
        if (matchStats.get(0).equals(SportType.BASKETBALL.name())) {
            return basketballService.getPlayerStats(matchStats);
        } else if (matchStats.get(0).equals(SportType.HANDBALL.name())) {
            return handballService.getPlayerStats(matchStats);
        }
        return Collections.emptyList();

    }

    private  String calculateMpv(Match match) {

        String winnerTeam = null;

        if (match.getSport().equals(SportType.BASKETBALL.name())) {
            Map matchResults = basketballService.calculateMpv(match);
            addPointsToWinnerTeam(matchResults);
            winnerTeam = getWinnerTeam(matchResults);

        } else if (match.getSport().equals(SportType.HANDBALL.name())) {
            winnerTeam = getWinnerTeam(match);
        } else {
            System.out.println("Sport unknown");
        }
        return winnerTeam;
    }


    private  void addPointsToWinnerTeam(Map<String, Integer> matchResults) {
        int max = Collections.max(matchResults.values());
        for (Map.Entry<String, Integer> team : matchResults.entrySet()) {
            if (team.getValue() == max) {
                team.setValue(team.getValue() + 10);
            }
        }
    }

    private  String getWinnerTeam(Map<String, Integer> matchResults) {

        String winnerTeam = null;
        int max = Collections.max(matchResults.values());
        for (Map.Entry<String, Integer> team : matchResults.entrySet()) {
            if (team.getValue() == max) {
                winnerTeam = "Winner team is " + team.getKey() + " with " + team.getValue() + " points";
            }
        }
        return winnerTeam;
    }

    private  String getWinnerTeam(Match<HandballPlayer> match) {

        HandballPlayer handballPlayer = match.getPlayers().stream()
                .max(Comparator.comparing(HandballPlayer::getGoalsMade))
                .orElseThrow(NoSuchElementException::new);

        return "Winner team is " + handballPlayer.getTeam() + " with " + handballPlayer.getGoalsMade() + " goals";
    }

}
