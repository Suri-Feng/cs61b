package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy -= 0.01;
    }

    public Clorus replicate() {
        double babyEnergy = energy * 0.5;
        energy *= 0.5;
        return new Clorus(babyEnergy);
    }

    public boolean checkPlip(Map<Direction, Occupant> neighbors){
        for (Direction key : neighbors.keySet()){
            if (neighbors.get(key).name().equals("plip")){
                return true;
            }
        }
        return false;
    }
    public Direction randomEntry(Deque<Direction> Neighbors) {
        int tot = Neighbors.size();
        double randomNumber = Math.random();
        for (int i = 0; i< tot; i++) {
            Direction item = Neighbors.pop();
            if (randomNumber >= i/tot && randomNumber <= (i+1)/tot){
                return item;
            }
        }
        return null;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlip = checkPlip(neighbors);

        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("plip")) {
                plipNeighbors.addFirst(key);
            }
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.addFirst(key);
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (anyPlip) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        } else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }
}
