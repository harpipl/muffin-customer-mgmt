package pl.harpi.muffin.customer.mgmt.api.model;

import lombok.Data;
import pl.harpi.muffin.address.repository.api.model.AddressDto;
import pl.harpi.muffin.customer.mgmt.api.enums.AddressType;

import java.util.UUID;

@Data
public class CustomerAddressDto {
    private UUID id;
    private AddressDto addressData;
    private AddressType type;
}
