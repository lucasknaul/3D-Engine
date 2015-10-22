import javax.swing.*;
import java.awt.*;
import org.jblas.DoubleMatrix;
import java.util.ArrayList;
import java.util.Random;

public class Engine3D {

  JFrame f;

  Geometry g;
  Renderer r;               //Jpanel responsável por desenhar na tela

  double debugX;

  public void testInit(){   //iniciação em teste
    //malha de vértices:
    //          Cada coluna é um vértice
    //          Cada linha é um eixo em coordenadas homogeneas (x,y,z,w)
    //              (semelhante a cartesiana com o w como um escalar)
    //     exemplo:
    //       v[1][3] corresponde a segunda linha (eixo y) da segunda coluna (segundo vértice)

    DoubleMatrix v = new DoubleMatrix(new double[][]{{0,50,50,0,0,0,50,50},
                                                     {0,0,50,50,0,50,0,50},
                                                     {0,0,0,0,50,50,50,50},
                                                     {1,1,1,1,1,1,1,1}});
    //faces:
    //          Faces são triângulos formados por três dos vértices acima
    //          Detalha como os vértices se conectam
    //          Cada coluna é um triângulo
    //          Cada linha é o endereço de um vértice na matriz de vértices
    //      exemplo:
    //          f[2][5] corresponde a terceira linha(3º vértice) da sexta coluna(6 face);
    //            O número retornado seria dois, o índice do vértice 50,50,0,1
    //            o terceiro vértice da sexta face é o vértice 50,50,0,1

    DoubleMatrix f = new DoubleMatrix(new double[][]{{0,0,0,0,1,1,6,7,4,4,3,3},
                                                     {1,2,4,5,6,7,4,4,6,6,2,7,},
                                                     {2,3,5,3,7,2,5,5,0,1,7,5}});
    //cria um mesh de testes usando os vetores e faces criados
    g.addMesh(new Mesh(v, f));

    //ARRUMAR ANINHAMENTO DE GEOMOBJECTS
    //PROBLEMA: CADA GEOMoBJET SO PODE SER FILHO DE UM OUTRO

    //mude parâmetros do random para alterar os valores dos objetos criados
    for(int i=0; i<random(20, 10); i++){
      g.createObject(0, random(500,-150), random(500,-150), random(500,-150), random(Math.PI*2, 0), random(Math.PI*2, 0), random(Math.PI*2, 0));
    }
  }

  private double random(double x, double a){
      Random r = new Random();
      return r.nextDouble()*x + a;
  }

  public void testRefresh(){    //funçao de atualizaçao teste
    debugX+=  Math.PI/1040000;      //rotaciona o objeto pelo eixo y
  }

  public void update(){
    Projection pr = new Projection(0,100,1000,0,debugX,0);          //matriz de projeção. Projeta o espaço de outro angulo e posiçao. Posição e angulo da camera. Camera girando em torno do eixo Y
    Perspective pe = new Perspective(90, 5000000, 1, 10, 1000);     //matriz de perspectiva. manipula os vértices aumentando o eixo w a partir do z e assim escala objetos de acordo com a distancia do "tela"

    r.update(g.getProjection(pe.mmul(pr)));            //envia para renderer a projeçao dos objetos da geometria transformados pela matriz de perspectiva aplicada à de projeção
  } 

  public Engine3D(){
    //inicialização de variáveis
    debugX = 0;

    g = new Geometry();
    f = new JFrame();
    r = new Renderer(g.getProjection(new DoubleMatrix(new double[][]{{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}})));
    f.setContentPane(r);
    f.setSize(1200,1200);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}
