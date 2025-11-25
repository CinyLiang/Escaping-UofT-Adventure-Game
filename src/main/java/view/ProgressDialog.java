package view;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {
    public ProgressDialog(String message) {

        String[] sep = message.split("\n");

        setTitle("Current Progress");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(new JLabel("Your Progress"));
        textPanel.add(Box.createVerticalStrut(20));
        for (String s : sep) {
            textPanel.add(new JLabel(s));
        }

        JPanel buttons = new JPanel();
        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());
        buttons.add(close);

        add(textPanel, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void showDialog() {
        setVisible(true);
    }
}
