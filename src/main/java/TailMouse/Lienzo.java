package TailMouse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import static java.lang.System.arraycopy;
import java.util.HashMap;
import javax.swing.JPanel;

public class Lienzo extends JPanel {
    
    private Point [] pointsMouse = new Point[5];
    private int contPoints = 0;
    private HashMap<String, Color> colors = new HashMap<String, Color>();
    private Color colorBackground;
    private Color colorTail;
    private boolean shapeTriangle;
    
    public Lienzo(){
        colors.put("Negro", Color.BLACK);
        colors.put("Naranja", Color.ORANGE);
        colors.put("Blanco", Color.WHITE);
        colors.put("Rosa", Color.PINK);
        colors.put("Verde", Color.GREEN);
        colors.put("Amarillo", Color.YELLOW);
        colors.put("Rojo", Color.RED);
        colors.put("Azul", Color.BLUE);
        colorTail = Color.BLACK;
        colorBackground = Color.WHITE;
        shapeTriangle = false;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(colorBackground);
        paintTail(g);
    }
    
    public void addPoints(Point newPoint){
        arraycopy(pointsMouse,0,pointsMouse,1,4);
        pointsMouse[0] = newPoint;
        if (contPoints < 5){
            contPoints++;
        }
    }
    
    public void paintTail(Graphics g){
        for (int i = 0; i<contPoints; i++){
            g.setColor(colorTail);
            if(!shapeTriangle){
                g.fillOval((int) pointsMouse[i].getX(), (int) pointsMouse[i].getY(), 10, 10);
            }else{
                g.fillPolygon(setTriangle((int)pointsMouse[i].getX(), (int)pointsMouse[i].getY()));
            }
        }
        
    }
    
    private Polygon setTriangle(int x, int y){
        int halfSide = (int) (7/Math.sqrt(2));
        Polygon triangle = new Polygon(new int[]{x, x-halfSide, x+halfSide},new int[]{y-7, y+halfSide, y+halfSide},3);
        return triangle;
    }
    
    public void setShape(){
        shapeTriangle = !shapeTriangle;
    }
    
    public void setColorTail(String nameColor) {
        colorTail = colors.get(nameColor);
    }
    
    public void setColorBackground(String nameColor) {
        colorBackground = colors.get(nameColor);
    }
}
