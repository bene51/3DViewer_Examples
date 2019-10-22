package examples;

import ij.process.StackConverter;
import ij.plugin.PlugIn;
import ij.ImagePlus;
import ij.IJ;

import ij3d.Image3DUniverse;

public class Movie_Recording implements PlugIn {

	  public static void main(String[] args) {
		    new ij.ImageJ();
		    IJ.runPlugIn("ij3d.examples.Movie_Recording", "");
	  }

	  public void run(String arg) {

		    // Open an image
		    String path = "/home/bene/PhD/brains/template.tif";
		    ImagePlus imp = IJ.openImage(path);
		    new StackConverter(imp).convertToGray8();

		    // Create a universe and show it
		    Image3DUniverse univ = new Image3DUniverse();
		    univ.show();

		    // Add the image as a volume
		    univ.addVoltex(imp);
		    sleep(5);

		    // animate the universe
		    univ.startAnimation();
		    sleep(5);

		    // record a 360 degree rotation around the y-axis
		    ImagePlus movie = univ.record360();
		    movie.show();
		    univ.pauseAnimation();


		    // remove all contents and close the universe
		    univ.removeAllContents();
		    univ.close();
	  }

	  private static void sleep(int sec) {
		    try {
				Thread.sleep(sec * 1000);
		    } catch(InterruptedException e) {
				System.out.println(e.getMessage());
		    }
	  }
}
