package snakePack;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {

    public Main() {
        
        inicializa();
    }
    
    private void inicializa() {
        
        add(new Ventana());
               
        setResizable(false);
        pack();
        
        setTitle("Snake by Juan Eguia");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame principal = new Main();
            principal.setVisible(true);
        });
    }
}
