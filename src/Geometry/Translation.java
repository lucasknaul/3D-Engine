import org.jblas.DoubleMatrix;

class Translation extends DoubleMatrix{
  Translation(double x,double y,double z){
    super(new double[][]{{1,0,0,x},
                  {0,1,0,y},
                  {0,0,1,z},
                  {0,0,0, 1}});
  }
  public void setX(double x){this.put(0,3,x);}
  public void setY(double y){this.put(1,3,y);}
  public void setZ(double z){this.put(2,3,z);}
  public void set(double x, double y, double z){
    this.put(0,3,x);
    this.put(1,3,y);
    this.put(2,3,z);
  }

  public double getX(){return this.get(0,3);}
  public double getY(){return this.get(1,3);}
  public double getZ(){return this.get(2,3);}
}
