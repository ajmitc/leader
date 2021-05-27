package leader.view;

import leader.Model;
import leader.game.Town;
import leader.view.component.HeadingLabel;
import leader.view.component.NormalLabel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class KingdomPanel extends JPanel {
    private Model model;
    private View view;

    private JLabel lblName;
    private JLabel lblTime;
    private JLabel lblSqAcres;

    private JTree treeTowns;
    private DefaultMutableTreeNode treeTownsRoot;

    public KingdomPanel(Model model, View view){
        super(new BorderLayout());
        this.model = model;
        this.view = view;

        lblName = new HeadingLabel();

        lblTime = new NormalLabel("0");
        JPanel pnlTime = new JPanel(new FlowLayout());
        pnlTime.add(new HeadingLabel("Months"));
        pnlTime.add(lblTime);

        JLabel lblSqAcresHeading = new HeadingLabel("Sq Acres");
        lblSqAcres = new NormalLabel();
        JPanel pnlSqAcres = new JPanel(new FlowLayout());
        pnlSqAcres.add(lblSqAcresHeading);
        pnlSqAcres.add(lblSqAcres);

        treeTownsRoot = new DefaultMutableTreeNode("Towns");
        treeTowns = new JTree(treeTownsRoot);
        treeTowns.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeTowns.getLastSelectedPathComponent();
                if (selectedNode == null)
                    return;
                if (selectedNode.getUserObject() != null && selectedNode.getUserObject() instanceof Town){
                    Town selectedTown = (Town) selectedNode.getUserObject();
                    view.getGamePanel().getGameDisplayPanel().showTown(selectedTown);
                }
            }
        });
        treeTowns.setExpandsSelectedPaths(true);
        treeTowns.expandRow(0);

        JPanel centerpanel = new JPanel();
        centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.PAGE_AXIS));
        centerpanel.add(pnlTime);
        centerpanel.add(pnlSqAcres);
        centerpanel.add(new JScrollPane(treeTowns));

        add(lblName, BorderLayout.NORTH);
        add(centerpanel, BorderLayout.CENTER);

        refresh();
    }

    public void refresh(){
        if (model.getGame() != null) {
            lblName.setText(model.getGame().getKingdom().getName());
            lblTime.setText("" + model.getGame().getTotalTicks());
            lblSqAcres.setText("" + model.getGame().getKingdom().getSqAcres());

            treeTownsRoot.removeAllChildren();
            for (Town town : model.getGame().getKingdom().getTowns()) {
                DefaultMutableTreeNode townNode = new DefaultMutableTreeNode(town);
                treeTownsRoot.add(townNode);
            }
        }
    }
}
