package leader.view;

import leader.Model;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Model model;
    private View view;

    private KingdomPanel kingdomPanel;
    private GameDisplayPanel gameDisplayPanel;

    public GamePanel(Model model, View view){
        super(new BorderLayout());
        this.model = model;
        this.view = view;

        kingdomPanel = new KingdomPanel(model, view);
        gameDisplayPanel = new GameDisplayPanel(model, view);

        add(kingdomPanel, BorderLayout.WEST);
        add(gameDisplayPanel, BorderLayout.CENTER);
    }

    public void refresh(){
        kingdomPanel.refresh();
        gameDisplayPanel.refresh();
    }

    public GameDisplayPanel getGameDisplayPanel() {
        return gameDisplayPanel;
    }
}
