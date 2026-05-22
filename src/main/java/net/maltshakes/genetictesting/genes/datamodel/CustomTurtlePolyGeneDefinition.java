package net.maltshakes.genetictesting.genes.datamodel;

import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

public class CustomTurtlePolyGeneDefinition extends GeneFormatting.DisplayEntry {
    private final int firstRangeStart;
    private final int firstRangeEnd;
    private final int secondRangeStart;
    private final int secondRangeEnd;
    private final int maxAlleleValue;

    public CustomTurtlePolyGeneDefinition(
            String label,
            List<String> mappings,
            int firstStart,
            int firstEnd,
            int secondStart,
            int secondEnd,
            int maxAlleleValue) {
        super(label, mappings, GeneType.POLYMORPHIC, -1);
        this.firstRangeStart = firstStart;
        this.firstRangeEnd = firstEnd;
        this.secondRangeStart = secondStart;
        this.secondRangeEnd = secondEnd;
        this.maxAlleleValue = maxAlleleValue;
    }

    public int getFirstRangeStart() {
        return firstRangeStart;
    }

    public int getFirstRangeEnd() {
        return firstRangeEnd;
    }

    public int getSecondRangeStart() {
        return secondRangeStart;
    }

    public int getSecondRangeEnd() {
        return secondRangeEnd;
    }

    public int getMaxAlleleValue() {
        return maxAlleleValue;
    }
}
