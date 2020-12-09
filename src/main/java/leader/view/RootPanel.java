package leader.view;

import javax.swing.*;
import java.awt.*;

public class RootPanel extends JPanel {
    private CardLayout cardLayout;

    public RootPanel(){
        super(new CardLayout());
        cardLayout = (CardLayout) getLayout();
    }

    public void showPanel(String name){
        cardLayout.show(this, name);
    }
}
