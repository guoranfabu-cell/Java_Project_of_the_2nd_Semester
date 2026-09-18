package com.pam.view;

import com.pam.*;
import com.pam.dao.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    public LoginView() {
        this.setTitle("Welcome to PAM!");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new GridBagLayout());

        JPanel loginBackgroundPanel = new JPanel();
        loginBackgroundPanel.setPreferredSize(new Dimension(1000, 600));
        loginBackgroundPanel.setBackground(new Color(255, 255, 255));
        loginBackgroundPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        loginBackgroundPanel.setLayout(null); // 내부 컴포넌트를 자유롭게 배치하기 위해 null 설정

        JPanel introPanel = new JPanel(null);
        introPanel.setBounds(1, 1, 530, 598);
        introPanel.setBackground(new Color(241, 246, 253));
        introPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        loginBackgroundPanel.add(introPanel);

        JLabel pamLogoLabel = new JLabel("PAM");
        pamLogoLabel.setFont(new Font("Arial", Font.BOLD, 42));
        pamLogoLabel.setForeground(new Color(20, 80, 160));
        pamLogoLabel.setBounds(70, 105, 250, 55);
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


        // 제목 라벨
        JLabel titleLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        titleLabel.setBounds(600, 60, 300, 100);
        loginBackgroundPanel.add(titleLabel);

        // 아이디 라벨 & 입력 칸
        RoundedJTextField idField = new RoundedJTextField(20);
        idField.setEmptyMessage("Email");
        idField.setBounds(600, 200, 300, 60);
        loginBackgroundPanel.add(idField);

        RoundedPasswordField pwField = new RoundedPasswordField(20);
        pwField.setEmptyMessage("Password");
        pwField.setBounds(600, 280, 300, 60);
        loginBackgroundPanel.add(pwField);

        // 로그인 버튼
        JButton loginButton = LoginButton(idField, pwField, pwField);
        loginBackgroundPanel.add(loginButton);

        // 회원가입 버튼
        JButton signupButton = new JButton("SignUp");
        signupButton.setFont(new Font("굴림", Font.BOLD, 18));
        signupButton.setBounds(600, 500, 300, 40);
        signupButton.setBorder(null);
        signupButton.setBackground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setForeground(Color.BLACK);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpView signupView = new SignUpView();
                signupView.setVisible(true);

                dispose();
            }
        });

        loginBackgroundPanel.add(signupButton);

        // 비밀번호 까먹 버튼
        JButton forgotThePassword = new JButton("forgot the password?");
        forgotThePassword.setFont(new Font("굴림", Font.BOLD, 13));
        forgotThePassword.setBounds(600, 550, 300, 40);
        forgotThePassword.setBorder(null);
        forgotThePassword.setBackground(Color.WHITE);
        forgotThePassword.setForeground(Color.BLACK);
        forgotThePassword.setFocusPainted(false);
        forgotThePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ForgotPasswordView();
                dispose();
            }
        });
        loginBackgroundPanel.add(forgotThePassword);

        // ------------------------------------------

        // 조립
        panel.add(loginBackgroundPanel);
        this.add(panel);

        // 로고 이미지 설정
        java.net.URL imgUrl = getClass().getResource("PAMLogo.png");
        if (imgUrl != null) {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgUrl));
        }

        this.setVisible(true);
    }

    public JButton LoginButton(JTextField idField, JPasswordField pwField, JTextField pwField2) {
        JButton loginButton = new AllButtonDesignOffset("Login");
        loginButton.setFont(new Font("굴림", Font.BOLD, 20));
        loginButton.setBounds(600, 400, 300, 80);
        loginButton.setBackground(new Color(0x002C7A)); // 버튼 배경색 (예시: 노란색 계열)
        loginButton.setFocusPainted(false); // 버튼 클릭 시 테두리 점선 제거
        loginButton.setForeground(new Color(255,255,255));
        loginButton.addActionListener(new ActionListener() {

            final UserDAO userDAO = new UserDAO();

            @Override
            public void actionPerformed(ActionEvent e) {
                String email = idField.getText().trim();
                String Password = pwField2.getText().trim();

                char[] id = idField.getText().toCharArray();
                char[] password = pwField.getPassword();

                if (password.length == 0 || id.length == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!");
                    return;
                } else {
                    if (userDAO.isValidLogin(email, Password)) {
                        String nickname = userDAO.getNickname(email);

                        JOptionPane.showMessageDialog(null, "Login successful!");
                        new MainDashboard(email, nickname).setVisible(true);
                        dispose();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid email or password!");
                        if (!userDAO.existByEmail(email)) {
                            idField.requestFocus();
                        } else {
                            pwField.requestFocus();
                        }
                    }
                }
            }
        });
        return loginButton;
    }
}
