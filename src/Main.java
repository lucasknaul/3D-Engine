/*
View:
  Screen
Model:
  3D Engine:
    Models
      Mesh
      Textura
    Objects
      Model
      Translation
      Rotation
Control:
  InputManager


*/
public class Main {
  public static void main(String[] fdfd) {
    Engine3D engine = new Engine3D();
      engine.testInit();
    for(int i=0; i<500000; i=i){
      engine.update();
      engine.testRefresh();
    }
  }
}
