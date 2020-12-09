package leader.view;

import leader.Model;
import leader.action.ExitAction;
import leader.action.NewGameAction;
import leader.view.component.TitleLabel;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel(Model model, View view){
        super(new BorderLayout());

        JLabel lblTitle = new TitleLabel("Leader");

        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.PAGE_AXIS));

        JButton btnNewGame = new JButton(new NewGameAction(model, view));
        pnlButtons.add(btnNewGame);

        JButton btnExit = new JButton(new ExitAction());
        pnlButtons.add(btnExit);

        add(lblTitle,   BorderLayout.NORTH);
        add(pnlButtons, BorderLayout.CENTER);
    }
}
