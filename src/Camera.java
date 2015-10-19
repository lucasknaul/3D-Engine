import org.jblas.DoubleMatrix;

class Camera{
  DoubleMatrix position;
  DoubleMatrix rotation;
  /*Translation linearProjection;
  Rotation angularProjection;*/

  Camera(double x, double y, double z, double xang, double yang, double zang){
    position = new DoubleMatrix(new double[][]{{x},{y},{z}});
    rotation = new DoubleMatrix(new double[][]{{xang},{yang},{zang}});
    /*linearProjection = new Translation(-x,-y,-z);
    angularProjection = new Rotation(-xang,-yang,-zang);*/
  }

  Camera(DoubleMatrix position, DoubleMatrix rotation){
    this.position = position;
    this.rotation = rotation;
  }

    public DoubleMatrix getPosition(){return position;}
    public DoubleMatrix getRotation(){return rotation;}

  public Translation linearProjection(){
    return new Translation(-position.get(0),-position.get(1),-position.get(2));
  }
    public Rotation angularProjection(){
      return new Rotation(-rotation.get(0),-rotation.get(1),-rotation.get(2));
    }

    public void setPosition(DoubleMatrix position){
      this.position = position;
      //this.linearProjection = new Translation(-position.get(0),-position.get(1),-position.get(2));
    }
    public void setRotation(DoubleMatrix rotation){
      this.rotation = rotation;
      //this.angularProjection = new Rotation(rotation.get(0), rotation.get(1), rotation.get(2));
    }

    public Mesh[] project(Mesh[] meshes){
      Mesh[] nMeshes= new Mesh[meshes.length];
      for (int i=0; i<meshes.length; i++){
        /*System.out.print("Initial : Angular Projection: " + angularProjection
                        +"Linear Projection: " + linearProjection + "\n");*/

        //dAng = diferença de angulo entre camera e objeto;
        //dPos = diferença de posiçao entre camera e objeto;
        //vertice.pos -= camera.pos;
        //rotacionar o objeto num angulo dAng
        //mover o objeto paraposição dPos

        //TUDO BULLSHIT: NOVO ALGORITMO:
        //CALCULAR USANDO POSIÇAO RELATIVA BASEADA NO CRUZAMENTO ENTRE UM PLANO ORTOGONAL
        //AO RAYTRACE DA CAMERA E O VERTICE.
        //

        DoubleMatrix v = meshes[i].getVertices();
        DoubleMatrix vf = angularProjection().mmul(linearProjection().mmul(v));
        nMeshes[i] = new Mesh(vf, meshes[i].getFaces());
        //System.out.print("projecting vertice(" + v.get(0) + "," + v.get(1) + "," + v.get(2) +
                  //    ") to (" + vf.get(0) + "," + vf.get(1) + "," + vf.get(2) + ").\n");

        /*System.out.print("Final: Angular Projection: " + angularProjection
                          +"Linear Projection: " + linearProjection + "\n");*/
      }
      return nMeshes;
    }
}
