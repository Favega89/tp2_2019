package tp_threads;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.System.out;

/**
 * Created by Yo on 17/4/2019.
 */
public class ThreadsDBWriter {

    private static ThreadsDBWriter INSTANCE;

    private static final String url = "jdbc:mysql://localhost:3306/tp2db";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static Connection con;
    private static final String userName = "root";
    private static final String password = "1234";


    public static ThreadsDBWriter getInstance() {
        if(Objects.isNull(INSTANCE)) {
            INSTANCE = new ThreadsDBWriter();
        }
        return INSTANCE;
    }

    public void writeResult(String word, String playerName, Date date){
        Connection c = getConnection();
        String query = " insert into registers (winner_name, word, fecha)"
                + " values (?, ?, ?)";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1,playerName);
            ps.setString(2,word);
            ps.setDate(3,date);
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
