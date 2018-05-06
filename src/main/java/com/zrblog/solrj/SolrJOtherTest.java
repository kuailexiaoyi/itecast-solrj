package com.zrblog.solrj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName: SolrJTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zrblog
 * @date 2017年12月29日
 * 
 */

public class SolrJOtherTest {

	private HttpSolrServer httpSolrServer;

	@Before
	public void init() {
		// 1.声明接口地址
		String url = "http://solr.taotao.com/solr";
		// 2.创建
		this.httpSolrServer = new HttpSolrServer(url);
	}

	@Test
	public void testUpdateOrAddSorlj() {
		// 1.创建HttpSolrServer，传入接口地址
		HttpSolrServer solrServer = new HttpSolrServer("http://solr.taotao.com/solr");

		// 2.创建SolrInputDocument对象，调用add方法构建文档对象。
		List<SolrInputDocument> list = new ArrayList<SolrInputDocument>();

		SolrInputDocument document = new SolrInputDocument();
		// test_solr_id1:我感觉很好！
		// test_solr_id2:我感觉好极了！
		// test_solr_id3:我感觉奇妙极了！
		// test_solr_id4:世界美好！

		document.addField("id", "test_solr_id1");
		document.addField("title", "我感觉很好！");
		list.add(document);

		SolrInputDocument document1 = new SolrInputDocument();

		document1.addField("id", "test_solr_id2");
		document1.addField("title", "我感觉好极了！");
		list.add(document1);

		SolrInputDocument document2 = new SolrInputDocument();

		document2.addField("id", "test_solr_id3");
		document2.addField("title", "我感觉奇妙极了！");
		list.add(document2);
		SolrInputDocument document3 = new SolrInputDocument();

		document3.addField("id", "test_solr_id4");
		document3.addField("title", "世界美好！");
		list.add(document3);

		// 3.通过HttpSolrServer的add方法，把SolrInputDocument对象添加到索引库中。
		try {
			solrServer.add(list);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteIndex() throws Exception {
		// 根据id删除索引数据
		// this.httpSolrServer.deleteById("test_solr_id");

		// this.httpSolrServer.deleteByQuery("title:你好，我是一个测试Solr程序");

		this.httpSolrServer.deleteByQuery("*:*");

		this.httpSolrServer.commit();
	}

	/**
	 * @Title: testQuery
	 * @Description: TODO 实现查询索引库
	 * @author z r
	 * @throws Exception
	 * @date 2017年12月29日
	 */
	@Test
	public void testQuery() throws Exception {
		// 1.创建搜索对象SolrQuery
		SolrQuery solrQuery = new SolrQuery();

		// 2.设置查询条件
		solrQuery.setQuery("title:很好");
		//设置联合查询条件，相当于and
		solrQuery.setFilterQueries("title:好");

		// 3.如果需要设置排序
		solrQuery.setSort("id", ORDER.asc);

		// 4.如有需要，设置分页
		solrQuery.setStart(0);
		solrQuery.setRows(10);

		// 5.如有需要设置f1，设置df(set("df","字段名"))，设置wt(set("wt","json"))
		//设置查询域的列表
		solrQuery.setFields("id", "title");
		//设置默认搜索域
		solrQuery.set("df", "title");
		//
		solrQuery.set("wt", "json");

		// 6.如有需要，设置高亮
		solrQuery.setHighlight(true);
		solrQuery.setHighlightSimplePre("<font color='red'>");
		solrQuery.setHighlightSimplePost("</font>");

		// 7.查询数据
		QueryResponse response = this.httpSolrServer.query(solrQuery);

		// 8.获取文档列表
		SolrDocumentList resultList = response.getResults();

		// 9.输出总条数
		System.out.println("搜索到数据总条数：" + resultList.getNumFound());

		// 10.获取高亮数据
		Map<String, Map<String, List<String>>> map = response.getHighlighting();
		// 11.解析查询结果
		for (SolrDocument solrDocument : resultList) {
			System.out.println("id:" + solrDocument.get("id") + ";title=" + solrDocument.get("title"));

			// 12.如有需要输出高亮
			System.out.println("高亮："+map.get(solrDocument.get("id")).get("title").get(0));
		}

	}
}
