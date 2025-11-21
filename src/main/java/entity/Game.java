//public class Game {
//    private Player player;
//    private Map<String, Location> locationMap;
//    private boolean isRunning;
//    private int totalPuzzles;
//    private String saveFilePath;
//    private List<Puzzle> puzzles;
//
//    public Game() {
//
//    }
//
//    public void clearHistory() {}
//    public String viewProgress() {}
//    public void startGame() {}
//    public void initializeWorld() {}
//    public boolean saveGame() {}
//    public boolean loadGame(String filePath) {}
//    public void quitGame() {}
//    public boolean isGameComplete() {}
//    public void processCommand(String command) {}
//
//    private void createLocationMap() {
//        locationMap = new HashMap<>();
//        // Initialize locations and add to locationMap
//        // location 1: kings college circle (middle)
//        Location kcc = new Location("Kings College Circle", "The central lawn of the university.", "start");
//        locationMap.put("Kings College Circle", kcc);
//
//        // location 2: knox college (west)
//        Location knox = new Location("Knox College", "Theological college with a haunting interior.", "west");
//        knox.setPuzzle(unknown);
//
//        // location 3: gerstein library (east)
//        Location gerstein = new Location("Gerstein Library", "Quiet library.", "east");
//        gerstein.setPuzzle(cardGame);
//
//        // location 4: university college (north)
//        Location uc = new Location("University College", "Founding college with an ornate door.", "north");
//        uc.setPuzzle(trivia);
//
//        // location 5: convocation hall (south)
//        Location conHall = new Location("Convocation Hall", "Rotunda with three locked doors.", "south");
//        conHall.setPuzzle(threeKeys);
//        conHall.setIsLocked(false);
//    }
//}
