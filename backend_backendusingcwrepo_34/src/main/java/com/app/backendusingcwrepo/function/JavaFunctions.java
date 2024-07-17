package com.app.backendusingcwrepo.function;

import com.app.backendusingcwrepo.model.Document;
import com.app.backendusingcwrepo.model.OperatingSupplies;
import com.app.backendusingcwrepo.model.Vendor;
import com.app.backendusingcwrepo.model.OrderAlert;
import com.app.backendusingcwrepo.model.Inventory;
import com.app.backendusingcwrepo.model.PurchaseOrder;
import com.app.backendusingcwrepo.model.Restuarant;
import com.app.backendusingcwrepo.enums.ItemType;
import com.app.backendusingcwrepo.converter.ItemTypeConverter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;
import com.app.backendusingcwrepo.repository.OrderAlertRepository;
import com.app.backendusingcwrepo.repository.RestuarantRepository;
import com.app.backendusingcwrepo.repository.PurchaseOrderRepository;
import com.app.backendusingcwrepo.repository.VendorRepository;
import com.app.backendusingcwrepo.repository.DocumentRepository;
import com.app.backendusingcwrepo.repository.OperatingSuppliesRepository;
import com.app.backendusingcwrepo.repository.InventoryRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaFunctions implements ODataFunction {


    
    
}
   
