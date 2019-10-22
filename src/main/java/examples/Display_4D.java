package examples;

import view4d.Timeline;

import ij.plugin.PlugIn;
import ij.ImagePlus;
import ij.IJ;
import ij.Prefs;

import ij3d.Content;
import ij3d.Image3DUniverse;

public class Display_4D implements PlugIn {

	public static void main(String[] args) {
		new ij.ImageJ();
		IJ.runPlugIn("ij3d.examples.Display_4D", "");
	}

	public void run(String arg) {

		// Open a hyperstack
		ImagePlus imp = IJ.openImage(
				Prefs.getImagesURL() + "Spindly-GFP.zip");

		// Create a universe and show it
		Image3DUniverse univ = new Image3DUniverse();
		univ.show();

		// load the stack in the viewer
		univ.addVoltex(imp);

		// get the Timeline object
		Timeline tl = univ.getTimeline();

		// start playback
		tl.play();

		// wait a bit
		try {
			Thread.sleep(5000);
		} catch(InterruptedException e) {}


		// Stop animation
		tl.pause();
	}
}
