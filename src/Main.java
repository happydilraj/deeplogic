import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        String url = "https://time.com/";
        List<NewsItem> newsList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements headlines = doc.select(".latest-stories__item");

            for (Element headline : headlines) {
                Element linkElement = headline.select("a").first();
                String link = linkElement.attr("href");

                Element titleElement = headline.select("a > h3").first();
                String title = titleElement.text();

                NewsItem newsItem = new NewsItem(title, link);
                newsList.add(newsItem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(newsList);
    }

    // Define a NewsItem class
    static class NewsItem {
        private String title;
        private String link;

        public NewsItem(String title, String link) {
            this.title = title;
            this.link = link;
        }

        @Override
        public String toString() {
            return "{\n" +
                    "\"title\": \"" + title + "\",\n" +
                    "\"link\": \" https://time.com" + link + "\"\n" +
                    "}";
        }
    }
}
