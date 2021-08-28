package ru.stqa.jft.sandbox;

public class hw {

    public static void main(String[] args) {
       hello("Vlad");

       Square s = new Square(5);
       System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

       Rectangle r = new Rectangle(4,6);
       System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

       Point p1 = new Point(2,5);
       Point p2 = new Point(4,1);
       Point p3 = new Point(0,0);
       Point p4 = new Point(2,2.23607);
       Point p5 = new Point(-1,-6);
       Point p6 = new Point(5,9);
       Point p7 = new Point(0.4,1.8);
       System.out.println("Расстояние между точками x и y = " + p1.distance(p2));
       System.out.println("Расстояние между точками x и y = " + p2.distance(p1));
       System.out.println("Расстояние между точками x и y = " + p3.distance(p4));
       System.out.println("Расстояние между точками x и y = " + p5.distance(p6));
       System.out.println("Расстояние между точками x и y = " + p7.distance(p1));

    }

    public static void hello (String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }


}