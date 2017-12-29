package pl.harpi.muffin.customer.mgmt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.harpi.muffin.common.model2d.ObjectIdentity2D;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "CM_CUSTOMER")
public class Customer extends ObjectIdentity2D<CustomerDto, Customer, CustomerVersion, CustomerInstance> {
    public Customer() {
        super(Customer.class, CustomerVersion.class, CustomerInstance.class);
    }

    public Customer(CustomerDto customerDto) {
        super(Customer.class, CustomerVersion.class, CustomerInstance.class);

        this.setBusinessId(customerDto.getBusinessId());
    }


    @Override
    public CustomerDto createDTO() {
        return CustomerDto.builder()
                .id(getUuid())
                .businessId(getBusinessId())
                .name(getVersions().get(0).getObjectInstance().getName())
                .surname(getVersions().get(0).getObjectInstance().getSurname())
                .build();
    }
}
