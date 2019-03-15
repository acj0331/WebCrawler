package com.cjahn.webcrawler.core;

import com.cjahn.webcrawler.config.OpenAPIConfig;
import com.cjahn.webcrawler.object.ReqCollect;

public class KakaoCrawler extends WebCrawler{
    public KakaoCrawler(Object v) {
        super(v);
    }

    @Override
	public void init(OpenAPIConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doCollect(ReqCollect reqCollect) {
		// TODO Auto-generated method stub
		System.out.println("kakao :: doCollect");
	}
}
