package dontlikenaming.springboot.semiprojectv7.utils;

import dontlikenaming.springboot.semiprojectv7.model.GalAttach;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("galutils")
public class GalleryUtils {
    // 이미지 파일 저장 위치
    @Value("${saveImgDir}") private String saveImgDir;
    public String makeUUID() {
        String uuid = LocalDate.now()+""+LocalTime.now();
        uuid = uuid.replace("-", "").replace(":", "")
                   .replace(".", "");
        return uuid;
    }

    public GalAttach processUpload(List<MultipartFile> attachs, Map<String, Object> ginfo) {
        // 이미지 첨부파일은 파일명과 사이즈를 리스트로 저장한 뒤 문자열로 변환
        List<String> fnames = new ArrayList<>();
        List<String> fsizes = new ArrayList<>();

        // 업로드할 파일 정보 취득
        GalAttach ga = new GalAttach();
        ga.setGno((Integer) ginfo.get("gno"));


        // 첨부된 파일들에 대한 반복처리
        for(MultipartFile attach : attachs) {
            String fname = attach.getOriginalFilename();

            int pos = fname.lastIndexOf(".")+1;
            String ext = fname.substring(pos);
            String fsize = attach.getSize() / 1024 + "";

            // 저장 시 사용할 파일 이름 생성
            // 파일 이름UUID.확장자
            String savefname = fname.substring(0, pos-1);
            String fullfname = savefname + ginfo.get("uuid") + "." + ext;
            savefname = saveImgDir + fullfname;

            try {
                // 첨부파일을 파일 시스템에 저장
                attach.transferTo(new File(savefname));

                // 첨부파일 정보를 리스트에 저장
                fnames.add(fullfname);  // 파일 이름UUID.확장자
                fsizes.add(fsize);
                System.out.println(fullfname + ", " + fsize);

            } catch (Exception ex) {
                System.out.println("업로드 중 오류 발생!");
                ex.printStackTrace();
            }
        }

        // 수집된 첨부파일 정보를 ga에 저장
        // join(구분자, 리스트 변수) :요소1;요소2;요소3
        ga.setFname(String.join(";", fnames));
        ga.setFsize(String.join(";", fsizes));


        return ga;
    }

    public void makeThumbnail(GalAttach ga, Object uuid) {
        // 업로드 된 이미지들 중 첫번째 이미지에 대해 썸네일 생성
        // 파일이름들을 ;으로 나눈 뒤 첫번째 파일명 추출
        String basename = ga.getFname().split(";")[0];

        // 서버에 업로드된 파일 경로로 재정의 : 썸네일 생성 시 참고할 파일
        String refname = saveImgDir + basename;

        // 썸네일 이미지 경로 정의
        String thumbname = saveImgDir + "_thumbs/small_" + basename;
        System.out.println(refname + ", " +thumbname);

        try {
            // 원본 이미지를 읽어서 메모리에 이미지 객체(캔버스) 생성
            BufferedImage img = ImageIO.read(new File(refname));

            // 이미지 크기 추측
            int imgW = Math.min(img.getHeight(), img.getWidth());
            int imgH = imgW;

            // 지정한 위치를 기준으로 잘라냄
            // crop(대상, x좌표, y좌표, 잘라낼 너비, 잘라낼 높이, 투명도)
            BufferedImage scaleImg = Scalr.crop(
                    img,
                    (img.getWidth()-imgW)/2,    // crop 할 좌표
                    (img.getHeight()-imgH)/2,
                    imgW, imgH,                     // crop 할 이미지 크기
                    null
            );

            // 잘라낸 이미지를 230x200 크기로 재조정
            BufferedImage resizeImg = Scalr.resize(scaleImg, 235, 200, null);

            // 재조정한 이미지를 실제 경로에 저장
            ImageIO.write(resizeImg, "png", new File(thumbname));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
