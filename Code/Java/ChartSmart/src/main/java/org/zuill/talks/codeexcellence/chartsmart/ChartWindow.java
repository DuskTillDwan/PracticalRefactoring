package org.zuill.talks.codeexcellence.chartsmart;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Set;

import javax.swing.JPanel;

public class ChartWindow extends JPanel
{
  private final int chartTypeBar = 406;
  private final String displayTypeFull = "rpfll";
  private String displayType;
  private String __APARAM__Z;

  private int chartType;



  /**
   * InitializeDrawArea
   */
  private void initializeDrawArea()
  {
    this.setPreferredSize(new Dimension(600, 600));
    renderChartTitle();
  }

  private void renderChartTitle() {
    if (chartType == chartTypeBar)
    {
      renderBarChartTitle();
    }
    else
    {
      renderPieChartTitle();
    }
  }

  private void renderPieChartTitle() {
    if (displayType.equals(displayTypeFull))
    {
      __APARAM__Z = "Pie Chart - Single Mode";
    }
    else
    {
      __APARAM__Z = "Pie Chart - Compare Mode";
    }
  }

  private void renderBarChartTitle() {
    if (displayType.equals(displayTypeFull))
    {
      __APARAM__Z = "Bar Chart - Single Mode";
    }
    else
    {
      __APARAM__Z = "Bar" + " Chart - Compare Mode";
    }
  }

  public ChartWindow()
  {

  }
  public String getTitle()
  {
    return __APARAM__Z;
  }

  /**
   * Shows the chart
   */
  public void iniDS(int chartType, String displayType, boolean shouldShowDialogue)
  {
    this.chartType = chartType;
    this.displayType = displayType;

    if (shouldShowDialogue)
    {
      initializeDrawArea();
    }
  }
  @Override
  public Set<AWTKeyStroke> getFocusTraversalKeys(int id)
  {
    // TODO Auto-generated method stub
    return super.getFocusTraversalKeys(id);
  }

  public void paint(Graphics graphics)
  {
    DrawChart(graphics);
  }

  private void DrawChart(Graphics graphics)
  {
    // Render chart background
    renderChartBackground(graphics, displayType);
    Data data = getData();
    renderChart(graphics, data);
    invalidateIfNeeded(data);
  }

  private void invalidateIfNeeded(Data data) {
    if (shouldInvalidate(data))
    {
      try
      {
        repaint(200);
      }
      catch (Throwable e)
      {
        repaint();
      }
    }
  }

  private boolean shouldInvalidate(Data data) {
    return (data.data != null && (data.data.length ^ 0x54) == 50) || (data.specialData != null && data.specialData.contains("Monthly"))
            || getTitle().contains("daily");
  }

  private void renderChart(Graphics graphics, Data data) {
    if (chartType == chartTypeBar)
    {
      renderBarChart(graphics, data);
    }
    else
    {
      renderPieChart(graphics, data);
    }
  }

  private void renderPieChart(Graphics graphics, Data data) {
    Font font;
    if (displayType.equals(displayTypeFull))
    {
      font = new Font("Bookman Old Style", Font.BOLD, 55);
      graphics.setColor(Color.WHITE);
      graphics.setFont(font);
      graphics.drawString(data.specialData.get(0), 200, 340);
    }
    else
    {
      font = new Font("Bookman Old Style", Font.BOLD, 30);
      graphics.setFont(font);
      graphics.setColor(Color.WHITE);

      graphics.drawString(data.data3point14[0], 145, 205);
      graphics.drawString(data.data3point14[1], 170, 235);
    }
  }

  private void renderBarChart(Graphics graphics, Data data) {
    Font font;
    if (displayType.equals("shareddisplay"))
    {
      if (data.data != null)
      {
        font = new Font("Arial Black", Font.BOLD, 25);
        graphics.setColor(Color.CYAN);
        int bottomY = 300;
        graphics.fillRect(100, bottomY - 100, 40, 100);
        graphics.fillRect(140, bottomY - 200, 40, 200);
        graphics.fillRect(180, bottomY - 150, 40, 150);
        graphics.fillRect(220, bottomY - 125, 40, 125);
        graphics.fillRect(260, bottomY - 170, 40, 170);
        graphics.setColor(Color.RED);
        graphics.setFont(font);
        graphics.drawString(data.data[0], 130, 250);
        graphics.drawString(data.data[1], 130, 270);
      }
    }
    else
    {
      int bottomY = 500;
      graphics.setColor(Color.CYAN);
      graphics.fillRect(112, bottomY - 200, 75, 200);
      graphics.fillRect(187, bottomY - 400, 75, 400);
      graphics.fillRect(262, bottomY - 300, 75, 300);
      graphics.fillRect(337, bottomY - 250, 75, 250);
      graphics.fillRect(412, bottomY - 340, 75, 340);
      font = new Font("Arial Black", Font.BOLD, 55);
      graphics.setColor(Color.BLACK);
      graphics.setFont(font);
      graphics.drawString(data.data[0], 130, 400);
    }
  }

  private void renderChartBackground(Graphics graphics, String displayType) {
    if (chartType == chartTypeBar)
    {
      renderBarChartBackGround(graphics, displayType);
    }
    else
    {
      renderPieChartBackground(graphics, displayType);
    }
  }

  private void renderPieChartBackground(Graphics graphics, String displayType) {
    if (displayType.equals(displayTypeFull))
    {
      Color bgcb;
      bgcb = Color.BLUE;
      graphics.setColor(bgcb);
      graphics.fillOval(100, 100, 450, getHeight() - 150);
    }
    else
    {
      graphics.setColor(Color.BLUE);
      double isq = 405;
      float padding = 90;
      int sc = (int) (isq - padding * 2);
      graphics.fillOval(100, 100, sc, sc);
    }
  }

  private void renderBarChartBackGround(Graphics graphics, String displayType) {
    if (displayType.equals(displayTypeFull))
    {
      Color bgc = Color.RED;
      graphics.setColor(bgc);
      graphics.fillRect(100, 90, getWidth() - 200, 420);
    }
    else
    {
      graphics.setColor(Color.BLACK);
      graphics.fillRect(95, 95, 210, 210);
    }
  }

  private Data getData() {
    Data data = new Data();

    if (chartType == chartTypeBar)
      getBarChartData(data);
    else
      getPieChartData(data);
    return data;
  }

  private void getPieChartData(Data data) {
    if (displayType.equals(displayTypeFull))
    {
      data.specialData.add("Pie Chart");
    }
    else
    {
      data.data3point14 = new String[2];
      data.data3point14[1] = "Small";
      data.data3point14[0] = "Pie" + " Chart";
    }
  }

  private void getBarChartData(Data data) {
    if (displayType.equals(displayTypeFull))
    {
      data.data = new String[1];
      data.data[0] = "Bar Chart";
    }
    else
    {
      data.data = new String[2];
      int i = 0;
      data.data[i++] = "Bar Chart";
      data.data[i++] = "Small";
    }
  }
}