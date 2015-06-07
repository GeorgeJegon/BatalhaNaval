package utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

  @SuppressWarnings("rawtypes")
  public static void populateWeapons(ArrayList<Weapon> listWeapons, Class c,
      int quantity) throws InstantiationException, IllegalAccessException {
    while (quantity-- > 0) {
      listWeapons.add((Weapon) createInstance(c));
    }
  }

  @SuppressWarnings("rawtypes")
  public static Object createInstance(Class c) throws InstantiationException,
      IllegalAccessException {
    return c.newInstance();
  }

  public static GridBagLayout createGridBagLayout(int width, int height) {
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[width + 1];
    gridBagLayout.rowHeights = new int[height + 1];
    gridBagLayout.columnWeights = new double[width + 1];
    gridBagLayout.rowWeights = new double[height + 1];
    gridBagLayout.columnWeights[width] = Double.MIN_VALUE;
    gridBagLayout.rowWeights[height] = Double.MIN_VALUE;

    return gridBagLayout;
  }

  public static GridBagConstraints createGridBagConstraints(int gridx, int gridy) {
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.gridx = gridx;
    gridBagConstraints.gridy = gridy;

    return gridBagConstraints;
  }
}
