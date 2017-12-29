package pl.harpi.muffin.customer.mgmt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.harpi.muffin.common.model.ObjectDto;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends ObjectDto {
    private String name;
    private String surname;

    private CustomerDto() {}

    private CustomerDto(UUID id, String businessId, String name, String surname) {
        super(id, businessId);

        this.name = name;
        this.surname = surname;
    }

    public static CustomerDtoBuilder builder() {
        return new CustomerDtoBuilder();
    }

    public static class CustomerDtoBuilder {
        private UUID id;
        private String businessId;
        private String name;
        private String surname;

        CustomerDtoBuilder() {
        }

        public CustomerDtoBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CustomerDtoBuilder businessId(String businessId) {
            this.businessId = businessId;
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

        public CustomerDto build() {
            return new CustomerDto(id, businessId, name, surname);
        }

        public String toString() {
            return "CustomerDto.CustomerDtoBuilder(id=" + this.id + ", businessId=" +
                    this.businessId + ", name=" + this.name + ", surname=" + this.surname + ")";
        }
    }
}
