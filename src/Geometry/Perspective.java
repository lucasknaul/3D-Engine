import org.jblas.DoubleMatrix;


class Perspective extends DoubleMatrix{         //recebe o pov, ponto de observaçao em relaçao ao plano de projeçao
  Perspective(double fov, double depth, double aspect, double nearDist, double farDist){    //X : X do vertice original         x: X a ser adicionado
    super(new double[][]{{1/Math.tan(fov/2)*aspect, 0,                 0,                         0},
                         {0,                        1/Math.tan(fov/2), 0,                         0},
                         {0,                        0,                 farDist/depth,             1},
                         {0,                        0,                 (-farDist*nearDist)/depth,   0}});     //F(W) = W/z
    }
}

/*class Perspective extends DoubleMatrix{         //recebe o pov, ponto de observaçao em relaçao ao plano de projeçao
  Perspective(double x, double y, double z){    //X : X do vertice original         x: X a ser adicionado
    super(new double[][]{{1, 0, -x/z, 0},       //F(X) = X - Z(x/z)
                         {0, 1, -y/z, 0},       //F(Y) = Y - Z(y/z)
                         {0, 0, 1,    0},       //F(Z) = Z
                         {0, 0, 1/z,  0}});     //F(W) = W/z
  }
}*/
