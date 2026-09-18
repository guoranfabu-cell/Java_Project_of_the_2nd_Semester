package com.pam.view.subView;

import com.pam.view.*;
import com.pam.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OthersUtilChose extends JFrame {
    public OthersUtilChose() {
        this.setTitle("OthersUtilChose");
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        JButton FortuneTest = new AllButtonDesignOffset("Today's Fortune!");
        FortuneTest.setBounds(100, 100, 100, 100);
        FortuneTest.setForeground(new Color(0xFFFFFF));
        FortuneTest.setFocusPainted(false);
        FortuneTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window[] windows = Window.getWindows();

                for (Window window : windows) {
                    window.dispose();
                }

                new FortuneTestView();
            }
        });
        JButton RandomMenuButton = new AllButtonDesignOffset("Random Menu!");
        RandomMenuButton.setBounds(300, 100, 100, 100);
        RandomMenuButton.setForeground(new Color(0xFFFFFF));
        RandomMenuButton.setFocusPainted(false);
        RandomMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window[] windows = Window.getWindows();

                for (Window window : windows) {
                    window.dispose();
                }

                new RandomMenuView();
            }
        });

        JButton BackButton = new AllButtonDesignOffset("Back!");
        BackButton.setBounds(170, 250, 160, 100);
        BackButton.setForeground(new Color(0xFFFFFF));
        BackButton.setFocusPainted(false);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(RandomMenuButton);
        this.add(BackButton);
        this.add(FortuneTest);

        this.setVisible(true);
    }
}
