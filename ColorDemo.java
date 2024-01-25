// In Java, colors are represented by Color objects. 
// The Java class library features a class named Color, 
// which is part of a package named java.awt. When you 
// write a program that uses Color objects, you must first
// import the java.awt.Color class into your code. That's
// what the next statement does.
import java.awt.Color;

/**
 * Illustrates working with Color objects.
 */
public class ColorDemo {
	
	public static void main (String[] args) {	

	    // Constructs a few color objects:		
		Color black  = new Color(0, 0, 0);
		Color white  = new Color(255, 255, 255);
		Color red    = new Color(255, 0, 0);
		Color yellow = new Color(255, 255, 0);

        // To access the RGB values of a Color object,
        // we use three "get" nethods. For example: 
        System.out.println("RGB values of yellow:");     
		System.out.println("R = " + yellow.getRed());   // prints 255
		System.out.println("G = " + yellow.getGreen()); // prints 255
		System.out.println("B = " + yellow.getBlue());  // prints   0

        // Tests the print method: 
		System.out.print("Red = "); print(red);
		System.out.println();

        // Constructs an array of Color objects, and prints the array elements.
		Color[] bwbw = { black, white, black, white };
		for (int i = 0; i < bwbw.length; i++) {
			print(bwbw[i]);
		}
		System.out.println();

	    // Illustrates another way of constructing the above array:
		Color[] bwbw1 = { new Color(0,0,0), new Color(255,255,255), new Color(255,255,255), new Color(0,0,0) };

        // Illustrates how a 4 by 4 array of Color objects can be constructed:
		Color[][] miniChessBoard = { 
			                         { black, white, black, white } ,
			                         { white, black, white, black } ,
			                         { black, white, black, white } ,
			                         { white, black, white, black } ,
		                           };
	}

    /**
	 * Prints the RGB values of a given color, using the format "(rrr,ggg,bbb)". 
	 * @param c - the given color
	 */
	public static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}
}

