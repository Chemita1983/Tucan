package infraestructure;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Service to calculate basketball match data
 */
public class BasketballService implements SportStats<BasketballPlayer> {

    public Map<String, Integer> calculateMpv(Match<BasketballPlayer> match) {

        Map<String, List<BasketballPlayer>> statsByTeam = match.getPlayers().stream()
                .collect(Collectors.groupingBy(Player::getTeam));

        return this.calculatePoints(statsByTeam);
    }

    @Override
    public Map<String, Integer> calculatePoints(Map<String, List<BasketballPlayer>> entryPlayer) {

        Map<String, Integer> teamResults = new HashMap<>();

        for (Map.Entry<String, List<BasketballPlayer>> entry : entryPlayer.entrySet()) {

            teamResults.put(entry.getKey(), entry.getValue().stream()
                    .map(this::calculatePlayerPoints)
                    .reduce(0, Integer::sum));
        }

        return teamResults;
    }

    @Override
    public Integer calculatePlayerPoints(BasketballPlayer player) {

        int result = 0;

        // Se podria implementar un patron chain of responsibility
        if (player.getPosition().equals(BasketballPosition.GUARD.getPosition())) {
            result = (player.getScoredPoints() * 2) + (player.getRebounds() * 3) + player.getAssists();
        }
        if (player.getPosition().equals(BasketballPosition.FORWARD.getPosition())) {
            result = (player.getScoredPoints() * 2) + (player.getRebounds() * 2) + (player.getAssists() * 2);
        }
        if (player.getPosition().equals(BasketballPosition.CENTER.getPosition())) {
            result = (player.getScoredPoints() * 2) + player.getRebounds() + (player.getAssists() * 3);
        }

        return result;
    }

    @Override
    public List<BasketballPlayer> getPlayerStats(List<String> matchStats) {
        List<BasketballPlayer> basketballPlayers = new ArrayList<>();
        for (int i = 1; i < matchStats.size(); i++) {
            basketballPlayers.add(new BasketballPlayer(matchStats.get(i).split("[;\\n]")));
        }
        return basketballPlayers;
    }
}
