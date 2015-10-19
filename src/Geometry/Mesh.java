import org.jblas.DoubleMatrix;

class Mesh{
  DoubleMatrix vertices;
  DoubleMatrix faces;

  Mesh(DoubleMatrix vertices, DoubleMatrix faces){
    this.vertices=vertices;
    this.faces=faces;
  }

  public DoubleMatrix getFaces(){
    return faces;
  }
  public DoubleMatrix getVertices(){
    return vertices;
  }
  public void setVertices(DoubleMatrix v){
    this.vertices = v;
  }
}
