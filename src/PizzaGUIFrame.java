import javax.swing.*;
import org.w3c.dom.Text;
import javax.swing.border.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.MessageFormat;


public class PizzaGUIFrame extends JFrame {
    JPanel mainP, twiceP, crustP, dimensionsP, toppingsP, paper, utilityP;
    JRadioButton thin, regular, StuffedCrust;
    JRadioButton combine;
    JComboBox groupBox;
    JCheckBox sockCB, wormsCB, scorpionsCB, spidersCB, olivesCB, cricketsCB, puffFishCB, octopusCB;
    JButton order, erase, terminate;
    JTextArea textArea;
    JScrollPane pane;
    double dimensionsPrice = 0, ingredPrice = 0, toppingsPrice = 0, total, tax = .07;

    public PizzaGUIFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension displaySize = kit.getScreenSize();
        int displayHeight = displaySize.height;
        int displayWidth = displaySize.width;

        setSize(700,650);
        setLocation(displayWidth / 4, (displayHeight - 600) / 2);

        setTitle("Order Your Pizza Here");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createGUI();
        setVisible(true);
    }

    private void createGUI()
    {
        mainP = new JPanel();
        twiceP = new JPanel();
        crustP = new JPanel();
        dimensionsP = new JPanel();
        toppingsP= new JPanel();
        paper = new JPanel();
        utilityP = new JPanel();

        mainP.setLayout(new BoxLayout(mainP, BoxLayout.PAGE_AXIS));
        twiceP.setLayout(new GridLayout(1,2));
        twiceP.add(crustP);
        twiceP.add(dimensionsP);
        mainP.add(twiceP);
        mainP.add(toppingsP);
        mainP.add(paper);
        mainP.add(utilityP);

        add(mainP);
        createCrustP();
        createdimensionsP();
        createtoppingsP();
        createpaperP();
        createUtilityP();
    }


    private void createCrustP() {
        crustP.setLayout(new GridLayout(1, 3));
        //  crustPnl.setBorder(new TitledBorder("Crust Type"));
        crustP.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Type of Crust"));



        combine = new JRadioButton();

        thin = new JRadioButton("Thin");
        thin.setHorizontalAlignment(JRadioButton.CENTER);
        thin.setVerticalAlignment(JRadioButton.CENTER);


        regular = new JRadioButton("Regular");
        regular.setHorizontalAlignment(JRadioButton.CENTER);
        regular.setVerticalAlignment(JRadioButton.CENTER);



        StuffedCrust = new JRadioButton("Stuffed Crust");
        StuffedCrust.setHorizontalAlignment(JRadioButton.CENTER);
        StuffedCrust.setVerticalAlignment(JRadioButton.CENTER);

        combine.add(thin);
        combine.add(regular);
        combine.add(StuffedCrust);

        crustP.add(thin);
        crustP.add(regular); regular.setSelected(true);
        crustP.add(StuffedCrust);
    }

    private void createdimensionsP() {
        //
        // sizePnl.setBorder(new TitledBorder("Pizza Size"));
        dimensionsP.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Size of Pizza"));

        groupBox = new JComboBox();
        groupBox.setAlignmentY(JComboBox.CENTER_ALIGNMENT);

        groupBox.addItem("Small");
        groupBox.addItem("Medium"); groupBox.setSelectedIndex(1);
        groupBox.addItem("Large");
        groupBox.addItem("Extra Large");

        // to get option pane for the select box type
        //   String box = (String) comboBox.getSelectedItem();
        dimensionsP.add(groupBox);
    }

    private void createtoppingsP() {
        toppingsP.setLayout(new GridLayout(2, 4));
        toppingsP.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Toppings"));

        sockCB = new JCheckBox("Sock");
        wormsCB = new JCheckBox("Worms");
        scorpionsCB = new JCheckBox("Scorpions");
        spidersCB= new JCheckBox("Spiders");
        olivesCB = new JCheckBox("Olives");
        cricketsCB = new JCheckBox("Crickets");
        puffFishCB = new JCheckBox("Puffer Fish");
        octopusCB= new JCheckBox("Octopus");

        toppingsP.add(sockCB);
        toppingsP.add(wormsCB);
        toppingsP.add(scorpionsCB);
        toppingsP.add(spidersCB);
        toppingsP.add(olivesCB);
        toppingsP.add(cricketsCB);
        toppingsP.add(puffFishCB);
        toppingsP.add(octopusCB);
    }

    private void createpaperP() {
        // receipt.setBorder(new TitledBorder("Receipt"));
        paper.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Receipt"));

        textArea = new JTextArea(20,25);
        textArea.setEditable(false);
        pane = new JScrollPane(textArea);
        paper.add(pane);
    }


    private void createUtilityP() {
        // controlPnl.setBorder(new TitledBorder("Control Options"));
        utilityP.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Control Options"));


        erase = new JButton("Erase");
        erase.addActionListener((ActionEvent ae) -> {

            eraseOrder();

            enableOrder();


        });

        order = new JButton("Order");
        order.addActionListener((ActionEvent ae ) -> {

            DecimalFormat df = new DecimalFormat("$0.00");

            String res = "Order Details:\n";
            res += "=============================\n";

            res += "Crust Type: ";
            if (thin.isSelected()) {
                res += "Thin\n";
            }
            else if (regular.isSelected()) {
                res += "Regular\n";
            }
            else {
                res += "Stuffed Crust\n";
            }

            String box = (String) groupBox.getSelectedItem();

            res += "Size: ";
            res += groupBox.getSelectedItem() + "\t\t";

            if (box.equals("Small")) {
                dimensionsPrice = 8;
                res += df.format(dimensionsPrice) + "\n";
            }
            else if (box.equals("Medium"))
            {
                dimensionsPrice = 12;
                res += df.format(dimensionsPrice) + "\n";
            }
            else if (box.equals("Large")) {
                dimensionsPrice = 16;
                res += df.format(dimensionsPrice) + "\n";
            }
            else {
                dimensionsPrice = 20;
                res += df.format(dimensionsPrice) + "\n";
            }

            res += "Toppings ($1 each):\n";

            if (sockCB.isSelected())
            {
                res+= "Sock\t\t";
                ingredPrice = 1;
                res += df.format(ingredPrice) + "\n";
                toppingsPrice = toppingsPrice + ingredPrice;

            }

            if (wormsCB.isSelected())
            {
                res+= "Worms\t\t";
                ingredPrice = 1;
                res += df.format(ingredPrice) + "\n";
                toppingsPrice = toppingsPrice + ingredPrice;
            }

            if (scorpionsCB.isSelected())
            {
                ingredPrice = 1;
                res += "Scorpions\t" + df.format(ingredPrice) + "\n";
                toppingsPrice = toppingsPrice + ingredPrice;
            }

            if (spidersCB.isSelected())
            {
                res+= "Spiders\t\t";
                ingredPrice = 1;
                res += df.format(ingredPrice) + "\n";
                toppingsPrice = toppingsPrice + ingredPrice;
            }
            if (olivesCB.isSelected())
            {
                res+= "Olives\t\t";
                ingredPrice = 1;
                res += df.format(ingredPrice) + "\n";
                toppingsPrice = toppingsPrice + ingredPrice;
            }

            if (cricketsCB.isSelected())
            {
                res+= "Crickets\t\t";
                ingredPrice = 1;
                res += df.format(ingredPrice) + "\n";
                toppingsPrice = toppingsPrice + ingredPrice;

            }

            if (puffFishCB.isSelected())
            {
                res+= "Puffer Fish\t\t";
                ingredPrice = 1;
                res += df.format(ingredPrice) + "\n";
                toppingsPrice = toppingsPrice + ingredPrice;
            }

            if (octopusCB.isSelected())
            {
                res+= "Octopus\t\t";
                ingredPrice = 1;
                res += df.format(ingredPrice) + "\n";
                toppingsPrice = toppingsPrice + ingredPrice;
            }


            res+= "\nTopping Price:\t\t" + df.format(toppingsPrice);

            res+= "\n";
            res+= "\n";

            total = dimensionsPrice + toppingsPrice;

            res += "Sub-total:\t\t";
            res += df.format(total) + "\n";

            tax = total * tax;

            res +=  "Tax:\t\t";
            res += df.format(tax) + "\n";

            total = total + tax;

            res += "------------------------------------\n";

            res += "Total:\t\t";
            res += df.format(total);

            res += "\n=============================\n";

            textArea.append(res);

            disableOrder();
        });

        terminate = new JButton("Quit");
        terminate.addActionListener((ActionEvent ae) -> {
            int terminate;
            terminate = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit your order?", "Quit Order", JOptionPane.YES_NO_OPTION);
            if (terminate == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });

        utilityP.add(erase);
        utilityP.add(order);
        utilityP.add(terminate);
    }

    private void eraseOrder()
    {
        textArea.setText(" ");
        tax = 0.07;
        dimensionsPrice = 0;
        ingredPrice = 0;
        total = 0;
        toppingsPrice = 0;

        regular.setSelected(true);

        groupBox.setSelectedIndex(1);

        sockCB.setSelected(false);
        wormsCB.setSelected(false);
        scorpionsCB.setSelected(false);
        spidersCB.setSelected(false);
        olivesCB.setSelected(false);
        cricketsCB.setSelected(false);
        puffFishCB.setSelected(false);
        octopusCB.setSelected(false);
    }
    private void enableOrder() {
        order.setEnabled(true);

        sockCB.setEnabled(true);
        wormsCB.setEnabled(true);
        scorpionsCB.setEnabled(true);
        spidersCB.setEnabled(true);
        olivesCB.setEnabled(true);
        cricketsCB.setEnabled(true);
        puffFishCB.setEnabled(true);
        octopusCB.setEnabled(true);

        groupBox.setEnabled(true);

        thin.setEnabled(true);
        regular.setEnabled(true);
        StuffedCrust.setEnabled(true);
    }
    private void disableOrder() {
        order.setEnabled(false);

        sockCB.setEnabled(false);
        wormsCB.setEnabled(false);
        scorpionsCB.setEnabled(false);
        spidersCB.setEnabled(false);
        olivesCB.setEnabled(false);
        cricketsCB.setEnabled(false);
        puffFishCB.setEnabled(false);
        octopusCB.setEnabled(false);

        groupBox.setEnabled(false);

        thin.setEnabled(false);
        regular.setEnabled(false);
        StuffedCrust.setEnabled(false);
    }

}