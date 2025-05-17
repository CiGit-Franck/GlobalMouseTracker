import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalMouseTracker extends JFrame {

    // Liste pour mémoriser les positions des clics
    private List<Point> clickPositions = new ArrayList<>();

    private MouseTrackerThread mouseTrackerThread;

    private Point position;

    public GlobalMouseTracker() {
        // Créer un JPanel optionnel pour afficher les points cliqués
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };

        this.addMouseListener(new MouseClickListener());

        mouseTrackerThread = new MouseTrackerThread();
        // Démarrer le thread
        mouseTrackerThread.start();

        // Configurer la fenêtre
        this.setTitle("Global Mouse Tracker");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> new GlobalMouseTracker());
    }

    class MouseTrackerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    Point pt = MouseInfo.getPointerInfo().getLocation();
                    position = pt;
                    // jlPosition.setText("Position [" + pt.x + " ," + pt.y + "]");
                } catch (InterruptedException ex) {
                    Logger.getLogger(
                        GlobalMouseTracker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class MouseClickListener extends MouseAdapter {
        public void mouseReleased(MouseEvent e) {
            Point pt = MouseInfo.getPointerInfo().getLocation();
            clickPositions.add(pt);
            System.out.println(pt);
        }
    }
}