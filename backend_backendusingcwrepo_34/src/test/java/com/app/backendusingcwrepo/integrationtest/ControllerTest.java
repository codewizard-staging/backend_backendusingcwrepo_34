package com.app.backendusingcwrepo.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.backendusingcwrepo.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/backendusingcwrepo/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/backendusingcwrepo/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("backendusingcwrepo", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreateOrderAlertInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("OrderAlertInstance.json"))
        .when()
        .post("/backendusingcwrepo/OrderAlerts")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsOrderAlert() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("OrderAlertInstance.json"))
        .when()
        .post("/backendusingcwrepo/OrderAlerts")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/backendusingcwrepo/OrderAlerts?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).OrderAlertId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/backendusingcwrepo/OrderAlerts/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateRestuarantInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("RestuarantInstance.json"))
        .when()
        .post("/backendusingcwrepo/Restuarants")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsRestuarant() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("RestuarantInstance.json"))
        .when()
        .post("/backendusingcwrepo/Restuarants")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/backendusingcwrepo/Restuarants?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).RestuarantID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/backendusingcwrepo/Restuarants/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreatePurchaseOrderInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PurchaseOrderInstance.json"))
        .when()
        .post("/backendusingcwrepo/PurchaseOrders")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPurchaseOrder() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PurchaseOrderInstance.json"))
        .when()
        .post("/backendusingcwrepo/PurchaseOrders")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/backendusingcwrepo/PurchaseOrders?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).PurchaseOrderId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/backendusingcwrepo/PurchaseOrders/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateVendorInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("VendorInstance.json"))
        .when()
        .post("/backendusingcwrepo/Vendors")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsVendor() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("VendorInstance.json"))
        .when()
        .post("/backendusingcwrepo/Vendors")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/backendusingcwrepo/Vendors?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).VendorID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/backendusingcwrepo/Vendors/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateDocumentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/backendusingcwrepo/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDocument() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/backendusingcwrepo/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/backendusingcwrepo/Documents?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DocId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/backendusingcwrepo/Documents/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateOperatingSuppliesInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("OperatingSuppliesInstance.json"))
        .when()
        .post("/backendusingcwrepo/OperatingSupplies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsOperatingSupplies() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("OperatingSuppliesInstance.json"))
        .when()
        .post("/backendusingcwrepo/OperatingSupplies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/backendusingcwrepo/OperatingSupplies?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ItemId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/backendusingcwrepo/OperatingSupplies/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateInventoryInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("InventoryInstance.json"))
        .when()
        .post("/backendusingcwrepo/Inventories")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsInventory() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("InventoryInstance.json"))
        .when()
        .post("/backendusingcwrepo/Inventories")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/backendusingcwrepo/Inventories?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).InventoryId", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/backendusingcwrepo/Inventories/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM backendusingcwrepo.OrderAlert");
    jdbcTemplate.execute("DELETE FROM backendusingcwrepo.Restuarant");
    jdbcTemplate.execute("DELETE FROM backendusingcwrepo.PurchaseOrder");
    jdbcTemplate.execute("DELETE FROM backendusingcwrepo.Vendor");
    jdbcTemplate.execute("DELETE FROM backendusingcwrepo.Document");
    jdbcTemplate.execute("DELETE FROM backendusingcwrepo.OperatingSupplies");
    jdbcTemplate.execute("DELETE FROM backendusingcwrepo.Inventory");
     jdbcTemplate.execute("DELETE FROM backendusingcwrepo.OrderAlertItemvendor");
     jdbcTemplate.execute("DELETE FROM backendusingcwrepo.InventoryStockalert");
     jdbcTemplate.execute("DELETE FROM backendusingcwrepo.OperatingSuppliesStock");
     jdbcTemplate.execute("DELETE FROM backendusingcwrepo.VendorOrderdetails");
     jdbcTemplate.execute("DELETE FROM backendusingcwrepo.RestuarantItemlist");
     jdbcTemplate.execute("DELETE FROM backendusingcwrepo.OperatingSuppliesItemimage");

    RestAssuredMockMvc.reset();
  }
}
