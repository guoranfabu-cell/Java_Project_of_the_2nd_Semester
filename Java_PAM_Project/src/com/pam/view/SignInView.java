package com.pam.view;

import com.pam.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignInView extends JFrame {
    public SignInView(String nickname) {
        this.setTitle("Welcome!!!");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(new Color(255, 255, 255));
        signUpPanel.setLayout(new GridBagLayout());

        JPanel startPanel = new JPanel();
        startPanel.setBackground(new Color(255, 255, 255));
        startPanel.setPreferredSize(new Dimension(800, 400));
        startPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        startPanel.setLayout(null);
        signUpPanel.add(startPanel);

        JLabel signUpLabel = new JLabel("Welcome to Your Account!!");
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signUpLabel.setBounds(0, 0, 800, 200);
        signUpLabel.setFont(new Font("굴림", Font.BOLD, 50));
        startPanel.add(signUpLabel);

        JButton startButton = StartButton(nickname);
        startPanel.add(startButton);

        this.add(signUpPanel);

        java.net.URL imgUrl = getClass().getResource("PAMLogo.png");
        if (imgUrl != null) {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgUrl));
        }

        this.setVisible(true);
    }

    private JButton StartButton(String nickname) {
        JButton startButton = new GradientButton("Lets get start!");
        startButton.setBackground(new Color(0x002C7A));
        startButton.setBounds(250,200,300, 100);
        startButton.setForeground(new Color(255, 255 ,255));
        startButton.setFont(new Font("굴림", Font.BOLD, 30));
        startButton.setFocusPainted(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainDashboard(nickname).setVisible(true);

                dispose();
            }
        });

        return startButton;
    }
}
