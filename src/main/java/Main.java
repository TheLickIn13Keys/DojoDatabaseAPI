import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        McDojoDatabase mc = new McDojoDatabase("Server_Stats", "622a75366f663f1d1e78980d356a1f10");
        mc.connect();
        ResultSet r = mc.get("SELECT * FROM `C1`");
        System.out.println(r.toString());
    }
}
