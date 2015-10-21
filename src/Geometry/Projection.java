import org.jblas.DoubleMatrix;

class Projection extends DoubleMatrix{
  public static double[][] getProjection(double x, double y, double z,
                                            double xa, double ya, double za){

    double sx = Math.sin(xa);
    double cx = Math.cos(xa);
    double sy = Math.sin(ya);
    double cy = Math.cos(ya);
    double sz = Math.sin(za);
    double cz = Math.cos(za);

    double r11 = cy*cz;
    double r12 = cz*sx*sy-cx*sz;
    double r13 = cx*cz*sy+sx*sz;
    double r21 = cy*sz;
    double r22 = cx*cz+sx*sy*sz;
    double r23 = -cz*sx+cx*sy*sz;
    double r31 = -sy;
    double r32 = cy*sx;
    double r33 = cx*cy;

    return new double[][]{{r11, r12, r13, x*r11+y*r12+z*r13},
                          {r21, r22, r23, x*r21+y*r22+z*r23},
                          {r31, r32, r33, x*r31+y*r32+z*r33},
                          {0,   0,   0,   1                }};
  }
  /*
  rotação: {{cosy*cosz, cosz*sinx*siny-cosx*sinz, cosx*cosz*siny+sinx*sinz,  0},
            {cosy*sinz, cosx*cosz+sinx*siny*sinz, -cosz*sinx+cosx*siny*sinz, 0},
            {-siny,     cosy*sinx,                cosx*cosy,                 0},
            {0,         0,                        0,                         1}};

  translação: {{1, 0, 0, a},
               {0, 1, 0, b},
               {0, 0, 1, c},
               {0, 0, 0, 1}}

              {{cosy*cosz, cosx*cosz+sinx*siny*sinz, cosx*cosz*siny+sinx*sinz,  a*(cosy*cosz)+b*(cosz*sinx*siny-cosx*sinz)+c*(cosx*cosz*siny+sinx*sinz)},
               {cosy*sinz, cosx*cosz+sinx*siny*sinz, -cosz*sinx+cosx*siny*sinz, a*(cosy*sinz)+b*(cosx*cosz+sinx*siny*sinz)+c*(-cosz*sinx+cosx*siny*sinz)},
               {-siny,     cosy*sinx,                cosx*cosy,                 a*(-siny)+b*(cosy*sinx)+c*cosx*cosy}

  */
  Projection(double x, double y, double z, double xa, double ya, double za){
    super(getProjection(x,y,z,xa,ya,za));
  }
}
