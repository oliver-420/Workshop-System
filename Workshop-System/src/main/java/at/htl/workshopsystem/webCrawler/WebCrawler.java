package at.htl.workshopsystem.webCrawler;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.*;

import at.htl.workshopsystem.PropertiesReader;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.*;

public class WebCrawler {
    static ArrayList<String> googleResults;       //storing results from google search.

    static int count = 0;

    public static String getImageURL(String query) {
        try {
            String imageUrl = getURL(query);
            if (imageUrl != null) {
                System.out.println("Image URL: " + imageUrl);
                return imageUrl;
            } else {
                System.out.println("No image found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getURL(String searchTerm) throws IOException {
        String apiUrl = "https://api.unsplash.com/search/photos";
        String query = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
        String searchUrl = apiUrl + "?query=" + query + "&per_page=1&client_id=" + PropertiesReader.getProperty("unsplash_api_key");

        URL url = new URL(searchUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        JsonArray results = json.getAsJsonArray("results");

        if (results != null && results.size() > 0) {
            JsonElement result = results.get(0);
            String imageUrl = result.getAsJsonObject().get("urls").getAsJsonObject().get("regular").getAsString();
            return imageUrl;
        }

        return null;
    }
}
