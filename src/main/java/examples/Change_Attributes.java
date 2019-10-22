package examples;

import org.scijava.vecmath.Color3f;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.StackConverter;
import ij3d.Content;
import ij3d.Image3DUniverse;

public class Change_Attributes implements PlugIn {

	  public static void main(String[] args) {
		    new ij.ImageJ();
		    IJ.runPlugIn("ij3d.examples.Change_Attributes", "");
	  }

	  @Override
	public void run(String arg) {

		    // Open an image
		    String path = "/home/bene/PhD/brains/template.tif";
		    ImagePlus imp = IJ.openImage(path);
		    new StackConverter(imp).convertToGray8();

		    // Create a universe and show it
		    Image3DUniverse univ = new Image3DUniverse();
		    univ.show();

		    // Add the image as an isosurface
		    Content c = univ.addMesh(imp);
		    sleep(5);

		    // Display the Content in purple
		    c.setColor(new Color3f(0.5f, 0, 0.5f));
		    sleep(5);

		    // Make it transparent
		    c.setTransparency(0.5f);
		    sleep(5);

		    // Change the isovalue (= threshold) of the surface
		    c.setThreshold(50);
		    sleep(5);

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
