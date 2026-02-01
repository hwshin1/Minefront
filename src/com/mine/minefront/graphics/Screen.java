package com.mine.minefront.graphics;

import com.mine.minefront.Game;

import java.util.Random;

public class Screen extends Render {

    private Render test;
    private Render3D render3D;

    private static final int SCREEN_WIDTH = 320;
    private static final int SCREEN_HEIGHT = 180;

    public Screen(int width, int height) {
        super(width, height);

        // Random 함수
        Random random = new Random();
        render3D = new Render3D(width, height);

        // 너비,높이 = 320, 180
        test = new Render(SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int i = 0; i < SCREEN_WIDTH * SCREEN_HEIGHT; i++) {
            test.pixels[i] = random.nextInt();
        }
    }

    public void render(Game game) {
        // 프레임마다 픽셀값 0
        for (int i = 0; i < width * height; i++) {
            pixels[i] = 0;
        }

        // 3D처럼 보이게
        /*
        for문에서 i 값의 범위를 크게 설정 하지 말고 for문 안에서의 i값을 따로 변경하면 훨씬 부드럽게 움직인다.
        예시:
        범위 i < 1000 / for문 안에서 i => for문을 천번 돌려야 해서 조금 느림
        범위 i < 100 / for문 안에서 i * 10 => for문을 100번 돌리지만 안에서 10을 곱해 숫자를 늘리는 방식
         */
        for (int i = 0; i < 50; i++) {
            // 애니메이션 생성
//            int animX = (int) (Math.sin((System.currentTimeMillis() + i * 8) % 2000.0 / 2000 * Math.PI * 2) * 80);
//            int animY = (int) (Math.cos((System.currentTimeMillis() + i * 8) % 2000.0 / 2000 * Math.PI * 2) * 60);

            // game class 방식
            int animX = (int) (Math.sin((game.time + i * 2) % 1000.0 / 100) * 100);
            int animY = (int) (Math.cos((game.time + i * 2) % 1000.0 / 100) * 100);
            // 픽셀을 화면 중간으로 + 애니메이션 보여주기
//            draw(test, (width - SCREEN_WIDTH) / 2 + animX, (height - SCREEN_HEIGHT) / 2 - animY);
        }

        render3D.floor(game);
        draw(render3D, 0, 0);
    }
}
