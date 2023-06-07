package dontlikenaming.springboot.semiprojectv7.DAO;

import dontlikenaming.springboot.semiprojectv7.model.GalAttach;
import dontlikenaming.springboot.semiprojectv7.model.Gallery;

public interface GalleryDAO {
    int insertGal(Gallery gallery);

    int insertGalAttach(GalAttach ga);


}
