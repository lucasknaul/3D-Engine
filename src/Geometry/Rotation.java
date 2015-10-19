import org.jblas.DoubleMatrix;

class Rotation extends DoubleMatrix{
  private double x,y,z;
  private static double[][] generateArray(double x, double y, double z){
    double sinx = Math.sin(x);
    double cosx = Math.cos(x);
    double siny = Math.sin(x);
    double cosy = Math.cos(y);
    double sinz = Math.sin(z);
    double cosz = Math.cos(z);

    return new double[][]{
          {cosy*cosz, cosz*sinx*siny-cosx*sinz, cosx*cosz*siny+sinx*sinz, 0},
          {cosy*sinz, cosx*cosz+sinx*siny*sinz, -cosz*sinx+cosx*siny*sinz, 0},
          {-siny,cosy*sinx,cosx*cosy,0},
          {0,0,0, 1}};
  }
  Rotation(double x,double y,double z){
    super(generateArray(x,y,z));
    this.x=x;
    this.y=y;
    this.z=z;
  }
  /*
  public void setX(double x){this.put(0,3,x);}
  public void setY(double y){this.put(1,3,y);}
  public void setZ(double z){this.put(2,3,z);}
*/
  public double getX(){return this.x;}
  public double getY(){return this.y;}
  public double getZ(){return this.z;}
}
