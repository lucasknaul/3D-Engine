import javax.swing.*;
import java.awt.*;
import org.jblas.DoubleMatrix;

public class Engine3D {
  static int MESH_SIZE = 10;

  protected boolean running;

  JFrame f;
  Renderer r;

  protected Mesh[] meshes;
  protected Camera[] cameras;

  private void test(){
    /*DoubleMatrix v = new DoubleMatrix(new double[][]{{100d,150d,150d,100d},
                                                    {100d,100d,150d,150d},
                                                    {0,0,0,0},
                                                    {0,0,0,0}});*/

    DoubleMatrix v = new DoubleMatrix(new double[][]{{100,150,150,100,100,100,150,150},
                                                    {100,100,150,150,100,150,100,150},
                                                    {0,0,0,0,50,50,50,50},
                                                    {1,1,1,1,1,1,1,1}});
    DoubleMatrix t = new DoubleMatrix(new double[][]{{0,0,0,0,1,1,6,7,4,4,3,3},
                                                     {1,2,4,5,6,7,4,4,6,6,2,7,},
                                                     {2,3,5,3,7,2,5,5,0,1,7,5}});
    Translation translation = new Translation(300,500,0);
    Rotation rotation = new Rotation(Math.PI/6,0,Math.PI/6);

    //TRANSLATION MATRIX:
    //coloca vetores na vertical e multiplica matriz de translaçao por eles
    //matriz identidade:
      //cada multiiplicaçao vai fazer 1*eixo multiplicado + 1*valor da ultima coluna da matriz
      //o 1* valor da uultima coluna é devido ao 1 de w


    meshes[0] = new Mesh(v, t);
    //cameras[0] = new Camera();
    //meshes[0] = new Mesh(v, t);
    //translation.mmul(meshes[0].getVertices());
  }
  public void testRefresh(){
    Rotation rotation = new Rotation(Math.PI/18000,0,Math.PI/18000);
    meshes[0].setVertices(rotation.mmul(meshes[0].getVertices()));
  }

  public void update(){
    r.update(meshes);
  }

  public Engine3D(){
    running = true;

    meshes = new Mesh[1];
    test();

    f = new JFrame();
    r = new Renderer(meshes);
    f.setContentPane(r);
    f.setSize(800,600);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
