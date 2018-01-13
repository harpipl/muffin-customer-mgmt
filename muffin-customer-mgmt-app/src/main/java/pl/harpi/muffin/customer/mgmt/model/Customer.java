package pl.harpi.muffin.customer.mgmt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.harpi.muffin.common.model2d.drafted.DraftedObjectIdentity2D;
import pl.harpi.muffin.customer.mgmt.api.model.ContactDto;
import pl.harpi.muffin.customer.mgmt.api.model.CustomerDto;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "CM_CUSTOMER")
public class Customer extends DraftedObjectIdentity2D<CustomerDto, Customer, CustomerVersion, CustomerInstance> {
    public Customer() {
        super(Customer.class, CustomerVersion.class, CustomerInstance.class);
    }

    public Customer(CustomerDto customerDto) {
        super(Customer.class, CustomerVersion.class, CustomerInstance.class);
    }

    @Override
    public CustomerDto createDTO() {
        // TODO: dorobić kontakty i adresy

        List<ContactDto> contacts = new ArrayList<>();

        for (Contact contact : getVersions().get(0).getObjectInstance().getContacts()) {
            contacts.add(contact.createDTO());
        }

        return CustomerDto.builder()
                .id(getUuid())
                .name(getVersions().get(0).getObjectInstance().getName())
                .surname(getVersions().get(0).getObjectInstance().getSurname())
                .contacts(contacts)
                .build();
    }
}
