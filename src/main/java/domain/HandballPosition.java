package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HandballPosition {

    GOALKEEPER("G"),
    FIELDPLAYER("F");

    private final String position;
}
