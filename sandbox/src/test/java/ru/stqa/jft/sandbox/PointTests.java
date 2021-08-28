package ru.stqa.jft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(2,2.23607);
        Assert.assertEquals(p1.distance(p2),3.0000015074829545);
        p1 = new Point(2,5);
        p2 = new Point(4,1);;
        Assert.assertEquals(p1.distance(p2),4.47213595499958);
    }
}
