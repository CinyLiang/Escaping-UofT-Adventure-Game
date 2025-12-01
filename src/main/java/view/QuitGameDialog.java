package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.quit_game.QuitGameController;
import interface_adapter.quit_game.QuitGameState;
import interface_adapter.quit_game.QuitGameViewModel;
import interface_adapter.save_progress.SaveProgressController;

public class QuitGameDialog extends JDialog implements PropertyChangeListener {

    private QuitGameController quitGameController;
    private QuitGameViewModel quitGameViewModel;
    private SaveProgressController saveProgressController;
    private NavigateViewModel navigateViewModel;

    public QuitGameDialog(QuitGameController quitGameController,
                          SaveProgressController saveProgressController, NavigateViewModel navigateViewModel,
                          QuitGameViewModel quitGameViewModel) {

        this.quitGameController = quitGameController;
        this.quitGameViewModel = quitGameViewModel;
        this.saveProgressController = saveProgressController;
        this.navigateViewModel = navigateViewModel;
        this.quitGameViewModel.addPropertyChangeListener(this);

        setTitle("Quit Game?");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Quit Game?");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttons = new JPanel();
        JButton quit = new JButton("Quit");
        JButton cancel = new JButton("Cancel");

        quit.addActionListener(e -> {
            quitGameController.execute();
        });

        cancel.addActionListener(e -> dispose());

        buttons.add(quit);
        buttons.add(cancel);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(QuitGameViewModel.SHOW_QUIT_DIALOG_PROPERTY)) {
            boolean visible = (boolean) quitGameViewModel.getState().isQuitDialogVisible();
            if (visible) {
                this.setVisible(true);
                this.toFront();
            }
        }

        else if (evt.getPropertyName().equals(QuitGameViewModel.SHOW_SAVE_DIALOG_PROPERTY)) {
            dispose();
            SaveGameDialog saveDialog = new SaveGameDialog(saveProgressController, navigateViewModel);
            saveDialog.showDialog();
        }

        else if (evt.getPropertyName().equals(QuitGameViewModel.EXIT_GAME_PROPERTY)) {
            QuitGameState state = (QuitGameState) evt.getNewValue();
            if (state.getShouldExitGame()) {
                this.dispose();
                System.exit(0);
            }
        }
    }
}

