import java.awt.*;
import javax.swing.*;
import org.jblas.DoubleMatrix;

class Renderer extends JPanel {
  static int X_OFFSET = 600;
  static int Y_OFFSET = 500;

  Mesh[] meshes;

  public Renderer(Mesh[] meshes){
    super();
    this.meshes = meshes;
  }

  public void update(Mesh[] meshes){
    this.meshes = meshes;
  }

  public void paint(Graphics g) {
    super.paint(g);
    for(int i=0; i<meshes.length; i++){
      DoubleMatrix vertices = meshes[i].getVertices();
      DoubleMatrix faces = meshes[i].getFaces();
      for(int j=0; j<faces.columns; j++){
        DoubleMatrix t = faces.getColumn(j);
        double p0w = vertices.get(3,(int)t.get(0));
        double p1w = vertices.get(3,(int)t.get(1));
        double p2w = vertices.get(3,(int)t.get(2));
        double p0x = X_OFFSET + vertices.get(0,(int)t.get(0))*p0w;
        double p0y = Y_OFFSET + vertices.get(1,(int)t.get(0))*p0w;
        double p0z = vertices.get(2,(int)t.get(0))*p0w;
        double p1x = X_OFFSET + vertices.get(0,(int)t.get(1))*p1w;
        double p1y = Y_OFFSET + vertices.get(1,(int)t.get(1))*p1w;
        double p1z = vertices.get(2,(int)t.get(1))*p1w;
        double p2x = X_OFFSET + vertices.get(0,(int)t.get(2))*p2w;
        double p2y = Y_OFFSET + vertices.get(1,(int)t.get(2))*p2w;
        double p2z = vertices.get(2,(int)t.get(2))*p2w;
        g.drawLine((int)p0x, (int)p0y, (int)p1x, (int)p1y);
        g.drawLine((int)p1x, (int)p1y, (int)p2x, (int)p2y);
        g.drawLine((int)p2x, (int)p2y, (int)p0x, (int)p0y);
        //g.drawLine((int)p2.getX(), (int)p2.getY(), (int)p0.getX(), (int)p0.getY());
        /*System.out.print("Desenhando triangulo " + j + " do mesh " + i + ":\n");
        System.out.print(
            "p0: (" + p0.getX() + "," + p0.getY() + ","+ p0.getZ() +")\n" +
            "p1 (" + p1.getX() + "," + p1.getY() + "," + p1.getZ() + ")\n" +
            "p2 (" + p2.getX() + "," + p2.getY() + "," + p2.getZ() + ")\n\n");*/
      }
    }
    this.repaint();
  }
}
