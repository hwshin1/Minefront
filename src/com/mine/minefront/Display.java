package com.mine.minefront;

import com.mine.minefront.graphics.Screen;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

// 디스플레이
public class Display extends Canvas implements Runnable {
    // 버전 ID
    private static final long serialVersionUID = 1L;

    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    static final String TITLE = "Minefront 개발 초기 버전";

    private Thread thread; // 스레드 객체
    private boolean running = false;
    private Screen screen;
    private Game game;
    private BufferedImage img;
    private int[] pixels; // 별도의 픽셀 배열변수

    public Display() {
        // render = new Render(WIDTH, HEIGHT);
        // render 없애고 screen 으로 변경
        screen = new Screen(WIDTH, HEIGHT);
        game = new Game();
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();

        // 새로운 차원 만들기
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

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
        // FPS Count
        int frames = 0; // 프레임
        int tickCount = 0; // 카운트
        double unprocessedSecond = 0;
        double secondsPerTick = 1 / 60.0; // 틱값
        long previousTime = System.nanoTime(); // 이전시간
        boolean ticked = false;

        while (running) {
            long currentTime = System.nanoTime(); // 현재시간
            long passedTime = currentTime - previousTime; // 통과시간
            previousTime = currentTime;
            unprocessedSecond += passedTime / 1000000000.0;

            while (unprocessedSecond > secondsPerTick) {
                tick();
                unprocessedSecond -= secondsPerTick;
                ticked = true;
                tickCount++;

                if (tickCount % 60 == 0) {
                    System.out.println(frames + "fps");
                    previousTime += 1000;
                    frames = 0;
                }
            }

            if (ticked) {
                render();
                frames++;
            }
            render();
            frames++;
        }
    }

    // 초당 프레임 처리
    private void tick() {
        game.tick();
    }

    // 렌더링
    private void render() {
        // 버퍼전략 초기화
        BufferStrategy bs = this.getBufferStrategy();

        // 버퍼 생성
        if (bs == null) {
            // 2차원은 2개 3차원은 3개 / 3D
            createBufferStrategy(3);
            return;
        }

        screen.render(game);

        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Display game = new Display();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 버튼 누르면 자동으로 종료
//        frame.setSize(WIDTH, HEIGHT); 새로운 차원 만들었으면 지워도 됨
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // 화면 크기 조절 유무
        frame.setVisible(true);

        System.out.println("실행중...");

        game.start();
    }
}
