package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import components.weapons.Weapon;

public class Utils {
  public static Properties getProp() throws IOException {
    Properties prop = new Properties();
    FileInputStream file = new FileInputStream("./src/config.properties");
    prop.load(file);
    return prop;
  }

  public static void populateWeapons(ArrayList<Weapon> listWeapons, Class c,
      int quantity) throws InstantiationException, IllegalAccessException {
    while (quantity-- > 0) {
      listWeapons.add((Weapon) createInstance(c));
    }
  }

  public static Object createInstance(Class c) throws InstantiationException,
      IllegalAccessException {
    return c.newInstance();
  }
}
