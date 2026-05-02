package net.maltshakes.genetesting.genes.datamodel;

import java.util.List;

import net.maltshakes.genetesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetesting.genes.format.GeneFormatting;

public class PolyScaleDefinition extends GeneFormatting.DisplayEntry {
    public int[] negIndices;
    public int[] posIndices;

    public PolyScaleDefinition(String label, List<String> mappings, int[] negIndices, int[] posIndices) {
        super(label, mappings, GeneType.POLYMORPHIC, -1);
        this.negIndices = negIndices != null ? negIndices : new int[0];
        this.posIndices = posIndices != null ? posIndices : new int[0];
    }

}
