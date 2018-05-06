package com.zrblog.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @ClassName: SolrJTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zrblog
 * @date 2017年12月29日
 * 
 */

public class SolrJTest {

	@Test
	public void testUpdateOrAddSorlj() {
		// 1.创建HttpSolrServer，传入接口地址
		HttpSolrServer solrServer = new HttpSolrServer("http://solr.taotao.com/solr");

		// 2.创建SolrInputDocument对象，调用add方法构建文档对象。
		SolrInputDocument document = new SolrInputDocument();

		document.addField("id", "test_solr_id");
		document.addField("title", "你好，我是一个测试Solr程序");

		// 3.通过HttpSolrServer的add方法，把SolrInputDocument对象添加到索引库中。
		try {
			solrServer.add(document);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
