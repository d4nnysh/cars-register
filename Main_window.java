import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.*;

public class Main_window extends JFrame implements ActionListener{
    private JButton kierowcy, samochody, wyjscie;            
    private JLabel stat, avgW, avgP, maxW, maxP, avgSpK; 
    private JPanel panel, right_panel, center_panel;  
    private Db_connect d;   
    
    public Main_window(){
        try {
            d= new Db_connect();
            setBounds(100, 20, 1200, 800);
            setTitle("Cars register");        
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            stat=new JLabel("Statystyki");            
            avgW=new JLabel("Sredni wiek pojazdu: "+d.avgWiek() +" lat");
            avgP=new JLabel("Sredni przebieg pojazdu: "+d.avgPrzebieg()+ " km");
            maxW=new JLabel("Najstarszy pojazd : "+d.maxWiek()+ " lat");
            maxP=new JLabel("Najwiekszy przebieg pojazdu: "+d.avgPrzebieg()+" km");
            avgSpK=new JLabel("Srednio pojazdow na kierowce: "+d.avgCarsPerDriver());

            stat.setFont(stat.getFont().deriveFont(64f));
            avgW.setFont(stat.getFont().deriveFont(16f));
            avgP.setFont(stat.getFont().deriveFont(16f));
            maxW.setFont(stat.getFont().deriveFont(16f));
            maxP.setFont(stat.getFont().deriveFont(16f));
            avgSpK.setFont(stat.getFont().deriveFont(16f));

            kierowcy=new JButton("Menu kierowcow");
            samochody=new JButton("Menu samochodow");
            wyjscie=new JButton("Wyjscie");
            kierowcy.addActionListener(this);
            samochody.addActionListener(this);
            wyjscie.addActionListener(this);

            wyjscie.setPreferredSize(new Dimension(0, 100));
            kierowcy.setFont(stat.getFont().deriveFont(50f));
            samochody.setFont(stat.getFont().deriveFont(50f));
            wyjscie.setFont(stat.getFont().deriveFont(50f));
            
            
            panel = new JPanel();
            right_panel = new JPanel();
            center_panel = new JPanel();

            panel.setLayout(new BorderLayout());
            right_panel.setLayout(new GridLayout(0,1));
            center_panel.setLayout(new GridLayout(0,1));
            center_panel.add(kierowcy);
            center_panel.add(samochody);
            
            right_panel.add(stat);
            right_panel.add(avgW);
            right_panel.add(avgP);
            right_panel.add(maxW);
            right_panel.add(maxP);
            right_panel.add(avgSpK);

            panel.add(center_panel, BorderLayout.CENTER);
            panel.add(wyjscie, BorderLayout.SOUTH);
            panel.add(right_panel, BorderLayout.EAST);

            getContentPane().add(panel);            
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        
    }
    public void odswiez(){
        Main_window mw= new Main_window();
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==samochody){
            Cars_window cw= new Cars_window(d, this);
        }
        else if(e.getSource()==kierowcy){
            Drivers_window dw= new Drivers_window(d, this);
        }
        else{
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        Main_window mw= new Main_window(); 
        
    }
}