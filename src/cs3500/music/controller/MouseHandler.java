package cs3500.music.controller;

import java.awt.*;
import java.awt.event.*;

/**
 * Represent a handler for mouse events
 */
public class MouseHandler implements MouseListener {
  private Point p;
  private Point startP;
  private Point endP;

  /**
   * Empty default constructor
   */
  public MouseHandler() {
    this.p = new Point(0, 0);
    this.startP = new Point(0, 0);
    this.endP = new Point(0, 0);
  }

  public Point getP() {
    return p;
  }

  public Point getStartP() {
    return startP;
  }

  public Point getEndP() {
    return endP;
  }

  public Point setPoint(Point point) {
    int x = point.x / 20 - 2;
    int y = point.y / 20 - 1;
    return new Point(x, y);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    this.p = setPoint(e.getPoint());
    // System.out.println("click: " + p.x + " " + p.y);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    this.startP = setPoint(e.getPoint());
    // System.out.println("press: " + startP.x + " " + startP.y);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    this.endP = setPoint(e.getPoint());
    // System.out.println("release:" + endP.x + " " + endP.y);

  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
