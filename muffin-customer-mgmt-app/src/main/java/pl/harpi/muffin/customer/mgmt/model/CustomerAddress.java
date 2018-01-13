package pl.harpi.muffin.customer.mgmt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.harpi.muffin.common.model.ImmutablePersistentEntity;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "CM_CUSTOMER_ADDRESS")
public class CustomerAddress extends ImmutablePersistentEntity<Long> {
    @Column(name = "UUID", nullable = false, length = 50)
    private UUID addressUUID;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="CUSTOMER_INSTANCE_ID")
    private CustomerInstance customerInstance;
}
