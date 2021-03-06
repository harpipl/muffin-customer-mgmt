package pl.harpi.muffin.customer.mgmt.repository;

import org.springframework.stereotype.Repository;
import pl.harpi.muffin.common.model2d.drafted.DraftedModelRepository2D;
import pl.harpi.muffin.customer.mgmt.api.model.CustomerDto;
import pl.harpi.muffin.customer.mgmt.model.Customer;
import pl.harpi.muffin.customer.mgmt.model.CustomerInstance;
import pl.harpi.muffin.customer.mgmt.model.CustomerVersion;

@Repository
public class CustomerRepository extends DraftedModelRepository2D<CustomerDto, Customer, CustomerVersion, CustomerInstance> {
    public CustomerRepository() {
        super(CustomerDto.class, Customer.class, CustomerVersion.class, CustomerInstance.class);
    }
}
