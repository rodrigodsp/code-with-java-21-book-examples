package com.example.chapter9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class SolarSystem extends JPanel implements Runnable {

    private static final long serialVersionUID = 9881646148L;

    private final int fPS = 60; // frames per second

    private int panelWidth;
    private int panelHeight;
    private int middleWidth;
    private int middleHeight;

    private List<Planet> planetList;

    private Thread panelThread;

    public SolarSystem() {
        this(1024, 1024);
    }

    public SolarSystem(int width, int height) {
        panelWidth = width;
        panelHeight = height;
        middleWidth = panelWidth / 2;
        middleHeight = panelHeight / 2;

        panelThread = Thread.ofVirtual()
            .name("solarSystemThread")
            .unstarted(this);

        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        this.planetList = new ArrayList<>();
        // Mercury
        planetList.add(new Planet(Color.DARK_GRAY, 20, 1, 5));
        // Venus
        planetList.add(new Planet(Color.GRAY, 48, 2, 4));
        // Earth
        planetList.add(new Planet(Color.BLUE, 50, 3, 3));
        // Mars
        planetList.add(new Planet(Color.RED, 25, 4, 2));

    }

    @Override
    public void run() {
        
        while (panelThread.isAlive()) {

            update();
            repaint();

            try {
                Thread.sleep(1000/fPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {

        for (Planet planet : planetList) {
            planet.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // draw sun
        int diameter = 48;
        int radius = diameter / 2;

        g2.setColor(Color.YELLOW);
        g2.fillOval(middleWidth - radius, middleHeight - radius, diameter, diameter);

        // draw planets
        for (Planet planet : planetList) {
            g2.setColor(planet.getColor());
            g2.fillOval(middleWidth + planet.getCoordX(), middleHeight + planet.getCoordY(), 
                planet.getDiameter(), planet.getDiameter());
        }

        g2.dispose();
    }

    public void start() {
        panelThread.start();
    }
    
}
