public class ClutcherPlayer{
    String UUID1;
    int map1;
    String bestTime1;
    int bestStreak1;
    String blocksPlaced1;
    String shardsEarned1;
    int failedAttemps1;
    int successfulAttempts1;
    int currentStreak1;
    public ClutcherPlayer(String UUID, String bestTime, int bestStreak, String blocksPlaced, String shardsEarned ,int failedAttempts, int successfulAttempts, int currentStreak){
        UUID1 = UUID;
        bestTime1 = bestTime;
        bestStreak1 = bestStreak;
        blocksPlaced1 = blocksPlaced;
        shardsEarned1 = shardsEarned;
        failedAttemps1 = failedAttempts;
        successfulAttempts1 = successfulAttempts;
        currentStreak1 = currentStreak;
    }

    public String getUUID(){
        return UUID1;
    }
    public String getBestTime(){
        return bestTime1;
    }
    public String getBlocksPlaced(){
        return blocksPlaced1;
    }
    public String getShardsEarned(){
        return shardsEarned1;
    }
    public int getBestStreak(){
        return bestStreak1;
    }
    public int getFailedAttempts(){
        return failedAttemps1;
    }
    public int getSuccessfulAttempts(){
        return successfulAttempts1;
    }
    public int getCurrentStreak(){
        return currentStreak1;
    }
}
