package soundnoisesmokestuff;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

class ParticleSystem {

	ArrayList<Particle> particles; // An arraylist for all the particles
	PVector origin; // An origin point for where particles are birthed
	PImage img;
	private PApplet parent;

	ParticleSystem(int num, PVector v, PImage img_, PApplet parent) {
		this.parent = parent;
		particles = new ArrayList<Particle>(); // Initialize the arraylist
		origin = v.get(); // Store the origin point
		img = img_;
		for (int i = 0; i < num; i++) {
			// Add "num" amount of particles to the arraylist
			particles.add(new Particle(origin, img, parent));
		}
	}

	void run() {
		for (int i = particles.size() - 1; i >= 0; i--) {
			Particle p = particles.get(i);
			p.run();
			if (p.isDead()) {
				particles.remove(i);
			}
		}
	}

	// Method to add a force vector to all particles currently in the system
	void applyForce(PVector dir) {
		// Enhanced loop!!!
		for (Particle p : particles) {
			p.applyForce(dir);
		}

	}

	void addParticle() {
		particles.add(new Particle(origin, img, parent));
	}

}