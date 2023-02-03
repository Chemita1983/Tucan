package domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketballPlayer extends Player {

    private int scoredPoints;
    private int rebounds;
    private int assists;

    public BasketballPlayer(String[] playerInput) {
        super(playerInput);
        this.scoredPoints = Integer.parseInt(playerInput[5]);
        this.rebounds = Integer.parseInt(playerInput[6]);
        this.assists = Integer.parseInt(playerInput[7]);
    }
}
