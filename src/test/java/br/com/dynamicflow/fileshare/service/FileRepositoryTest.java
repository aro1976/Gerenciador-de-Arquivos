package br.com.dynamicflow.fileshare.service;

import static org.junit.Assert.*;
import static org.springframework.data.document.mongodb.query.Criteria.*;
import static org.springframework.data.document.mongodb.query.Query.query;

import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoCollectionUtils;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Index;
import org.springframework.data.document.mongodb.query.Order;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.data.document.mongodb.query.Update;
import br.com.dynamicflow.fileshare.service.FileMetadata;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//public class FileRepositoryTest {
//
//	@Autowired
//	MongoTemplate mongoTemplate;
//
//	@Before
//	public void setUp() {
//		if (mongoTemplate.getCollectionNames().contains("MyNewCollection")) {
//			mongoTemplate.dropCollection("MyNewCollection");
//		}
//
//		String collectionName = MongoCollectionUtils.getPreferredCollectionName(File.class);
//		if (mongoTemplate.getCollectionNames().contains(collectionName)) {
//			mongoTemplate.dropCollection(collectionName);
//		}
//		mongoTemplate.createCollection(collectionName);
//	}
//
//	@Test
//	public void createAndDropCollection() {
//		assertFalse(mongoTemplate.getCollectionNames().contains("MyNewCollection"));
//
//		/* create a new collection */
//		DBCollection collection = null;
//		if (!mongoTemplate.getCollectionNames().contains("MyNewCollection")) {
//			collection = mongoTemplate.createCollection("MyNewCollection");
//		}
//
//		assertNotNull(collection);
//		assertTrue(mongoTemplate.getCollectionNames().contains("MyNewCollection"));
//
//		mongoTemplate.dropCollection("MyNewCollection");
//
//		assertFalse(mongoTemplate.getCollectionNames().contains("MyNewCollection"));
//	}
//
//	@Test
//	public void createAnIndex() {
//		String collectionName = MongoCollectionUtils.getPreferredCollectionName(File.class);
//		if (!mongoTemplate.getCollectionNames().contains(collectionName)) {
//			mongoTemplate.createCollection(collectionName);
//		}
//		mongoTemplate.ensureIndex(new Index().on("name", Order.ASCENDING), collectionName);
//	}
//
//	@Test
//	public void saveAndRetrieveDocuments() {
//		String collectionName = MongoCollectionUtils.getPreferredCollectionName(File.class);
//
//		File p = new File("Bob", 33);
//		mongoTemplate.insert(p);
//
//		assertEquals(1, mongoTemplate.getCollection(collectionName).count());
//
//		File qp = mongoTemplate.findOne(new Query(where("id").is(p.getId())), File.class);
//
//		assertNotNull(qp);
//		assertEquals(p.getName(), qp.getName());
//	}
//
//	@Test
//	public void queryingForDocuments() {
//		String collectionName = MongoCollectionUtils.getPreferredCollectionName(File.class);
//
//		File p1 = new File("Bob", 33);
//		p1.addAccount(new Account("198-998-2188", Account.Type.SAVINGS, 123.55d));
//		mongoTemplate.insert(p1);
//		File p2 = new File("Mary", 25);
//		p2.addAccount(new Account("860-98107-681", Account.Type.CHECKING, 400.51d));
//		mongoTemplate.insert(p2);
//		File p3 = new File("Chris", 68);
//		p3.addAccount(new Account("761-002-8901", Account.Type.SAVINGS, 10531.00d));
//		mongoTemplate.insert(p3);
//		File p4 = new File("Janet", 33);
//		p4.addAccount(new Account("719-100-0019", Account.Type.SAVINGS, 1209.10d));
//		mongoTemplate.insert(p4);
//
//		assertEquals(4, mongoTemplate.getCollection(collectionName).count());
//
//		List<File> result = mongoTemplate.find(
//				new Query(where("age").lt(50).and("accounts.balance").gt(1000.00d)),
//				File.class);
//
//		System.out.println(result);
//		assertNotNull(result);
//		assertEquals(1, result.size());
//	}
//
//	@Test
//	public void updatingDocuments() {
//		String collectionName = MongoCollectionUtils.getPreferredCollectionName(File.class);
//
//		File p1 = new File("Bob", 33);
//		p1.addAccount(new Account("198-998-2188", Account.Type.SAVINGS, 123.55d));
//		mongoTemplate.insert(p1);
//		File p2 = new File("Mary", 25);
//		p2.addAccount(new Account("860-98107-681", Account.Type.CHECKING, 400.51d));
//		mongoTemplate.insert(p2);
//		File p3 = new File("Chris", 68);
//		p3.addAccount(new Account("761-002-8901", Account.Type.SAVINGS, 10531.00d));
//		mongoTemplate.insert(p3);
//		File p4 = new File("Janet", 33);
//		p4.addAccount(new Account("719-100-0019", Account.Type.SAVINGS, 1209.10d));
//		mongoTemplate.insert(p4);
//
//		assertEquals(4, mongoTemplate.getCollection(collectionName).count());
//
//		WriteResult wr = mongoTemplate.updateMulti(
//				query(where("accounts.accountType").is(Account.Type.SAVINGS)),
//				new Update().inc("accounts.$.balance", 50.00),
//				File.class);
//
//		assertNotNull(wr);
//		assertEquals(3, wr.getN());
//	}
//}
