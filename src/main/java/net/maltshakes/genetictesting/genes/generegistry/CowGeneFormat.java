package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import java.util.stream.IntStream;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

public class CowGeneFormat extends GeneFormatting {

  private static final List<String> COW_EXTENSION_GENES =
      List.of(
          "0",
          "D", // Dom Black
          "+", // Wildtype
          "e", // Red
          "BR", // Black-red
          "m" // Masked
          );

  private static final List<String> COW_DILUTE_GENES =
      List.of(
          "0",
          "+", // Wildtype
          "s", // Simmental Dilute
          "c" // Charolais Dilute
          );

  private static final List<String> COW_AGOUTI_GENES =
      List.of(
          "0",
          "b", // Dark Agouti
          "+", // Wildtype
          "W", // White-Bellied Agouti
          "BR", // Brindle
          "F", // Fawn
          "a" // Recessive Black
          );

  private static final List<String> COW_WHITEFACE_GENES =
      List.of(
          "0",
          "H", // Hereford
          "P", // Pinzgauer
          "+", // Wildtype
          "p" // Piebald
          );

  private static final List<String> COW_WHITEFACEEXT_GENES =
      List.of(
          "0",
          "+", // + Spots
          "0", // Normal
          "-" // - Spots
          );

  private static final List<String> COW_LEGACY_BELTED_GENES =
      List.of(
          "0",
          "LBe", // Legacy Belted
          "LBl", // Legacy Blaze
          "LBr", // Legacy Brockling
          "+" // Wildtype
          );

  private static final List<String> COW_MEALY_GENES =
      List.of(
          "0",
          "NM", // No nose ring
          "m", // Wildtype
          "ex" // Extended Mealy
          );

  private static final List<String> COW_RUFOUS_SCALE =
      List.of(
          "Min", // -20
          "Near Min",
          "Near Min",
          "Near Min", // -19 -17
          "Ultra Low",
          "Ultra Low",
          "Ultra Low", // -16 -14
          "Super Low",
          "Super Low",
          "Super Low", // -13 -11
          "Very Low",
          "Very Low",
          "Very Low", // -10 -8
          "Low",
          "Low",
          "Low", // -7 -5
          "Med-Low",
          "Med-Low",
          "Med-Low", // -4 -2
          "Medium",
          "Medium",
          "Medium", // -1 +1
          "Med-High",
          "Med-High",
          "Med-High", // +2 +4
          "High",
          "High",
          "High", // +5 +7
          "Very High",
          "Very High",
          "Very High", // +8 +10
          "Super High",
          "Super High",
          "Super High", // +11 +13
          "Ultra High",
          "Ultra High",
          "Ultra High", // +14 +16
          "Near Max",
          "Near Max",
          "Near Max", // +17 +19
          "Max" // +20
          );

  private static final List<String> COW_SHADING_SCALE =
      List.of(
          "Min", // -14
          "Near Min",
          "Near Min", // -13 -12
          "Ultra Low",
          "Ultra Low", // -11 -10
          "Super Low",
          "Super Low", // -9 -8
          "Very Low",
          "Very Low", // -7 -6
          "Low",
          "Low", // -5 -4
          "Med-Low",
          "Med-Low", // -3 -2
          "Medium",
          "Medium",
          "Medium", // -1 +1
          "Med-High",
          "Med-High",
          "Med-High", // +2 +4
          "High",
          "High",
          "High", // +5 +7
          "Very High",
          "Very High", // +8 +9
          "Super High",
          "Super High", // +10 +11
          "Ultra High",
          "Ultra High", // +12 +13
          "Near Max",
          "Near Max", // +14 +15
          "Max" // +16
          );

  private static final List<String> COW_SOOTY_SCALE =
      List.of(
          "Min", // -26
          "Near Min",
          "Near Min",
          "Near Min",
          "Near Min", // -25 -22
          "Ultra Low",
          "Ultra Low",
          "Ultra Low",
          "Ultra Low", // -21 -18
          "Super Low",
          "Super Low",
          "Super Low",
          "Super Low", // -17 -14
          "Very Low",
          "Very Low",
          "Very Low",
          "Very Low", // -13 -10
          "Low",
          "Low",
          "Low",
          "Low", // -9 -6
          "Med-Low",
          "Med-Low",
          "Med-Low",
          "Med-Low", // -5 -2
          "Medium",
          "Medium",
          "Medium", // -1 +1
          "Med-High",
          "Med-High",
          "Med-High",
          "Med-High", // +2 +5
          "High",
          "High",
          "High",
          "High", // +6 +9
          "Very High",
          "Very High",
          "Very High",
          "Very High", // +10 +13
          "Super High",
          "Super High",
          "Super High",
          "Super High", // +14 +17
          "Ultra High",
          "Ultra High",
          "Ultra High", // +18 +20
          "Near Max",
          "Near Max",
          "Near Max", // +21 +23
          "Max" // +24
          );

  // Maps the genes of a cow
  public CowGeneFormat() {
    setBookColour(0x424244); // Black
    addCategory("Genetic tests (color)");
    addPairMapping("Extension", COW_EXTENSION_GENES, GeneType.POLYMORPHIC, 0); // [0,1] - Extension
    addPairMapping("Agouti", COW_AGOUTI_GENES, GeneType.POLYMORPHIC, 2); // [4,5] - Agouti
    addPairMapping("Dom Red", "e", GeneType.BINARY_INVERTED, 3); // [6,7] - Dominant Red
    addPairMapping("Dilute", COW_DILUTE_GENES, GeneType.POLYMORPHIC, 1); // [2,3] - Dilutes
    addPairMapping("Dun", "D", GeneType.BINARY, 64); // [128,129] - Dun Dilute
    addPairMapping("Chocolate", "c", GeneType.BINARY, 5); // [10,11] - Chocolate Dilute
    addPairMapping("Roan", "Rn", GeneType.BINARY, 4); // [8,9] - Roan
    addPairMapping("Speckled", "P", GeneType.BINARY_INVERTED, 7); // [14,15] - Speckled
    addPairMapping(
        "White Face", COW_WHITEFACE_GENES, GeneType.POLYMORPHIC, 8); // [16,17] - White Face
    addPairMapping(
        "Pinz Extension",
        COW_WHITEFACEEXT_GENES,
        GeneType.POLYMORPHIC,
        11); // [22,23] - Pinzguaer/White Face Extension
    addPairMapping("Colorsided", "CS", GeneType.BINARY_INVERTED, 10); // [20,21] - Colorsided
    addPairMapping("Belted", "Be", GeneType.BINARY, 125); // [250,251] - Belted
    addPairMapping("Blaze", "Bl", GeneType.BINARY, 126); // [252,253] - Blaze
    addLegacyMapping(
        "Legacy Belted",
        COW_LEGACY_BELTED_GENES,
        GeneType.POLYMORPHIC,
        9,
        4); // [18,19] - Legacy Belted
    addPairMapping("Mealy", COW_MEALY_GENES, GeneType.POLYMORPHIC, 12); // [24,25] - Mealy
    addPairMapping("Eelstripe", "eel", GeneType.BINARY, 60); // [120,121] - Eelstripe
    addPolyScaleMapping(
        "Rufousing",
        COW_RUFOUS_SCALE,
        IntStream.rangeClosed(130, 149).toArray(), // [130-149] - Yellow Rufousing (-red)
        IntStream.rangeClosed(150, 169).toArray() // [150-169] - Burgandy Rufousing (+red)
        );
    addPolyScaleMapping(
        "Red Shading",
        COW_SHADING_SCALE,
        IntStream.rangeClosed(170, 183).toArray(), // [170-183] - lighter
        IntStream.rangeClosed(184, 199).toArray() // [184-199] - darker
        );
    addPolyScaleMapping(
        "Sootiness",
        COW_SOOTY_SCALE,
        IntStream.rangeClosed(200, 225).toArray(), // [200-225] - lighter pattern
        IntStream.rangeClosed(226, 249).toArray() // [226-249] - darker pattern
        );
  }
}
