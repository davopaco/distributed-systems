package co.edu.upb.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class URLRepository {
    public final ArrayList<URL> urls;

    public URLRepository() {
        urls = new ArrayList<>();
        configURLS();
    }

    void configURLS() {
        URL[] urlsArray = {
            new URL("https://www.google.com", "Google"),
            new URL("https://www.facebook.com", "Facebook"),
            new URL("https://www.instagram.com", "Instagram"),
            new URL("https://www.twitter.com", "Twitter"),
            new URL("https://www.linkedin.com", "LinkedIn"),
            new URL("https://www.youtube.com", "YouTube"),
            new URL("https://www.github.com", "GitHub"),
            new URL("https://www.stackoverflow.com", "Stack Overflow"),
            new URL("https://www.medium.com", "Medium"),
            new URL("https://www.reddit.com", "Reddit"),
            new URL("https://www.tiktok.com", "TikTok"),
            new URL("https://www.whatsapp.com", "WhatsApp"),
            new URL("https://www.spotify.com", "Spotify"),
            new URL("https://www.netflix.com", "Netflix"),
            new URL("https://www.amazon.com", "Amazon"),
            new URL("https://www.ebay.com", "eBay"),
            new URL("https://www.apple.com", "Apple"),
            new URL("https://www.microsoft.com", "Microsoft"),
            new URL("https://www.ibm.com", "IBM"),
            new URL("https://www.oracle.com", "Oracle"),
            new URL("https://www.adobe.com", "Adobe"),
            new URL("https://www.cisco.com", "Cisco"),
            new URL("https://www.intel.com", "Intel"),
            new URL("https://www.samsung.com", "Samsung"),
            new URL("https://www.huawei.com", "Huawei"),
            new URL("https://www.xiaomi.com", "Xiaomi"),
            new URL("https://www.sony.com", "Sony"),
            new URL("https://www.panasonic.com", "Panasonic"),
            new URL("https://www.lg.com", "LG"),
            new URL("https://www.nokia.com", "Nokia"),
            new URL("https://www.htc.com", "HTC"),
            new URL("https://www.asus.com", "ASUS"),
        };

        this.urls.addAll(Arrays.asList(urlsArray));
    }

    ArrayList<URL> getURLs() {
        return this.urls;
    }
}
