package com.pam.view;

import com.pam.AllButtonDesignOffset;
import com.pam.RoundedJTextField;
import com.pam.RoundedPasswordField;
import com.pam.dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPasswordView extends JFrame {
    public ForgotPasswordView() {
        this.setTitle("Find Your Password!");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new GridBagLayout());

        JPanel inputBackgroundPanel = new JPanel();
        inputBackgroundPanel.setPreferredSize(new Dimension(400, 300));
        inputBackgroundPanel.setBackground(new Color(255, 255, 255));
        inputBackgroundPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        inputBackgroundPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Find Your Password");
        titleLabel.setBounds(70, 30, 300, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        inputBackgroundPanel.add(titleLabel);

        RoundedJTextField idInputField = new RoundedJTextField(20);
        idInputField.setBounds(50, 80, 300, 60);
        idInputField.setEmptyMessage("Email");
        inputBackgroundPanel.add(idInputField);

        JButton submitButton = submitButton(idInputField);
        inputBackgroundPanel.add(submitButton);

        JButton backButton = new AllButtonDesignOffset("Back");
        backButton.setBounds(80, 260, 240, 25);
        backButton.setForeground(new Color(255,255,255));
        backButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView().setVisible(true);
                dispose();
            }
        });
        inputBackgroundPanel.add(backButton);

        panel.add(inputBackgroundPanel);
        this.add(panel);

        java.net.URL imgUrl = getClass().getResource("PAMLogo.png");
        if (imgUrl != null) {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgUrl));
        }

        this.setVisible(true);
    }

    public JButton submitButton(JTextField idInputField) {
        JButton submitButton = new AllButtonDesignOffset("Submit");
        submitButton.setBounds(80, 160, 240, 80);
        submitButton.setForeground(new Color(0xFFFFFF));
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 40));
        submitButton.addActionListener(new ActionListener() {
            UserDAO userDAO = new UserDAO();

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = userDAO.getPassword(idInputField.getText());
                String id = idInputField.getText().trim();

                if (userDAO.existByEmail(id)) {
                    JOptionPane.showMessageDialog(null, "Your Password is [ " + password + " ]");
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Email ID!!");
                }
            }
        });

        return submitButton;
    }
}