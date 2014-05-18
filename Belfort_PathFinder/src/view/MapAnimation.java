package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public abstract class MapAnimation implements ActionListener {
	
	private enum AnimationType {
	        ZOOM_IN, ZOOM_OUT
	    }
	    
    private final AnimationType type;
    private final Timer timer;
    private long t0 = -1L;
    private long dt;
    private final long duration;

    public MapAnimation(AnimationType type, int fps, long duration) {
        this.type = type;
        this.duration = duration;
        int delay = 1000 / fps;
        timer = new Timer(delay, this);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
    }
    
    public AnimationType getType() {
        return type;
    }

    protected abstract void onComplete();

    protected abstract void onFrame();

    public double getFactor() {
        return (double) getDt() / getDuration();
    }

    public void actionPerformed(ActionEvent e) {
        if (getDt() >= duration) {
            kill();
            onComplete();
            return;
        }
        onFrame();
    }

    public long getDuration() {
        return duration;
    }

    public long getDt() {
        if (!timer.isRunning())
            return dt;
        long now = System.currentTimeMillis();
        if (t0 < 0)
            t0 = now;
        return now - t0 + dt;
    }

    public void run() {
        if (timer.isRunning())
            return;
        timer.start();
    }

    public void kill() {
        if (!timer.isRunning())
            return;
        dt = getDt();
        timer.stop();
    }
}
