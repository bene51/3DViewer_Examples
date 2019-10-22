package examples;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.scijava.vecmath.Point3d;
import org.scijava.vecmath.Point3f;

import customnode.CustomQuadMesh;
import ij.IJ;
import ij.ImageJ;
import ij.plugin.PlugIn;
import ij3d.Content;
import ij3d.Image3DUniverse;
import ij3d.behaviors.InteractiveBehavior;

public class Custom_Behavior implements PlugIn {

	private static final float W = 5f;
	private static final float H = 5f;
	private Image3DUniverse univ;

	public static void main(String[] args) {
		new ImageJ();
		IJ.runPlugIn("examples.CustomBehavior", "");
	}

	@Override
	public void run(String arg) {

		ArrayList<Point3f> pts = new ArrayList<Point3f>();
		pts.add(new Point3f(0, 0, 0));
		pts.add(new Point3f(W, 0, 0));
		pts.add(new Point3f(W, H, 0));
		pts.add(new Point3f(0, H, 0));

		CustomQuadMesh cqm = new CustomQuadMesh(pts);

		univ = new Image3DUniverse();
		univ.show();
		Content c = univ.addCustomMesh(cqm, "quad");

		// this is necessary for catching the mouse events
		univ.setInteractiveBehavior(new CustomBehavior(univ, c));
	}

	// Class that extends the (default) InteractiveBehavior.
	// It picks the cases we are interested in (i.e. STRG + mouse press),
	// and otherwise calls super.xxx() to invoke the default behavior.
	private class CustomBehavior extends InteractiveBehavior {

		private Content c;

		CustomBehavior(Image3DUniverse univ, Content c) {
			super(univ);
			this.c = c;
		}

		@Override
		public void doProcess(MouseEvent e) {
			if(!e.isControlDown() ||
				e.getID() != MouseEvent.MOUSE_PRESSED) {
				super.doProcess(e);
				return;
			}
			// Get the point on the geometry where the mouse
			// press occurred
			Point3d p = univ.getPicker().
				getPickPointGeometry(c, e.getX(), e.getY());
			if(p == null)
				return;

			IJ.showMessage("Picked " + new Point3f(p));
		}
	}
}

