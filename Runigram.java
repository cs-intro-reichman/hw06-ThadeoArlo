import java.awt.Color;

public class Runigram {
	public static void main(String[] args) {
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);
		// Color[][] imageOut;
		// imageOut = flippedHorizontally(tinypic);
		// imageOut = flippedVertically(tinypic);
		// imageOut = grayScaled(tinypic);
		// imageOut = scaled(tinypic,3,5);
		// System.out.println();
		// print(imageOut);
	}

	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		Color[][] image = new Color[numRows][numCols];
		System.out.println(in.readLine());	
		for (int i=0; i<numRows; i++) {
			for (int j=0; j<numCols; j++) {
				image[i][j] = new Color(in.readInt(),in.readInt(),in.readInt());
			}
		}
		return image;
	}

	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed()); 
		System.out.printf("%3s,", c.getGreen());
        System.out.printf("%3s",  c.getBlue());
        System.out.print(")  ");
	}

	private static void print(Color[][] image) {
		int row = image.length;
		int col = image[0].length;
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) { 
				print(image[i][j]);
			}
			System.out.println();
		}
	}
	
	public static Color[][] flippedHorizontally(Color[][] image) {
		int row = image.length;
		int col = image[0].length;
		Color[][] newImage = new Color[row][col];
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				newImage[i][j] = image[i][col-1-j];
			}
		}
		return newImage;
	}
	
	public static Color[][] flippedVertically(Color[][] image){
		int row = image.length;
		int col = image[0].length;
		Color[][] newImage = new Color[row][col];
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				newImage[i][j] = image[row-1-i][j];
			}
		}
		return newImage;
	}
	
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		int lum = (int)(0.299*pixel.getRed() + 0.587*pixel.getGreen() + 0.114*pixel.getBlue());
		return new Color(lum,lum,lum);
	}
	
	public static Color[][] grayScaled(Color[][] image) {
		int row = image.length;
		int col = image[0].length;
		Color[][] newImage = new Color[row][col];
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				newImage[i][j] = luminance(image[i][j]);
			}
		}
		return newImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		int oriH = image.length;
		int oriW = image[0].length;
		Color[][] newImage = new Color[height][width];
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				newImage[i][j] = image[(int)(i*((double)oriH/height))][(int)(j*((double)oriW/width))];
				// int srcI = (int)Math.floor(i * ((double)oriH / height));
				// int srcJ = (int)Math.floor(j * ((double)oriW / width));
				// srcI = Math.min(srcI, oriH - 1);
				// srcJ = Math.min(srcJ, oriW - 1);
				// newImage[i][j] = image[srcI][srcJ];
			}
		}
		return newImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int r = (int)(alpha*c1.getRed()+(1-alpha)*c2.getRed());
		int g = (int)(alpha*c1.getGreen()+(1-alpha)*c2.getGreen());
		int b = (int)(alpha*c1.getBlue()+(1-alpha)*c2.getBlue());
		r = Math.min(255, Math.max(0, r));
		g = Math.min(255, Math.max(0, g));
		b = Math.min(255, Math.max(0, b));
		return new Color(r,g,b);
	}

	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		int row = image1.length;
		int col = image1[0].length;
		Color[][] newImage = new Color[row][col];
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				newImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		return newImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		Color[][] scaledTarget = scaled(target, source[0].length, source.length);
		for (int i=0; i<n; i++) {
			display(blend(source, scaledTarget, ((double)i)/n));
			// StdDraw.pause(500);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

