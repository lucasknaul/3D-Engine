import java.awt.*;
import javax.swing.*;
import org.jblas.DoubleMatrix;

class Renderer extends JPanel {
  Mesh[] meshes;

  public Renderer(Mesh[] meshes){
    super();
    this.meshes = meshes;
  }

  public void update(Mesh[] meshes){
    meshes = this.meshes;
  }

  public void paint(Graphics g) {
    super.paint(g);
    for(int i=0; i<meshes.length; i++){
      DoubleMatrix vertices = meshes[i].getVertices();
      DoubleMatrix Faces = meshes[i].getFaces();
      for(int j=0; j<Faces.columns; j++){
        DoubleMatrix t = Faces.getColumn(j);
        int p0x = (int)vertices.get(0,(int)t.get(0));
        int p0y = (int)vertices.get(1,(int)t.get(0));
        int p0z = (int)vertices.get(2,(int)t.get(0));
        int p1x = (int)vertices.get(0,(int)t.get(1));
        int p1y = (int)vertices.get(1,(int)t.get(1));
        int p1z = (int)vertices.get(2,(int)t.get(1));
        int p2x = (int)vertices.get(0,(int)t.get(2));
        int p2y = (int)vertices.get(1,(int)t.get(2));
        int p2z = (int)vertices.get(2,(int)t.get(2));
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
