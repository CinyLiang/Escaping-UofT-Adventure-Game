package use_case.navigate;

import org.jetbrains.annotations.NotNull;

public class NavigateInteractor implements NavigateInputBoundary {

    private final NavigateOutputBoundary navigatePresenter;

    public NavigateInteractor(NavigateOutputBoundary navigatePresenter) {
        this.navigatePresenter = navigatePresenter;
    }

    /*
     * Takes a direction and returns updated story
     */
    @Override
    public void execute(NavigateInputData inputData) {
        String direction = inputData.getDirection();
        navigatePresenter.prepareSuccessView(new NavigateOutputData(getTargetView(direction)));
    }
    @NotNull
    private static String getTargetView(String direction) {
        return switch (direction.toLowerCase()) {
            case "north" -> "UC exterior"; // UC exterior > interior > card game
            case "south" -> "con hall exterior";
            case "east" -> "Gerstein exterior"; // gerstein exterior > interior > trivia game
            case "west" -> "Knox exterior"; // knox exterior > interior > card game

            case "knox interior" -> "Knox interior";
            case "gerstein interior" -> "Gerstein interior";
            case "uc interior" -> "UC interior";
            case "con hall interior" -> "Con Hall interior";

            case "card game" -> "Card game";
            case "trivia game" -> "Trivia game";
            case "win game" -> "win game";
            default -> "";
        };
    }
}
