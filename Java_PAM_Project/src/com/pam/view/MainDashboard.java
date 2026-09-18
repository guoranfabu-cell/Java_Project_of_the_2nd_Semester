package com.pam.view;

import com.pam.*;
import com.pam.dao.UserDAO;
import com.pam.view.subView.OthersUtilChose;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainDashboard extends JFrame {
    public MainDashboard(String email, String nickname) {
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
        usernameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(0xFFFFFF));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setBounds(10, 10, 100, 30);
        ProfilePanel.add(usernameLabel);

        JLabel welcomeLabel = welcomeLabel(email, nickname);
        backgroundPanel.add(welcomeLabel);

        JButton othersUtilButton = othersUtilButton();
        backgroundPanel.add(othersUtilButton);

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

    public JLabel welcomeLabel(String email, String nickname) {
        UserDAO userDAO = new UserDAO();
        String welcomeText = " ";
        String gender = userDAO.getGender(email);

        if (gender.equals("MALE")) {
            welcomeText = "Welcome! My Mr. [ " + nickname + " ]";
        } else if (gender.equals("FEMALE")) {
            welcomeText = "Welcome! My Ms. [" + nickname + " ]";
        } else {
            welcomeText = "Welcome! My Owner [ " + nickname + " ]";
        }
        JLabel welcomeLabel = new JLabel(welcomeText);
        welcomeLabel.setBounds(300, 100, 1000, 100);
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        return welcomeLabel;
    }

    public JButton othersUtilButton() {
        JButton othersUtilButton = new JButton("...");
        othersUtilButton.setBackground(new Color(0xFFFFFF));
        othersUtilButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        othersUtilButton.setForeground(new Color(0x000000));
        othersUtilButton.setFocusPainted(false);
        othersUtilButton.setBounds(10, 10, 80, 80);
        othersUtilButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
        othersUtilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OthersUtilChose();
            }
        });

        return othersUtilButton;
    }
}
