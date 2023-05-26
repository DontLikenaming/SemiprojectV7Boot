package dontlikenaming.springboot.semiprojectv7.service;

import dontlikenaming.springboot.semiprojectv7.model.Pds;
import dontlikenaming.springboot.semiprojectv7.model.PdsAttach;
import dontlikenaming.springboot.semiprojectv7.model.PdsReply;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PdsService {
    Map<String, Object> readPds(Integer cpage);

    List<String> readFtype();

    Map<String, Object> readPds(Integer cpage, String ftype, String fkey);

    Map<String, Object> newPds(Pds pds);

    Pds readOnePds(Integer pno);

    boolean newPdsAttach(MultipartFile attach, Map<String, Object> pinfo);

    PdsAttach readOneAttach(Integer pno);

    HttpHeaders getHeader(String fname);

    UrlResource getResource(String fname, String uuid);

    List<PdsReply> readPdsReply(Integer pno);
}
