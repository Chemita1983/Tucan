package domain;

import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter
@Builder(setterPrefix = "with")
@AllArgsConstructor
/**
 * Class that contains match data
 */
public class Match <T extends Player>{

    private String sport;

    private List<T> players;

}
