package infraestructure;

import domain.BasketballPlayer;
import domain.Match;
import domain.Player;

import java.util.List;
import java.util.Map;

public interface SportStats <T extends Player> {

     Map<String, Integer> calculatePoints(Map<String, List<T>> entryPlayer);

     Integer calculatePlayerPoints(T player);

     Map<String, Integer> calculateMpv(Match<T> match);

     List<T> getPlayerStats(List<String> matchStats);
}
