package pl.harpi.muffin.customer.mgmt.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.harpi.muffin.common.exceptions.ApplicationException;
import pl.harpi.muffin.common.model.ObjectInstance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public CustomerInstance(CustomerDto customerDto) throws ApplicationException {
        this.name = customerDto.getName();
        this.surname = customerDto.getSurname();
    }
}
