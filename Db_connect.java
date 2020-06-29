import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.*;
/**
 * Klasa sluzaca do laczenia sie z baza danych
 */
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
    /**
     * Funkcja sluzaca do laczenia sie z baza danych
     */
    public void connect() throws Exception{
        Class.forName("org.sqlite.JDBC");        
        con=DriverManager.getConnection("jdbc:sqlite:"+DB_NAME);
        con.setAutoCommit(false);
    }
    /**
     * Funkcja zwracajaca najwiekszy przebieg samochodu w bazie danych
     */
    public int maxPrzebieg() throws SQLException{
        int p=0;
        stmt=con.createStatement();
        query="SELECT MAX(przebieg) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            p=rs.getInt("MAX(przebieg)");
        }
        stmt.close();
        rs.close();
        return p;
    }
    /**
     * Funkcja zwracajaca najwiekszy wiek samochodu w bazie danych
     */
    public int maxWiek() throws SQLException{
        int w= LocalDate.now().getYear();
        stmt=con.createStatement();
        query="SELECT MIN(rok) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            w=rs.getInt("MIN(rok)");
        }
        stmt.close();
        rs.close();
        return LocalDate.now().getYear()-w;
    }
    /**
     * Funkcja zwracajaca sredni przebieg samochodow w bazie danych
     */
    public double avgPrzebieg() throws SQLException{
        double p=0;
        stmt=con.createStatement();
        query="SELECT AVG(przebieg) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            p=rs.getInt("AVG(przebieg)");
        }
        stmt.close();
        rs.close();
        return p;
    }
    /**
     * Funkcja zwracajaca sredni wiek samochodow w bazie danych
     */
    public double avgWiek() throws SQLException{
        double w=LocalDate.now().getYear();
        stmt=con.createStatement();
        query="SELECT AVG(rok) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            w=rs.getDouble("AVG(rok)");
        }
        stmt.close();
        rs.close();
        return (double)LocalDate.now().getYear()-w;
    }
    /**
     * Funkcja zwracajaca srednia ilosc samochodow przypadajacych na kierowce w bazie danych
     */
    public double avgCarsPerDriver() throws SQLException{
        int k=0, s=0;
        stmt=con.createStatement();
        query="SELECT COUNT(pesel) FROM drivers;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            k=rs.getInt("COUNT(pesel)");
        }
        rs.close();
        query="SELECT COUNT(rejestracja) FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            s=rs.getInt("COUNT(rejestracja)");
        }
        stmt.close();
        rs.close();
        return (double)s/k;
    }
    /**
     * Funkcja zwracajaca liste wszystkich samochodow w bazie danych
     */
    public ArrayList<Car> getCarsList() throws SQLException{
        ArrayList<Car> samochody= new ArrayList<Car>();
        stmt=con.createStatement();
        query="SELECT * FROM cars;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            samochody.add(new Car(rs.getString("rejestracja"), rs.getString("marka"), rs.getString("model"), rs.getInt("rok"), rs.getInt("przebieg")));
        }
        stmt.close();
        rs.close();
        return samochody;
    }
    /**
     * Funkcja zwracajaca liste wszystkich kierwcow w bazie danych
     */
    public ArrayList<Driver> getDriversList() throws SQLException{
        ArrayList<Driver> kierowcy= new ArrayList<Driver>();
        stmt=con.createStatement();
        query="SELECT * FROM drivers;";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            kierowcy.add(new Driver(rs.getLong("pesel"), rs.getString("imie"), rs.getString("nazwisko")));
        }
        stmt.close();
        rs.close();
        return kierowcy;
    }
    /**
     * Funkcja sluzaca do dodania samochodu
     */
    public void addCar(Car c) throws SQLException{
        stmt=con.createStatement();
        query="INSERT INTO cars VALUES ('"+c.getRejestracja().replace("'","''")+"', '"+c.getMarka().replace("'","''")+"', '"+c.getModel().replace("'","''")+"', '"+c.getRok()+"', '"+c.getPrzebieg()+"');";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    /**
     * Funkcja sluzaca do edytowania samochodu 
     */
    public void editCar(Car c) throws SQLException{
        stmt=con.createStatement();
        query="UPDATE cars SET marka='"+c.getMarka().replace("'","''")+"', model='"+c.getModel().replace("'","''")+"', rok='"+c.getRok()+"', przebieg='"+c.getPrzebieg()+"' WHERE rejestracja='"+c.getRejestracja().replace("'","''")+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    /**
     * Funkcja sluzaca do usuwania samochodu 
     */
    public void deleteCar(Car c) throws SQLException{
        stmt=con.createStatement();
        query="DELETE FROM cars WHERE rejestracja='"+c.getRejestracja().replace("'","''")+"';";
        stmt.executeUpdate(query);
        con.commit();
        query="DELETE FROM owns WHERE rejestracja='"+c.getRejestracja().replace("'","''")+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    /**
     * Funkcja sluzaca do dodania kierowcy 
     */
    public void addDriver(Driver d) throws SQLException{
        stmt=con.createStatement();
        query="INSERT INTO drivers VALUES ('"+d.getPesel()+"', '"+d.getImie().replace("'","''")+"', '"+d.getNazwisko().replace("'","''")+"');";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    /**
     * Funkcja sluzaca do edytowania kierowcy 
     */
    public void editDriver(Driver d) throws SQLException{
        stmt=con.createStatement();
        query="UPDATE drivers SET imie='"+d.getImie().replace("'","''")+"', nazwisko='"+d.getNazwisko().replace("'","''")+"' WHERE pesel='"+d.getPesel()+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }    
    /**
     * Funkcja sluzaca do usuwania kierowcy 
     */
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
    /**
     * Funkcja sluzaca do znajdowania samochodow
     */
    public Car findCar(String rej) throws SQLException{
        Car c=null;
        stmt=con.createStatement();
        query="SELECT * FROM cars WHERE rejestracja='"+rej.replace("'","''")+"';";
        rs=stmt.executeQuery(query);
        while(rs.next()){
           c=new Car(rs.getString("rejestracja"), rs.getString("marka"), rs.getString("model"), rs.getInt("rok"), rs.getInt("przebieg"));
        }
        stmt.close();
        rs.close();
        return c;
    }    
    /**
     * Funkcja sluzaca do znajdowania kierowcow 
     */
    public Driver findDriver(long pesel) throws SQLException{
        Driver d=null;
        stmt=con.createStatement();
        query="SELECT * FROM drivers WHERE pesel='"+pesel+"';";
        rs=stmt.executeQuery(query);
        while(rs.next()){
           d=new Driver(rs.getLong("pesel"), rs.getString("imie"), rs.getString("nazwisko"));
        }
        stmt.close();
        rs.close();
        return d;
    }
    /**
     * Funkcja sluzaca do znajdowania samochodow nieprzypisanych do zadnego kierowcy 
     */
    public ArrayList<String> getFreeCars() throws SQLException{
        ArrayList<String> samochody= new ArrayList<String>();
        stmt=con.createStatement();
        query="SELECT rejestracja FROM cars WHERE rejestracja NOT IN(SELECT cars.rejestracja FROM cars INNER JOIN owns ON cars.rejestracja = owns.rejestracja);";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            samochody.add(rs.getString("rejestracja"));
        }
        stmt.close();
        rs.close();
        return samochody;
    }
    /**
     * Funkcja sluzaca do znajdowania samochodow przypisanych do konkretnego kierowcy 
     */
    public ArrayList<String> getCarsOfDriver(long pesel) throws SQLException{
        ArrayList<String> samochody= new ArrayList<String>();
        stmt=con.createStatement();
        query="SELECT rejestracja FROM owns WHERE pesel='"+pesel+"';";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            samochody.add(rs.getString("rejestracja"));
        }
        stmt.close();
        rs.close();
        return samochody;
    }
    /**
     * Funkcja sluzaca do dodawania samochodu do konkretnego kierowcy 
     */
    public void addCarToDriver(long pesel, String rejestracja) throws SQLException{
        stmt=con.createStatement();
        query="INSERT INTO owns VALUES ('"+pesel+"', '"+rejestracja.replace("'","''")+"');";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    /**
     * Funkcja sluzaca do usuwania samochodu od konkretnego kierowcy 
     */
    public void deleteCarOfDriver(String rejestracja) throws SQLException{
        stmt=con.createStatement();
        query="DELETE FROM owns WHERE rejestracja='"+rejestracja.replace("'","''")+"';";
        stmt.executeUpdate(query);
        con.commit();
        stmt.close();
    }
    /**
     * Funkcja sluzaca do znajdowania peselu wlasciciela konkretnego samochodu 
     */
    public long getDriverPesel(String rejestracja) throws SQLException{
        long x= 0;
        stmt=con.createStatement();
        query="SELECT pesel FROM owns WHERE rejestracja='"+rejestracja.replace("'","''")+"';";
        rs=stmt.executeQuery(query);
        while(rs.next()){
           x=rs.getLong("pesel");
        }
        stmt.close();
        rs.close();
        return x;
    }
    /**
     * Funkcja sprawdzajaca czy kierowca o podanym peselu moze byc dodany do bazy danych 
     */
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
        rs.close();
        return true;
    }
    /**
     * Funkcja sprawdzajaca czy samochod o danej rejestracji moze byc dodany do bazy danych 
     */
    public boolean canAddRejestracja(String rejestracja) throws SQLException{
        if(rejestracja.length()>8){
            return false;
        }
        stmt=con.createStatement();
        query="SELECT rejestracja FROM cars WHERE rejestracja='"+rejestracja.replace("'","''")+"';";
        rs=stmt.executeQuery(query);
        if(rs.next()){
            return false;
        }
        stmt.close();
        rs.close();
        return true;
    }
}