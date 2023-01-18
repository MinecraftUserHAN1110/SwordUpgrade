package upgrade.sword.utils;

public class ThreadUtil {
    public static final ThreadUtil THREAD = new ThreadUtil();

    private Runnable runnable;
    private Thread thread;

    public ThreadUtil addRunnable(Runnable runnable) {
        thread = new Thread(runnable);
        return this;
    }

    public void start() {
        thread.start();
    }

    public ThreadUtil addThread(Thread thread) {
        this.thread = thread;
        return this;
    }

    public ThreadUtil modifyThread(Thread thread) {
        this.thread = thread;
        return this;
    }
}
