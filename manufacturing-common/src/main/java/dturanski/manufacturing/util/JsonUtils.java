package dturanski.manufacturing.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public abstract class JsonUtils {
    public static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String getResourceContents(String path) {
        Resource resource = new ClassPathResource(path);

        try {
            return convertStreamToString(resource.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

}
