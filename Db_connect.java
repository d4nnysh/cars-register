import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class Db_connect {
    private Connection con= null;
    private ArrayList<Driver> kierowcy= new ArrayList<Driver>();
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



    public void connect() throws Exception{
        Class.forName("org.sqlite.JDBC");        
        con=DriverManager.getConnection("jdbc:sqlite:"+DB_NAME);
        con.setAutoCommit(false);
    }
    
}