package soundnoisesmokestuff;

import processing.core.PApplet;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class SoundVisualizer {

	FFT fft; // this is god damn magic :D

	private PApplet parent;
	private ddf.minim.AudioPlayer track;
	private Minim minim;

	public float lowFreq = 0;
	public float midFreq = 0;
	public float hiFreq = 0;

	private String audioSample = "../../data/test.mp3";

	SoundVisualizer(PApplet parent) {
		this.parent = parent;
		minim = new Minim(parent);
		track = minim.loadFile(audioSample, 2048);
		fft = new FFT(track.bufferSize(), track.sampleRate());
		track.loop();
	}

	void draw() {
		parent.textSize(24);
		parent.strokeWeight(1.5f);

		// forward fft on one of track's buffers
		fft.forward(track.mix);

		parent.text("frequency", 0, parent.height * 4 / 5);
		parent.pushMatrix();
		parent.translate(200, 0);

		// Draw frequency spectrum as a series of vertical lines
		for (int i = 0; i < 0 + fft.specSize(); i++) {
			parent.stroke(i, i, i);
			parent.line(i, parent.height * 4 / 5, i, parent.height * 4 / 5
					- fft.getBand(i) * 4);

			if (i >= 100 && i <= 200) {
				parent.stroke(255, 255, 0);
				parent.line(i, parent.height * 4 / 5, i, parent.height * 4 / 5
						- fft.getBand(i) * 4);
			}
			if (i >= 200 && i <= 300) {
				parent.stroke(0, 255, 255);
				parent.line(i, parent.height * 4 / 5, i, parent.height * 4 / 5
						- fft.getBand(i) * 4);
			}
		}

		parent.popMatrix();

		lowFreq = fft.getBand(50);
		midFreq = fft.getBand(150);
		hiFreq = fft.getBand(350);

		// changing the line color
		parent.stroke(0, 255, 0);

		// the waveform is drawn by connecting neighbor values with a line.
		// The values in the buffers are between -1 and 1.
		// If we don't scale them up our waveform will look like a straight
		// line.
		// Thus each of the values is multiplied by 50

		parent.text("left amplitude", 0, 50);
		parent.text("right amplitude", 0, 150);
		parent.text("mixed amplitude", 0, 250);
		for (int i = 200; i < track.left.size() - 1; i++) {
			parent.line(i, 50 + track.left.get(i) * 50, i + 1,
					50 + track.left.get(i + 1) * 50);
			parent.line(i, 150 + track.right.get(i) * 50, i + 1,
					150 + track.right.get(i + 1) * 50);
			parent.line(i, 250 + track.mix.get(i) * 50, i + 1,
					250 + track.mix.get(i + 1) * 50);
		}
	}

	void stop() {
		track.close();
		minim.stop();
	}
}
