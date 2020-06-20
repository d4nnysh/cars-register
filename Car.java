public class Car {
    private String rejestracja;
    private String marka;
    private String model;
    private int rok;
    private int przebieg;
    public Car(String rejestracja, String marka, String model, int rok, int przebieg){
        this.rejestracja=rejestracja;
        this.marka=marks;
        this.model=model;
        this.rok=rok;
        this.przebieg=przebieg;
    }
    public String getMarka() {
        return marka;
    }
    public String getModel() {
        return model;
    }
    public int getPrzebieg() {
        return przebieg;
    }
    public String getRejestracja() {
        return rejestracja;
    }
    public int getRok() {
        return rok;
    }
    public void setMarka(String marka) {
        this.marka = marka;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }
    public void setRejestracja(String rejestracja) {
        this.rejestracja = rejestracja;
    }
    public void setRok(int rok) {
        this.rok = rok;
    }

}