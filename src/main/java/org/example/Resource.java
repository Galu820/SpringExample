package org.example;

import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.core.io.InputStreamSource;

import javax.print.DocFlavor;
import java.io.IOException;

public interface Resource extends InputStreamSource {

    boolean exists();
    boolean isOpen();
    DocFlavor.URL getURL() throws IOException;
    SslProperties.Bundles.Watch.File getFile() throws IOException;
    Resource createRelative(String relativePath) throws IOException;
    String getFilename();
    String getDescription();
}
