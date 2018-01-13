package pl.harpi.muffin.customer.mgmt.api.model;

import lombok.Builder;
import lombok.Data;
import pl.harpi.muffin.customer.mgmt.api.enums.ContactType;

@Data
@Builder
public class ContactDto {
    private ContactType type;
    private String value;
}
