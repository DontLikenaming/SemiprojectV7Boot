package dontlikenaming.springboot.semiprojectv7.pilot;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Controller
@RequestMapping(value = "/pilot")
public class PilotController {

    @GetMapping("/write")   // 입력폼
    public String write(){
        return "pilot/write";
    }

    @PostMapping("/write")  // 전송된 데이터 처리
    public String writeok(String title, String content, MultipartFile attach, Model m) throws IOException {

        // 일반 폼 요소 처리
        m.addAttribute("title", title);
        m.addAttribute("content", content);

        // 멀티파트 폼 요소 처리
        if(attach.isEmpty())m.addAttribute("attach", "첨부 파일이 없습니다.");
        else {
            // 업로드한 파일 이름 출력
            m.addAttribute("filename", attach.getOriginalFilename());
            // 업로드한 파일 종류 출력
            m.addAttribute("filetype", attach.getContentType());
            // 업로드한 파일 크기 출력
            m.addAttribute("filesize", attach.getSize()/1024);

            // 파일명 중복을 막기 위해 유니크한 값 생성 1
            String uuid = UUID.randomUUID().toString().replace("-", "");    // uuid에서 "-" 제거

            // 파일명 중복을 막기 위해 유니크한 값 생성 2
            /*String fmt = "yyyyMMddHHmmss";
            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            uuid = sdf.format(new Date());*/
            uuid = LocalDate.now() + "" + LocalTime.now();
            uuid = uuid.replace("-", "").replace(":", "")
                    .replace(".", "");

            // 파일명과 확장자 사이에 생성된 값 넣기
            String filename = attach.getOriginalFilename();

            String fname = filename.substring(0, filename.lastIndexOf("."));
            String fileExt = filename.substring(filename.lastIndexOf(".") + 1);
            filename = fname + uuid + "." + fileExt;

            m.addAttribute("uuid", uuid);

            // 업로드 한 파일 저장하기
            attach.transferTo(new File("C:/Java/bootUpload/" + filename));
        }

        return "pilot/list";
    }

    @GetMapping("/list")
    public String list(){
        return "pilot/list";
    }

    @GetMapping("/down")
    public ResponseEntity<Resource> down(int pno) throws IOException {
        String fname = "";
        String savePath = "C:/Java/bootUpload/";

        if(pno == 1) fname = "멍멍이.jpg";
        else if(pno == 2) fname = "01hellojs.html";
        else if(pno == 3) fname = "zipcode_20130201.zip";

        // 파일 이름에 한글이 포함되면 글자가 깨지므로 적절한 인코딩 작업 수행
        fname = UriUtils.encode(fname, StandardCharsets.UTF_8);

        // 다운로드 할 파일 객체 생성
        UrlResource resource = new UrlResource("file:"+(savePath+fname));

        // MIME 타입 지정
        // 브라우저에 다운로드할 파일에 대한 정보 제공
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", Files.probeContentType(Paths.get(savePath+fname)));
        header.add("Content-Disposition", "attachment; filename=" + fname);

        // 브라우저로 파일 전송하기
        return ResponseEntity.ok().headers(header).body(resource);
    }

    @GetMapping("/showimg")
    @ResponseBody   // view 없이 본문 출력
    public Resource showimg() throws MalformedURLException {
        String fname = "C:/Java/bootUpload/" + "멍멍이.jpg";
        fname = UriUtils.encode(fname, StandardCharsets.UTF_8);

        return new UrlResource("file:"+fname);
    }
}
