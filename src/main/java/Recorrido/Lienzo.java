package Recorrido;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JPanel;

public class Lienzo extends JPanel implements MouseMotionListener {

    HashMap<String, Color> colores = new HashMap<>();
    final int puntos = 5;
    final double tam = 5;
    LinkedList<Point> puntosEstela = new LinkedList<>();
    Color estela;
    Color fondo;    

    public Lienzo() {
        addMouseMotionListener(this);
        fillColorMap();        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        this.setBackground(fondo);
        g2d.setColor(estela);
        for (Point point : puntosEstela) {
            Shape circle = new Ellipse2D.Double(point.getX() - tam, point.getY() - tam, 1.0 * tam, 1.0 * tam);
            g2d.fill(circle);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        raton(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        raton(e);
    }
    
    private void raton(MouseEvent e){
        puntosEstela.add(e.getPoint());
        if (puntosEstela.size() > 5) {
            puntosEstela.pop();
        }
        repaint();
    }
    
    public void setFondo(String color){
        fondo = colores.get(color);
        repaint();
    }

    public void setEstela(String color){
        estela = colores.get(color);
        repaint();
    }    
    
    private void fillColorMap(){
        colores.put("Verde", new Color(0,255,0));
        colores.put("Negro", new Color(0,0,0));
        colores.put("Azul", new Color(0,0,255));
        colores.put("Rojo", new Color(255,0,0));
        colores.put("Amarillo", new Color(255,255,0));
        colores.put("Blanco", new Color(255,255,255));

    }
}
