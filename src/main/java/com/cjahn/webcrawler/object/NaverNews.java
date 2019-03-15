package com.cjahn.webcrawler.object;

import java.util.List;

public class NaverNews extends NaverObject{
    private List<NaverNewsItem> items;

    public final List<NaverNewsItem> getItems() {
        return items;
    }

    public final void setItems(List<NaverNewsItem> items) {
        this.itemSize = items.size();
        this.items = items;
    }
}
