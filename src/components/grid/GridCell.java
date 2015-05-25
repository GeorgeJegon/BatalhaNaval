package components.grid;

import components.weapons.Weapon;

public class GridCell {
  private int status;
  private Weapon content;
  
   public GridCell () {
    this.status = 0;
  }
  
  public boolean isEmpty() {
    return this.content == null;
  }
    
  public int getStatus() {
    return this.status;
  }
  
  public Weapon getContent() {
	return content;
  }

  public void setContent(Weapon content) {
	this.content = content;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
