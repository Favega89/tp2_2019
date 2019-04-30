package commons.persistence;

import tp_strategy.controller.tournament.Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.System.out;

/**
 * Created by Yo on 17/4/2019.
 */
public class TournamentDBWriter {

    private static TournamentDBWriter INSTANCE;

    private static final String url = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10288388  ";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static Connection con;
    private static final String userName = "sql10288388";
    private static final String password = "7hXNZdV75H";


    public static TournamentDBWriter getInstance() {
        if(Objects.isNull(INSTANCE)) {
            INSTANCE = new TournamentDBWriter();
        }
        return INSTANCE;
    }

    public void writeResult(Register result){
        Connection c = getConnection();
        String query = " insert into tournament (team2points, team1points, winner)"
                + " values (?, ?, ?)";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1,result.getWinnerPoints());
            ps.setInt(2,result.getLooserPoints());
            ps.setString(3,result.getWinnerName());
            ps.execute();
        } catch (SQLException e) {
            out.println(" la consulta fallo");
        }
    }

    public static Connection getConnection(){
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url,userName,password);
                out.print("Connection success");
            } catch (SQLException e) {
                out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException e) {
            out.println("Driver not found.");
        }
        return con;
    }

}
