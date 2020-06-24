import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

public class Edit_driver extends JFrame implements ActionListener{
    private Db_connect d;
    private Driver k;
    private JLabel pesel, imie, nazwisko; 
    private JTextField wpiszP, wpiszI, wpiszN; 
    private JButton wroc, edytuj, usun, dodajS;
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


    }
    public Edit_driver(Db_connect d){
        this.d=d;
        createWindow();
        edytuj.setText("Dodaj");
        usun.setVisible(false);
        nowyKierowca=true;
        
    }
    public void createWindow(){
        setBounds(100, 20, 600, 400);
        setTitle("Edytuj kierowce");        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        wroc=new JButton("Wroc");
        edytuj=new JButton("Edytuj");
        usun=new JButton("Usun");
        dodajS=new JButton("Dodaj samochod");
        pesel=new JLabel("PESEL: ");
        imie=new JLabel("Imie: ");
        nazwisko=new JLabel("Nazwisko: ");
        wpiszP= new JTextField();
        wpiszI= new JTextField();
        wpiszN= new JTextField();
        edytuj.addActionListener(this);
        wroc.addActionListener(this);
        usun.addActionListener(this);
        dodajS.addActionListener(this);
        panel.setLayout(new GridLayout(0,2));

        panel.add(pesel);
        panel.add(wpiszP);
        panel.add(imie);
        panel.add(wpiszI);
        panel.add(nazwisko);
        panel.add(wpiszN);
        panel.add(edytuj);
        panel.add(usun);
        panel.add(wroc);
        panel.add(dodajS);



        getContentPane().add(panel); 
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==edytuj){
                if(nowyKierowca){
                    d.addDriver(new Driver(Long.valueOf(wpiszP.getText()), wpiszN.getText(), wpiszI.getText()));
                    setVisible(false);
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
        } catch (Exception x) {
            x.printStackTrace();
            System.exit(0);
        }
        
        if(e.getSource()==wroc){
            setVisible(false);
        }

    }
}