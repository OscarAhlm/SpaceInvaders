package spaceInvaders;

import java.sql.*;
import java.util.Arrays;

public class Driver {
    private Connection conn;
    private String[] scores = new String[10];
    private String[] names = new String[10];
    private String user = "root";
    private String password = "!ze24BR@06";
    private int index;
    public Driver() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spaceinvaders", user, password);

        } catch(SQLException e) {
            e.printStackTrace();
        }
        setScores();
    }

    public void setScores()  {
        index = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, score FROM highscores ORDER BY score DESC");
            while(rs.next() && index < names.length) {
                names[index] = rs.getString(1);
                scores[index] = rs.getString(2);
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNewScore(String name, String score) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO highscores (name, score) VALUES (?, ?)");
            pstmt.setString(1, name);
            pstmt.setString(2, score);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        setScores();
    }

    public String[] getNames() {
        return names;
    }

    public String[] getScores() {
        return scores;
    }
}
