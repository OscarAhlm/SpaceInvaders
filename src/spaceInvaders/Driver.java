package spaceInvaders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Driver {
    private String[] scores = new String[999];
    private String user = "root";
    private String password = "!ze24BR@06";
    private int index = 0;
    public Driver() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spaceinvaders", user, password);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from highscores");
            while(rs.next()) {
                scores[index] = rs.getString(2) + "   " + rs.getString(3);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getScores() {
        return scores;
    }
}
