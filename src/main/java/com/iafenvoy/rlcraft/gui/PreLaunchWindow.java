package com.iafenvoy.rlcraft.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PreLaunchWindow {
    private static final JDialog frame = new JDialog();
    private static boolean disposed = false;

    static {
        frame.setTitle("Re: RLCraft pre-launch window");
        frame.setResizable(false);
        frame.setSize(300, 75);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setAlwaysOnTop(true);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBorderPainted(true);
        progressBar.setBackground(Color.LIGHT_GRAY);
        progressBar.setForeground(Color.BLUE);
        progressBar.setStringPainted(true);
        progressBar.setString("Re: RLCraft is launching, please wait.<br>Press 'P' to play Tetris.");
        progressBar.setBorderPainted(true);
        frame.add(progressBar);

        frame.addKeyListener(new PreLaunchWindowKeyListener());
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
            if (e.getKeyChar() == 'p' && tetris == null) {
                tetris = new Tetris();
                tetris.setAlwaysOnTop(true);
                tetris.setVisible(true);
            }
        }
    }
}
