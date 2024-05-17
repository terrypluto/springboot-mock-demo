package com.terryliu.springbootmockdemo.third;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.parser.AutoDetectParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/5/17
 */
public class TikaTest {
    @Test
    public void test1() throws IOException {
        Tika tika = new Tika();
        String detect = tika.detect(new File("C:\\Users\\terry\\Pictures\\headicon.jpg"));
        System.out.println(detect);
    }

    @Test
    public void test2() throws TikaException, IOException {
        TikaConfig tikaConfig = new TikaConfig();
        AutoDetectParser parser = new AutoDetectParser();
        Detector detector = parser.getDetector();
        final Metadata metadata = new Metadata();
        metadata.add(TikaCoreProperties.RESOURCE_NAME_KEY, "headicon.jpg");
        TikaInputStream tikaInputStream = TikaInputStream.get(new FileInputStream("C:\\Users\\terry\\Pictures\\headicon.jpg"));
        MediaType detect = detector.detect(tikaInputStream, metadata);
        MimeType mimeType = tikaConfig.getMimeRepository().forName(detect.toString());
        String extension = mimeType.getExtension();
        System.out.println(extension);
    }
}
