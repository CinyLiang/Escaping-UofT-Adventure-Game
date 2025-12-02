package interface_adapter.quit_game;

import interface_adapter.ViewModel;

public class QuitGameViewModel extends ViewModel<QuitGameState> {
    public static final String SHOW_QUIT_DIALOG_PROPERTY = "show_quit_dialog";
    public static final String SHOW_SAVE_DIALOG_PROPERTY = "show_save_prompt";
    public static final String EXIT_GAME_PROPERTY = "exit_game";

    public QuitGameViewModel() {
        super("quit game");
        setState(new QuitGameState());
    }

    public void fireShowQuitDialog() {
        super.firePropertyChange(SHOW_QUIT_DIALOG_PROPERTY);
    }

    public void fireShowSavePrompt() {
        // Uses the parent class method to fire a named property change
        super.firePropertyChange(SHOW_SAVE_DIALOG_PROPERTY);
    }

    public void fireExitGame() {
        super.firePropertyChange(EXIT_GAME_PROPERTY);
    }
}