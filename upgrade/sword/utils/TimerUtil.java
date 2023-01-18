package upgrade.sword.utils;

public class TimerUtil {
    public static final TimerUtil INSTANCE = new TimerUtil();

    private long currentTime = System.currentTimeMillis();
    public boolean pass(long time) {
        if (System.currentTimeMillis() - currentTime >= time) {
            reset();
            return true;
        }
        return false;
    }

    public void reset() {
        currentTime = 0;
    }

}
