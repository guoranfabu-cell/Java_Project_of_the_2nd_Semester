/** start button Gradients Offset */

package com.pam;

import javax.swing.*;
import java.awt.*;

public class AllButtonDesignOffset extends JButton {
    private final int radius = 20;

    public AllButtonDesignOffset(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (getModel().isPressed()) {
            g2.setColor(new Color(177, 196, 213));
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(106, 164, 221));
        } else {
            g2.setColor(new Color(0, 86, 177));
        }

        g2.fillRoundRect(0,0,getWidth(),getHeight(),radius,radius);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.DARK_GRAY);
        g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,radius,radius);
        g2.dispose();
    }
}