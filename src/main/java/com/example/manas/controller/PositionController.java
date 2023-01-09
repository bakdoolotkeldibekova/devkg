package com.example.manas.controller;

import com.example.manas.entity.Position;
import com.example.manas.service.PositionService;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("/create")
    public String create() throws IOException {
        Document doc = Jsoup.connect("https://devkg.com/ru/jobs")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                .get();

        for (Element paragraph : doc.select("article[class=\"item\"]")) {

//            System.out.println(paragraph.text());
            String url = paragraph.select("a[href]").attr("href");

            Document innerDocument = Jsoup.connect("https://devkg.com" + url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();

//            System.out.println(innerDocument.select("section[class=\"information\"]").text());

            Position position = new Position();
            position.setCompany(innerDocument.select("section[class=\"information\"]").select("div[class=\"organizations-item-field name\"]").text());
            position.setTitle(innerDocument.select("h1").text());
            position.setDescription(innerDocument.select("section[class=\"information\"]").select("div[class=\"text\"]").text());
            position.setCompany_logo("https://devkg.com" + innerDocument.select("section[class=\"information\"]").select("img[src]").attr("src"));
            position.setLocation(innerDocument.select("section[class=\"information\"]").select("div[class=\"type\"]").first().text()); //type
            String howToApply = innerDocument.select("section[class=\"information\"]").select("div[class=\"type\"]").text();
            position.setHow_to_apply(howToApply.replaceAll(position.getLocation(), ""));
            position.setHow_to_apply(position.getHow_to_apply().replaceAll("E-Mail [email protected]", ""));
            position.setType(innerDocument.select("section[class=\"information\"]").select("div[class=\"price\"]").text()); //price
            position.setUrl("https://devkg.com" + url);
            position.setCompany_url(innerDocument.select("section[class=\"information\"]").select("a[href]").attr("href")); //телеграм ссылка

            position.setCreated_at("");

//			position.setCreated_at(innerDocument.select("script").select("created_at").first().text());


//            System.out.println();

            positionService.save(position);
        }

        return "done with size " + doc.select("article[class=\"item\"]").size();
    }

    @GetMapping("/getAll")
    public List<Position> getAll(){
        return positionService.getAll();
    }

    @GetMapping("/getAllByType/{type}")
    public List<Position> getAllByType(@PathVariable String type){
        return positionService.getAllByType(type);
    }

    @GetMapping("/getAllByUrl/{url}")
    public List<Position> getAllByUrl(@PathVariable String url){
        return positionService.getAllByUrl(url);
    }

    @GetMapping("/getAllByCompany/{company}")
    public List<Position> getAllByCompany(@PathVariable String company){
        return positionService.getAllByCompany(company);
    }

    @GetMapping("/getAllByLocation/{location}")
    public List<Position> getAllByLocation(@PathVariable String location){
        return positionService.getAllByLocation(location);
    }

    @GetMapping("/getAllByTitle/{title}")
    public List<Position> getAllByTitle(@PathVariable String title){
        return positionService.getAllByTitle(title);
    }


    public static String convertToEmail(String hex) {

        try {
            byte[] decoded = Hex.decodeHex(hex.toCharArray());
            byte firstByte = decoded[0];

            byte[] newBytes = new byte[decoded.length - 1];

            for (int i = 0; i < decoded.length; i++) {
                byte result = (byte) (decoded[i] ^ firstByte);

                if (i == 0) {
                    continue;
                }

                newBytes[i - 1] = result;
            }

            return new String(newBytes);

        } catch (DecoderException e) {
            e.printStackTrace();
        }

        return null;
    }
}
