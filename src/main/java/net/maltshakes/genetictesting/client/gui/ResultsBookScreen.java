package net.maltshakes.genetictesting.client.gui;

import java.util.List;
import javax.annotation.Nullable;
import net.maltshakes.genetictesting.client.GeneBookDisplay;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * The client-side graphical user interface for viewing analyzed genetic data. This screen displays
 * the contents of a {@link GeneBookDisplay} using a custom book-like texture, supporting
 * dual-column layouts and multi-page navigation.
 */
@OnlyIn(Dist.CLIENT)
public class ResultsBookScreen extends Screen {

  // The location of the GUI texture for the gene book background
  public static final ResourceLocation BACKGROUND_LOCATION =
      ResourceLocation.fromNamespaceAndPath("genetictesting", "textures/gui/gene_book.png");
  // The formatted data provider containing the pages and columns to render
  private final GeneBookDisplay display;
  // The index of the currently displayed page (starts at 0).
  private int currPage = 0;
  // Store the tint colour of the book
  private final int bookColour;

  private final int imageWidth = 295;
  private final int imageHeight = 180;
  private final int pageHeight = 170;
  private final int textureWidth = 512;
  private final int textureHeight = 512;
  private PageButton nextButton;
  private PageButton prevButton;
  private int feedbackTimer = 0;
  private Component feedbackMessage = Component.empty();

  /**
   * Constructs a new results screen.
   *
   * @param title The title of the screen (typically the name of the animal).
   * @param display The formatted display logic containing the genetic entries.
   */
  public ResultsBookScreen(Component title, GeneBookDisplay display, int bookColour) {
    super(title);
    this.display = display;
    this.bookColour = bookColour;
  }

  /**
   * Initializes the GUI, calculating positions for the background and adding navigation and close
   * buttons.
   */
  @Override
  protected void init() {
    int leftPos = (this.width - imageWidth) / 2;
    int topPos = (this.height - imageHeight) / 2;

    // Close button
    this.addRenderableWidget(
        Button.builder(Component.literal("Close"), (button) -> this.onClose())
            .bounds(leftPos + (imageWidth / 2) - 35, topPos + imageHeight + 10, 70, 20)
            .build());

    // Next button (isForward = true)
    this.nextButton =
        this.addRenderableWidget(
            new PageButton(
                leftPos + 255,
                topPos + 155,
                true,
                (btn) -> {
                  if (currPage < display.getPages().size() - 1) {
                    currPage++;
                    updateButtonVisibility();
                  }
                },
                true));

    // Previous button (isForward = false)
    this.prevButton =
        this.addRenderableWidget(
            new PageButton(
                leftPos + 15,
                topPos + 155,
                false,
                (btn) -> {
                  if (currPage > 0) {
                    currPage--;
                    updateButtonVisibility();
                  }
                },
                true));

    updateButtonVisibility();
  }

  /**
   * Updates the visibility of the "Next" and "Previous" buttons based on whether there are more
   * pages to view in the current direction.
   */
  private void updateButtonVisibility() {
    if (this.nextButton != null) this.nextButton.visible = currPage < display.getPages().size() - 1;
    if (this.prevButton != null) this.prevButton.visible = currPage > 0;
  }

  @Override
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    if (button == 0) { // Left click
      Style style = this.getClickedComponentStyleAt((int) mouseX, (int) mouseY);
      if (style != null && this.handleComponentClicked(style)) {
        // Check if it was a copy event to provide specific feedback
        if (style.getClickEvent() != null
            && style.getClickEvent().getAction() == ClickEvent.Action.COPY_TO_CLIPBOARD) {
          // Standard copy logic
          this.minecraft.keyboardHandler.setClipboard(style.getClickEvent().getValue());
          // Set feedback for custom renderer
          this.feedbackMessage =
              Component.literal("Copied to clipboard!").withStyle(ChatFormatting.GREEN);
          this.feedbackTimer = 60; // Show for 3 seconds (20 ticks = 1s)
          return true;
        }
        return true;
      }
    }
    return super.mouseClicked(mouseX, mouseY, button);
  }

  @Nullable
  private Style getClickedComponentStyleAt(int mouseX, int mouseY) {
    if (display.getPages().isEmpty() || currPage >= display.getPages().size()) return null;

    GeneBookDisplay.Page page = display.getPages().get(currPage);
    int leftPos = (this.width - imageWidth) / 2;
    int topPos = (this.height - imageHeight) / 2;
    int lineHeight = 11;

    // Check Left Column
    Style leftStyle =
        getStyleInColumn(
            page.leftColumn,
            mouseX,
            mouseY,
            leftPos + 21,
            topPos + 25,
            GeneBookDisplay.COLUMN_WIDTH,
            lineHeight);
    if (leftStyle != null) return leftStyle;

    // Check Right Column
    Style rightStyle =
        getStyleInColumn(
            page.rightColumn,
            mouseX,
            mouseY,
            leftPos + 160,
            topPos + 25,
            GeneBookDisplay.COLUMN_WIDTH,
            lineHeight);
    if (rightStyle != null) return rightStyle;

    return null;
  }

  @Nullable
  private Style getStyleInColumn(
      List<Component> lines,
      int mouseX,
      int mouseY,
      int startX,
      int startY,
      int width,
      int lineHeight) {
    int currentY = startY;
    for (Component component : lines) {
      // Split components
      for (FormattedCharSequence subLine : this.font.split(component, width)) {
        // Check if mouse is within the vertical bounds of this specific line
        if (mouseY >= currentY && mouseY < currentY + lineHeight) {
          // Check if mouse is within the horizontal bounds of the text
          if (mouseX >= startX && mouseX <= startX + width) {
            // Minecraft's font splitter can find the Style at a specific X offset
            return this.font.getSplitter().componentStyleAtWidth(subLine, mouseX - startX);
          }
        }
        currentY += lineHeight;
        if (currentY > startY + (10 * lineHeight)) break; // Stop if it exceeds the column height
      }
    }
    return null;
  }

  private void drawSquareShadow(GuiGraphics guiGraphics, FormattedCharSequence line, int x, int y) {
    final int[] currentXOffset = {0};
    guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

    line.accept(
        (index, style, codePoint) -> {
          // Check if the character is square (code point 9608 is '█')
          if (codePoint == 9608) {
            int shadowX = x + currentXOffset[0];
            guiGraphics.fill(shadowX - 1, y - 1, shadowX + 9, y + 9, 0x44404040);
          }
          // Advance the offset by the width of the character just checked
          currentXOffset[0] += this.font.width((char) codePoint + "");
          return true;
        });
  }

  /**
   * Renders the screen components including the background texture, the text for both columns, and
   * the page numbers.
   *
   * @param guiGraphics The graphics context for drawing textures and text.
   * @param mouseX Current X position of the mouse.
   * @param mouseY Current Y position of the mouse.
   * @param partialTick The partial tick time for smooth rendering.
   */
  @Override
  public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    this.renderBackground(guiGraphics);

    int leftPos = (this.width - imageWidth) / 2;
    int topPos = (this.height - imageHeight) / 2;

    // Set the tint colour (ARGB format)
    float r = (float) (bookColour >> 16 & 255) / 255.0F;
    float g = (float) (bookColour >> 8 & 255) / 255.0F;
    float b = (float) (bookColour & 255) / 255.0F;
    guiGraphics.setColor(r, g, b, 1.0F);

    // Render border
    guiGraphics.blit(
        BACKGROUND_LOCATION,
        leftPos,
        topPos,
        0,
        0,
        imageWidth,
        imageHeight,
        textureWidth,
        textureHeight);

    // Reset the colours for the rest of the GUI
    guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

    // Render pages
    guiGraphics.blit(
        BACKGROUND_LOCATION,
        leftPos,
        topPos,
        0,
        182,
        imageWidth,
        pageHeight,
        textureWidth,
        textureHeight);

    // Build lines per page
    if (currPage < display.getPages().size()) {
      GeneBookDisplay.Page page = display.getPages().get(currPage);
      int lineHeight = 11; // Height of the line margin

      // Left column
      int leftYOffset = 0;
      for (Component component : page.leftColumn) {
        // font.split(Component) preserves all formatting/RGB across line breaks
        for (FormattedCharSequence subLine :
            this.font.split(component, GeneBookDisplay.COLUMN_WIDTH)) {
          if (leftYOffset < GeneBookDisplay.COLUMN_SIZE) {
            int x = leftPos + 21;
            int y = topPos + 25 + (leftYOffset * lineHeight);
            this.drawSquareShadow(guiGraphics, subLine, x, y);
            guiGraphics.drawString(this.font, subLine, x, y, 0x404040, false);

            // Check for tooltips/hover events
            if (mouseX >= x
                && mouseX <= x + GeneBookDisplay.COLUMN_WIDTH
                && mouseY >= y
                && mouseY <= y + lineHeight) {
              // Find the specific style at the mouse's X position within the text line
              Style styleAtMouse =
                  this.font.getSplitter().componentStyleAtWidth(subLine, mouseX - x);
              if (styleAtMouse != null && styleAtMouse.getHoverEvent() != null) {
                guiGraphics.renderComponentHoverEffect(this.font, styleAtMouse, mouseX, mouseY);
              }
            }
            leftYOffset++;
          }
        }
      }
      // Right column
      int rightYOffset = 0;
      for (Component component : page.rightColumn) {
        for (FormattedCharSequence subLine :
            this.font.split(component, GeneBookDisplay.COLUMN_WIDTH)) {
          if (rightYOffset < GeneBookDisplay.COLUMN_SIZE) {
            int x = leftPos + 160;
            int y = topPos + 25 + (rightYOffset * lineHeight);
            this.drawSquareShadow(guiGraphics, subLine, x, y);
            guiGraphics.drawString(this.font, subLine, x, y, 0x404040, false);
            if (mouseX >= x
                && mouseX <= x + GeneBookDisplay.COLUMN_WIDTH
                && mouseY >= y
                && mouseY <= y + lineHeight) {
              // Find the specific style at the mouse's X position within the text line
              Style styleAtMouse =
                  this.font.getSplitter().componentStyleAtWidth(subLine, mouseX - x);
              if (styleAtMouse != null && styleAtMouse.getHoverEvent() != null) {
                guiGraphics.renderComponentHoverEffect(this.font, styleAtMouse, mouseX, mouseY);
              }
            }
            rightYOffset++;
          }
        }
      }
      int leftNum = (currPage * 2) + 1;
      int rightNum = (currPage * 2) + 2;
      int totalColumns = display.getPages().size() * 2;

      // Left column page indicator
      String leftIndicator = "Page " + leftNum + " of " + totalColumns;
      int leftIndWidth = this.font.width(leftIndicator);
      guiGraphics.drawString(
          this.font, leftIndicator, leftPos + 15 + leftIndWidth, topPos + 12, 0x888888, false);

      // Right column page indicator
      String rightIndicator = "Page " + rightNum + " of " + totalColumns;
      int rightIndWidth = this.font.width(rightIndicator);
      guiGraphics.drawString(
          this.font,
          rightIndicator,
          leftPos + imageWidth - rightIndWidth - 20,
          topPos + 12,
          0x888888,
          false);
    }
    super.render(guiGraphics, mouseX, mouseY, partialTick);
    if (feedbackTimer > 0) {
      // Center on the screen width
      int x = this.width / 2;
      int y = (this.height / 2) + (imageHeight / 2) - 10; // Inside bottom of book

      // Add a slight fade out effect
      int alpha = Math.min(255, feedbackTimer * 10);
      int color = (alpha << 24) | 0x00FF00; // Green with alpha

      guiGraphics.drawCenteredString(this.font, feedbackMessage, x, y, color);
    }
  }

  // Allows the fade out effect of text to happen when the game is paused.
  @Override
  public void tick() {
    super.tick();
    if (feedbackTimer > 0) {
      feedbackTimer--;
    }
  }
}
