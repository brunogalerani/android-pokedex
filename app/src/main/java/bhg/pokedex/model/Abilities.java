package bhg.pokedex.model;

/**
 * Created by Bruno on 23/10/2017.
 */

public class Abilities {
    private boolean isHidden;
    private String name;

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
