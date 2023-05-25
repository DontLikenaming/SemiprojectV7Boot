package dontlikenaming.springboot.semiprojectv7.utils;

import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Component("pdsutils")
public class PdsUtils {
    // 첨부파일 저장위치
    @Value("${saveDir}") private String saveDir;

    public String makeUUID() {
        String uuid = LocalDate.now()+""+LocalTime.now();
        uuid = uuid.replace("-", "").replace(":", "")
                   .replace(".", "");
        return uuid;
    }

    public PdsAttach processUpload(MultipartFile attach, Map<String, Object> pinfo) {

        // 업로드할 파일 정보 취득
        PdsAttach pa = new PdsAttach();

        String fileName = attach.getOriginalFilename();
        pa.setFname(fileName);

        int pos = (pa.getFname().lastIndexOf(".")) + 1;
        String ftype = pa.getFname().substring(pos);
        pa.setFtype(ftype);

        String fsize = String.valueOf(attach.getSize()/1024);
        pa.setFsize(fsize);

        int pno = (Integer) pinfo.get("pno");
        pa.setPno(pno);

        // 첨부파일을 파일 시스템에 저장
        // 시스템에 저장할 파일명 : "파일 이름"+"UUID"."확장자"
        String fname = pa.getFname().substring(0, pos-1);
        String savefname = saveDir + fname + pinfo.get("uuid") + "." + pa.getFtype();

        try {
            attach.transferTo(new File(savefname));
        } catch (Exception ex){
            System.out.println("업로드 중 오류 발생!");
            ex.printStackTrace();
        }

        return pa;
    }
}
