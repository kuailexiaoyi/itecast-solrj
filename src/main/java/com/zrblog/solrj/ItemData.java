package com.zrblog.solrj;

/**
 * @ClassName: ItemData
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zrblog
 * @date 2017年12月29日
 * 
 */

public class ItemData {
	/*private CloudSolrServer cloudSolrServer;
	private ItemMapper itemMapper;

	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");

		// 从容器中取出bean
		this.cloudSolrServer = context.getBean(CloudSolrServer.class);

		this.itemMapper = context.getBean(ItemMapper.class);
	}

	@Test
	public void testItemData() throws Exception {
		// 可以循环来获取所有商品的数据
		int page = 1;
		int pageSize = 500;
		do {
			PageHelper.startPage(page, pageSize);
			List<Item> list = this.itemMapper.select(null);
			List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
			for (Item item : list) {
				// 将获取的商品数据批量导入到solr的索引库中
				SolrInputDocument document = new SolrInputDocument();
				// 商品id
				document.setField("id", item.getId().toString());
				document.setField("item_title", item.getTitle());
				document.setField("item_price", item.getPrice());
				// 商品图片
				if (StringUtils.isNotBlank(item.getImage())) {
					document.setField("item_image", StringUtils.split(item.getImage(), ",")[0]);
				} else {
					document.setField("item_image", "");
				}
				document.setField("item_cid", item.getCid());
				document.setField("item_status", item.getStatus());
				documents.add(document);
				
				this.cloudSolrServer.add(documents);
				this.cloudSolrServer.commit();

				page++;
				pageSize = list.size();
			}
		} while (pageSize == 500);
	}*/

}
