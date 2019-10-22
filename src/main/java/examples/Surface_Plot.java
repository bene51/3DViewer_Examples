package examples;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.StackConverter;
import ij3d.Content;
import ij3d.Image3DUniverse;
import surfaceplot.SurfacePlotGroup;

public class Surface_Plot implements PlugIn {

	public static void main(String[] args) {
		new ij.ImageJ();
		IJ.runPlugIn("ij3d.examples.Surface_Plot", "");
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
		double a = -40 * Math.PI / 180;
		univ.getViewPlatformTransformer().rotateY(-a);
		univ.getViewPlatformTransformer().rotateX(a);

		// Add the image as a volume
		Content c = univ.addSurfacePlot(imp);
		sleep(5);

		// Retrieve the VoltexGroup
		SurfacePlotGroup splot = (SurfacePlotGroup)c.getContent();

		// Scroll through the slices
		for(int i = 0; i < 15; i++) {
			splot.setSlice(i + 1);
			sleep(1);
		}
	}

	private static void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
