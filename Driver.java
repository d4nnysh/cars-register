import java.util.ArrayList;

public class Driver {
    private long pesel;
    private String imie;
    private String nazwisko;
    private ArrayList<Car> cars= new ArrayList<>();
    public Driver(long pesel, String imie, String nazwisko){
        this.pesel=pesel;
        this.imie=imie;
        this.nazwisko=nazwisko;
    }
    public void addCar(Car c){
        cars.add(c);
    }
    public ArrayList<Car> getCars() {
        return cars;
    }
    public String getImie() {
        return imie;
    }
    public String getNazwisko() {
        return nazwisko;
    }
    public long getPesel() {
        return pesel;
    }
    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public void setPesel(long pesel) {
        this.pesel = pesel;
    }


}