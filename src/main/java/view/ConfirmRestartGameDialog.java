package view;

import javax.swing.*;
import java.awt.*;

import interface_adapter.clear_history.ClearHistoryController;

public class ConfirmRestartGameDialog extends JDialog {

    private ClearHistoryController clearHistoryController;

    public ConfirmRestartGameDialog(ClearHistoryController clearHistoryController) {

        this.clearHistoryController = clearHistoryController;

        setTitle("Reset Progress");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Reset progress?");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttons = new JPanel();
        JButton reset = new JButton("Reset");
        JButton cancel = new JButton("Cancel");

        reset.addActionListener(evt -> {
            dispose();
            this.clearHistoryController.execute();
        });

        cancel.addActionListener(evt -> dispose());

        buttons.add(reset);
        buttons.add(cancel);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void showDialog() {
        setVisible(true);
    }
}
