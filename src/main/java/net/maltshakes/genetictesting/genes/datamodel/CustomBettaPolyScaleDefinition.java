package net.maltshakes.genetictesting.genes.datamodel;

import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

public class CustomBettaPolyScaleDefinition extends GeneFormatting.DisplayEntry {
  public int[] firstPair; // Genes 32, 33 (Values: 2=+1, 3=-1)
  public int[] secondPair; // Genes 34, 35 (Value: 2=-1)

  public CustomBettaPolyScaleDefinition(
      String label, List<String> mappings, int[] firstPair, int[] secondPair) {
    super(label, mappings, GeneType.POLYMORPHIC, -1);
    this.firstPair = firstPair;
    this.secondPair = secondPair;
  }
}
