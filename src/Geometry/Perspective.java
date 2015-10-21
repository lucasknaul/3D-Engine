import org.jblas.DoubleMatrix;

class Perspective extends DoubleMatrix{         //recebe o pov, ponto de observaçao em relaçao ao plano de projeçao
  Perspective(double x, double y, double z){    //X : X do vertice original         x: X a ser adicionado
    super(new double[][]{{1, 0, -x/z, 0},       //F(X) = X - Z(x/z)
                         {0, 1, -y/z, 0},       //F(Y) = Y - Z(y/z)
                         {0, 0, 1,    0},       //F(Z) = Z
                         {0, 0, 1/z,  0}});     //F(W) = W/z
  }
}
