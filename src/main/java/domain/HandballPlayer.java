package domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HandballPlayer extends Player {

    private int goalsMade;

    private int goalsReceived;

    public HandballPlayer(String[] playerInput) {
        super(playerInput);
        this.goalsMade = Integer.parseInt(playerInput[5]);
        this.goalsReceived = Integer.parseInt(playerInput[6]);
    }
}
