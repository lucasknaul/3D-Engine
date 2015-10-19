
public class Main {
  public static void main(String[] fdfd) {
    Engine3D engine = new Engine3D();

    for(int i=0; i<50000; i++){
      engine.testRefresh();
      engine.update();
    }
  }
}
