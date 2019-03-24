package com.cjahn.webcrawler.object;

import java.util.List;

import javax.json.bind.JsonbBuilder;

public class NaverObject {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    protected int itemSize;

    private List<ItemObject> items;
    
    public final String getLastBuildDate() {
        return lastBuildDate;
    }
    public final void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }
    public final int getTotal() {
        return total;
    }
    public final void setTotal(int total) {
        this.total = total;
    }
    public final int getStart() {
        return start;
    }
    public final void setStart(int start) {
        this.start = start;
    }
    public final int getDisplay() {
        return display;
    }
    public final void setDisplay(int display) {
        this.display = display;
    }
    public final int getItemSize() {
        return itemSize;
    }
    public final void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public List<ItemObject> getItems() {
		return items;
	}
	public void setItems(List<ItemObject> items) {
		this.items = items;
	}
	@Override
    public String toString() {
        return JsonbBuilder.create().toJson(this);
    }
}
