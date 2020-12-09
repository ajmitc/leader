package leader.util;

import java.awt.*;

public class GridBagBuilder {
    private Container container;
    private GridBagConstraints constraints;

    public GridBagBuilder(Container container){
        this.container = container;
        setLayout(container);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
    }

    public GridBagBuilder setLayout(Container container){
        container.setLayout(new GridBagLayout());
        return this;
    }

    public GridBagBuilder add(Component component){
        container.add(component, constraints);
        constraints.gridx += 1;
        return this;
    }

    public GridBagBuilder newRow(){
        constraints.gridy += 1;
        constraints.gridx = 0;
        return this;
    }

    public GridBagBuilder setGridWidth(int v){
        constraints.gridwidth = v;
        return this;
    }

    public GridBagBuilder resetGridWidth(){
        constraints.gridwidth = 1;
        return this;
    }

    public GridBagBuilder setInternalPadding(int x, int y){
        constraints.ipadx = x;
        constraints.ipady = y;
        return this;
    }
}
