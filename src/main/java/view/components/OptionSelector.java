package view.components;

import javax.swing.*;
import java.awt.*;

public class OptionSelector extends JPanel {

    private final JLabel label;
    private final JLabel leftArrow;
    private final JLabel rightArrow;
    private final JLabel valueLabel;

    private final String[] values;
    private int index;

    public OptionSelector(String labelText, String[] values, int initialIndex) {
        this.values = values;
        this.index = initialIndex;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 45));
        setBackground(new Color(30, 30, 30));
        setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(80, 80, 80)));

        label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Consolas", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        leftArrow = new JLabel("<  ");
        rightArrow = new JLabel("  >");

        styleArrow(leftArrow);
        styleArrow(rightArrow);

        valueLabel = new JLabel(values[index], SwingConstants.CENTER);
        valueLabel.setForeground(Color.CYAN);
        valueLabel.setFont(new Font("Consolas", Font.BOLD, 20));

        JPanel rightBox = new JPanel(new BorderLayout());
        rightBox.setOpaque(false);

        rightBox.add(leftArrow, BorderLayout.WEST);
        rightBox.add(valueLabel, BorderLayout.CENTER);
        rightBox.add(rightArrow, BorderLayout.EAST);

        add(label, BorderLayout.WEST);
        add(rightBox, BorderLayout.EAST);

        // Click
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

    private void styleArrow(JLabel arrow) {
        arrow.setForeground(Color.GRAY);
        arrow.setFont(new Font("Consolas", Font.BOLD, 20));
        arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public String getValue() {
        return values[index];
    }
}