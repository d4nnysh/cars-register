import javax.swing.*;
import java.awt.event.*;
public class Edit_car extends JFrame implements ActionListener{
    private Db_connect d;
    private JLabel rejestracja, marka, model, rok, przebieg; 
    private JTextField wpiszRe, wpiszMa, wpiszMo, wpiszRo, wpiszP; 
    private JButton wroc, edytuj, usun;
    private JPanel panel= new JPanel();


    public Edit_car(Db_connect d, Car s){
        createWindow();
        wpiszRe.setEditable(false);
        wpiszRe.setText(s.getRejestracja());
        wpiszMa.setText(s.getMarka());
        wpiszMo.setText(s.getModel());
        wpiszRo.setText(String.valueOf(s.getRok()));
        wpiszP.setText(String.valueOf(s.getPrzebieg()));


    }
    public Edit_car(Db_connect d){

        createWindow();
        edytuj.setText("Dodaj");
        usun.setVisible(false);
        
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
    }
}