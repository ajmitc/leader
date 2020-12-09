package leader.view.component;

import leader.view.ViewUtil;

import javax.swing.*;

public class NormalLabel extends JLabel {
    public NormalLabel(){
        super();
        setFont(ViewUtil.NORMAL_FONT);
    }
    public NormalLabel(String text){
        this();
        setText(text);
    }
}
