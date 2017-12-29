package pl.harpi.muffin.customer.mgmt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.harpi.muffin.common.model2d.ObjectVersion2D;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CM_CUSTOMER_V")
public class CustomerVersion extends ObjectVersion2D<CustomerDto, Customer, CustomerVersion, CustomerInstance> {
    public CustomerVersion() {
        super(Customer.class, CustomerVersion.class, CustomerInstance.class);
    }
}
