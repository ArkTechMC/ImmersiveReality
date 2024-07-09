package com.iafenvoy.rlcraft.gui;

import com.iafenvoy.rlcraft.IconStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PreLaunchWindow {
    private static final JDialog frame = new JDialog();
    private static boolean disposed = false;

    static {
        frame.setTitle("Re: RLCraft pre-launch window");
        frame.setResizable(true);
        frame.setSize(300, 82);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.addKeyListener(new PreLaunchWindowKeyListener());
        frame.setIconImage(new ImageIcon(IconStorage.getUrl("icon_256x256.png")).getImage());

        JProgressBar memoryBar = new JProgressBar();
        memoryBar.setIndeterminate(false);
        memoryBar.setBackground(Color.LIGHT_GRAY);
        memoryBar.setForeground(Color.RED);
        memoryBar.setStringPainted(true);
        memoryBar.setString("Memory: 0/0MB");
        memoryBar.setMinimum(0);
        frame.add(memoryBar, BorderLayout.NORTH);
        new Thread(() -> {
            while (!disposed) {
                long memMax = Runtime.getRuntime().maxMemory();//Get all can allocate, not already allocated
                long memTotal = Runtime.getRuntime().totalMemory();//Here is already allocated
                long memFree = Runtime.getRuntime().freeMemory();
                long memUsed = memTotal - memFree;
                long maxInMb = bytesToMb(memMax);
                long usedInMb = bytesToMb(memUsed);
                memoryBar.setMaximum((int) maxInMb);
                memoryBar.setValue((int) usedInMb);
                memoryBar.setString(String.format("Memory: %d/%dMB", usedInMb, maxInMb));
            }
        }).start();

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBackground(Color.LIGHT_GRAY);
        progressBar.setForeground(Color.BLUE);
        progressBar.setStringPainted(true);
        progressBar.setString("Re: RLCraft is launching, please wait.");
        frame.add(progressBar, BorderLayout.SOUTH);
    }

    public static void display() {
        if (disposed) throw new IllegalStateException("Pre-launch window has been disposed!");
        frame.setVisible(true);
    }

    public static void remove() {
        if (disposed) return;
        frame.setVisible(false);
        frame.dispose();
        disposed = true;
    }

    public static class PreLaunchWindowKeyListener extends KeyAdapter {
        private static Tetris tetris = null;

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == 't' && tetris == null) {
                tetris = new Tetris();
                tetris.setAlwaysOnTop(true);
                tetris.setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        display();
    }

    private static long bytesToMb(long bytes) {
        return bytes / 1024L / 1024L;
    }
}
