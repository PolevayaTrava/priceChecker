package com.example.pricechecker.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserServiceImpl {

    public List<String> getMainPageChitaiGorod() throws IOException {
        List<String> itemsList = new ArrayList<>();

        Document document = Jsoup.connect("https://www.chitai-gorod.ru")
                .userAgent("Chrome/4.0.249.0")
                .referrer("https://www.google.com")
                .get();

        Elements articleItem = document.body().select("article");

        for (Element element : articleItem) {
            String name = element.getElementsByClass("product-title__head").text();
            String oldPrice = element.getElementsByClass("product-price__old").text();
            String newPrice = element.getElementsByClass("product-price__value").text();
            String author = element.getElementsByClass("product-title__author").text();
            String posterPicture = element.select("img").attr("data-src");
            itemsList.add(name + " Старая цена: " + oldPrice + " Новая цена: " + newPrice + " Автор: " + author + " Картинка " + posterPicture);
        }
        return itemsList;
    }

    public List<String> getMainPageLabint() throws IOException {
        List<String> itemsList = new ArrayList<>();

        Document document = Jsoup.connect("https://www.labirint.ru")
                .userAgent("Chrome/4.0.249.0")
                .referrer("https://www.google.com")
                .get();

        Elements articleItem = document.body().select("product");

        for (Element element : articleItem) {
            String name = element.getElementsByClass("product-title-link").attr("title");
            String oldPrice = element.getElementsByClass("price-gray").text();
            String newPrice = element.getElementsByClass("price-val").text();
            String author = element.getElementsByClass("product-author").attr("title");
            String posterPicture = element.select("img").attr("src");
            itemsList.add(name + " Старая цена: " + oldPrice + " Новая цена: " + newPrice + " Автор: " + author + " Картинка " + posterPicture);
        }
        return itemsList;
    }
}
