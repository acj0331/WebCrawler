package com.cjahn.webcrawler.object;

import javax.json.bind.JsonbBuilder;

public class NaverBlogItem extends ItemObject{
    private String bloggername;
    private String bloggerlink;
    
    public final String getBloggername() {
        return bloggername;
    }
    public final void setBloggername(String bloggername) {
        this.bloggername = bloggername;
    }
    public final String getBloggerlink() {
        return bloggerlink;
    }
    public final void setBloggerlink(String bloggerlink) {
        this.bloggerlink = bloggerlink;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
