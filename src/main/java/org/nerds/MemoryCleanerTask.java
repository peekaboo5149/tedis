package org.nerds;

import java.util.TimerTask;

public class MemoryCleanerTask extends TimerTask {
    @Override
    public void run() {
        System.gc();
    }
}
