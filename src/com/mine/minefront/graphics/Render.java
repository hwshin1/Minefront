package com.mine.minefront.graphics;

public class Render {
    public final int width;
    public final int height;
    public final int[] pixels;

    // 생성자
    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void draw(Render render, int xOffset, int yOffset) {
        // y값 픽셀 채우기
        for (int y = 0; y < render.height; y++) {
            int yPix = y + yOffset;

            // 범위 넘어가지 않게
            if (yPix < 0 || yPix >= height) {
                continue;
            }

            // x값 픽셀 채우기
            for (int x = 0; x < render.width; x++) {
                int xPix = x + xOffset;

                // 범위 넘어가지 않게
                if (xPix < 0 || xPix >= width) {
                    continue;
                }

                int alpha = render.pixels[x + y * render.width];
                // 렌더링 하고 싶지 않은 부분 렌더링 안하게 하기
                if (alpha > 0) {
                    pixels[xPix + yPix * width] = alpha;
                }
            }
        }
    }
}
