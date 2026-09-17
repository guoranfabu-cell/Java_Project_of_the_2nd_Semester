package com.pam.view;

import com.pam.*;
import com.pam.dao.UserDAO;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;

public class SignUpView extends JFrame {
    public String[] genders = {"MALE", "FEMALE", "NONE"};

    public SignUpView(){
        this.setTitle("Create Your Account");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(new Color(255, 255, 255));
        signUpPanel.setLayout(new GridBagLayout());

        JPanel loginBackgroundPanel = new JPanel();
        loginBackgroundPanel.setPreferredSize(new Dimension(400, 450));
        loginBackgroundPanel.setBackground(new Color(255, 255, 255));
        loginBackgroundPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        loginBackgroundPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Create Your Account");
        titleLabel.setBounds(140, 20, 200,20);
        loginBackgroundPanel.add(titleLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(60, 80, 120, 20);
        emailLabel.setFont(new Font("굴림", Font.BOLD, 15));
        loginBackgroundPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(200, 80, 150, 20);
        loginBackgroundPanel.add(emailField);

        JLabel pwLabel = new JLabel("Password");
        pwLabel.setBounds(60, 120, 60, 30);
        pwLabel.setFont(new Font("굴림", Font.BOLD, 12));
        loginBackgroundPanel.add(pwLabel);

        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(200, 120, 150, 20);
        pwField.setDocument(new LimitDocument(20));
        loginBackgroundPanel.add(pwField);

        JLabel VerifyPasswordLabel = new JLabel("Verify your Password");
        VerifyPasswordLabel.setBounds(60, 160, 150, 30);
        VerifyPasswordLabel.setFont(new Font("굴림", Font.BOLD, 12));
        loginBackgroundPanel.add(VerifyPasswordLabel);

        JPasswordField VerifyPasswordField = new JPasswordField();
        VerifyPasswordField.setBounds(200, 160, 150, 20);
        VerifyPasswordField.setDocument(new LimitDocument(20));
        loginBackgroundPanel.add(VerifyPasswordField);

        JLabel nicknameLabel = new JLabel("What is your Nickname?");
        nicknameLabel.setBounds(120, 200, 200, 30);
        loginBackgroundPanel.add(nicknameLabel);

        JTextField nicknameField = new JTextField();
        nicknameField.setBounds(60, 230, 290, 20);
        nicknameField.setDocument(new LimitDocument(12));
        loginBackgroundPanel.add(nicknameField);

        JLabel genderChooseText = new JLabel("Please Choose your Gender");
        genderChooseText.setBounds(120, 260, 200, 30);
        loginBackgroundPanel.add(genderChooseText);

        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setSelectedIndex(2);
        genderBox.setBounds(60, 300, 290, 20);
        loginBackgroundPanel.add(genderBox);

        JButton submitButton = createButton(pwField, VerifyPasswordField, emailField, pwField, genderBox, nicknameField);
        loginBackgroundPanel.add(submitButton);

        JButton backButton = backJButton();
        loginBackgroundPanel.add(backButton);
        
        signUpPanel.add(loginBackgroundPanel);
        this.add(signUpPanel);

        java.net.URL imgUrl = getClass().getResource("PAMLogo.png");
        if (imgUrl != null) {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgUrl));
        }

        this.setVisible(true);
    }

    private JButton createButton(JPasswordField pwField, JPasswordField VerifyPasswordField, JTextField emailField, JTextField password, JComboBox<String> genderBox, JTextField nicknameLabel) {
        JButton submitButton = new GradientButton("Create");
        submitButton.setFont(new Font("굴림", Font.BOLD, 16));
        submitButton.setBounds(140, 350, 120, 40);
        submitButton.setForeground(new Color(255,255,255));
        submitButton.setBackground(new Color(0x002C7A));
        submitButton.setFocusPainted(false);

        UserDAO userDAO = new UserDAO();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String Password = password.getText().trim();
                String gender = Objects.requireNonNull(genderBox.getSelectedItem()).toString();
                String nickname = nicknameLabel.getText().trim();

                char[] emailFieldLength = emailField.getText().toCharArray();

                if (emailFieldLength.length == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!");
                    return;
                }

                char[] password = pwField.getPassword();
                char[] verifyPassword = VerifyPasswordField.getPassword();

                if (password.length == 0 || verifyPassword.length == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!");
                    return;
                }

                if (Arrays.equals(password, verifyPassword)) {
                    if (userDAO.existByEmail(email)) {
                        JOptionPane.showMessageDialog(null, "이미 있는 계정입니다!");
                        emailField.requestFocus();
                        return;
                    } else {
                        boolean isSaved = userDAO.insertUser(email, Password, nickname, gender);

                        if (isSaved) {
                            JOptionPane.showMessageDialog(null, "You are successfully created your account.");
                            new SignInView(nickname).setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Passwords do not match!");

                            pwField.setText("");
                            VerifyPasswordField.setText("");
                            VerifyPasswordField.requestFocus();
                        }
                    }
                }
            }
        });

        return submitButton;
    }

    private JButton backJButton() {
        JButton backButton = new GradientButton("Return to login");
        backButton.setFont(new Font("굴림", Font.PLAIN, 12));
        backButton.setBounds(100, 400, 200, 25);
        backButton.setBackground(new Color(0x002C7A));
        backButton.setForeground(new Color(255,255,255));
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);

                dispose();
            }
        });
        return backButton;
    }
}