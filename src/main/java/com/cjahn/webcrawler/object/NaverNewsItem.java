package com.cjahn.webcrawler.object;

public class NaverNewsItem extends ItemObject{
    private String originallink;
    private String pubDate;
    
    public final String getOriginallink() {
        return originallink;
    }
    public final void setOriginallink(String originallink) {
        this.originallink = originallink;
    }
    public final String getPubDate() {
        return pubDate;
    }
    public final void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
