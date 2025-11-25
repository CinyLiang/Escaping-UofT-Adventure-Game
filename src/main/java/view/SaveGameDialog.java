package view;

import interface_adapter.navigate.NavigateState;
import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.save_progress.SaveProgressController;

import javax.swing.*;
import java.awt.*;

public class SaveGameDialog extends JDialog {

    private SaveProgressController saveProgressController;

    public SaveGameDialog(SaveProgressController saveProgressController, NavigateViewModel navigateViewModel) {
        this.saveProgressController = saveProgressController;

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
            closeGame();
        });

        quitWithoutSaving.addActionListener(e -> closeGame());

        buttons.add(saveAndQuit);
        buttons.add(quitWithoutSaving);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void closeGame() {
        dispose();
        System.exit(0);
    }

    public void showDialog() {
        setVisible(true);
    }
}
