package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BasketballPosition {
    GUARD("G"),
    FORWARD("F"),
    CENTER("C");

    private final String position;
}
