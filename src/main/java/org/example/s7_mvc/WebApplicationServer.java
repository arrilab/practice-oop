package org.example.s7_mvc;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class WebApplicationServer {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    public static void main(String[] args) throws Exception {
        // 1) 포트
        int port = 8080;

        // 2) 웹 루트: 루트 webapps (Gradle syncToWebapps가 채워줌)
        var webapps = new File("webapps").getAbsoluteFile();
        if (!webapps.exists() && !webapps.mkdirs()) {
            throw new IOException("Failed to create directory: " + webapps);
        }

        // 3) Tomcat 준비
        var tomcat = new Tomcat();
        tomcat.setBaseDir(new File("build/tomcat").getAbsolutePath()); // 임시/작업 디렉터리
        tomcat.setPort(port);
        tomcat.getHost().setAppBase(webapps.getAbsolutePath());

        // 4) 컨텍스트 등록 (컨텍스트 경로는 루트 "/")
        var ctx = (StandardContext) tomcat.addWebapp("/", webapps.getAbsolutePath());
        log.info("Starting Tomcat on port {}", port);
        log.info("DocBase: {}", webapps.getAbsolutePath());

        // 5) 종료 훅
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                log.info("Stopping Tomcat...");
                tomcat.stop();
                tomcat.destroy();
            } catch (Exception e) {
                log.warn("Error on shutdown", e);
            }
        }));

        // 6) 실행
        tomcat.start();
        tomcat.getServer().await();
    }
}
