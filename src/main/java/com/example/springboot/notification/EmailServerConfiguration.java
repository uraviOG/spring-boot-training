package com.example.springboot.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties("email.server")
public class EmailServerConfiguration {

    private String hostName;

    private int port;

    private String from;

    private List<String> defaultRecipients;

    private Map<String, String> additionalHeaders;

    private Credentials credentials;

    public String getHostName() {
        return hostName;
    }

    public EmailServerConfiguration setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public int getPort() {
        return port;
    }

    public EmailServerConfiguration setPort(int port) {
        this.port = port;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public EmailServerConfiguration setFrom(String from) {
        this.from = from;
        return this;
    }

    public List<String> getDefaultRecipients() {
        return defaultRecipients;
    }

    public EmailServerConfiguration setDefaultRecipients(List<String> defaultRecipients) {
        this.defaultRecipients = defaultRecipients;
        return this;
    }

    public Map<String, String> getAdditionalHeaders() {
        return additionalHeaders;
    }

    public EmailServerConfiguration setAdditionalHeaders(Map<String, String> additionalHeaders) {
        this.additionalHeaders = additionalHeaders;
        return this;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public EmailServerConfiguration setCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    @Override
    public String toString() {
        return "EmailServerConfiguration{" +
                "hostName='" + hostName + '\'' +
                ", port=" + port +
                ", from='" + from + '\'' +
                ", defaultRecipients=" + defaultRecipients +
                ", additionalHeaders=" + additionalHeaders +
                ", credentials=" + credentials +
                '}';
    }

    public static class Credentials {

        private String userName;

        private String password;

        public String getUserName() {
            return userName;
        }

        public Credentials setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public Credentials setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public String toString() {
            return "Credentials{" +
                    "userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Loaded EmailServerConfiguration :: " + this.toString());
    }

}
