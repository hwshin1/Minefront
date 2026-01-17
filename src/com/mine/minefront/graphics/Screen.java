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
        draw(test, 0 ,0 );
    }
}
