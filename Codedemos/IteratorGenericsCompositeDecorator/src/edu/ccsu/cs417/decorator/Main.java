package edu.ccsu.cs417.decorator;

import edu.ccsu.cs417.composite.CPU;
import edu.ccsu.cs417.composite.ComputerComponent;
import edu.ccsu.cs417.composite.ComputerComposite;
import edu.ccsu.cs417.composite.GraphicsCard;
import edu.ccsu.cs417.composite.Motherboard;
import edu.ccsu.cs417.composite.RAMChip;

/**
 * Demonstration of decorator pattern
 */
public class Main {
  public static void main(String[] args) {
    // Construct part-whole hierarchy
    ComputerComposite graphicsCard = new GraphicsCard(300, "gc1");
    graphicsCard.add(new CPU(4));
    graphicsCard.add(new RAMChip(200));
    ComputerComposite motherboard = new Motherboard(600, 1.5);
    // Add decorator for price to be adjusted based on market
    motherboard.add(new MarketPriceAdjusterDecorator(graphicsCard));
    motherboard.add(new CPU(400));
    motherboard.add(new CPU(400));
    // 10% discount on these all motherboard RAMChips
    motherboard.add(new SaleDecorator(.9, new RAMChip(200)));
    ComputerComponent chip = new RAMChip(200);
    chip = new SaleDecorator(.9, chip);
    // Extra 50% off for this chip, so add a decorator on the decorator
    chip = new SaleDecorator(.5, chip);
    motherboard.add(chip);
    System.out.println(motherboard.toString());
  }
}
