// 디스플레이
package com.mine.minefront;

import javax.swing.JFrame;
import java.awt.Canvas;

public class Display extends Canvas implements Runnable {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    static final String TITLE = "Minefront 개발 초기 버전";

    // 스레드 객체
    private Thread thread;
    private boolean running = false;

    // 스레드 초기화
    private void start() {
        // 무한 실행 방지
        if (running) return;
        running = true;
        thread = new Thread(this);
        // 스레드 시작
        thread.start();

        System.out.println("작동중");
    }

    // 스레드 종료
    private void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void run() {
        while (running) {

        }
    }

    public static void main(String[] args) {
        Display game = new Display();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        System.out.println("실행중...");

        game.start();
    }
}
