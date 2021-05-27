package leader.game.grievance;

import leader.game.Game;

public abstract class GrievanceResolution {
    private String description;

    public GrievanceResolution(String description){
        this.description = description;
    }

    public abstract void apply(Game game);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
