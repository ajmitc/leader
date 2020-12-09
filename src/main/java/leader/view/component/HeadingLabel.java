package leader.view.component;

import leader.view.ViewUtil;

import javax.swing.*;

public class HeadingLabel extends JLabel {
    public HeadingLabel(){
        super();
        setFont(ViewUtil.HEADING_FONT);
    }
    public HeadingLabel(String text){
        this();
        setText(text);
    }
}
