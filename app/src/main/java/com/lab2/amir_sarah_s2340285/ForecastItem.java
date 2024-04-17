package com.lab2.amir_sarah_s2340285;

//
// Name                 Sarah Amir
// Student ID           S2340285
// Programme of Study   BSc Computing
//

public class ForecastItem {
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String georssPoint;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setGeorssPoint(String georssPoint) {
        this.georssPoint = georssPoint;
    }



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getGeorssPoint() {
        return georssPoint;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description + ", Link: " + link + ", PubDate: " + pubDate + ", GeoRSS Point: " + georssPoint;
    }

}
