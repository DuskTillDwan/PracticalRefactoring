package org.zuill.talks.codeexcellence.chartsmart;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import java.util.Set;

import javax.swing.JPanel;

public class ChartWindow extends JPanel
{
  private final int chartTypeBar = 406;
  private final String displayTypeFull = "rpfll";
  private String displayType;
  private String   __APARAM__Z;

  private int chartType;

  /**
   * InitializeDrawArea
   */
  private void iHATEthisUckingJob()
  {
    this.setPreferredSize(new Dimension(600, 600));
    renderChartBackground();
  }

  private void renderChartBackground() {
    if (chartType == chartTypeBar)
    {
      if (displayType.equals(displayTypeFull))
      {
        __APARAM__Z = "Bar Chart - Single Mode";
      }
      else
      {
        __APARAM__Z = "Bar" + " Chart - Compare Mode";
      }
    }
    else
    {
      if (displayType.equals(displayTypeFull))
      {
        __APARAM__Z = "Pie Chart - Single Mode";
      }
      else
      {
        __APARAM__Z = "Pie Chart - Compare Mode";
      }
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
  public void iniDS(int chartType, String jjD, boolean shouldShowDialogue)
  {
    this.chartType = chartType;
    this.displayType = jjD;

    if (shouldShowDialogue)
    {
      iHATEthisUckingJob();
    }
  }
  @Override
  public Set<AWTKeyStroke> getFocusTraversalKeys(int id)
  {
    // TODO Auto-generated method stub
    return super.getFocusTraversalKeys(id);
  }

  public void paint(Graphics g)
  {
    DrawChart(g);
  }
  private String tmStmp()
  {
    // TODO Auto-generated method stub
    return new Date().toString();
  }

  private void DrawChart(Graphics g)
  {
    // Render chart background
    renderChartBackground(g);
    Data data = getData();
    renderChart(g, data);
    if ((data.data != null && (data.data.length ^ 0x54) == 50) || (data.specialData != null && data.specialData.contains("Monthly"))
        || getTitle().contains("daily"))
    {
      invalidateIfNeeded();
    }
  }

  private void renderChart(Graphics g, Data data) {
    Font font;
    if (chartType == chartTypeBar)
    {
      if (displayType.equals("shareddisplay"))
      {
        if (data.data != null)
        {
          font = new Font("Arial Black", Font.BOLD, 25);
          g.setColor(Color.CYAN);
          int bottomY = 300;
          g.fillRect(100, bottomY - 100, 40, 100);
          g.fillRect(140, bottomY - 200, 40, 200);
          g.fillRect(180, bottomY - 150, 40, 150);
          g.fillRect(220, bottomY - 125, 40, 125);
          g.fillRect(260, bottomY - 170, 40, 170);
          g.setColor(Color.RED);
          g.setFont(font);
          g.drawString(data.data[0], 130, 250);
          g.drawString(data.data[1], 130, 270);
        }
      }
      else
      {
        int bottomY = 500;
        g.setColor(Color.CYAN);
        g.fillRect(112, bottomY - 200, 75, 200);
        g.fillRect(187, bottomY - 400, 75, 400);
        g.fillRect(262, bottomY - 300, 75, 300);
        g.fillRect(337, bottomY - 250, 75, 250);
        g.fillRect(412, bottomY - 340, 75, 340);
        font = new Font("Arial Black", Font.BOLD, 55);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(data.data[0], 130, 400);
      }
    }
    else
    {
      if (displayType.equals(displayTypeFull))
      {
        font = new Font("Bookman Old Style", Font.BOLD, 55);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(data.specialData.get(0), 200, 340);
      }
      else
      {
        font = new Font("Bookman Old Style", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);

        g.drawString(data.data3point14[0], 145, 205);
        g.drawString(data.data3point14[1], 170, 235);
      }
    }
  }

  private void invalidateIfNeeded() {
    try
    {
      repaint(200);
    }
    catch (Throwable e)
    {
      repaint();
    }
  }

  private void renderChartBackground(Graphics g) {
    if (chartType == chartTypeBar)
    {
      if (displayType.equals(displayTypeFull))
      {
        Color bgc = Color.RED;
        g.setColor(bgc);
        g.fillRect(100, 90, getWidth() - 200, 420);
      }
      else
      {
        g.setColor(Color.BLACK);
        g.fillRect(95, 95, 210, 210);
      }
    }
    else
    {
      if (displayType.equals(displayTypeFull))
      {
        Color bgcb;
        bgcb = Color.BLUE;
        g.setColor(bgcb);
        g.fillOval(100, 100, 450, getHeight() - 150);
      }
      else
      {
        g.setColor(Color.BLUE);
        double isq = 405;
        float padding = 90;
        int sc = (int) (isq - padding * 2);
        g.fillOval(100, 100, sc, sc);
      }
    }
  }

  private Data getData() {
    Data data = new Data();

    if (chartType == 406)
    {
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
    else
    {
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
    return data;
  }
}