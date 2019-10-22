package examples;

import org.scijava.vecmath.Point3d;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.StackConverter;
import ij3d.AxisConstants;
import ij3d.Content;
import ij3d.Image3DUniverse;
import orthoslice.OrthoGroup;

public class Orthoslices_ implements PlugIn {

	public static void main(String[] args) {
		new ij.ImageJ();
		IJ.runPlugIn("ij3d.examples.Orthoslices_", "");
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
		Point3d center = new Point3d();
		univ.getViewPlatformTransformer().rotateY(-a);
		univ.getViewPlatformTransformer().rotateX(a);

		// Add the image as a volume
		Content c = univ.addOrthoslice(imp);
		sleep(5);

		// Retrieve the VoltexGroup
		OrthoGroup ortho = (OrthoGroup)c.getContent();

		for(int i = 0; i < 10; i++) {
			ortho.increase(AxisConstants.Z_AXIS);
			sleep(1);
		}

		// Hide the x-axis
		ortho.setVisible(AxisConstants.X_AXIS, false);
		sleep(5);

		// Show it again and hide the z-axis
		ortho.setVisible(AxisConstants.X_AXIS, true);
		ortho.setVisible(AxisConstants.Z_AXIS, false);
		sleep(5);

		// Show it again
		ortho.setVisible(AxisConstants.Z_AXIS, true);
	}

	private static void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
