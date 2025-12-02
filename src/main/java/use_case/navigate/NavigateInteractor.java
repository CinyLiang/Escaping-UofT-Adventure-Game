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
        navigatePresenter.prepareSuccessView(new NavigateOutputData2(getTargetView(direction)));
    }

    @NotNull
    private static String getTargetView(String direction) {
        return switch (direction.toLowerCase()) {
            case "north" -> "Card game"; // UC exterior > interior > card game
            case "south" -> "Win game";
            case "east" -> "Trivia game"; // gerstein exterior > interior > trivia game
            case "west" -> "Knox exterior"; // knox exterior > interior > card game
            case "Card game" -> "Card game";
            default -> "";
        };
    }
}
