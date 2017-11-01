package bhg.pokedex.model;

import java.util.List;

/**
 * Created by Bruno on 23/10/2017.
 */

public class Pokemon {
    private String name;
    private List<Abilities> abilities;
    private List<Stats> stats;
    private List<Types> types;
    private int height;
    private int weight;
    private List<Integer> evolution;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Integer> getEvolution() {
        return evolution;
    }

    public void setEvolution(List<Integer> evolution) {
        this.evolution = evolution;
    }
}
