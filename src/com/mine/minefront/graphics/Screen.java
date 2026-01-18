package com.mine.minefront.graphics;

import java.util.Random;

public class Screen extends Render {

    private Render test;
    private static final int SCREEN_WIDTH = 320;
    private static final int SCREEN_HEIGHT = 180;

    public Screen(int width, int height) {
        super(width, height);

        // Random 함수
        Random random = new Random();

        // 너비,높이 = 320, 180
        test = new Render(SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int i = 0; i < SCREEN_WIDTH * SCREEN_HEIGHT; i++) {
            test.pixels[i] = random.nextInt();
        }
    }

    public void render() {
        // 프레임마다 픽셀값 0
        for (int i = 0; i < width * height; i++) {
            pixels[i] = 0;
        }

        // 3D처럼 보이게
        for (int i = 0; i < 100; i++) {
            // 애니메이션 생성
            int animX = (int) (Math.sin((System.currentTimeMillis() + i) % 2000.0 / 2000 * Math.PI * 2) * 80);
            int animY = (int) (Math.cos((System.currentTimeMillis() + i) % 2000.0 / 2000 * Math.PI * 2) * 60);

            // 픽셀을 화면 중간으로 + 애니메이션 보여주기
            draw(test, (width - SCREEN_WIDTH) / 2 + animX, (height - SCREEN_HEIGHT) / 2 - animY);
        }
    }
}
