package leader.view;

import leader.Model;

import javax.swing.*;
import java.awt.*;

public class View {
    private static final String MAINMENU = "MainMenu";
    private static final String GAME = "Game";

    protected Model model;
    private JFrame frame;
    private RootPanel rootPanel;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;

    public View(Model model) {
        this.model = model;
        frame = new JFrame();
        frame.setTitle("Leader");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        rootPanel = new RootPanel();
        frame.getContentPane().add(rootPanel, BorderLayout.CENTER);

        mainMenuPanel = new MainMenuPanel(model, this);
        rootPanel.add(mainMenuPanel, MAINMENU);

        gamePanel = new GamePanel(model, this);
        rootPanel.add(gamePanel, GAME);
    }

    public void refresh(){
        gamePanel.refresh();
    }

    public void showMainMenu(){
        rootPanel.showPanel(MAINMENU);
    }

    public void showGame(){
        rootPanel.showPanel(GAME);
        gamePanel.refresh();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public JFrame getFrame(){return frame;}
}