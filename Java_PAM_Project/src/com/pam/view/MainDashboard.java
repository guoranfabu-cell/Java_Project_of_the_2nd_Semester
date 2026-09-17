package com.pam.view;

import com.pam.*;
import com.pam.dao.UserDAO;

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
    public MainDashboard(String nickname) {
        this.setTitle("PAM");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setSize(1920, 1080);
        backgroundPanel.setBackground(Color.white);
        backgroundPanel.setLayout(null);

        JPanel ProfilePanel = new JPanel();
        ProfilePanel.setBounds(1770,30,120,50);
        ProfilePanel.setBackground(new Color(0x000000));
        ProfilePanel.setLayout(null);
        backgroundPanel.add(ProfilePanel);

        JLabel usernameLabel = new JLabel(nickname);
        usernameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(0xFFFFFF));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setBounds(10, 10, 100, 30);
        ProfilePanel.add(usernameLabel);

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
