package javasierpinsky;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.GroupLayout;

import javax.swing.JPanel;

public class Canvas extends JPanel {
    
    private int level = 0;
    private final static int OFFSET = 10;
    
    public Canvas(JPanel parent) {
        super();
        GroupLayout canvasLayout = new GroupLayout(this);
        this.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout parentLayout = new GroupLayout(parent);
        parent.setLayout(parentLayout);
        parentLayout.setHorizontalGroup(
            parentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(this, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        parentLayout.setVerticalGroup(
            parentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(this, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
    
    public void printSierpinsky(int level){
        this.level = level;
        this.repaint();
    }
    
    
    @Override
    public void paint(Graphics g) {
        Graphics2D canvas = (Graphics2D) g;

        int xMax = this.xWidth();
        int yMax = this.yHeight();
        
        // borrar pantalla
        canvas.clearRect(0, 0, xMax, yMax);
        
        // pintar el triangulo grande
        int x1 = OFFSET;
        int y1 = yMax-OFFSET;
        Point2D p1 = new Point2D.Float(x1, y1);
        
        int x2 = xMax-OFFSET;
        int y2 = yMax-OFFSET;
        Point2D p2 = new Point2D.Float(x2, y2);
        
        int x3 = xMax/2;
        int y3 = OFFSET;
        Point2D p3 = new Point2D.Float(x3, y3);
        
        Line2D l1 = new Line2D.Float(p1, p2);
        Line2D l2 = new Line2D.Float(p2, p3);
        Line2D l3 = new Line2D.Float(p3, p1);
        
        canvas.draw(l1);
        canvas.draw(l2);
        canvas.draw(l3);
        
        sierpinsky(canvas, p1, p2, p3, level);
    }

    private void sierpinsky(Graphics2D canvas, Point2D p1, Point2D p2, Point2D p3, int iterations){
        
        if(iterations > 0){
            
            iterations--;
            
            Point2D p4 = halfPoint(p1, p2);
            Point2D p5 = halfPoint(p2, p3);
            Point2D p6 = halfPoint(p3, p1);
            
            Line2D l1 = new Line2D.Float(p4, p5);
            Line2D l2 = new Line2D.Float(p5, p6);
            Line2D l3 = new Line2D.Float(p6, p4);
            
            canvas.draw(l1);
            canvas.draw(l2);
            canvas.draw(l3);
            
            sierpinsky(canvas, p1, p4, p6, iterations);
            sierpinsky(canvas, p4, p2, p5, iterations);
            sierpinsky(canvas, p6, p5, p3, iterations);
        }
    }
    
    private Point2D halfPoint(Point2D p1, Point2D p2){
        float x = (float) ((p1.getX()+p2.getX())/2.0);
        float y = (float) ((p1.getY()+p2.getY())/2.0);
        return new Point2D.Float( x, y);
    }
    
    public int xWidth(){
        return this.getSize().width;
    }
    
    public int yHeight(){
        return this.getSize().height;
    }
    
}
