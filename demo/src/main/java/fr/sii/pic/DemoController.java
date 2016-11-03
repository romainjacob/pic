package fr.sii.pic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author matthieu_jaulin
 */
@RestController
public class DemoController {

    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }
    
    @RequestMapping("/demo")
    public String demo() {
        return "Bim!";
    }
}
