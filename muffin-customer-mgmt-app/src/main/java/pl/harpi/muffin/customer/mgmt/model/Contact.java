package pl.harpi.muffin.customer.mgmt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.harpi.muffin.common.exceptions.ApplicationException;
import pl.harpi.muffin.common.model.ImmutablePersistentEntity;
import pl.harpi.muffin.common.types.DTOCreatable;
import pl.harpi.muffin.customer.mgmt.api.enums.ContactType;
import pl.harpi.muffin.customer.mgmt.api.model.ContactDto;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "CM_CONTACT")
public class Contact extends ImmutablePersistentEntity<Long> implements DTOCreatable<ContactDto> {
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", length = 40)
    private ContactType type;

    @Column(name = "VALUE", length = 400)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_INSTANCE_ID")
    private CustomerInstance customerInstance;

    public Contact(ContactDto contactDto) throws ApplicationException {
        this.type = contactDto.getType();
        this.value = contactDto.getValue();
    }

    @Override
    public ContactDto createDTO() {
        return ContactDto.builder()
                .type(this.type)
                .value(this.value)
                .build();
    }
}
