package view.components;

import javax.swing.*;
import java.awt.*;

public class OptionSelector extends JPanel {
    private final JLabel leftArrow;
    private final JLabel rightArrow;
    private final JLabel valueLabel;

    private final String[] values;
    private int index;

    public OptionSelector(String[] values, int initialIndex) {
        this.values = values;
        this.index = initialIndex;

        setLayout(new BorderLayout());
        setOpaque(false);

        leftArrow = new JLabel("<");
        rightArrow = new JLabel(">");
        valueLabel = new JLabel(values[index], SwingConstants.CENTER);

        style(leftArrow);
        style(rightArrow);
        style(valueLabel);

        add(leftArrow, BorderLayout.WEST);
        add(valueLabel, BorderLayout.CENTER);
        add(rightArrow, BorderLayout.EAST);

        leftArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                index = (index - 1 + values.length) % values.length;
                valueLabel.setText(values[index]);
            }
        });

        rightArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                index = (index + 1) % values.length;
                valueLabel.setText(values[index]);
            }
        });
    }

    private void style(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
    }

    public String getValue() {
        return values[index];
    }
}
