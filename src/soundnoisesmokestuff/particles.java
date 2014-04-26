package soundnoisesmokestuff;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

class Particle {
	PVector location;
	PVector velocity;
	PVector acceleration;
	float lifespan;
	PImage img;
	PApplet parent;

	Particle(PVector location, PImage image, PApplet parent) {
		this.parent = parent;
		this.acceleration = new PVector(0, 0);
		float vx = (float) (parent.randomGaussian() * 0.3);
		float vy = (float) (parent.randomGaussian() * 0.3 - 1.0);
		this.velocity = new PVector(vx, vy);
		this.location = location.get();
		this.lifespan = 100.0f;
		this.img = image;
	}

	void run() {
		updateLocation();
		render();
	}

	// Method to apply a force vector to the Particle object
	// Note we are ignoring "mass" here
	void applyForce(PVector f) {
		acceleration.add(f);
	}

	void updateLocation() {
		velocity.add(acceleration);
		location.add(velocity);
		lifespan -= 2.5;
		acceleration.mult(0); // clear Acceleration
	}

	void render() {
		parent.imageMode(parent.CENTER);
		parent.tint(255, lifespan);
		parent.image(img, location.x, location.y);
	}

	boolean isDead() {
		if (lifespan <= 0.0) {
			return true;
		} else {
			return false;
		}
	}
}