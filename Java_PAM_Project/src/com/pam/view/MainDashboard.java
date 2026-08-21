package com.pam.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MainDashboard extends JFrame {
    public MainDashboard() {
        this.setTitle("PAM");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setSize(1920, 1080);
        backgroundPanel.setLayout(null);

        JPanel ProfilePanel = new JPanel();
        ProfilePanel.setBounds(1770,80,100,50);
        ProfilePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        ProfilePanel.setLayout(null);

        JPanel OthersPanel = new JPanel();
        OthersPanel.setLayout(null);

        JPanel DialogPanel = new JPanel();
        DialogPanel.setLayout(null);

        JPanel CloseSchedulePanel = new JPanel();
        CloseSchedulePanel.setLayout(null);

        JPanel CallUserPanel = new JPanel();
        CallUserPanel.setLayout(null);

        this.add(backgroundPanel);

        java.net.URL imgUrl = getClass().getResource("PAMLogo.png");
        if (imgUrl != null) {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgUrl));
        }

        this.setVisible(true);
    }
}
