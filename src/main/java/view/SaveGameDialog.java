package view;

import interface_adapter.navigate.NavigateState;
import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.quit_game.QuitGameController;
import interface_adapter.quit_game.QuitGameState;
import interface_adapter.quit_game.QuitGameViewModel;
import interface_adapter.save_progress.SaveProgressController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SaveGameDialog extends JDialog implements PropertyChangeListener {

    private SaveProgressController saveProgressController;
    private QuitGameViewModel quitGameViewModel;
    private QuitGameController quitGameController;

    public SaveGameDialog(SaveProgressController saveProgressController,
                          NavigateViewModel navigateViewModel,
                          QuitGameViewModel quitGameViewModel,
                          QuitGameController quitGameController) {
        this.saveProgressController = saveProgressController;
        this.quitGameController = quitGameController;
        this.quitGameViewModel = quitGameViewModel;
        this.quitGameViewModel.addPropertyChangeListener(this);

        NavigateState navigateState = navigateViewModel.getState();

        setTitle("Save Game");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Save game before quitting?");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttons = new JPanel();
        JButton saveAndQuit = new JButton("Save and quit");
        JButton quitWithoutSaving = new JButton("Quit without saving");

        saveAndQuit.addActionListener(e -> {
            this.saveProgressController.execute(navigateState.getLocation(), navigateState.getNumberOfKeys(), navigateState.getPuzzlesSolved());
            this.quitGameController.exitGame();
        });

        quitWithoutSaving.addActionListener(e -> this.quitGameController.exitGame());

        buttons.add(saveAndQuit);
        buttons.add(quitWithoutSaving);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(QuitGameViewModel.EXIT_GAME_PROPERTY)) {
            QuitGameState state = (QuitGameState) evt.getNewValue();
            if (state.getShouldExitGame()) {
                this.dispose();
                System.exit(0);
            }
        }
    }
}
