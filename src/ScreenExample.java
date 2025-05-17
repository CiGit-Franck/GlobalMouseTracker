import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ScreenExample extends Window implements MouseListener {

    private List<Point> clickPositions = new ArrayList<>();

    public ScreenExample {
        // Créer une JFrame
        JFrame frame = new JFrame("Mon exemple");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Récupérer l'environnement graphique
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Récupérer tous les périphériques graphiques (écrans)
        GraphicsDevice[] devices = ge.getScreenDevices();

        // Boucle sur les périphériques graphiques pour trouver celui qui correspond à la JFrame
        for (GraphicsDevice device : devices) {
            Rectangle bounds = device.getDefaultConfiguration().getBounds();

            // Vérifier si la JFrame est affichée sur cet écran
            if (bounds.contains(frame.getLocation())) {
                device.getFullScreenWindow().addMouseListener(this);
                System.out.println("La JFrame est sur l'écran : " + device.getIDstring());
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> new ScreenExample());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point pt = MouseInfo.getPointerInfo().getLocation();
        clickPositions.add(pt);
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
