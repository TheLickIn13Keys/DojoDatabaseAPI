import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class McDojoDatabase {

    private final String token1;
    private Connection connection;
    private String host, database, username, password;
    private int port;
    private static HttpURLConnection APIconnection;

    public McDojoDatabase(String databaseName, String token){
        database = databaseName;
        token1 = token;
    }

    public void connect() throws SQLException, ClassNotFoundException, IOException {
        System.out.println("Using Connect");
        SecureDetails sd = new SecureDetails();
            if (checkToken() == true) {
                System.out.println("Connected...");
                host = sd.getIP();
                username = "discord";
                password = sd.getPass();
                database = database;
                port = 3306;
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
            }


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

    private boolean checkToken() throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        URL url = new URL("https://the-token-master.glitch.me/getDreams");
        APIconnection = (HttpURLConnection) url.openConnection();

        APIconnection.setRequestMethod("GET");
        APIconnection.setConnectTimeout(5000);
        APIconnection.setReadTimeout(5000);

        int status = APIconnection.getResponseCode();
        System.out.println(status);

        if(status > 299){
            reader = new BufferedReader(new InputStreamReader(APIconnection.getErrorStream()));
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
        }
        else{
            reader = new BufferedReader(new InputStreamReader(APIconnection.getInputStream()));
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
        }

        JsonParser jp = new JsonParser();
        JsonArray dreams = (JsonArray) jp.parse(responseContent.toString());
        System.out.println(token1);
        for(int i = 0; i<dreams.size(); i++){
            JsonObject dream = (JsonObject) dreams.get(i);
            String foo = dream.get("dream").toString();
            String token = foo.substring(1,foo.length()-1);
            System.out.println(token);
            if(token.equals(token1)){
                return true;
            }

        }
        APIconnection.disconnect();
        return false;

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
