import org.jblas.DoubleMatrix;
import java.util.ArrayList;

public class Geometry {

  protected Mesh[] meshes;  //vetor de malhas de vértices no sistema
  protected int meshCount;
  protected GeomObject world;     //GeomObj base ao qual os outros sao adicionados
  
  Geometry(){
    meshes = new Mesh[10];
    world = new GeomObject();
    meshCount=0;
  }
  //os meshs sao adicionados ao vetor de meshes e so existem nele,
  //instancias de objetos sao apenas matrizes de transformaçao aplicadas em tempo real ao mesh
  public void addMesh(Mesh mesh){
    meshes[meshCount] = mesh;
    meshCount++;
  }
  //passando o indice do mesh modelo, o vetor de translaçao e o de rotaçao, criar um objeto, adicioná-lo ao mundo e retorná-lo
  public GeomObject createObject(int m, Translation t, Rotation r){
    GeomObject go = new GeomObject(meshes[m],t,r);
    world.addChild(go);
    return go;
  }
  //cria um objeto passando o indice do mesh modelo e os parametros das variaveis de translaçao e rotaçao separadamente
  public GeomObject createObject(int m, double x, double y, double z, double xa, double ya, double za){
    GeomObject go = new GeomObject(meshes[m], new Translation(x,y,z), new Rotation(xa,ya,za));
    world.addChild(go);
    return go;
  }
  //retorna um vetor de todos os meshes transformados pela matriz p
  public Mesh[] getProjection(DoubleMatrix p){
    ArrayList<Mesh> m = world.getMeshes(p);
    return  (Mesh[])m.toArray(new Mesh[1]);
  }
}
