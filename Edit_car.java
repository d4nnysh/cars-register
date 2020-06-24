import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

public class Edit_car extends JFrame implements ActionListener{
    private Db_connect d;
    private Car s;
    private JLabel rejestracja, marka, model, rok, przebieg; 
    private JTextField wpiszRe, wpiszMa, wpiszMo, wpiszRo, wpiszP; 
    private JButton wroc, edytuj, usun;
    private JPanel panel= new JPanel();
    private boolean nowySamochod=false;


    public Edit_car(Db_connect d, Car s){
        this.d=d;
        this.s=s;
        createWindow();
        wpiszRe.setEditable(false);
        wpiszRe.setText(s.getRejestracja());
        wpiszMa.setText(s.getMarka());
        wpiszMo.setText(s.getModel());
        wpiszRo.setText(String.valueOf(s.getRok()));
        wpiszP.setText(String.valueOf(s.getPrzebieg()));


    }
    public Edit_car(Db_connect d){
        this.d=d;
        createWindow();
        edytuj.setText("Dodaj");
        usun.setVisible(false);
        nowySamochod=true;
        
    }
    public void createWindow(){
        setBounds(100, 20, 600, 400);
        setTitle("Edytuj samochod");        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        wroc=new JButton("Wroc");
        edytuj=new JButton("Edytuj");
        usun=new JButton("Usun");
        rejestracja=new JLabel("Rejestracja: ");
        marka=new JLabel("Marka: ");
        model=new JLabel("Model: ");
        rok=new JLabel("Rok: ");
        przebieg=new JLabel("Przebieg: ");
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
                if(nowySamochod){
                    d.addSamochod(new Car(wpiszRe.getText(), wpiszMa.getText(), wpiszMo.getText(), Integer.parseInt(wpiszRo.getText()), Integer.parseInt(wpiszP.getText())));
                    setVisible(false);
                }
                else{
                    d.editSamochod(new Car(wpiszRe.getText(), wpiszMa.getText(), wpiszMo.getText(), Integer.parseInt(wpiszRo.getText()), Integer.parseInt(wpiszP.getText())));
                    setVisible(false);
                }
            }
            else if(e.getSource()==usun){                
                d.deleteSamochod(s);
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