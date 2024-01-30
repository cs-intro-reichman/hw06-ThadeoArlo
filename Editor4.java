import java.awt.Color;

// Converts an Image to a greyscaled version of itself.
// Takes in an image file name and the number of steps for morphing.

public class Editor4 {

	public static void main (String[] args) {
		String source = args[0];
		int n = Integer.parseInt(args[1]);
		Color[][] sourceImage = Runigram.read(source);
		Color[][] targetImage = Runigram.grayScaled(sourceImage);
		Runigram.setCanvas(sourceImage);
		Runigram.morph(targetImage, sourceImage, n);
	}
}
