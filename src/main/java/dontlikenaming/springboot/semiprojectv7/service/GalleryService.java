package dontlikenaming.springboot.semiprojectv7.service;

import dontlikenaming.springboot.semiprojectv7.model.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface GalleryService {
    Map<String, Object> newGallery(Gallery gallery);

    boolean newGalAttach(List<MultipartFile> attachs, Map<String, Object> ginfo);
}
