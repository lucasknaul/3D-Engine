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
        int p0w = (int)vertices.get(3,(int)t.get(0));
        int p1w = (int)vertices.get(3,(int)t.get(1));
        int p2w = (int)vertices.get(3,(int)t.get(2));
        int p0x = X_OFFSET + (int)vertices.get(0,(int)t.get(0))*p0w;
        int p0y = Y_OFFSET + (int)vertices.get(1,(int)t.get(0))*p0w;
        int p0z = (int)vertices.get(2,(int)t.get(0))*p0w;
        int p1x = X_OFFSET + (int)vertices.get(0,(int)t.get(1))*p1w;
        int p1y = Y_OFFSET + (int)vertices.get(1,(int)t.get(1))*p1w;
        int p1z = (int)vertices.get(2,(int)t.get(1))*p1w;
        int p2x = X_OFFSET + (int)vertices.get(0,(int)t.get(2))*p2w;
        int p2y = Y_OFFSET + (int)vertices.get(1,(int)t.get(2))*p2w;
        int p2z = (int)vertices.get(2,(int)t.get(2))*p2w;
        g.drawLine(p0x, p0y, p1x, p1y);
        g.drawLine(p1x, p1y, p2x, p2y);
        g.drawLine(p2x, p2y, p0x, p0y);
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
