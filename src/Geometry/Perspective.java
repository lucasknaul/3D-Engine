import org.jblas.DoubleMatrix;

class Perspective extends DoubleMatrix{         
  Perspective(double fov, double depth, double aspect, double nearDist, double farDist){    //X : X do vertice original         x: X a ser adicionado
    super(new double[][]{{1/Math.tan(fov/2)*aspect, 0,                 0,                         0},
                         {0,                        1/Math.tan(fov/2), 0,                         0},
                         {0,                        0,                 farDist/depth,             1},
                         {0,                        0,                 (-farDist*nearDist)/depth,   0}});     //F(W) = W/z
    }
}
