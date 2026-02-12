package com.mine.minefront.graphics;

import com.mine.minefront.Game;

public class Render3D extends Render{

    public Render3D(int width, int height){
        super(width, height);
    }

    // 이미지 생성 및 층 랜더링
    public void floor(Game game) {
        // y축 높이를 위한 전체 루프
        for (int y = 0; y < height; y++){
            double ceiling = (y - height / 2.0) / height;

            // 천장값을 반전시키기
            if (ceiling < 0) {
                ceiling = -ceiling;
            }

            double z = 8.0 / ceiling;

            for (int x = 0; x < width; x++) {
                double depth = (x - width / 2.0) / height;
                depth *= z;

                // 비트 연산자로 설정
                // time으로 애니메이션 처럼 보이게
                double xx = depth + game.time; // time 추가시 좌우 이동
                double yy = z + game.time; // time 추가시 앞뒤 이동

                // xx,yy를 double 타입으로 저장하고 싶어서 만든 변수들
                int xPix = (int) (xx);
                int yPix = (int) (yy);
                pixels[x + y * width] = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 8;
//                System.out.println(xx); // 진단용 프린트
            }
        }
    }
}
