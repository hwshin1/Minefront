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
        // 애니메이션 생성
        int anim = (int) (Math.sin(System.currentTimeMillis() % 1000.0 / 1000 * Math.PI * 2) * 100);
        // 픽셀을 화면 중간으로 + 애니메이션 보여주기
        draw(test, (width - SCREEN_WIDTH) / 2 + anim, (height - SCREEN_HEIGHT) / 2);
    }
}
