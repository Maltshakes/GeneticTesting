package net.maltshakes.genetictesting.genes.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import mokiyoki.enhancedanimals.entity.util.Colouration;
import net.maltshakes.genetictesting.genes.datamodel.BookEntry;
import net.maltshakes.genetictesting.genes.datamodel.CustomBettaPolyScaleDefinition;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.datamodel.PolyGeneDefinition;
import net.maltshakes.genetictesting.genes.datamodel.PolyScaleDefinition;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public abstract class GeneFormatting {

  private final List<DisplayEntry> displayEntries = new ArrayList<>();
  // Default colour of the GeneBook item
  private int bookColour = 0x99452E; // Default Brown

  public static class DisplayEntry {
    protected String label;
    protected List<String> mappings;
    GeneType type;
    int dataIndex = -1; // -1 for no data
    public Map<Integer, Component> valueMapping;
    BiPredicate<Integer, Integer> visibilityCondition = (v1, v2) -> true;
    boolean isSexLinked = false;

    // Constructor for standard gene pairs
    public DisplayEntry(String label, List<String> mappings, GeneType type, int dataIndex) {
      this.label = label;
      this.mappings = mappings;
      this.type = type;
      this.dataIndex = dataIndex;
    }

    // Constructor for static entries (headers, comments)
    DisplayEntry(String label, GeneType type) {
      this.label = label;
      this.type = type;
      this.mappings = new ArrayList<>();
    }

    // Constructor for conditional comments
    DisplayEntry(String label, int dataIndex, BiPredicate<Integer, Integer> condition) {
      this.label = "[C]" + label;
      this.type = GeneType.OTHER;
      this.dataIndex = dataIndex;
      this.visibilityCondition = condition;
    }

    // Constructor for legacy/conditional gene pairs
    DisplayEntry(
        String label,
        List<String> mappings,
        GeneType type,
        int dataIndex,
        BiPredicate<Integer, Integer> condition) {
      this.label = label;
      this.mappings = mappings;
      this.type = type;
      this.dataIndex = dataIndex;
      this.visibilityCondition = condition;
    }

    // Constructor for SGenes
    DisplayEntry(GeneType type, String label, int dataIndex, Map<Integer, Component> mapping) {
      this.type = type;
      this.label = label;
      this.dataIndex = dataIndex;
      this.valueMapping = mapping;
      this.isSexLinked = true;
    }

    public DisplayEntry setSexLinked(boolean val) {
      this.isSexLinked = val;
      return this;
    }
  }

  /**
   * Registers a mapping for a specific gene pair using a single descriptive string. Used for genes
   * where only one label is needed to represent if the gene in present or not.
   *
   * @param label The display name of the gene.
   * @param value The single label to display when the gene condition is met.
   * @param type The {@link GeneType} (BINARY or BINARY_INVERTED).
   * @param dataIndex The index representing the specific pair of genes in the array.
   */
  public void addPairMapping(String label, String value, GeneType type, int dataIndex) {
    displayEntries.add(new DisplayEntry(label, List.of(value), type, dataIndex));
  }

  /**
   * Registers a mapping for a specific gene pair at the given data index. This creates a {@link
   * DisplayEntry} which maps two individual alleles into visual labels. The {@code dataIndex}
   * determines which pair in the gene array is being targeted.
   *
   * @param label The display name of the gene.
   * @param values A list of descriptive strings used to map raw gene values.
   * @param type The {@link GeneType} determining how values are processed.
   * @param dataIndex The index representing the specific pair of genes in the array.
   */
  public void addPairMapping(String label, List<String> values, GeneType type, int dataIndex) {
    displayEntries.add(new DisplayEntry(label, values, type, dataIndex));
  }

  /**
   * Registers a mapping for a specific sex-linked pair using a single descriptive string. Used for
   * genes where only one label is needed to represent if the gene in present or not.
   *
   * @param label The display name of the gene.
   * @param value The single label to display when the gene condition is met.
   * @param type The {@link GeneType} (BINARY or BINARY_INVERTED).
   * @param dataIndex The index representing the specific pair of genes in the array.
   */
  public void addSexLinkedPairMapping(String label, String value, GeneType type, int dataIndex) {
    displayEntries.add(
        new DisplayEntry(label, List.of(value), type, dataIndex).setSexLinked(true));
  }

  /**
   * Registers a mapping for a specific sex-linked gene pair at the given data index. This creates a
   * {@link DisplayEntry} which maps two individual alleles into visual labels. The {@code
   * dataIndex} determines which pair in the gene array is being targeted.
   *
   * @param label The display name of the gene.
   * @param values A list of descriptive strings used to map raw gene values.
   * @param type The {@link GeneType} determining how values are processed.
   * @param dataIndex The index representing the specific pair of genes in the array.
   */
  public void addSexLinkedPairMapping(
      String label, List<String> values, GeneType type, int dataIndex) {
    displayEntries.add(new DisplayEntry(label, values, type, dataIndex).setSexLinked(true));
  }

  /**
   * Registers a polymorphic gene mapping for a range of alleles where each allele is expected to be
   * a binary value (1 or 2).
   *
   * <p>This is a convenience method that defaults the maximum allele value to 2. The sum of the
   * range is used to calculate a percentage between the minimum possible sum (all 1s) and maximum
   * possible sum (all 2s) to select a mapping string.
   *
   * @param label The display name for this gene entry in the book.
   * @param mappings A list of descriptive strings (["Low", "Medium", "High"]).
   * @param start The starting index in the genomic array.
   * @param end The ending index (inclusive) in the genomic array.
   */
  public void addPolyRangeMapping(String label, List<String> mappings, int start, int end) {
    displayEntries.add(new PolyGeneDefinition(label, mappings, start, end));
  }

  /**
   * Registers a polymorphic gene mapping for a range of alleles with a custom maximum value per
   * allele (for example, 1 to 10).
   *
   * <p>The total sum of the alleles in the range {@code [start, end]} is calculated. The result is
   * then normalized based on the {@code maxVal} to determine the appropriate index in the {@code
   * mappings} list.
   *
   * @param label The display name for this gene entry in the book.
   * @param mappings A list of descriptive strings to map the result to.
   * @param start The starting index in the genomic array.
   * @param end The ending index (inclusive) in the genomic array.
   * @param maxVal The maximum possible value an individual allele can contain.
   */
  public void addPolyRangeMapping(
      String label, List<String> mappings, int start, int end, int maxVal) {
    displayEntries.add(new PolyGeneDefinition(label, mappings, start, end, maxVal));
  }

  /**
   * Registers a polygenic scale mapping where some genes subtract and others add.
   *
   * @param label The display name
   * @param mappings An array of strings. Must be exactly (totalCount + 1) in size.
   * @param negIndices Indices that decrease the value (if value is 2).
   * @param posIndices Indices that increase the value (if value is 2).
   */
  public void addPolyScaleMapping(
      String label, List<String> mappings, int[] negIndices, int[] posIndices) {
    int totalGenes =
        (negIndices != null ? negIndices.length : 0) + (posIndices != null ? posIndices.length : 0);

    // Range is from -negCount to +posCount. Total possible outcomes = totalGenes + 1
    if (totalGenes == 0) {
      return;
    }
    if (mappings.size() != totalGenes + 1) {
      throw new IllegalArgumentException(
          String.format(
              "Mapping size mismatch for '%s': Expected %d labels, got %d. (Range: -%d to +%d)",
              label,
              totalGenes + 1,
              mappings.size(),
              negIndices != null ? negIndices.length : 0,
              posIndices != null ? posIndices.length : 0));
    }
    displayEntries.add(new PolyScaleDefinition(label, mappings, negIndices, posIndices));
  }

  /**
   * Registers a legacy gene that is hidden if both alleles are the specified wildtype value. If
   * either allele differs from the wildtype, the entry is displayed.
   *
   * @param label The display name for the legacy trait.
   * @param values The mapping labels for the gene values.
   * @param type The gene type for processing.
   * @param dataIndex The index of the legacy gene pair.
   * @param wildtypeValue The value that represents "Wildtype" (hidden state) for this gene.
   */
  public void addLegacyMapping(
      String label, List<String> values, GeneType type, int dataIndex, int wildtypeValue) {
    displayEntries.add(
        new DisplayEntry(
            label,
            values,
            type,
            dataIndex,
            (v1, v2) -> v1 != wildtypeValue || v2 != wildtypeValue));
  }

  /**
   * Registers a new custom genetic scale definition with a specific label and set of mappings.
   *
   * @param label The display name or identifier for this scale.
   * @param mappings A list of 7 strings representing the possible outcomes of the scale.
   * @param firstPair An array of gene indices used for the primary score calculation.
   * @param secondPair An array of gene indices used for the secondary score calculation.
   * @throws IllegalArgumentException if {@code mappings} does not contain exactly 7 elements.
   */
  public void addCustomBettaPolyScaleMapping(
      String label, List<String> mappings, int[] firstPair, int[] secondPair) {
    // Range -4 to +2 = 7 possible labels
    if (mappings.size() != 7) {
      throw new IllegalArgumentException(
          "Custom mapping for " + label + " requires exactly 7 labels.");
    }
    displayEntries.add(new CustomBettaPolyScaleDefinition(label, mappings, firstPair, secondPair));
  }

  /**
   * Adds a category style header to the geneBook.
   *
   * @param title the String to be displayed as a header
   */
  public void addCategory(String title) {
    displayEntries.add(new DisplayEntry("[H]" + title, GeneType.OTHER));
  }

  /**
   * Adds a comment (in italics) to the geneBook.
   *
   * @param title the String to be displayed as a comment
   */
  public void addComment(String title) {
    displayEntries.add(new DisplayEntry("[C]" + title, GeneType.OTHER));
  }

  /**
   * Adds a comment that only appears if a specific gene pair meets a condition.
   *
   * @param text The comment text.
   * @param dataIndex The gene index to check.
   * @param condition A condition taking (val1, val2). Example: (v1, v2) -> v1 == 2 and v2 == 2
   */
  public void addConditionalComment(
      String text, int dataIndex, BiPredicate<Integer, Integer> condition) {
    displayEntries.add(new DisplayEntry(text, dataIndex, condition));
  }

  /** Adds a line break to the geneBook. */
  public void addLineBreak() {
    displayEntries.add(new DisplayEntry("---", GeneType.LINE_BREAK));
  }

  /**
   * Adds a page break to the geneBook. This is handled internally as a different column, not as a
   * new page as Minecraft books understand it.
   */
  public void addPageBreak() {
    displayEntries.add(new DisplayEntry("PAGE_BREAK", GeneType.PAGE_BREAK));
  }

  /**
   * Returns the registered pair labels.
   *
   * @return Array of the pair label strings
   */
  public String[] getPairLabels() {
    return displayEntries.toArray(new String[0]);
  }

  /**
   * Sets the colour of the book.
   *
   * @param colour the colour as a hex value
   */
  public void setBookColour(int colour) {
    this.bookColour = colour;
  }

  /**
   * Getter for the book colour.
   *
   * @return an hex int for the book colour
   */
  public int getBookColour() {
    return this.bookColour;
  }

  /**
   * Processes a {@link PolyGeneDefinition} by summing the values of genes within a specified range
   * and mapping that sum to a descriptive category string. Calculates the relative position of the
   * sum between the minimum possible sum (all 1s) and the maximum possible sum (all 2s). This
   * percentage is then used to select an index from the provided mapping list.
   *
   * @param poly The definition containing the gene range
   * @param agenes The raw array of gene values (expected values are 1 or 2 for this function).
   * @return A {@link Component} containing the mapped category string (["Min", "Medium", "Max"]).
   */
  private Component processPolyGene(PolyGeneDefinition poly, int[] agenes) {
    int sum = 0;
    int start = poly.getRangeStart();
    int end = poly.getRangeEnd();
    int maxVal = poly.getMaxAlleleValue();

    // Go through genes in a range
    for (int i = start; i <= end; i++) {
      if (i < agenes.length) {
        sum += agenes[i];
      }
    }
    int rangeLength = (end - start) + 1;
    int minPossible = rangeLength;
    int maxPossible = rangeLength * maxVal;
    int listIndex;

    if (maxPossible <= minPossible) {
      // Prevents dividing by zero
      listIndex = 0;
    } else if (sum <= minPossible) {
      // Force absolute minimum
      listIndex = 0;
    } else if (sum >= maxPossible) {
      // Force absolute maximum
      listIndex = poly.mappings.size() - 1;
    } else {
      double progress = (double) (sum - minPossible) / (maxPossible - minPossible);
      listIndex = (int) Math.round(progress * (poly.mappings.size() - 1));
      listIndex = Math.max(0, Math.min(listIndex, poly.mappings.size() - 1));
    }
    return Component.literal(poly.mappings.get(listIndex));
  }

  /**
   * Processes a {@link PolyScaleDefinition} by calculating a weighted sum from a set of alleles and
   * mapping the result to a display component.
   *
   * <p>The logic evaluates specific indices in the {@code agenes} array:
   *
   * <ul>
   *   <li>Indices in {@code poly.negIndices} subtract 1 from the total if the value is 2.
   *   <li>Indices in {@code poly.posIndices} add 1 to the total if the value is 2.
   * </ul>
   *
   * <p>The final sum is normalized based on the minimum possible value (the negative of the number
   * of negative indices) to determine the index for the {@code poly.mappings} list.
   *
   * @param poly The scale definition containing index rules and string mappings.
   * @param agenes An array of genomic data values to be evaluated.
   * @return A {@link Component} containing the mapped string based on the calculated score.
   */
  private Component processPolyScale(PolyScaleDefinition poly, int[] agenes) {
    int totalSum = 0;

    // Subtract for negative indices (Value 2 = -1, Value 1 = 0)
    for (int idx : poly.negIndices) {
      if (idx < agenes.length && agenes[idx] == 2) {
        totalSum -= 1;
      }
    }

    // Add for positive indices (Value 2 = +1, Value 1 = 0)
    for (int idx : poly.posIndices) {
      if (idx < agenes.length && agenes[idx] == 2) {
        totalSum += 1;
      }
    }

    // Calculate the index for the List<String> mappings
    // The lowest possible sum is -negIndices.length
    // Offset the totalSum by the negative count to start the array at 0
    int minPossible = -poly.negIndices.length;
    int listIndex = totalSum - minPossible;

    // Safety clamp
    listIndex = Math.max(0, Math.min(listIndex, poly.mappings.size() - 1));

    return Component.literal(poly.mappings.get(listIndex));
  }

  /**
   * Processes a custom genetic scale to determine a display component based on specific gene
   * values.
   *
   * <p>The logic calculates a cumulative score from two pairs of gene indices:
   *
   * <ul>
   *   <li><b>First Pair:</b> Increases the sum if the gene value is 2, decreases if it is 3.
   *   <li><b>Second Pair:</b> Decreases the sum if the gene value is 2.
   * </ul>
   *
   * <p>The resulting sum (ranging from -4 to 2 based on logic, normalized to 0 by adding 4) is used
   * as an index to retrieve a string mapping, which is returned as a {@link Component}.
   *
   * @param poly The scale definition containing the gene index pairs and result mappings.
   * @param agenes The array of genetic data to evaluate.
   * @return A {@link Component} containing the literal string from the calculated mapping index.
   */
  private Component processCustomBettaPolyScale(CustomBettaPolyScaleDefinition poly, int[] agenes) {
    int totalSum = 0;

    // Process Genes 32 & 33 (Indices in firstPair)
    for (int idx : poly.firstPair) {
      if (idx < agenes.length) {
        if (agenes[idx] == 2) totalSum += 1; // "higher"
        else if (agenes[idx] == 3) totalSum -= 1; // "lower"
      }
    }
    // Process Genes 34 & 35 (Indices in secondPair)
    for (int idx : poly.secondPair) {
      if (idx < agenes.length && agenes[idx] == 2) {
        totalSum -= 1; // "lower"
      }
    }
    // Normalization:
    // Min is -4. To map -4 to index 0, subtract the min (totalSum - (-4))
    int listIndex = totalSum + 4;

    // Safety clamp
    listIndex = Math.max(0, Math.min(listIndex, poly.mappings.size() - 1));
    return Component.literal(poly.mappings.get(listIndex));
  }

  /**
   * Converts an individual gene value into a visual {@link Component} based on the gene's type.
   *
   * <p>BINARY: Maps 1 to "+" (wildtype), 2 to a specific label. BINARY_INVERTED: Maps 2 to "+"
   * (wildtype), 1 to a specific label. POLY: Maps the raw value directly to an index in the entry's
   * mapping list. COLOUR: Converts a 0-255 value into a coloured "█" symbol with a hex-code.
   *
   * @param entry The display configuration containing mappings and gene type.
   * @param val The raw gene value (integer around 1-20 for traits, or 0-255 for colours).
   * @return A formatted {@link Component} representing the gene's value visually.
   */
  private Component processGeneValue(DisplayEntry entry, int val) {
    // To handle SGenes
    if (entry.valueMapping != null) {
      return entry.valueMapping.getOrDefault(val, Component.literal("?"));
    }

    List<String> mappings = entry.mappings;
    GeneType type = entry.type;
    // To handle AGenes
    if (type == GeneType.BINARY) {
      return Component.literal((val == 2) ? mappings.get(0) : "+");
    }
    if (type == GeneType.BINARY_INVERTED) {
      return Component.literal((val == 1) ? mappings.get(0) : "+");
    }
    if (type == GeneType.POLYMORPHIC && val < mappings.size()) {
      return Component.literal(mappings.get(val));
    }
    if (type == GeneType.COLOUR) {
      int hueRGB = Colouration.HSBtoARGB((float) val / 256.0F, 0.75F, 0.65F) & 0x00FFFFFF;
      String hexCode = String.format("#%06X", hueRGB);
      return Component.literal("█")
          .withStyle(
              style ->
                  style
                      .withColor(TextColor.fromRgb(hueRGB))
                      // Tooltip: Shows the Hex code when hovered
                      .withHoverEvent(
                          new HoverEvent(
                              HoverEvent.Action.SHOW_TEXT,
                              Component.literal("Hex: ")
                                  .append(
                                      Component.literal(hexCode)
                                          .withStyle(
                                              Style.EMPTY.withColor(TextColor.fromRgb(hueRGB))))
                                  .append("\n§7Click to copy to clipboard")))
                      // Click Action: Copies the hex code to the player's clipboard
                      .withClickEvent(
                          new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, hexCode)));
    }
    return Component.literal("?");
  }

  /**
   * Transforms autosomal and sex-linked gene data into a displayable list of entries.
   *
   * <p>This method iterates through {@code displayEntries} to apply visibility logic, 
   * handle structural elements (headers, breaks), and process polygenic calculations. 
   * For sex-linked genes in females, it automatically collapses the display to a 
   * single allele.
   *
   * @param agenes   the raw integer array of autosomal gene values.
   * @param sgenes   the raw integer array of sex-linked gene values.
   * @param isFemale whether to apply single-allele formatting for sex-linked entries.
   * @return a list of {@link BookEntry} objects containing formatted labels and values.
   */
  public List<BookEntry> mapToEntries(int[] agenes, int[] sgenes, boolean isFemale) {
    List<BookEntry> result = new ArrayList<>();

    for (DisplayEntry entry : displayEntries) {
      // Determine which array to use for this entry
      int[] currentArray = entry.isSexLinked ? sgenes : agenes;

      // Handle Visibility Conditions
      if (entry.dataIndex != -1 && entry.visibilityCondition != null) {
        int v1Idx = entry.dataIndex * 2;
        int v2Idx = v1Idx + 1;
        int v1 = (v1Idx < currentArray.length) ? currentArray[v1Idx] : 0;
        int v2 = (v2Idx < currentArray.length) ? currentArray[v2Idx] : 0;
        if (!entry.visibilityCondition.test(v1, v2)) continue;
      }
      // Handle Structural Elements (Breaks/Labels)
      if (entry.type == GeneType.LINE_BREAK) {
        result.add(new BookEntry(BookEntry.Type.COMMENT, " "));
        continue;
      }
      if (entry.type == GeneType.PAGE_BREAK) {
        result.add(new BookEntry(BookEntry.Type.COMMENT, "[PAGE_BREAK]"));
        continue;
      }
      if (entry.type == GeneType.OTHER) {
        if (entry.label.startsWith("[H]")) {
          result.add(new BookEntry(BookEntry.Type.HEADER, entry.label.substring(3)));
        } else if (entry.label.startsWith("[C]")) {
          result.add(new BookEntry(BookEntry.Type.COMMENT, entry.label.substring(3)));
        }
        continue;
      }
      if (entry.label.equalsIgnoreCase("Skip")) continue;

      // Handle Calculations (Polygenic)
      if (entry instanceof PolyScaleDefinition polyScale) {
        Component polyVal = processPolyScale(polyScale, currentArray);
        result.add(new BookEntry(polyScale.label, polyVal, Component.literal("‽")));
        continue;
      }
      if (entry instanceof PolyGeneDefinition polyGene) {
        Component polyVal = processPolyGene(polyGene, currentArray);
        result.add(new BookEntry(polyGene.label, polyVal, Component.literal("‽")));
        continue;
      }
      if (entry instanceof CustomBettaPolyScaleDefinition polyScale) {
        Component polyVal = processCustomBettaPolyScale(polyScale, currentArray);
        result.add(new BookEntry(polyScale.label, polyVal, Component.literal("‽")));
        continue;
      }

      // Handle Standard Gene Pairs
      int v1Idx = entry.dataIndex * 2;
      int v2Idx = v1Idx + 1;
      Component val1 =
          (v1Idx < currentArray.length)
              ? processGeneValue(entry, currentArray[v1Idx])
              : Component.literal("?");

      // Female sex-linked genes only show one allele
      if (entry.isSexLinked && isFemale) {
        result.add(new BookEntry(entry.label, val1, Component.literal("‽")));
      } else {
        Component val2 =
            (v2Idx < currentArray.length)
                ? processGeneValue(entry, currentArray[v2Idx])
                : Component.literal("?");
        result.add(new BookEntry(entry.label, val1, val2));
      }
    }
    return result;
  }
}
