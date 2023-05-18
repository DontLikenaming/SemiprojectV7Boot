package dontlikenaming.springboot.semiprojectv7;

import dontlikenaming.springboot.semiprojectv7.model.Zipcode;
import dontlikenaming.springboot.semiprojectv7.repository.ZipcodeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
