package examples;

import java.awt.Frame;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij3d.IJAdapter;
import ij3d.Image3DUniverse;
import ij3d.ImageCanvas3D;

public class Two_Canvasses implements PlugIn {

	@Override
	public void run(String arg) {

		// Open a hyperstack
		String path = "d:/template.tif";
		ImagePlus imp = IJ.openImage(path);

		// Create a universe and show it
		Image3DUniverse univ = new Image3DUniverse();
		univ.addVoltex(imp);
		univ.show();

		// retrieve some info about the 1st canvas
		int w = univ.getCanvas().getWidth();
		int h = univ.getCanvas().getHeight();

		// Create a new canvas and add it
		ImageCanvas3D canvas2 = new ImageCanvas3D(w, h, new IJAdapter());
		univ.getViewer().getView().addCanvas3D(canvas2);

		// create a new window, add the canvas and show it
		Frame f = new Frame();
		f.add(canvas2);
		f.pack();
		f.show();
	}
}
