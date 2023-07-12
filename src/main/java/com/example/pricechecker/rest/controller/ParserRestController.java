package com.example.pricechecker.rest.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ParserRestController {

    @GetMapping("parser")
    public List<String> parser() throws IOException {
        return getBooksChitaiGorod();
    }

    public List<String> getBooksLabint() throws IOException {
        List<String> itemsList = new ArrayList<>();

        Document document = Jsoup.connect("https://www.labirint.ru/books/")
                .userAgent("Chrome/4.0.249.0")
                .referrer("https://www.google.com")
                .get();

        Elements articleItem = document.body().select("div.inner-catalog").select("div.genres-carousel__item");

        for (Element element : articleItem) {
            String name = element.getElementsByClass("product-title").text();
            String oldPrice = element.getElementsByClass("price-gray").text();
            String newPrice = element.getElementsByClass("price-val").text();
            String author = element.getElementsByClass("product-author").select("a").attr("title");
            String posterPicture = element.getElementsByClass("book-img-cover").attr("data-src");
            itemsList.add(name + " Старая цена: " + oldPrice + " Новая цена: " + newPrice + " Автор: " + author + " Картинка " + posterPicture);
        }
        return itemsList;
    }

    public List<String> getBooksChitaiGorod() throws IOException {
        List<String> itemsList = new ArrayList<>();

        Document document = Jsoup.connect("https://www.chitai-gorod.ru/catalog/books-18030")
                .userAgent("Chrome/4.0.249.0")
                .referrer("https://www.google.com")
                .get();

        Elements articleItem = document.body().select("article");

        for (Element element : articleItem) {
            String name = element.getElementsByClass("product-title__head").text();
            String oldPrice = element.getElementsByClass("product-price__old").text();
            String newPrice = element.getElementsByClass("product-price__value").text();
            String author = element.getElementsByClass("product-title__author").text();
            String posterPicture = element.getElementsByClass("product-picture__img").attr("data-src");
            itemsList.add(name + " Старая цена: " + oldPrice + " Новая цена: " + newPrice + " Автор: " + author + " Картинка " + posterPicture);
        }
        return itemsList;
    }
}
