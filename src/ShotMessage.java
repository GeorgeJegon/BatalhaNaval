import java.io.Serializable;

import components.shot.Shot;

public class ShotMessage implements Serializable {
  private static final long serialVersionUID = 1527291121207777501L;
  private Shot shot;
  
  public ShotMessage(Shot shot){
    this.shot = shot;
  }

  public Shot getShot() {
    return this.shot;
  }
}
