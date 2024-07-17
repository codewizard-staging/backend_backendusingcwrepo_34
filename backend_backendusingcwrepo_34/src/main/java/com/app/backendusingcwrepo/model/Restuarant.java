package com.app.backendusingcwrepo.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
import com.app.backendusingcwrepo.model.Document;
import com.app.backendusingcwrepo.model.OperatingSupplies;
import com.app.backendusingcwrepo.model.Vendor;
import com.app.backendusingcwrepo.model.OrderAlert;
import com.app.backendusingcwrepo.model.Inventory;
import com.app.backendusingcwrepo.model.PurchaseOrder;
import com.app.backendusingcwrepo.model.Restuarant;
import com.app.backendusingcwrepo.enums.ItemType;
import com.app.backendusingcwrepo.converter.ItemTypeConverter;
import com.app.backendusingcwrepo.converter.DurationConverter;
import com.app.backendusingcwrepo.converter.UUIDToByteConverter;
import com.app.backendusingcwrepo.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "Restuarant")
@Table(name = "\"Restuarant\"", schema =  "\"backendusingcwrepo\"")
@Data
                        
public class Restuarant {
	public Restuarant () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"RestuarantID\"", nullable = true )
  private Integer restuarantID;
	  
  @Column(name = "\"OwnerName\"", nullable = true )
  private String ownerName;
  
	  
  @Column(name = "\"OwnerPh\"", nullable = true )
  private Long ownerPh;
  
	  
  @Column(name = "\"RestuarantName\"", nullable = true )
  private String restuarantName;
  
  
  
  
   
	
@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"RestuarantItemlist\"",
            joinColumns = @JoinColumn( name="\"RestuarantID\""),
            inverseJoinColumns = @JoinColumn( name="\"ItemId\""), schema = "\"backendusingcwrepo\"")
private List<OperatingSupplies> itemlist = new ArrayList<>();
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "Restuarant [" 
  + "RestuarantID= " + restuarantID  + ", " 
  + "OwnerName= " + ownerName  + ", " 
  + "OwnerPh= " + ownerPh  + ", " 
  + "RestuarantName= " + restuarantName 
 + "]";
	}
	
}