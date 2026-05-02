package net.maltshakes.genetesting.genes.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.maltshakes.genetesting.genes.datamodel.BookEntry;
import net.maltshakes.genetesting.genes.format.GeneFormatting;
import net.maltshakes.genetesting.genes.generegistry.AxolotlGeneFormat;
import net.maltshakes.genetesting.genes.generegistry.BettaGeneFormat;
import net.maltshakes.genetesting.genes.generegistry.ChickenGeneFormat;
import net.maltshakes.genetesting.genes.generegistry.CowGeneFormat;
import net.maltshakes.genetesting.genes.generegistry.LlamaGeneFormat;
import net.maltshakes.genetesting.genes.generegistry.PigGeneFormat;
import net.maltshakes.genetesting.genes.generegistry.RabbitGeneFormat;
import net.maltshakes.genetesting.genes.generegistry.SheepGeneFormat;
import net.maltshakes.genetesting.genes.generegistry.TurtleGeneFormat;


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
     * Resolves the mapping for a given entity type and maps the provided gene values.
     * 
     * @param entityType the entity type as a string
     * @param values the integer array of the gene values to map
     * @return the mapped character array, or {@code null} if the entity type is unknown
     */
    public static List<BookEntry> resolveAllEntries(String entityType, int[] agenes, int[] sgenes, boolean isFemale) {
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
