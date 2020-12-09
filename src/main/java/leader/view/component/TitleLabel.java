package leader.view.component;

import leader.view.ViewUtil;

import javax.swing.*;

public class TitleLabel extends JLabel {
    public TitleLabel(String text){
        super(text);
        setFont(ViewUtil.TITLE_FONT);
    }
}
