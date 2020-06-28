import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

public class Edit_car extends JFrame implements ActionListener{
    private Db_connect d;
    private Car s;
    private JLabel rejestracja, marka, model, rok, przebieg, wlasciciel; 
    private JTextField wpiszRe, wpiszMa, wpiszMo, wpiszRo, wpiszP; 
    private JButton wroc, edytuj, usun;
    private JPanel panel= new JPanel();
    private boolean nowySamochod=false;
    private Cars_window cw;


    public Edit_car(Db_connect d, Car s, Cars_window cw){
        this.d=d;
        this.s=s;
        this.cw=cw;
        createWindow();
        wpiszRe.setEditable(false);
        wpiszRe.setText(s.getRejestracja());
        wpiszMa.setText(s.getMarka());
        wpiszMo.setText(s.getModel());
        wpiszRo.setText(String.valueOf(s.getRok()));
        wpiszP.setText(String.valueOf(s.getPrzebieg()));
        try {
            if (d.getDriverPesel(s.getRejestracja())==0) {
                wlasciciel.setText("PESEL wlasciciela: -");
            }
            else{
                wlasciciel.setText("PESEL wlasciciela: "+d.getDriverPesel(s.getRejestracja()));
            }
        } catch (Exception e) {
        }
    }
    public Edit_car(Db_connect d, Cars_window cw){
        this.d=d;
        this.cw=cw;
        createWindow();
        edytuj.setText("Dodaj");
        usun.setVisible(false);
        wlasciciel.setVisible(false);
        nowySamochod=true;
        
    }
    public void createWindow(){
        setBounds(100, 20, 600, 400);
        setTitle("Edytuj samochod");        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        wroc=new JButton("Wroc");
        edytuj=new JButton("Zatwierdz zmiany");
        usun=new JButton("Usun");
        rejestracja=new JLabel("Rejestracja: ");
        marka=new JLabel("Marka: ");
        model=new JLabel("Model: ");
        rok=new JLabel("Rok: ");
        przebieg=new JLabel("Przebieg: ");
        wlasciciel=new JLabel("");
        wpiszRe= new JTextField();
        wpiszMa= new JTextField();
        wpiszMo= new JTextField();
        wpiszRo= new JTextField();
        wpiszP= new JTextField();
        edytuj.addActionListener(this);
        wroc.addActionListener(this);
        usun.addActionListener(this);
        panel.setLayout(new GridLayout(0,2));

        panel.add(rejestracja);
        panel.add(wpiszRe);
        panel.add(marka);
        panel.add(wpiszMa);
        panel.add(model);
        panel.add(wpiszMo);
        panel.add(rok);
        panel.add(wpiszRo);
        panel.add(przebieg);
        panel.add(wpiszP);
        panel.add(wlasciciel);
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
                    int r, p;
                    r=Integer.parseInt(wpiszRo.getText());
                    p=Integer.parseInt(wpiszP.getText());
                    if(nowySamochod&&d.canAddRejestracja(wpiszRe.getText())){                    
                        d.addCar(new Car(wpiszRe.getText(), wpiszMa.getText(), wpiszMo.getText(), r, p));
                        dispose();
                        cw.odswiez();                                          
                    }
                    else if(!nowySamochod){
                        d.editCar(new Car(wpiszRe.getText(), wpiszMa.getText(), wpiszMo.getText(), r, p));
                        dispose();
                        cw.odswiez();
                    }
                    else{
                        Alert_window aw= new Alert_window(3);
                    }
                } catch (Exception x) {
                    x.printStackTrace();
                    Alert_window aw= new Alert_window(4);
                }
            }
            else if(e.getSource()==usun){                
                d.deleteCar(s);
                dispose();
                cw.odswiez();
            
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        
        if(e.getSource()==wroc){
            dispose();
        }

    }
}