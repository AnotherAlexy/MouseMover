package com.company;

import java.awt.*;
import java.time.LocalDateTime;

public class Main {

    private static float baseMovement = 2;

    private static Point prevLocation = new Point();

    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void moveLocation(PointerInfo pointerInfo) {
        int dx,dy;
        do {
            dx = (int)Math.floor(Math.random() * (baseMovement * 2.0 + 1.0)  - baseMovement);
            dy = (int)Math.floor(Math.random() * (baseMovement * 2.0 + 1.0)  - baseMovement);
        } while((dx == 0) && (dy == 0));
        Point p = pointerInfo.getLocation();
        System.out.println(dx + " " + dy);
        robot.mouseMove(p.x + dx, p.y + dy);
        prevLocation = new Point(MouseInfo.getPointerInfo().getLocation());
        System.out.println(LocalDateTime.now() + ": " + p + " -> " + prevLocation);
    }

    private static void checkPos(PointerInfo pointerInfo) {
        if (pointerInfo.getLocation().equals(prevLocation)) {
            moveLocation(pointerInfo);
        } else {
            prevLocation.setLocation(pointerInfo.getLocation());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        prevLocation = MouseInfo.getPointerInfo().getLocation();
        while(true) {
            checkPos(MouseInfo.getPointerInfo());
            // once per minute...
            Thread.sleep(1000 * 60);
        }
    }
}
