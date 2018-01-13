package pl.harpi.muffin.customer.mgmt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.harpi.muffin.common.exceptions.ApplicationException;
import pl.harpi.muffin.common.model.ObjectInstance;
import pl.harpi.muffin.customer.mgmt.api.model.ContactDto;
import pl.harpi.muffin.customer.mgmt.api.model.CustomerDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "CM_CUSTOMER_I")
public class CustomerInstance extends ObjectInstance {
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "SURNAME", nullable = false, length = 200)
    private String surname;

    @OneToMany(mappedBy="customerInstance")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "customerInstance")
    private List<CustomerAddress> addresses;

    public CustomerInstance(CustomerDto customerDto) throws ApplicationException {
        this.name = customerDto.getName();
        this.surname = customerDto.getSurname();

        for (ContactDto contactDto : customerDto.getContacts()) {
            this.getContacts().add(new Contact(contactDto));
        }
    }

    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }

        return contacts;
    }

    public List<CustomerAddress> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }

        return addresses;
    }
}
