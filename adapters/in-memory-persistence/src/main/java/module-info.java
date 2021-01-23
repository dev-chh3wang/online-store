import billing.port.impl.ProductRepositoryInmemoryAdapter;
import billing.port.impl.SaveOrderInMemoryAdapter;
import billing.port.impl.UpdateItemInventoryPortAdapter;
import billing.store.usecase.port.ProductRepository;
import billing.store.usecase.port.SaveOrderPort;
import billing.store.usecase.port.UpdateItemInventoryPort;

module mt.everest.np.adapters {
    requires mt.everest.np.usecase;
    requires com.billing.domain;

    exports billing.port.impl;
    provides SaveOrderPort with SaveOrderInMemoryAdapter;
    provides ProductRepository with ProductRepositoryInmemoryAdapter;
    provides UpdateItemInventoryPort with UpdateItemInventoryPortAdapter;
}