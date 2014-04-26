package soundnoisesmokestuff;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

@SuppressWarnings("serial")
public class IndustrialSounds extends PApplet {

	ParticleSystem ps;
	SoundVisualizer sv;

	@Override
	public void setup() {
		size(1024, 768);
		frameRate(30);
		smooth();
		strokeWeight(1);
		PImage img = loadImage("../../data/texture.png");
		ps = new ParticleSystem(0, new PVector(width / 2, height / 2), img,
				this);
		sv = new SoundVisualizer(this);
	}

	@Override
	public void draw() {
		background(sv.hiFreq * 10);

		// Calculate a "wind" force based on mouse horizontal position
		float dx = -0.1f * sv.midFreq;
		PVector wind = new PVector(0, dx);
		pushMatrix();
		ps.applyForce(wind);
		ps.run();
		for (int i = 0; i < 2; i++) {
			ps.addParticle();
		}
		popMatrix();
		sv.draw();
	}
}
