package com.cjahn.webcrawler.utility;

import java.util.Optional;

import com.cjahn.webcrawler.elasticsearch.service.CollectESService;
import com.cjahn.webcrawler.object.CollectInfo;

public class CrawlerChecker extends Thread{
	private CollectESService collectEsSvc;
	private boolean stop;
    private String crawlerStatus;
	private CollectInfo collectInfo;
	
	public CollectESService getCollectEsSvc() {
		return collectEsSvc;
	}
	public void setCollectEsSvc(CollectESService collectEsSvc) {
		this.collectEsSvc = collectEsSvc;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public CollectInfo getCollectInfo() {
		return collectInfo;
	}
	public void setCollectInfo(CollectInfo collectInfo) {
		this.collectInfo = collectInfo;
	}
	public CrawlerChecker() {
		this.stop = false;
	}
	public String getCrawlerStatus() {
		return crawlerStatus;
	}
	public void setCrawlerStatus(String crawlerStatus) {
		this.crawlerStatus = crawlerStatus;
	}
	@Override
	public void run() {
		this.setCrawlerStatus(this.collectInfo.getCrawlerStatus());
		 
		while(!stop) {
			/*
			 * Check CrawlerInfo
			 * */
			Optional<CollectInfo> collectInfo = collectEsSvc.findById(this.collectInfo.getId());
			if(collectInfo.get().getCrawlerStatus().equals("canceled")) {
				this.setCrawlerStatus(collectInfo.get().getCrawlerStatus());
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
