package pl.harpi.muffin.customer.mgmt.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.harpi.muffin.address.repository.api.model.AddressDto;
import pl.harpi.muffin.common.model.ObjectDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends ObjectDto {
    private String name;
    private String surname;
    private List<ContactDto> contacts;
    private List<CustomerAddressDto> addresses;

    private CustomerDto() {}

    private CustomerDto(UUID id, String name, String surname, List<ContactDto> contacts, List<CustomerAddressDto> addresses) {
        super(id);

        this.name = name;
        this.surname = surname;
        this.contacts = contacts;
        this.addresses = addresses;
    }

    public List<ContactDto> getContacts() {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        return contacts;
    }

    public List<CustomerAddressDto> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        return addresses;
    }

    public static CustomerDtoBuilder builder() {
        return new CustomerDtoBuilder();
    }

    public static class CustomerDtoBuilder {
        private UUID id;
        private String businessId;
        private String name;
        private String surname;
        private List<ContactDto> contacts;
        private List<CustomerAddressDto> addresses;

        CustomerDtoBuilder() {
        }

        public CustomerDtoBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CustomerDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerDtoBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public CustomerDtoBuilder contacts(List<ContactDto> contacts) {
            this.contacts = contacts;
            return this;
        }

        public CustomerDtoBuilder addresses(List<CustomerAddressDto> addresses) {
            this.addresses = addresses;
            return this;
        }

        public CustomerDto build() {
            return new CustomerDto(id, name, surname, contacts, addresses);
        }

        public String toString() {
            return "CustomerDto.CustomerDtoBuilder(id=" + this.id + ", name=" + this.name + ", surname=" + this.surname + ")";
        }
    }
}
