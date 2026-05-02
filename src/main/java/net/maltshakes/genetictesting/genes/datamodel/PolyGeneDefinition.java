package net.maltshakes.genetictesting.genes.datamodel;

import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

public class PolyGeneDefinition extends GeneFormatting.DisplayEntry {
    private final int rangeStart;
    private final int rangeEnd;
    private final int maxAlleleValue;

    // Default binary constructor (2 values)
    public PolyGeneDefinition(String label, List<String> mappings, int start, int end) {
        this(label, mappings, start, end, 2);
    }

    // Non-binary constructor (allows higher ranges)
    public PolyGeneDefinition(
            String label, List<String> mappings, int start, int end, int maxAlleleValue) {
        super(label, mappings, GeneType.POLYMORPHIC, -1);
        this.rangeStart = start;
        this.rangeEnd = end;
        this.maxAlleleValue = maxAlleleValue;
    }

    public int getRangeStart() {
        return rangeStart;
    }

    public int getRangeEnd() {
        return rangeEnd;
    }

    public int getMaxAlleleValue() {
        return maxAlleleValue;
    }
}
