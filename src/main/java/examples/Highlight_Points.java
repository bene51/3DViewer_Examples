package examples;

import vib.PointList;

import ij.process.StackConverter;
import ij.plugin.PlugIn;
import ij.ImagePlus;
import ij.IJ;

import ij3d.Image3DUniverse;
import ij3d.Content;

public class Highlight_Points implements PlugIn {

	  public static void main(String[] args) {
		    new ij.ImageJ();
		    IJ.runPlugIn("ij3d.examples.Highlight_Points", "");
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
		    Content c = univ.addVoltex(imp);
		    sleep(5);

		    // Make the point list visible
		    c.showPointList(true);
		    
		    // Retrieve the point list
		    PointList pl = c.getPointList();

		    // Add a few points
		    pl.add(190, 450, 170);
		    sleep(5);
		    pl.add(330, 370, 300);
		    sleep(5);
		    pl.add(430, 90, 150);
		    sleep(5);

		    // Add a point at a specific canvas position
		    univ.getPicker().addPoint(c, 256, 256);
		    sleep(5);

		    // Change the size of the points
		    float curr = c.getLandmarkPointSize();
		    c.setLandmarkPointSize(curr * 2);
		    sleep(5);

		    // delete the first point
		    pl.remove(0);
		    sleep(5);

		    // rename the now first point
		    pl.rename(pl.get(0), "newName");
		    sleep(5);

		    // change the position of the now first point
		    pl.placePoint(pl.get(0), 190, 450, 170);
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
