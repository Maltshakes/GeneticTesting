package net.maltshakes.genetictesting.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextColor;

public class GeneHelpers {

  public final String type;
  public final String label;

  public GeneHelpers(String type, String text) {
    this.type = type;
    this.label = text;
  }

  /**
   * Find the closest hex code that represents the given colour.
   *
   * @param colour the TextColor to convert. This can be any RGB color.
   * @return the formatting code best representing the given text color.
   */
  public static ChatFormatting convertToClosestColour(TextColor colour) {
    ChatFormatting closestColour = ChatFormatting.BLACK;
    int distance = Integer.MAX_VALUE;
    for (ChatFormatting formatting : ChatFormatting.values()) {
      Integer colorValue = formatting.getColor();
      if (colorValue == null) {
        continue;
      }

      // Find the Euclidean distance between the two colors (without taking the square root).
      int dr = Math.abs((colorValue >> 16 & 0xff) - (colour.getValue() >> 16 & 0xff));
      int dg = Math.abs((colorValue >> 8 & 0xff) - (colour.getValue() >> 8 & 0xff));
      int db = Math.abs((colorValue & 0xff) - (colour.getValue() & 0xff));

      int dist = dr + dg + db;
      if (dist < distance) {
        closestColour = formatting;
        distance = dist;
      }
    }
    return closestColour;
  }

  /**
   * Extracts an integer array from an NBT tag string by key. Expects the format {@code
   * key:[I;v1,v2,...vN]}.
   *
   * @param fullGeneTag the full NBT Genetics tag
   * @param key the key identifying the array ("AGenes" or "SGenes")
   * @return the parsed integer array, or {@code null} if the key is not found
   */
  public static int[] extractIntFromArray(String fullGeneTag, String key) {
    int keyStart = fullGeneTag.indexOf(key + ":[I;");
    if (keyStart == -1) {
      return null;
    }
    int arrayStart = fullGeneTag.indexOf("[I;", keyStart) + 3;
    int arrayEnd = fullGeneTag.indexOf("]", arrayStart);
    if (arrayEnd == -1) {
      return null;
    }
    String[] arrayParts = fullGeneTag.substring(arrayStart, arrayEnd).split(",");
    int[] values = new int[arrayParts.length];
    for (int i = 0; i < arrayParts.length; i++) {
      values[i] = Integer.parseInt(arrayParts[i].trim());
    }
    return values;
  }
}
