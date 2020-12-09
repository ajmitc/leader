package leader.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitAction extends AbstractAction {
    public ExitAction(){
        super("Exit");
        putValue(Action.SHORT_DESCRIPTION, "Exit the game");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
