package com.cjahn.webcrawler.utility;

import java.util.Optional;

import com.cjahn.webcrawler.elasticsearch.service.ReqCollectESService;
import com.cjahn.webcrawler.object.ReqCollect;

public class CrawlerChecker extends Thread{
	private ReqCollectESService collectEsSvc;
	private boolean stop;
    private String crawlerStatus;
	private ReqCollect reqCollect;
	
	public ReqCollectESService getCollectEsSvc() {
		return collectEsSvc;
	}
	public void setCollectEsSvc(ReqCollectESService collectEsSvc) {
		this.collectEsSvc = collectEsSvc;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public ReqCollect getReqCollect() {
		return reqCollect;
	}
	public void setReqCollect(ReqCollect reqCollect) {
		this.reqCollect = reqCollect;
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
		this.setCrawlerStatus(this.reqCollect.getCrawlerStatus());
		 
		while(!stop) {
			/*
			 * Check CrawlerInfo
			 * */
			Optional<ReqCollect> collectInfo = collectEsSvc.findById(reqCollect.getId());
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
