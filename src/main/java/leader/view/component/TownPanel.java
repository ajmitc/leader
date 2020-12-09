package leader.view.component;

import leader.Model;
import leader.Town;
import leader.population.PersonAttribute;
import leader.population.PopulationStatistics;
import leader.population.PersonStruct;
import leader.util.GridBagBuilder;
import leader.view.View;

import javax.swing.*;
import java.awt.*;

public class TownPanel extends JPanel {
    private Town town;
    private TitleLabel lblName;
    private NormalLabel lblSqAcres;
    private NormalLabel lblCoordinates;
    private NormalLabel lblPopulationSize;
    private NormalLabel lblPopulationMale;
    private NormalLabel lblPopulationFemale;
    private NormalLabel lblPopulationNumChildren;
    private NormalLabel lblPopulationNumAdolescents;
    private NormalLabel lblPopulationNumAdults;
    private NormalLabel lblPopulationNumElderly;

    public TownPanel(Model model, View view){
        super(new BorderLayout());

        JLabel lblSqAcresHeading = new HeadingLabel("Sq Acres: ");
        JLabel lblCoordinatesHeading = new HeadingLabel("Coordinates: ");
        JLabel lblPopulationSizeHeading = new HeadingLabel("Total Population: ");
        JLabel lblPopulationMaleHeading = new HeadingLabel("Males: ");
        JLabel lblPopulationFemaleHeading = new HeadingLabel("Females: ");
        JLabel lblPopulationNumChildrenHeading = new HeadingLabel("Children: ");
        JLabel lblPopulationNumAdolescentsHeading = new HeadingLabel("Adolescents: ");
        JLabel lblPopulationNumAdultsHeading = new HeadingLabel("Adults: ");
        JLabel lblPopulationNumElderlyHeading = new HeadingLabel("Elderly: ");

        lblName = new TitleLabel("");
        lblSqAcres = new NormalLabel();
        lblCoordinates = new NormalLabel();
        lblPopulationSize = new NormalLabel();
        lblPopulationMale = new NormalLabel();
        lblPopulationFemale = new NormalLabel();
        lblPopulationNumChildren = new NormalLabel();
        lblPopulationNumAdolescents = new NormalLabel();
        lblPopulationNumAdults = new NormalLabel();
        lblPopulationNumElderly = new NormalLabel();

        JPanel pnlPopulation = new JPanel();
        new GridBagBuilder(pnlPopulation)
                .add(lblSqAcresHeading).add(lblSqAcres).newRow()
                .add(lblCoordinatesHeading).add(lblCoordinates).newRow()
                .add(lblPopulationSizeHeading).add(lblPopulationSize).newRow()
                .add(lblPopulationMaleHeading).add(lblPopulationMale).newRow()
                .add(lblPopulationFemaleHeading).add(lblPopulationFemale).newRow()
                .add(lblPopulationNumChildrenHeading).add(lblPopulationNumChildren).newRow()
                .add(lblPopulationNumAdolescentsHeading).add(lblPopulationNumAdolescents).newRow()
                .add(lblPopulationNumAdultsHeading).add(lblPopulationNumAdults).newRow()
                .add(lblPopulationNumElderlyHeading).add(lblPopulationNumElderly).newRow();

        add(lblName, BorderLayout.NORTH);
        add(pnlPopulation, BorderLayout.CENTER);
    }

    public void setTown(Town town){
        this.town = town;
        refresh();
    }

    public void refresh(){
        if (town == null)
            return;
        lblName.setText(town.getName());
        lblSqAcres.setText("" + town.getSqAcres());
        lblCoordinates.setText(town.getX() + ", " + town.getY());
        lblPopulationSize.setText("" + town.getPopulation().size());

        PopulationStatistics stats = town.getPopulation().generateStats();
        lblPopulationMale.setText("" + stats.get(PersonAttribute.MALE));
        lblPopulationFemale.setText("" + stats.get(PersonAttribute.FEMALE));
        lblPopulationNumChildren.setText("" + stats.get(PersonAttribute.CHILD));
        lblPopulationNumAdolescents.setText("" + stats.get(PersonAttribute.ADOLESCENT));
        lblPopulationNumAdults.setText("" + stats.get(PersonAttribute.ADULT));
        lblPopulationNumElderly.setText("" + stats.get(PersonAttribute.ELDERLY));
    }
}
