import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class McDojoDatabase {

    private Connection connection;
    private String host, database, username, password;
    private int port;
    private static HttpURLConnection APIconnection;

    public McDojoDatabase(String databaseName){
        database = databaseName;
    }

    public void connect(String token) throws SQLException, ClassNotFoundException, IOException {
        REST r = new REST();
        SecureDetails sd = new SecureDetails();
        ArrayList<String> tokens = r.getTokens();
        for (String s : tokens) {
            if (s.equals(token)) {
                host = sd.getIP();
                username = "discord";
                password = "oFijpowijgwogjioapgejsloi2";
                if (connection != null && !connection.isClosed()) {
                    return;
                }

                synchronized (this) {
                    if (connection != null && !connection.isClosed()) {
                        return;
                    }
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, password);
                }
                return;
            }
        }

        System.out.println("INVALID TOKEN");

    }


    public ResultSet get(String sqlStatement) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(sqlStatement);
        ResultSet result = statement.executeQuery();
        return result;
    }
    public void set(String sqlStatement) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.execute();

    }

    public ResultSet get(PreparedStatement statement) throws SQLException {

        ResultSet result = statement.executeQuery();
        return result;

    }
    public void set(PreparedStatement statement) throws SQLException {

        statement.execute();

    }
    /*public ResultSet getC1(String UUID) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `C1` WHERE UUID = ?");
        statement.setString(0, UUID);
        ResultSet result = statement.executeQuery();
        statement.close();
        return result;
    }
    public ResultSet getC2(String UUID) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `C2` WHERE UUID = ?");
        statement.setString(0, UUID);
        ResultSet result = statement.executeQuery();
        statement.close();
        return result;
    }
    public ResultSet getC3(String UUID) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `C3` WHERE UUID = ?");
        statement.setString(0, UUID);
        ResultSet result = statement.executeQuery();
        statement.close();
        return result;
    }
    public ResultSet getGeneral(String UUID) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `general` WHERE UUID = ?");
        statement.setString(0, UUID);
        ResultSet result = statement.executeQuery();
        statement.close();
        return result;
    }
    public ResultSet getServerInfo(String UUID) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM `serverinfo` WHERE UUID = ?");
        statement.setString(0, UUID);
        ResultSet result = statement.executeQuery();
        return result;
    }
    public void setC1(ClutcherPlayer p, int map) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE `C1` SET `map` = ?', `bestTime` = ?, `bestStreak` = ?, `blocksPlaced` = ?, `shardsEarned` = ?, `failedAttempts` = ?, `successfulAttempts` = ?, `currentStreak` = ? WHERE `C1`.`UUID` = " + p.getUUID());
        statement.setInt(0, map);
        statement.setString(1, p.getBestTime());
        statement.setInt(2, p.getBestStreak());
        statement.setString(3, p.getBlocksPlaced());
        statement.setString(4, p.getShardsEarned());
        statement.setString(5, p.getShardsEarned());
        statement.setInt(6, p.getSuccessfulAttempts());
        statement.setInt(7, p.getCurrentStreak());
        statement.execute();
        statement.close();

    }
    public void setC2(ClutcherPlayer p, int map) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE `C2` SET `map` = ?', `bestTime` = ?, `bestStreak` = ?, `blocksPlaced` = ?, `shardsEarned` = ?, `failedAttempts` = ?, `successfulAttempts` = ?, `currentStreak` = ? WHERE `C1`.`UUID` = " + p.getUUID());
        statement.setInt(0, map);
        statement.setString(1, p.getBestTime());
        statement.setInt(2, p.getBestStreak());
        statement.setString(3, p.getBlocksPlaced());
        statement.setString(4, p.getShardsEarned());
        statement.setString(5, p.getShardsEarned());
        statement.setInt(6, p.getSuccessfulAttempts());
        statement.setInt(7, p.getCurrentStreak());
        statement.execute();
        statement.close();

    }
    public void setC3(ClutcherPlayer p, int map) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE `C3` SET `map` = ?', `bestTime` = ?, `bestStreak` = ?, `blocksPlaced` = ?, `shardsEarned` = ?, `failedAttempts` = ?, `successfulAttempts` = ?, `currentStreak` = ? WHERE `C1`.`UUID` = " + p.getUUID());
        statement.setInt(0, map);
        statement.setString(1, p.getBestTime());
        statement.setInt(2, p.getBestStreak());
        statement.setString(3, p.getBlocksPlaced());
        statement.setString(4, p.getShardsEarned());
        statement.setString(5, p.getShardsEarned());
        statement.setInt(6, p.getSuccessfulAttempts());
        statement.setInt(7, p.getCurrentStreak());
        statement.execute();
        statement.close();

    }

    public void newC1Player

     */


}
