package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.clear_history.ClearHistoryViewModel;
import interface_adapter.quit_game.QuitGameState;
import interface_adapter.quit_game.QuitGameViewModel;

public class ConfirmRestartGameDialog extends JDialog implements PropertyChangeListener {

    private final ClearHistoryController clearHistoryController;
    private ClearHistoryViewModel clearHistoryViewModel;

    public ConfirmRestartGameDialog(ClearHistoryController clearHistoryController, ClearHistoryViewModel clearHistoryViewModel) {

        this.clearHistoryController = clearHistoryController;
        this.clearHistoryViewModel = clearHistoryViewModel;
        this.clearHistoryViewModel.addPropertyChangeListener(this);

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ClearHistoryViewModel.CLEAR_HISTORY_STATE)) {
            boolean visible = clearHistoryViewModel.getState().isClearDialogVisible();
            if (visible) {
                this.setVisible(true);
                this.toFront();
            }
        }
    }
}
