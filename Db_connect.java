import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.*;

public class Db_connect {
    private Connection con= null;
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
    public ArrayList<Car> getCarsList() throws SQLException{
        ArrayList<Car> samochody= new ArrayList<Car>();
        stmt=con.createStatement();
        query="SELECT * FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            samochody.add(new Car(rs.getString("rejestracja"), rs.getString("marka"), rs.getString("model"), rs.getInt("rok"), rs.getInt("przebieg")));
        }
        stmt.close();
        return samochody;
    }
    public ArrayList<Driver> getDriversList() throws SQLException{
        ArrayList<Driver> kierowcy= new ArrayList<Driver>();
        stmt=con.createStatement();
        query="SELECT * FROM drivers;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            kierowcy.add(new Driver(rs.getLong("pesel"), rs.getString("imie"), rs.getString("nazwisko")));
        }
        stmt.close();
        return kierowcy;
    }
    public void addCar(Car c) throws SQLException{
        stmt=con.createStatement();
        query="INSERT INTO cars VALUES ('"+c.getRejestracja()+"', '"+c.getMarka()+"', '"+c.getModel()+"', '"+c.getRok()+"', '"+c.getPrzebieg()+"');";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    public void editCar(Car c) throws SQLException{
        stmt=con.createStatement();
        query="UPDATE cars SET marka='"+c.getMarka()+"', model='"+c.getModel()+"', rok='"+c.getRok()+"', przebieg='"+c.getPrzebieg()+"' WHERE rejestracja='"+c.getRejestracja()+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();

    }
    public void deleteCar(Car c) throws SQLException{
        stmt=con.createStatement();
        query="DELETE FROM cars WHERE rejestracja='"+c.getRejestracja()+"';";
        stmt.executeUpdate(query);
        con.commit();
        query="DELETE FROM owns WHERE rejestracja='"+c.getRejestracja()+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    public void addDriver(Driver d) throws SQLException{
        stmt=con.createStatement();
        query="INSERT INTO drivers VALUES ('"+d.getPesel()+"', '"+d.getImie()+"', '"+d.getNazwisko()+"');";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    public void editDriver(Driver d) throws SQLException{
        stmt=con.createStatement();
        query="UPDATE drivers SET imie='"+d.getImie()+"', nazwisko='"+d.getNazwisko()+"' WHERE pesel='"+d.getPesel()+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();

    }
    public void deleteDriver(Driver d) throws SQLException{
        stmt=con.createStatement();
        query="DELETE FROM drivers WHERE pesel='"+d.getPesel()+"';";
        stmt.executeUpdate(query);
        con.commit();
        query="DELETE FROM owns WHERE pesel='"+d.getPesel()+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    public Car findCar(String rej) throws SQLException{
        Car c=null;
        stmt=con.createStatement();
        query="SELECT * FROM cars WHERE rejestracja='"+rej+"';";
        rs=stmt.executeQuery(query);
        while(rs.next()){
           c=new Car(rs.getString("rejestracja"), rs.getString("marka"), rs.getString("model"), rs.getInt("rok"), rs.getInt("przebieg"));
        }
        stmt.close();
        return c;
    }    
    public Driver findDriver(long pesel) throws SQLException{
        Driver d=null;
        stmt=con.createStatement();
        query="SELECT * FROM drivers WHERE pesel='"+pesel+"';";
        rs=stmt.executeQuery(query);
        while(rs.next()){
           d=new Driver(rs.getLong("pesel"), rs.getString("imie"), rs.getString("nazwisko"));
        }
        stmt.close();
        return d;
    }
    public ArrayList<String> getFreeCars() throws SQLException{
        ArrayList<String> samochody= new ArrayList<String>();
        stmt=con.createStatement();
        query="SELECT rejestracja FROM cars WHERE rejestracja NOT IN(SELECT cars.rejestracja FROM cars INNER JOIN owns ON cars.rejestracja = owns.rejestracja);";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            samochody.add(rs.getString("rejestracja"));
        }
        stmt.close();
        return samochody;
    }
    public ArrayList<String> getCarsOfDriver(long pesel) throws SQLException{
        ArrayList<String> samochody= new ArrayList<String>();
        stmt=con.createStatement();
        query="SELECT rejestracja FROM owns WHERE pesel='"+pesel+"';";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            samochody.add(rs.getString("rejestracja"));
        }
        stmt.close();
        return samochody;
    }
    public void addCarToDriver(long pesel, String rejestracja) throws SQLException{
        stmt=con.createStatement();
        query="INSERT INTO owns VALUES ('"+pesel+"', '"+rejestracja+"');";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    public void deleteCarOfDriver(String rejestracja) throws SQLException{
        stmt=con.createStatement();
        query="DELETE FROM owns WHERE rejestracja='"+rejestracja+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    public long getDriverPesel(String rejestracja) throws SQLException{
        long x= 0;
        stmt=con.createStatement();
        query="SELECT pesel FROM owns WHERE rejestracja='"+rejestracja+"';";
        rs=stmt.executeQuery(query);
        while(rs.next()){
           x=rs.getLong("pesel");
        }
        stmt.close();
        return x;
    }
    public boolean isEmptyString(String s){
        if(s.equals("")){
            return true;
        }
        return false;
    }
    public boolean canAddPesel(long pesel) throws SQLException{
        int l = (int) (Math.log10(pesel) + 1);
        if(l!=11){
            return false;
        }
        stmt=con.createStatement();
        query="SELECT pesel FROM drivers WHERE pesel='"+pesel+"';";
        rs=stmt.executeQuery(query);
        if(rs.next()){
            return false;
        }
        stmt.close();
        return true;
    }
    public boolean canAddRejestracja(String rejestracja){
        if(rejestracja.length()>8){
            return false;
        }
        return true;
    }
    
    
    



    public void connect() throws Exception{
        Class.forName("org.sqlite.JDBC");        
        con=DriverManager.getConnection("jdbc:sqlite:"+DB_NAME);
        con.setAutoCommit(false);
    }
    
}