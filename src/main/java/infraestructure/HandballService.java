package infraestructure;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Service to calculate handball match data
 */
public class HandballService implements  SportStats<HandballPlayer> {

    @Override
    public Map<String, Integer> calculateMpv(Match<HandballPlayer> match) {

        Map<String, List<HandballPlayer>> statsByTeam = match.getPlayers().stream()
                .collect(Collectors.groupingBy(Player::getTeam));

        return this.calculatePoints(statsByTeam);
    }

    @Override
    public Map<String, Integer> calculatePoints(Map<String, List<HandballPlayer>> entryPlayer) {

        Map<String, Integer> teamResults = new HashMap<>();

        for (Map.Entry<String, List<HandballPlayer>> entry : entryPlayer.entrySet()) {

            teamResults.put(entry.getKey(), entry.getValue().stream()
                    .map(this::calculatePlayerPoints)
                    .reduce(0, Integer::sum));
        }

        return teamResults;
    }

    @Override
    public Integer calculatePlayerPoints(HandballPlayer player) {

        int result = 0;

        if (player.getPosition().equals(HandballPosition.GOALKEEPER.getPosition())) {
            result = (50 + player.getGoalsMade() * 5) - (player.getGoalsReceived() * 2);
        }
        if (player.getPosition().equals(HandballPosition.FIELDPLAYER.getPosition())) {
            result = (20 + player.getGoalsMade()) - (player.getGoalsReceived());
        }

        return result;
    }


    @Override
    public List<HandballPlayer> getPlayerStats(List<String> matchStats) {

        List<HandballPlayer> handballPlayers = new ArrayList<>();
        for (int i = 1; i < matchStats.size(); i++) {
            handballPlayers.add(new HandballPlayer(matchStats.get(i).split("[;\\n]")));
        }
        return handballPlayers;
    }



}
