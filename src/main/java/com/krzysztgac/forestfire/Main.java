/*
 Discrete modelling classes - Forest Fire (AGH)
         @author  Krzysztof Gąciarz
         Github-project: https://github.com/szampeq/forestfire
 */

package com.krzysztgac.forestfire;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

import com.krzysztgac.forestfire.data.BinaryMapLoader;
import com.krzysztgac.forestfire.data.JForestPanel;
import com.krzysztgac.forestfire.data.Map;
import com.krzysztgac.forestfire.tools.Button;

public class Main extends JFrame {

    private final JPanel buttonPanel;
    static BinaryMapLoader bml;
    static JForestPanel jForestPanel;
    static Map map;

    public Main(String title){
        super(title);

        // =====================

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        mainPanel.add(BorderLayout.EAST, buttonPanel);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonPanel.setBackground(Color.WHITE);

        bml = new BinaryMapLoader();
        jForestPanel = new JForestPanel(bml);
        mainPanel.add(jForestPanel);
        forestFire();



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        this.setSize(new Dimension(1000, 520));
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {

        Main mw = new Main("Forest Fire");
        mw.setVisible(true);
    }

    public void forestFire(){
        // ButtonPanel settings
        buttonPanel.setLayout(new GridLayout(10, 1));

        // ButtonPanel - MapLoadLabel and MapLoader
        JLabel mapLabel = new JLabel("Load forest map:");
        buttonPanel.add(mapLabel);

        Button mapLoadButton = new Button("Load Forest", buttonPanel);
        JFileChooser pattern = new JFileChooser(new File("src/main/resources/binaryMaps/"));

        mapLoadButton.button.addActionListener(e -> {
            int result = pattern.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = pattern.getSelectedFile();
                String patternPath = selectedFile.getAbsolutePath();
                    bml.loadImage(jForestPanel, patternPath);
                map = new Map(bml);
                map.createForest();
            }
        });

        // Button Panel - Forest type
        JLabel forestTypeLabel = new JLabel("Select forest type:");
        buttonPanel.add(forestTypeLabel);

        // Button Panel - Select Season
        JLabel seasonLabel = new JLabel("Select season:");
        buttonPanel.add(seasonLabel);

        // Button Panel - Select Wind Force & Direction
        JLabel windLabel = new JLabel("Wind Force & Direction:");
        buttonPanel.add(windLabel);

        // Button Panel - Select Humidity
        JLabel humidityLabel = new JLabel("Humidity: ");
        buttonPanel.add(humidityLabel);
    }

}
