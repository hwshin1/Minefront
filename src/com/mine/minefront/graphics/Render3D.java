package com.mine.minefront.graphics;

public class Render3D extends Render{

    public Render3D(int width, int height){
        super(width, height);
    }

    double time = 0;

    // 이미지 생성 및 바닥 랜더링
    public void floor() {
        // y축 높이를 위한 전체 루프
        for (int y = 0; y < height; y++){
            double ceiling = (y - height / 2.0) / height;
            double z = 2.0 / ceiling;

            time+=0.00005; // time 조절

            for (int x = 0; x < width; x++) {
                double depth = (x - width / 2.0) / height;
                depth *= z;

                // 비트 연산자로 설정
                int xx = (int) (depth) & 15;
                int yy = (int) (z + time) & 15; // time으로 애니메이션 처럼 보이게
                pixels[x + y * width] = (xx * 16) | (yy * 16) << 8;
//                System.out.println(xx); // 진단용 프린트
            }
        }
    }
}
