package examples;

import java.util.ArrayList;
import java.util.List;

import org.scijava.vecmath.Point3f;

import customnode.CustomPointMesh;
import ij.IJ;
import ij.plugin.PlugIn;
import ij3d.Image3DUniverse;

public class Plot_Points implements PlugIn {

	public static void main(String[] args) {
		new ij.ImageJ();
		IJ.runPlugIn("ij3d.examples.Plot_Points", "");
	}

	@Override
	public void run(String arg) {

		// Create a few example points
		float[][] points = new float[10][3];
		for(int i = 0; i < points.length; i++)
			points[i][0] = points[i][1] = points[i][2] = i;

		// Create a CustomMesh from them
		List<Point3f> mesh = new ArrayList<Point3f>();
		for(int i = 0; i < points.length; i++) {
			mesh.add(new Point3f(
						points[i][0],
						points[i][1],
						points[i][2]));
		}

		CustomPointMesh cm = new CustomPointMesh(mesh);

		// Create a universe and show it
		Image3DUniverse univ = new Image3DUniverse();
		univ.showAttribute(
			Image3DUniverse.ATTRIBUTE_COORD_SYSTEM, false);
		univ.show();

		// Add the mesh
		String name = "points";
		univ.addCustomMesh(cm, name);

		cm.setPointSize(10);

		// Have a look at the source code of CustomPointMesh
		// for changing point size and anti-aliasing
	}
}

