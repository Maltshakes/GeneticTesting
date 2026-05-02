package net.maltshakes.genetictesting.genes.datamodel;

import java.util.List;

/**
 * Defines the metadata and formatting rules for a specific gene.
 *
 * <p>This class acts as a template for how genetic data should be interpreted and displayed in the
 * book. It includes a human-readable label, a list of possible string mappings for values, and a
 * type that dictates the formatting logic applied.
 */
public class GeneDefinition {
  private final String label;
  private final List<String> mappings;
  private final GeneType type;
  private final int priority;

  /**
   * @param label The display name of the gene.
   * @param mappings A list of strings representing the possible genetic expressions.
   * @param type The {@link GeneType} used to determine formatting logic.
   * @param priority The sorting order of the gene; lower values typically appear first.
   */
  public GeneDefinition(String label, List<String> mappings, GeneType type, int priority) {
    this.label = label;
    this.mappings = mappings;
    this.type = type;
    this.priority = priority;
  }

  // Getters for variables
  public String getLabel() {
    return label;
  }

  public List<String> getMappings() {
    return mappings;
  }

  public GeneType getType() {
    return type;
  }

  public int getPriority() {
    return priority;
  }

  /**
   * GeneTypes that will be handled differently through mapGenes in GeneFormatting.
   *
   * <p>BINARY means there are two choices, with wildtype ("+") first, then the gene.
   *
   * <p>BINARY_INVERTED means there are two choices, where the gene will be first, then the
   * wildtype. POLYMORPHIC means the gene is polymorphic and has multiple choices. COLOUR means the
   * gene relates to a colour and transforms HSB to RGB. SCALE refers to math calculation (unused).
   * OTHER is for options that don't relate to genes or formatting, but categories. SKIP refers to
   * genes that should be skipped during recording in the book.
   */
  public enum GeneType {
    BINARY,
    BINARY_INVERTED,
    POLYMORPHIC,
    SCALE,
    COLOUR,
    OTHER,
    LINE_BREAK,
    PAGE_BREAK,
    SKIP
  }
}
