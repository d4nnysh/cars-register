import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

public class Edit_driver extends JFrame implements ActionListener{
    private Db_connect d;
    private Driver k;
    private JLabel pesel, imie, nazwisko; 
    private JTextField wpiszP, wpiszI, wpiszN; 
    private JButton wroc, edytuj, usun, dodajS, usunS;
    private JComboBox samochody, wybierzSamochod;
    private JPanel panel= new JPanel();
    private boolean nowyKierowca=false;


    public Edit_driver(Db_connect d, Driver k){
        this.d=d;
        this.k=k;
        createWindow();
        
        wpiszP.setEditable(false);
        
        wpiszP.setText(String.valueOf(k.getPesel()));       
        wpiszI.setText(k.getImie());
        wpiszN.setText(k.getNazwisko());
        try {            
            d.getCarsOfDriver(k.getPesel()).forEach(x -> samochody.addItem(x));
        } catch (Exception e) {
            //TODO: handle exception
        }


    }
    public Edit_driver(Db_connect d){
        this.d=d;
        createWindow();
        edytuj.setText("Dodaj");
        usun.setVisible(false);
        samochody.setVisible(false);
        wybierzSamochod.setVisible(false);
        dodajS.setVisible(false);
        usunS.setVisible(false);

        nowyKierowca=true;
        
    }
    public void createWindow(){
        setBounds(100, 20, 600, 400);
        setTitle("Edytuj kierowce");        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        }
        

        
        edytuj.addActionListener(this);
        wroc.addActionListener(this);
        usun.addActionListener(this);
        dodajS.addActionListener(this);
        usunS.addActionListener(this);
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
                if(nowyKierowca){
                    Driver dr=new Driver(Long.valueOf(wpiszP.getText()), wpiszN.getText(), wpiszI.getText());
                    d.addDriver(dr);
                    setVisible(false);
                    Edit_driver ed= new Edit_driver(d, dr);
                }
                else{
                    d.editDriver(new Driver(Long.valueOf(wpiszP.getText()), wpiszN.getText(), wpiszI.getText()));
                    setVisible(false);
                }
            }
            else if(e.getSource()==usun){                
                d.deleteDriver(k);
                setVisible(false);
            
            }
            else if(e.getSource()==dodajS){                
                d.addCarToDriver(k.getPesel(), wybierzSamochod.getSelectedItem().toString());
                setVisible(false);
                Edit_driver ed= new Edit_driver(d, k);            
            }
            else if(e.getSource()==usunS){                
                d.deleteCarOfDriver(samochody.getSelectedItem().toString());
                setVisible(false);
                Edit_driver ed= new Edit_driver(d, k); 
            
            }
        } catch (Exception x) {
            x.printStackTrace();
            System.exit(0);
        }
        
        if(e.getSource()==wroc){
            setVisible(false);
        }

    }
}