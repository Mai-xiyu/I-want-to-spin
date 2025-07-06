package org.xiyu.yee.datl.config;

public class DatlConfig {
    private static DatlConfig instance;
    
    private boolean spinEnabled = false;
    private float spinSpeed = 2.0f;
    private boolean bhopEnabled = false;
    
    private DatlConfig() {}
    
    public static DatlConfig getInstance() {
        if (instance == null) {
            instance = new DatlConfig();
        }
        return instance;
    }
    
    public boolean isSpinEnabled() {
        return spinEnabled;
    }

    public float getSpinSpeed() {
        return spinSpeed;
    }
    
    public void setSpinSpeed(float spinSpeed) {
        this.spinSpeed = spinSpeed;
    }
    
    public boolean isBhopEnabled() {
        return bhopEnabled;
    }

    public void toggleSpin() {
        this.spinEnabled = !this.spinEnabled;
    }
    
    public void toggleBhop() {
        this.bhopEnabled = !this.bhopEnabled;
    }
}
