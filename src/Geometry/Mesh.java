import org.jblas.DoubleMatrix;

//Guarda as informações geométricas do objeto representado
//Embora possa ser redefinido com setVertices() e setFaces(), nao pode ser modificado
//É carregado diretamente de um arquivo e suas coordenadas nao mudam
//Serve como modelo para os 3DObject que representam instancias do mesh

class Mesh{
  DoubleMatrix vertices;
  DoubleMatrix faces;

  Mesh(DoubleMatrix vertices, DoubleMatrix faces){
    this.vertices=vertices;
    this.faces=faces;
  }
  public Mesh getTransformed(DoubleMatrix t){
    return new Mesh(t.mmul(vertices), faces);
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
