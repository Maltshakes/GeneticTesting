package net.maltshakes.genetictesting.genes.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.maltshakes.genetictesting.genes.datamodel.BookEntry;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;
import net.maltshakes.genetictesting.genes.generegistry.AxolotlGeneFormat;
import net.maltshakes.genetictesting.genes.generegistry.BettaGeneFormat;
import net.maltshakes.genetictesting.genes.generegistry.ChickenGeneFormat;
import net.maltshakes.genetictesting.genes.generegistry.CowGeneFormat;
import net.maltshakes.genetictesting.genes.generegistry.LlamaGeneFormat;
import net.maltshakes.genetictesting.genes.generegistry.PigGeneFormat;
import net.maltshakes.genetictesting.genes.generegistry.RabbitGeneFormat;
import net.maltshakes.genetictesting.genes.generegistry.SheepGeneFormat;
import net.maltshakes.genetictesting.genes.generegistry.TurtleGeneFormat;

public class AnimalResolver {

  private static Map<String, GeneFormatting> animalMappings = new HashMap<>();

  static {
    animalMappings.put("eanimod:enhanced_axolotl", new AxolotlGeneFormat());
    animalMappings.put("eanimod:enhanced_chicken", new ChickenGeneFormat());
    animalMappings.put("eanimod:enhanced_cow", new CowGeneFormat());
    animalMappings.put("eanimod:enhanced_moobloom", new CowGeneFormat());
    animalMappings.put("eanimod:enhanced_mooshroom", new CowGeneFormat());
    animalMappings.put("eanimod:enhanced_llama", new LlamaGeneFormat());
    animalMappings.put("eanimod:enhanced_pig", new PigGeneFormat());
    animalMappings.put("eanimod:enhanced_rabbit", new RabbitGeneFormat());
    animalMappings.put("eanimod:enhanced_sheep", new SheepGeneFormat());
    animalMappings.put("eanimod:enhanced_turtle", new TurtleGeneFormat());
    animalMappings.put("eanimod:enhanced_betta", new BettaGeneFormat());
  }

  /**
   * Resolves the mapping definition for a specific entity type and transforms the provided gene
   * data into displayable entries.
   *
   * @param entityType the identifier for the animal type.
   * @param agenes the raw integer array of autosomal gene values.
   * @param sgenes the raw integer array of sex-linked gene values.
   * @param isFemale whether the entity is female (affects sex-linked gene display).
   * @return a list of formatted {@link BookEntry} objects, or {@code null} if the entity type is
   *     unknown or autosomal data is missing.
   */
  public static List<BookEntry> resolveAllEntries(
      String entityType, int[] agenes, int[] sgenes, boolean isFemale) {
    if (entityType == null || agenes == null) {
      return null;
    }
    GeneFormatting mapping = animalMappings.get(entityType);
    if (mapping == null) {
      return null;
    }
    return mapping.mapToEntries(agenes, sgenes, isFemale);
  }

  /**
   * Returns the GeneFormatting for a given entity.
   *
   * @param entityType the entity type as a string
   * @return the GeneFormatting, or {@code null} if the entity type is unknown
   */
  public static GeneFormatting getFormat(String entityType) {
    return animalMappings.get(entityType);
  }
}
