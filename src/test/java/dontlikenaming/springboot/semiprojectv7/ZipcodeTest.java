package dontlikenaming.springboot.semiprojectv7;

import dontlikenaming.springboot.semiprojectv7.model.Zipcode;
import dontlikenaming.springboot.semiprojectv7.repository.ZipcodeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
public class ZipcodeTest {

    @Autowired
    ZipcodeRepository zipcodeRepository;

    @Test
    @DisplayName("zipcode")
    public void findDong(){
        String dong = "구로";
        List<Zipcode> items = zipcodeRepository.findZipcodeByDong(dong);

        for(Zipcode item : items) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("zipcode page1")
    public void page1Zipcode(){

        Pageable pageable = PageRequest.of(0, 15);
        Page<Zipcode> page = zipcodeRepository.findAll(pageable);
        List<Zipcode> zips = page.getContent();

        for(Zipcode zip : zips) {
            System.out.println(zip);
        }
    }

    @Test
    @DisplayName("zipcode page2")
    public void page2Zipcode(){
        String dong = "%"+"구로"+"%";
        Pageable pageable = PageRequest.of(0, 15);
        Page<Zipcode> page = zipcodeRepository.findByDongLike(dong, pageable);
        List<Zipcode> zips = page.getContent();

        for(Zipcode zip : zips) {
            System.out.println(zip);
        }
    }

}
