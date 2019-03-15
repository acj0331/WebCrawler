package com.cjahn.webcrawler.object;

import java.util.List;

public class NaverWeb extends NaverObject{
    private List<NaverWebItem> items;

    public final List<NaverWebItem> getItems() {
        return items;
    }

    public final void setItems(List<NaverWebItem> items) {
        this.itemSize = items.size();
        this.items = items;
    }
}
