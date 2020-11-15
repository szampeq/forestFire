/*
 Discrete modelling classes - Forest Fire (AGH)
         @author  Krzysztof Gąciarz
         Github-project: https://github.com/szampeq/forestfire
 */

package com.krzysztgac.forestfire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;

import com.krzysztgac.forestfire.data.BinaryMapLoader;
import com.krzysztgac.forestfire.data.JForestPanel;
import com.krzysztgac.forestfire.data.Map;
import com.krzysztgac.forestfire.forest.Forest;
import com.krzysztgac.forestfire.states.*;
import com.krzysztgac.forestfire.tools.Button;

public class Main extends JFrame {

    private final JPanel buttonPanel;
    private final JPanel fireButtonPanel;
    static BinaryMapLoader bml;
    static JForestPanel jForestPanel;
    static Forest forestData;
    static Map map;

    public Main(String title){
        super(title);

        // ===================== PROGRAM SETTINGS ==========

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // ============= Button Panels =============

        buttonPanel = new JPanel();
        fireButtonPanel = new JPanel();
        mainPanel.add(BorderLayout.EAST, buttonPanel);
        mainPanel.add(BorderLayout.SOUTH, fireButtonPanel);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonPanel.setBackground(Color.WHITE);
        fireButtonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        fireButtonPanel.setBackground(Color.WHITE);

        // ============= Data =================
        bml = new BinaryMapLoader();
        forestData = new Forest();
        jForestPanel = new JForestPanel(forestData);
        mainPanel.add(jForestPanel);
        forestFire();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        this.setSize(new Dimension(1010, 550));
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {

        Main mw = new Main("Forest Fire");
        mw.setVisible(true);
    }

    public void forestFire(){
        // ButtonPanel settings
        buttonPanel.setLayout(new GridLayout(16, 1));

        // ButtonPanel - MapLoadLabel and MapLoader
        JLabel mapLabel = new JLabel("Load forest map:");
        buttonPanel.add(mapLabel);

        Button mapLoadButton = new Button("Load Forest", buttonPanel);
        JFileChooser pattern = new JFileChooser(new File("src/main/resources/binaryMaps/"));


        // Button Panel - Forest type
        JLabel forestTypeLabel = new JLabel("Select forest type:");
        buttonPanel.add(forestTypeLabel);

        JComboBox<ForestState> forestType = new JComboBox<>();
        forestType.setModel(new DefaultComboBoxModel<>(ForestState.values()));
        buttonPanel.add(forestType);

        // Forest density
        JLabel forestDensityLabel = new JLabel("Forest density:");
        buttonPanel.add(forestDensityLabel);

        JComboBox<DensityState> densityType = new JComboBox<>();
        densityType.setModel(new DefaultComboBoxModel<>(DensityState.values()));
        buttonPanel.add(densityType);

        // Button Panel - Select Season
        JLabel seasonLabel = new JLabel("Select season:");
        buttonPanel.add(seasonLabel);

        JComboBox<SeasonState> seasonType = new JComboBox<>();
        seasonType.setModel(new DefaultComboBoxModel<>(SeasonState.values()));
        buttonPanel.add(seasonType);

        // Button Panel - Select Wind Force & Direction
        JLabel windLabel = new JLabel("Wind Force & Direction:");
        buttonPanel.add(windLabel);

        JComboBox<WindState> windType = new JComboBox<>();
        windType.setModel(new DefaultComboBoxModel<>(WindState.values()));
        buttonPanel.add(windType);

        JComboBox<WindForceState> windForce = new JComboBox<>();
        windForce.setModel(new DefaultComboBoxModel<>(WindForceState.values()));
        buttonPanel.add(windForce);

        // Button Panel - Select Rainfall and Humidity
        JLabel rainfallLabel = new JLabel("Rainfall: ");
        buttonPanel.add(rainfallLabel);

        JComboBox<RainfallState> rainfallType = new JComboBox<>();
        rainfallType.setModel(new DefaultComboBoxModel<>(RainfallState.values()));
        buttonPanel.add(rainfallType);

        JLabel humidityLabel = new JLabel("Humidity: ");
        buttonPanel.add(humidityLabel);

        JComboBox<HumidityState> humidityType = new JComboBox<>();
        humidityType.setModel(new DefaultComboBoxModel<>(HumidityState.values()));
        buttonPanel.add(humidityType);

        // Create Data
        mapLoadButton.button.addActionListener(e -> {
            int result = pattern.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = pattern.getSelectedFile();
                String patternPath = selectedFile.getAbsolutePath();
                bml.loadImage(jForestPanel, patternPath);
                map = new Map(bml);
                forestData.matrix = map.ImageToMatrix();
                forestData.setupForest((ForestState)forestType.getSelectedItem(), (DensityState)densityType.getSelectedItem(),
                        (SeasonState)seasonType.getSelectedItem(), (WindState)windType.getSelectedItem(), (WindForceState)windForce.getSelectedItem(),
                        (RainfallState)rainfallType.getSelectedItem(), (HumidityState)humidityType.getSelectedItem());
            }
        });

        // Update Data
        Button updateButton = new Button("Update Data", buttonPanel);
        updateButton.button.addActionListener(e -> {
            forestData.deforestation();
            forestData.setupForest((ForestState)forestType.getSelectedItem(), (DensityState)densityType.getSelectedItem(),
                    (SeasonState)seasonType.getSelectedItem(), (WindState)windType.getSelectedItem(), (WindForceState)windForce.getSelectedItem(),
                    (RainfallState)rainfallType.getSelectedItem(), (HumidityState)humidityType.getSelectedItem());
        });

        // Button Panel - Start a Fire
        fireButtonPanel.setLayout(new FlowLayout());
        Button startButton = new Button("Start/Stop Fire!", fireButtonPanel);
        Button setFireButton = new Button("Set Random Fire", fireButtonPanel);
        Button sendFireman = new Button("Send Fireman", fireButtonPanel);
        Button sendHelicopter = new Button("Send Helicopter", fireButtonPanel);


        // ================== MOUSE LISTENERS ==================

        final int windowXCorrection = 7;
        final int windowYCorrection = 30;

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() - windowXCorrection;
                int y = e.getY() - windowYCorrection;

               // if (isBoardCreated.get())
                forestData.setFire(x, y);
            }

        });

        // =============================================

    }

}
