import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;
/**
 * Okno dodawania oraz edycji kierowcow
 */
public class Edit_driver extends JFrame implements ActionListener{
    private Db_connect d;
    private Driver k;
    private JLabel pesel, imie, nazwisko; 
    private JTextField wpiszP, wpiszI, wpiszN; 
    private JButton wroc, edytuj, usun, dodajS, usunS;
    private JComboBox samochody, wybierzSamochod;
    private JPanel panel= new JPanel();
    private boolean nowyKierowca=false;
    private Drivers_window dw;
    /**
     * Konstruktor do edycji kierowcy
     */
    public Edit_driver(Db_connect d, Driver k, Drivers_window dw){
        this.d=d;
        this.k=k;
        this.dw=dw;
        createWindow();        
        wpiszP.setEditable(false);        
        wpiszP.setText(String.valueOf(k.getPesel()));       
        wpiszI.setText(k.getImie());
        wpiszN.setText(k.getNazwisko());
        try {            
            d.getCarsOfDriver(k.getPesel()).forEach(x -> samochody.addItem(x));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Konstruktor do dodawania nowego kierowcy
     */
    public Edit_driver(Db_connect d, Drivers_window dw){
        this.d=d;
        this.dw=dw;
        createWindow();
        edytuj.setText("Dodaj kierowce");
        usun.setVisible(false);
        samochody.setVisible(false);
        wybierzSamochod.setVisible(false);
        dodajS.setVisible(false);
        usunS.setVisible(false);
        nowyKierowca=true;        
    }
    /**
     * Funkcja tworzaca okno
     */
    public void createWindow(){
        setBounds(100, 20, 600, 400);
        setTitle("Edytuj kierowce");        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        wpiszP= new JTextField();
        wpiszI= new JTextField();
        wpiszN= new JTextField();
        wroc=new JButton("Wroc");
        edytuj=new JButton("Zatwierdz zmiany");
        usun=new JButton("Usun kierowce");
        dodajS=new JButton("Dodaj samochod");
        usunS=new JButton("Usun samochod");
        pesel=new JLabel("PESEL: ");
        imie=new JLabel("Imie: ");
        nazwisko=new JLabel("Nazwisko: ");
        samochody= new JComboBox<>();
        try {
            wybierzSamochod= new JComboBox<>(d.getFreeCars().toArray());            
        } catch (Exception e) {
            e.printStackTrace();
        }        
        edytuj.addActionListener(this);
        wroc.addActionListener(this);
        usun.addActionListener(this);
        dodajS.addActionListener(this);
        usunS.addActionListener(this);

        wroc.setFont(wroc.getFont().deriveFont(20f));
        edytuj.setFont(wroc.getFont().deriveFont(20f));
        usun.setFont(wroc.getFont().deriveFont(20f));
        dodajS.setFont(wroc.getFont().deriveFont(20f));
        usunS.setFont(wroc.getFont().deriveFont(20f));
        wpiszP.setFont(wroc.getFont().deriveFont(20f));
        wpiszI.setFont(wroc.getFont().deriveFont(20f));
        wpiszN.setFont(wroc.getFont().deriveFont(20f));
        samochody.setFont(wroc.getFont().deriveFont(20f));
        wybierzSamochod.setFont(wroc.getFont().deriveFont(20f));
        pesel.setFont(wroc.getFont().deriveFont(20f));
        imie.setFont(wroc.getFont().deriveFont(20f));
        nazwisko.setFont(wroc.getFont().deriveFont(20f));

        panel.setLayout(new GridLayout(0,2));
        panel.add(pesel);
        panel.add(wpiszP);
        panel.add(imie);
        panel.add(wpiszI);
        panel.add(nazwisko);
        panel.add(wpiszN);
        panel.add(samochody);
        panel.add(usunS);
        panel.add(wybierzSamochod);
        panel.add(dodajS);
        panel.add(edytuj);
        panel.add(usun);
        panel.add(wroc);        
        getContentPane().add(panel); 
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==edytuj){
                try {
                    long p= Long.valueOf(wpiszP.getText());
                    if(nowyKierowca&&d.canAddPesel(p)){
                        Driver dr=new Driver(p, wpiszI.getText(), wpiszN.getText());
                        d.addDriver(dr);
                        dispose();
                        Edit_driver ed= new Edit_driver(d, dr, dw);
                    }
                    else if(!nowyKierowca){
                        d.editDriver(new Driver(p, wpiszI.getText(), wpiszN.getText()));
                        dispose();
                        dw.odswiez();
                    }
                    else{
                        Alert_window aw= new Alert_window(1);
                    }
                } catch (Exception x) {
                    x.printStackTrace();
                    Alert_window aw= new Alert_window(1);
                }                
            }
            else if(e.getSource()==usun){                
                d.deleteDriver(k);
                dispose();
                dw.odswiez();            
            }
            else if(e.getSource()==dodajS&&wybierzSamochod.getSelectedIndex()>-1){                
                d.addCarToDriver(k.getPesel(), wybierzSamochod.getSelectedItem().toString());
                dispose();
                Edit_driver ed= new Edit_driver(d, k, dw);            
            }
            else if(e.getSource()==usunS&&samochody.getSelectedIndex()>-1){                
                d.deleteCarOfDriver(samochody.getSelectedItem().toString());
                dispose();
                Edit_driver ed= new Edit_driver(d, k, dw);             
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        if(e.getSource()==wroc){
            setVisible(false);
            dw.odswiez();
        }
    }
}