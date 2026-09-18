package com.pam;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RoundedJTextField extends JTextField {
    private final int radius = 16;
    private String emptyMessage = "";

    public RoundedJTextField(int columns) {
        super(columns);

        setOpaque(false);
        setBorder(new EmptyBorder(0, 16, 0, 16));
        setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                repaint();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                repaint();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                repaint();
            }
        });
    }

    public void setEmptyMessage(String message) {
        this.emptyMessage = message;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(new Color(0xEDEDED));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        if (hasFocus()) {
            g2.setColor(new Color(50, 80, 125));
        } else {
            g2.setColor(new Color(0xEDEDED));
        }

        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {

    }

    @Override
    public void paintChildren(Graphics g) {
        super.paintChildren(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (getText().isEmpty() && !hasFocus() && !emptyMessage.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.DARK_GRAY);
            g2.setFont(getFont());

            Insets insets = getInsets();
            FontMetrics fm = g2.getFontMetrics();

            int x = insets.left + 8;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

            g2.drawString(emptyMessage, x, y);
            g2.dispose();
        }
    }

    public Insets getInsets() {
        return new Insets(0, 16, 0, 16);
    }
}
