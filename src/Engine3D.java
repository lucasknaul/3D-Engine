import javax.swing.*;
import java.awt.*;
import org.jblas.DoubleMatrix;

public class Engine3D {
  static int MESH_SIZE = 10;

  protected boolean running;

  JFrame f;
  Renderer r;

  protected Mesh[] meshes;
  //protected Camera[] cameras;

  private double cx, cy, cz;

  private void test(){
    /*DoubleMatrix v = new DoubleMatrix(new double[][]{{100d,150d,150d,100d},
                                                    {100d,100d,150d,150d},
                                                    {0,0,0,0},
                                                    {0,0,0,0}});*/

    DoubleMatrix v = new DoubleMatrix(new double[][]{{0,50,50,0,0,0,50,50},
                                                     {0,0,50,50,0,50,0,50},
                                                     {0,0,0,0,50,50,50,50},
                                                     {1,1,1,1,1,1,1,1}});
    DoubleMatrix f = new DoubleMatrix(new double[][]{{0,0,0,0,1,1,6,7,4,4,3,3},
                                                     {1,2,4,5,6,7,4,4,6,6,2,7,},
                                                     {2,3,5,3,7,2,5,5,0,1,7,5}});
    /*Translation translation = new Translation(300,500,0);
    Rotation rotation = new Rotation(Math.PI/6,0,Math.PI/6);*/

    //TRANSLATION MATRIX:
    //coloca vetores na vertical e multiplica matriz de translaçao por eles
    //matriz identidade:
      //cada multiiplicaçao vai fazer 1*eixo multiplicado + 1*valor da ultima coluna da matriz
      //o 1* valor da uultima coluna é devido ao 1 de w

    meshes[0] = new Mesh(v, f);
    //cameras[0] = new Camera(-200,-200,0,0,Math.PI/4,0);
    //meshes[0] = new Mesh(v, t);
    //translation.mmul(meshes[0].getVertices());

    //TESTE DAS TRANSFORMAÇOES:
    double tx = 0;
    double ty = 100;
    double tz = 20;
    double rx = 0;
    double ry = Math.PI/48;
    double rz = 0;
    double px = 0;
    double py = 0;
    double pz = 40;
    Translation t = new Translation(tx, ty, tz);
    Rotation r = new Rotation(rx, ry, rz);
    Projection pr = new Projection(tx, ty, tz, rx, ry, rz);
    Perspective pe = new Perspective(px,py,pz);

    System.out.print("\nCubo original: \n");
    for(int i=0; i<v.columns; i++){ System.out.print("v" + (int)i + "<: " + (int)v.get(0,i) + " , " + (int)v.get(1,i) + " , " + (int)v.get(2,i) + " , " + (int)v.get(3,i) + ">\n"); }

    System.out.print("\nCubo translacionado: <" + (int)tx + "," + (int)ty + "," + (int)tz + ">\n");
    DoubleMatrix tv = t.mmul(v);
    for(int i=0; i<v.columns; i++){ System.out.print("v" + (int)i + "<: " + (int)tv.get(0,i) + " , " + (int)tv.get(1,i) + " , " + (int)tv.get(2,i) + " , " + (int)tv.get(3,i) + ">\n"); }

    System.out.print("\nCubo rotacionado: <" + (int)rx + "," + (int)ry + "," + (int)rz + ">\n");
    DoubleMatrix rv = r.mmul(v);
    for(int i=0; i<v.columns; i++){ System.out.print("v" + (int)i + "<: " + (int)rv.get(0,i) + " , " + (int)rv.get(1,i) + " , " + (int)rv.get(2,i) + " , " + (int)rv.get(3,i) + ">\n"); }

    System.out.print("\nCubo translacionado  <" + (int)tx + " , " + (int)ty + " , " + (int)tz + "> e rotacionado:  <" + (int)rx + " , " + (int)ry + " , " + (int)rz + ">\n");
    DoubleMatrix rtv = r.mmul(t.mmul(v));
    for(int i=0; i<v.columns; i++){ System.out.print("v" + (int)i + "<: " + (int)rtv.get(0,i) + " , " + (int)rtv.get(1,i) + " , " + (int)rtv.get(2,i) + " , " + (int)rtv.get(3,i) + ">\n"); }

    System.out.print("\nCubo projetado: <" + (int)tx + " , " + (int)ty + " , " + (int)tz + (int)rx + " , " + (int)ry + " , " + (int)rz + ">\n");
    DoubleMatrix pv = pr.mmul(v);
    for(int i=0; i<v.columns; i++){ System.out.print("v" + (int)i + "<: " + (int)pv.get(0,i) + " , " + (int)pv.get(1,i) + " , " + (int)pv.get(2,i) + " , " + (int)pv.get(3,i) + ">\n"); }

    System.out.print("\nCubo em projeçao em perspectiva: <" + (int)px + " , " + (int)py + " , " + (int)pz + ">\n");
    DoubleMatrix pev = pe.mmul(pr.mmul(v));
    for(int i=0; i<v.columns; i++){ System.out.print("v" + (int)i + "<: " + (int)pev.get(0,i) + " , " + (int)pev.get(1,i) + " , " + (int)pev.get(2,i) + " , " + pev.get(3,i) + ">\n"); }

    //VARIAVEISDE TESTE DE ROTAÇAO DE OBJETOS
    cx=0;
    cy=0;
    cz=0;
    cx= Math.PI/6;
  }
  public void testRefresh(){
  //cameras[0].setRotation(new DoubleMatrix(new double[][]{{cx},{cy},{cz}} ));
  cy+=  Math.PI/1040000;
  //cz+= cz+=
  /*  Rotation rotation = new Rotation(Math.PI/18000,0,Math.PI/18000);
    Translation translation = new Translation(0.005,0.005,0.0);
    meshes[0].setVertices(translation.mmul(rotation.mmul(meshes[0].getVertices())));*/
  }

  public void update(){
    Mesh[] nmeshes = new Mesh[meshes.length];
    for(int i=0; i<meshes.length; i++){
      Translation t = new Translation(0,100,20);
      Rotation r = new Rotation(cx,Math.PI/48,cz);

      Projection pr = new Projection(-80,80,100,cx,cy,cz);
      Perspective pe = new Perspective(0,0,30);
      //Translation pc = new Translation(-600,-500,0);      //translaçao para colocar os objetos no centro da tela

      //nmeshes[i] = new Mesh( pr.mmul(meshes[i].getVertices()), meshes[i].getFaces());
      //nmeshes[i] = new Mesh(r.mmul(t.mmul(meshes[i].getVertices())), meshes[i].getFaces());
      //nmeshes[i] = new Mesh( pe.mmul(r.mmul(t.mmul((meshes[i].getVertices())))), meshes[i].getFaces());
      nmeshes[i] = new Mesh(pe.mmul(pr.mmul(meshes[i].getVertices())), meshes[i].getFaces());      //comente essa linha para psicodelizar (e se conseguir, arrume)
    }
    r.update(nmeshes);
  }

  public Engine3D(){
    running = true;

    meshes = new Mesh[1];
    //cameras = new Camera[1];
    test();

    f = new JFrame();
    r = new Renderer(meshes);
    f.setContentPane(r);
    f.setSize(1200,1000);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
