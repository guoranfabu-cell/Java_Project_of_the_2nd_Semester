package com.pam.view;

import com.pam.*;
import com.pam.dao.UserDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        loginBackgroundPanel.setPreferredSize(new Dimension(1000, 600));
        loginBackgroundPanel.setBackground(new Color(255, 255, 255));
        loginBackgroundPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        loginBackgroundPanel.setLayout(null);

        JPanel introPanel = new JPanel(null);
        introPanel.setBounds(1, 1, 530, 598);
        introPanel.setBackground(new Color(241, 246, 253));
        introPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        loginBackgroundPanel.add(introPanel);

        JLabel pamLogoLabel = new JLabel("Join To PAM");
        pamLogoLabel.setFont(new Font("Arial", Font.BOLD, 42));
        pamLogoLabel.setForeground(new Color(20, 80, 160));
        pamLogoLabel.setBounds(70, 105, 350, 55);
        introPanel.add(pamLogoLabel);

        JLabel welcomeLabel = new JLabel("Welcome to PAM");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(35, 45, 60));
        welcomeLabel.setBounds(70, 175, 400, 40);
        introPanel.add(welcomeLabel);

        JLabel descriptionLabel = new JLabel(
                "<html>Your Personal Assistant Manager.<br>"
                        + "Plan your day, manage tasks, and make every day easier.</html>"
        );
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setForeground(new Color(80, 90, 105));
        descriptionLabel.setBounds(70, 230, 390, 60);
        introPanel.add(descriptionLabel);

        JLabel featureLabel = new JLabel(
                "<html>"
                        + "✓ Manage your schedule<br><br>"
                        + "✓ Never miss important tasks<br><br>"
                        + "✓ Enjoy a smarter daily routine"
                        + "</html>"
        );
        featureLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        featureLabel.setForeground(new Color(45, 75, 120));
        featureLabel.setBounds(75, 335, 340, 130);
        introPanel.add(featureLabel);

        JLabel titleLabel = new JLabel("Create Your Account");
        titleLabel.setBounds(640, 40, 300,60);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
        loginBackgroundPanel.add(titleLabel);

        RoundedJTextField emailField = new RoundedJTextField(20);
        emailField.setEmptyMessage("Email");
        emailField.setBounds(600, 110, 340, 40);
        loginBackgroundPanel.add(emailField);

        RoundedPasswordField pwField = new RoundedPasswordField(20);
        pwField.setEmptyMessage("Password");
        pwField.setBounds(600, 170, 340, 40);
        loginBackgroundPanel.add(pwField);

        RoundedPasswordField VerifyPasswordField = new RoundedPasswordField(20);
        VerifyPasswordField.setEmptyMessage("Verify Password");
        VerifyPasswordField.setBounds(600, 230, 340, 40);
        loginBackgroundPanel.add(VerifyPasswordField);

        RoundedJTextField nicknameField = new RoundedJTextField(20);
        nicknameField.setEmptyMessage("Nickname");
        nicknameField.setBounds(600, 290, 340, 40);
        loginBackgroundPanel.add(nicknameField);

        JLabel genderChooseText = new JLabel("Please Choose your Gender");
        genderChooseText.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        genderChooseText.setBounds(600, 330, 300, 40);
        loginBackgroundPanel.add(genderChooseText);

        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setSelectedIndex(2);
        genderBox.setBounds(600, 370, 340, 30);
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
        JButton submitButton = new AllButtonDesignOffset("Create");
        submitButton.setFont(new Font("굴림", Font.BOLD, 24));
        submitButton.setBounds(660, 430, 240, 80);
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
                            new SignInView(email, nickname).setVisible(true);
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
        JButton backButton = new AllButtonDesignOffset("Return to login");
        backButton.setFont(new Font("굴림", Font.PLAIN, 12));
        backButton.setBounds(680, 530, 200, 25);
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