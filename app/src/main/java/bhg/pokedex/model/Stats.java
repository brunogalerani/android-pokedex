package bhg.pokedex.model;

/**
 * Created by Bruno on 23/10/2017.
 */

public class Stats {

    private String name;
    private int baseStat;
    private int effort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }
}
