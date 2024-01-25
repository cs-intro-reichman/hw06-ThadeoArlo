import java.awt.Color;

public class ImageProcessingTester {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ImageProcessingTester <function_name> <filename1> [filename2]");
            return;
        }

        String functionName = args[0];
        String filename1 = args[1];
        String filename2 = args.length > 2 ? args[2] : null;

        switch (functionName) {
            case "flippedHorizontally":
                testFlippedHorizontally(filename1);
                break;
            case "flippedVertically":
                testFlippedVertically(filename1);
                break;
            case "grayScaled":
                testGrayScaled(filename1);
                break;
            case "scaled1":
                testScaled1(filename1);
                break;
            case "scaled2":
                testScaled2(filename1);
                break;
            case "luminance":
                testLuminance();
                break;
            case "blend":
                if (filename2 != null) {
                    testBlend(filename1, filename2);
                } else {
                    System.out.println("Usage: java ImageProcessingTester blend <filename1> <filename2>");
                }
                break;
            default:
                System.out.println("Invalid function name.");
        }
    }

    private static void print(Color[][] image) {
        for (Color[] row : image) {
            for (Color pixel : row) {
                System.out.print("(" + pixel.getRed() + "," + pixel.getGreen() + "," + pixel.getBlue() + ") ");
            }
            System.out.println();
        }
    }

    private static void testFlippedHorizontally(String filename) {
        // Read the image from the file
        Color[][] sampleImage = Runigram.read(filename);

        Color[][] result = Runigram.flippedHorizontally(sampleImage);
        print(result);
    }

    private static void testFlippedVertically(String filename) {
        // Read the image from the file
        Color[][] sampleImage = Runigram.read(filename);

        Color[][] result = Runigram.flippedVertically(sampleImage);
        print(result);
    }

    private static void testGrayScaled(String filename) {
        // Read the image from the file
        Color[][] sampleImage = Runigram.read(filename);

        Color[][] result = Runigram.grayScaled(sampleImage);
        print(result);
    }

    private static void testScaled1(String filename) {
        // Read the image from the file
        Color[][] sampleImage = Runigram.read(filename);

        Color[][] result = Runigram.scaled(sampleImage, 200, 150);
        print(result);
        System.out.println(result.length);
        System.out.println(result[0].length);
    }

    private static void testScaled2(String filename) {
        // Read the image from the file
        Color[][] sampleImage = Runigram.read(filename);

        Color[][] result = Runigram.scaled(sampleImage, 200, 300);
        print(result);
        System.out.println(result.length);
        System.out.println(result[0].length);
    }

    private static void testLuminance() {

        Color color = new Color(255,255,255);

        Color result = Runigram.luminance(color);
        System.out.println(result);
    }

    private static void testBlend(String filename1, String filename2) {
        // Read two images from the files
        Color[][] sampleImage1 = Runigram.read(filename1);
        Color[][] sampleImage2 = Runigram.read(filename2);
        sampleImage1 = Runigram.scaled(sampleImage1, 200, 200);
        sampleImage2 = Runigram.scaled(sampleImage2, 200, 200);

        Color[][] result = Runigram.blend(sampleImage1, sampleImage2, 0.5);
        print(result);
    }
}
