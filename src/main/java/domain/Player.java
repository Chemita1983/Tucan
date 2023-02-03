package domain;

import lombok.*;


/**
 * Class that contains player data match
 */
@Getter
@Setter
public class Player {

    private String player;
    private String alias;
    private int number;
    private String team;
    private String position;

    public Player(String[] playerInput) {

        this.player = playerInput[0];
        this.alias = playerInput[1];
        this.number = Integer.parseInt(playerInput[2]);
        this.team = playerInput[3];
        this.position = playerInput[4];
    }

}
