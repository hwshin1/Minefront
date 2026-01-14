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
}
