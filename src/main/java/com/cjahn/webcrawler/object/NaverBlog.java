package com.cjahn.webcrawler.object;

import java.util.List;

public class NaverBlog extends NaverObject {
    private String postdate;
//    private List<NaverBlogItem> items;
    public final String getPostdate() {
        return postdate;
    }
    public final void setPostdate(String postdate) {
        this.postdate = postdate;
    }
//    public final List<NaverBlogItem> getItems() {
//        return items;
//    }
//    public final void setItems(List<NaverBlogItem> items) {
//        this.itemSize = items.size();
//        this.items = items;
//    }
}
