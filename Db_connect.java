import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.*;

public class Db_connect {
    private Connection con= null;
    private ArrayList<Driver> kierowcy= new ArrayList<Driver>();
    private Statement stmt=null;
    private String query=null;
    private ResultSet rs=null;
    public static final String DB_NAME="cars-register.db";

    public Db_connect(){
        File db= new File(DB_NAME);
        if(!db.exists()){
            System.out.println("Database "+DB_NAME+" not found");
            System.exit(0);
        }
        try {
            connect();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
    public Connection getCon() {
        return con;
    }
    public static String getDbName() {
        return DB_NAME;
    }
    public void setCon(Connection con) {
        this.con = con;
    }
    public void setKierowcy(ArrayList<Driver> kierowcy) {
        this.kierowcy = kierowcy;
    }
    public int minPrzebieg() throws SQLException{
        int p=0;
        stmt=con.createStatement();
        query="SELECT MIN(przebieg) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            p=rs.getInt("MIN(przebieg)");

        }
        stmt.close();
        return p;

    }
    public int maxWiek() throws SQLException{
        int w= LocalDate.now().getYear();
        stmt=con.createStatement();
        query="SELECT MIN(rok) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            w=rs.getInt("MIN(rok)");

        }
        stmt.close();
        return LocalDate.now().getYear()-w;

    }
    public double avgPrzebieg() throws SQLException{
        double p=0;
        stmt=con.createStatement();
        query="SELECT AVG(przebieg) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            p=rs.getInt("AVG(przebieg)");

        }
        stmt.close();
        return p;

    }
    public double avgWiek() throws SQLException{
        double w=LocalDate.now().getYear();
        stmt=con.createStatement();
        query="SELECT AVG(rok) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            w=rs.getDouble("AVG(rok)");

        }
        stmt.close();
        return (double)LocalDate.now().getYear()-w;

    }
    public double avgCarsPerDriver() throws SQLException{
        int k=0, s=0;
        stmt=con.createStatement();
        query="SELECT COUNT(pesel) FROM drivers;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            k=rs.getInt("COUNT(pesel)");
        }
        query="SELECT COUNT(rejestracja) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            s=rs.getInt("COUNT(rejestracja)");
        }
        stmt.close();
        return (double)s/k;

    }
    



    public void connect() throws Exception{
        Class.forName("org.sqlite.JDBC");        
        con=DriverManager.getConnection("jdbc:sqlite:"+DB_NAME);
        con.setAutoCommit(false);
    }
    
}