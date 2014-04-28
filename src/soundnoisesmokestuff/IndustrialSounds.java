package soundnoisesmokestuff;

import java.awt.Component;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

@SuppressWarnings("serial")
public class IndustrialSounds extends PApplet {

	Preset data = new Preset();

	@Override
	public void setup() {
		size(1024, 768, OPENGL);
		frameRate(30);
		smooth();
		strokeWeight(1.0f);
		PImage img = loadImage("../../data/texture.png");
		data.ps = new ParticleSystem(0, new PVector(width / 2, height / 2),
				img, this);
		data.sv = new SoundVisualizer(this);
		data.loadDefaults();
		switchResolution();
	}

	private void switchResolution() {
		try {
			GraphicsDevice myGraphicsDevice = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			if (myGraphicsDevice.isDisplayChangeSupported()) {
				DisplayMode myDisplayMode = new DisplayMode(width, height, 32,
						DisplayMode.REFRESH_RATE_UNKNOWN);
				myGraphicsDevice.setDisplayMode(myDisplayMode);
			}

			Component[] myComponents = frame.getComponents();
			for (int i = 0; i < myComponents.length; i++) {
				if (myComponents[i] instanceof PApplet) {
					myComponents[i].setLocation(0, 0);
				}
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void draw() {
		if (data.colorfullBackground) {
			background(data.sv.lowFreq * data.colorMultiplier
					* data.redMultiplier, data.sv.midFreq
					* data.colorMultiplier * data.greenMultiplier,
					data.sv.hiFreq * data.colorMultiplier * data.blueMultiplier);
		}

		// Calculate a "wind" force based on mouse horizontal position
		float dx = -0.1f * data.sv.midFreq;
		PVector wind = new PVector(0, dx);
		pushMatrix();
		data.ps.applyForce(wind);
		data.ps.run();
		if (data.psEnabled) {
			for (int i = 0; i < 1; i++) {
				data.ps.addParticle();
			}
		}
		if (data.colorfullBackground) {

		}
		popMatrix();

		if (data.psCenter)
			data.ps.origin = new PVector(width / 2, height / 2);
		else
			data.ps.origin = new PVector(mouseX, mouseY);

		data.sv.draw();
	}

	@Override
	public void keyPressed() {
		switch (key) {
		case 'p':
		case 'P':
			this.data.psEnabled = !data.psEnabled;
			break;
		case 'f':
		case 'F':
			data.sv.spectrumEnabled = !data.sv.spectrumEnabled;
			break;
		case 'i':
		case 'I':
			data.sv.directVal = !data.sv.directVal;
			break;
		case 'e':
		case 'E':
			data.sv.equilizerEnabled = !data.sv.equilizerEnabled;
			break;
		case 'r':
			data.redMultiplier -= 1;
			break;
		case 'R':
			data.redMultiplier += 1;
			break;
		case 'c':
			data.colorMultiplier -= 1;
			break;
		case 'C':
			data.colorMultiplier += 1;
			break;
		case 'g':
			data.greenMultiplier -= 1;
			break;
		case 'G':
			data.greenMultiplier += 1;
			break;
		case 'b':
			data.blueMultiplier -= 1;
			break;
		case 'B':
			data.blueMultiplier += 1;
			break;
		case '0':
			data.loadDefaults();
			break;
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			data.loadPreset(Character.getNumericValue(key));
			break;
		default:
			break;
		}

		if (keyCode != PConstants.SHIFT)
			data.updateConsole(this);
	}

	@Override
	public void mouseClicked() {
		data.psCenter = !data.psCenter;
	}
}
