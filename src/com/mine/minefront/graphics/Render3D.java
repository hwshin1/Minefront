package com.mine.minefront.graphics;

import com.mine.minefront.Game;

public class Render3D extends Render{

    public Render3D(int width, int height){
        super(width, height);
    }

    // 이미지 생성 및 층 랜더링
    public void floor(Game game) {

        double rotation = game.time / 100.0; // 회전 속도 조절
        double cosine = Math.cos(rotation); // 코싸인 값
        double sine = Math.sin(rotation); // 싸인 값

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

                // 360도 회전
                double xx = depth * cosine + z * sine;
                double yy = z * cosine - depth * sine;

                // 180도 회전 후 다시 되돌아옴
//                double xx = depth * cosine + z;
//                double yy = z * cosine - depth;

                // xx,yy를 double 타입으로 저장하고 싶어서 만든 변수들
                int xPix = (int) (xx);
                int yPix = (int) (yy);
                pixels[x + y * width] = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 8;
//                System.out.println(xx); // 진단용 프린트
            }
        }
    }
}
