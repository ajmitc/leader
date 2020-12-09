package leader.action;

import leader.Model;
import leader.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewGameAction extends AbstractAction {
    private Model model;
    private View view;

    public NewGameAction(Model model, View view){
        super("New Game");
        putValue(Action.SHORT_DESCRIPTION, "Start a new game");
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.newGame();
        view.showGame();
        view.refresh();
    }
}
