package leader.view;

import leader.Model;
import leader.game.Town;
import leader.view.component.TownPanel;

import javax.swing.*;
import java.awt.*;

public class GameDisplayPanel extends JPanel {
    public static final String TOWN = "Town";

    private CardLayout cardLayout;
    private TownPanel townPanel;

    public GameDisplayPanel(Model model, View view){
        super();
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        townPanel = new TownPanel(model, view);

        add(townPanel, TOWN);
    }

    public void showTown(Town town){
        townPanel.setTown(town);
        cardLayout.show(this, TOWN);
    }

    public void refresh(){
        townPanel.refresh();
    }
}
