package soundnoisesmokestuff;

public class Preset {
	public ParticleSystem ps;
	public SoundVisualizer sv;
	public boolean psEnabled;
	public boolean colorfullBackground;
	public float colorMultiplier;
	public float redMultiplier;
	public float greenMultiplier;
	public float blueMultiplier;
	public boolean psCenter;

	public Preset() {
	}

	public void loadDefaults() {
		loadPreset(0);
	}

	public void loadPreset(int i) {
		if (i == 0) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 10;
			redMultiplier = 1;
			greenMultiplier = 1;
			blueMultiplier = 1;
			sv.directVal = false;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = false;
		}
		if (i == 1) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 1;
			redMultiplier = 8;
			greenMultiplier = 37;
			blueMultiplier = 37;
			sv.directVal = true;
			sv.equilizerEnabled = true;
			sv.spectrumEnabled = false;
		}
		if (i == 2) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 1;
			redMultiplier = 10;
			greenMultiplier = 0;
			blueMultiplier = 0;
			sv.directVal = false;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = false;
		}
		if (i == 3) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 20;
			redMultiplier = 1;
			greenMultiplier = 1;
			blueMultiplier = 1;
			sv.directVal = false;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = false;
		}
		if (i == 4) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 10;
			redMultiplier = 10;
			greenMultiplier = 0;
			blueMultiplier = 0;
			sv.directVal = true;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = false;
		}

		if (i == 5) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 10;
			redMultiplier = 0;
			greenMultiplier = 15;
			blueMultiplier = 0;
			sv.directVal = true;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = false;
		}
		if (i == 6) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 10;
			redMultiplier = 0;
			greenMultiplier = 0;
			blueMultiplier = 20;
			sv.directVal = true;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = false;
		}
		if (i == 7) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 1;
			redMultiplier = 25;
			greenMultiplier = 50;
			blueMultiplier = 100;
			sv.directVal = true;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = false;
		}
		if (i == 8) {
			psEnabled = true;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 0;
			redMultiplier = 0;
			greenMultiplier = 0;
			blueMultiplier = 0;
			sv.directVal = true;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = false;
		}
		if (i == 9) {
			psEnabled = false;
			psCenter = true;
			colorfullBackground = true;
			colorMultiplier = 0;
			redMultiplier = 0;
			greenMultiplier = 0;
			blueMultiplier = 0;
			sv.directVal = true;
			sv.equilizerEnabled = false;
			sv.spectrumEnabled = true;
		}
	}
	
	void updateConsole(IndustrialSounds industrialSounds) {
		IndustrialSounds.println("-----------------------------");
		IndustrialSounds.println("Presets (1, 2, 3, 4, 5) Default 0");
		IndustrialSounds.println("Options");
		IndustrialSounds.println("p   - Particule System:   ", (psEnabled) ? "Enabled" : "Disabled");
		IndustrialSounds.println("P   - Particule Center:   ", psCenter);
		IndustrialSounds.println("i   - Intensity:          ", (sv.directVal) ? "Hard" : "Soft");
		IndustrialSounds.println("e   - Equilizer:          ", (sv.equilizerEnabled) ? "Enabled" : "Disabled");
		IndustrialSounds.println("s   - Frequency Spectrum: ", (sv.spectrumEnabled) ? "Enabled" : "Disabled");
		IndustrialSounds.println("");
		IndustrialSounds.println("Multipliers");
		IndustrialSounds.println("c/C - Color Multiplier: ", colorMultiplier);
		IndustrialSounds.println("r/R - Red Multiplier:   ", redMultiplier);
		IndustrialSounds.println("g/G - Green Multiplier: ", greenMultiplier);
		IndustrialSounds.println("b/B - Blue Multiplier:  ", blueMultiplier);
	}
}