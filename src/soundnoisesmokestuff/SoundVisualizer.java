package soundnoisesmokestuff;

import processing.core.PApplet;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

public class SoundVisualizer {

	FFT fft; // this is god damn magic :D

	private PApplet parent;
	private AudioInput audioStream;
	private Minim minim;

	public float lowFreq = 0;
	public float midFreq = 0;
	public float hiFreq = 0;

	private float lowTemp = 0;
	private float midTemp = 0;
	private float hiTemp = 0;

	public boolean directVal = false;
	public boolean spectrumEnabled = false;
	public boolean equilizerEnabled = false;

	SoundVisualizer(PApplet parent) {
		this.parent = parent;
		minim = new Minim(parent);

		// Load microphone input
		audioStream = minim.getLineIn(Minim.STEREO, 1024);

		// All of the magic comes from this :)
		fft = new FFT(audioStream.bufferSize(), audioStream.sampleRate());
	}

	void draw() {

		// forward fft on one of track's buffers
		fft.forward(audioStream.mix);

		// parent.text("frequency", 0, parent.height * 4 / 5);
		parent.pushMatrix();
		parent.translate(0, 0);

		// Draw frequency spectrum as a series of vertical lines
		// for (int i = 0; i < 0 + fft.specSize(); i++) {}

		parent.popMatrix();

		lowTemp = 0;
		midTemp = 0;
		hiTemp = 0;

		// low freqs
		for (int i = 0; i <= 100; i++) {
			if (!directVal)
				lowTemp += fft.getBand(i);
			// Frequency Spectrum
			if (spectrumEnabled) {
				drawSpectrum(i, 255.0f, 0, 0.0f, 100.0f);
			}
		}

		// mid freqs
		for (int i = 100; i <= 200; i++) {
			if (!directVal)
				midTemp += fft.getBand(i);
			if (spectrumEnabled) {
				drawSpectrum(i, 0, 255.0f, 0.0f, 100.0f);
			}
		}

		// hi freqs
		for (int i = 200; i <= 300; i++) {
			if (!directVal)
				hiTemp += fft.getBand(i);
			if (spectrumEnabled) {
				drawSpectrum(i, 0, 0, 255.0f, 100.0f);
			}
		}

		if (directVal) {
			lowFreq = fft.getBand(50);
			midFreq = fft.getBand(150);
			hiFreq = fft.getBand(250);
		} else {
			lowFreq = lowTemp / 100.0f;
			midFreq = midTemp / 100.0f;
			hiFreq = hiTemp / 100.0f;
		}

		if (equilizerEnabled) {
			drawEquilizer();
		}
	}

	private void drawEquilizer() {
		parent.stroke(255.0f, 255.0f, 255.0f, 100.0f);
		for (int i = 0; i < audioStream.mix.size() - 1; i++) {
			parent.line(i, parent.height / 2 + audioStream.mix.get(i) * 100,
					i + 1, parent.height / 2 + audioStream.mix.get(i + 1) * 100);
		}
	}

	private void drawSpectrum(int x, float r, float g, float b, float a) {
		parent.stroke(r, g, b, a);
		parent.line(x, parent.height, x, parent.height - fft.getBand(x) * 4);
	}

	void stop() {
		audioStream.close();
		minim.stop();
	}
}
