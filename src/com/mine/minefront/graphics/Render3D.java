package com.mine.minefront.graphics;

public class Render3D extends Render{

    public Render3D(int width, int height){
        super(width, height);
    }

    // 이미지 생성 및 바닥 랜더링
    public void floor() {
        // y축 높이를 위한 전체 루프
        for (int y = 0; y < height; y++){
            double yDepth = y - height / 2.0;
            double z = 100.0 / yDepth;

            for (int x = 0; x < width; x++) {
                double xDepth = x - width / 2.0;
                xDepth *= z;

                // 비트 연산자로 설정
                int x2 = (int) (xDepth) & 5;
                pixels[x + y * width] = x2 * 8;
            }
        }
    }
}
