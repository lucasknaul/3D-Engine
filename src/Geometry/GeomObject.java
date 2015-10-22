import org.jblas.DoubleMatrix;
import java.util.ArrayList;

  //Guarda um ponteiro para o mesh e informaçoes de posicionamento deste
  //Nao guarda informaçoes dos vértices individuais
  //informaçoes do vertice sao calculadas quando requisitadas
  //podem ser aninhados infinitamente para divisões do espaço

class GeomObject{
  public static int MAX_MESHES = 10;
  Mesh m;
  Translation t;
  Rotation r;
  ArrayList <GeomObject> children;

  GeomObject(Mesh m, Translation t, Rotation r){
    children = new ArrayList<GeomObject>();
    this.m = m;
    this.t = t;
    this.r = r;
  }

  GeomObject(Translation t, Rotation r){
    children = new ArrayList<GeomObject>();
    this.t = t;
    this.r = r;
  }
  GeomObject(){
    children = new ArrayList<GeomObject>();
    this.t =new Translation(0,0,0);
    this.r = new Rotation(0,0,0);
  }

      //é importante que o transform seja calculado a medida que se adentra a cadeia de childs e chegue no mesh já pronto
      //usar .mmul() diretamente faria com que a matriz mais interna fosse multiplicada primeiro, que seria a matriz de vértices
      //como a largura da matriz de vértices é muito maior que a largura das transformações e multiplicação de matrizes é comutativa,
      //resolvi resolver as transformações no caminho até os vertices e entregar apenas uma 4X4 matriz pronta a ser calculada

  public ArrayList<Mesh> getMeshes(DoubleMatrix pt){
    DoubleMatrix myTransform = pt.mmul(r.mmul(t));
    ArrayList <Mesh> meshes = new ArrayList<Mesh>();
    if(m != null)                                     //Podem ser só uma âncora no espaço para outros objetos, nao precisam de meshs.
      meshes.add(m.getTransformed(myTransform));          //se tiver um mesh, pega a versao transformada do mesh com posiçao e rotaçao ajustadas.
    for(int i=0; i<children.size(); i++){
      meshes.addAll(children.get(i).getMeshes(myTransform));  //requisita os meshes dos filhos e os adiciona a lista de vertices a ser retornada.
    }
    return meshes;
  }

  public DoubleMatrix getVertices(){
    return r.mmul(t.mmul(m.getVertices()));
  }
  public DoubleMatrix getFaces(){
    return m.getFaces();
  }
  public void addChild(GeomObject c){
    children.add(c);
  }
  public GeomObject getChild(int i){
    if(i<children.size())
      return children.get(i);
    else{
      System.out.print("GeomObject: Nao existe componente filho de índice " + i + ".\n");
      return null;
    }

  }
}
