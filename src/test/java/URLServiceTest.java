import com.orange.Application;
import com.orange.service.UrlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class URLServiceTest {

    @Autowired
    UrlService urlService;

    @Test
    public void testShortUrlLength(){
        String originalUrl = "www.google.com";
        assertThat(urlService.encodeUrlToBase64(originalUrl)).hasSize(6);
    }

    @Test
    public void testUniqueUrl(){
        String originalUrl = "www.google.com";
        Set<String> code = new HashSet<>();
        String shortUrl = urlService.encodeUrlToBase64(originalUrl);
        assertThat(code).doesNotContain(shortUrl);
        code.add(shortUrl);
        while(code.contains(shortUrl)) {
            shortUrl = urlService.shuffle(shortUrl);
        }
        assertThat(code).doesNotContain(shortUrl);
    }
}
