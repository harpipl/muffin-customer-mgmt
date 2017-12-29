package pl.harpi.muffin.customer.mgmt.service;

import org.springframework.stereotype.Service;
import pl.harpi.muffin.common.exceptions.ApplicationException;
import pl.harpi.muffin.common.model2d.OperationContext2D;
import pl.harpi.muffin.common.model2d.VersionDefinition2D;
import pl.harpi.muffin.common.service.BaseService;
import pl.harpi.muffin.common.types.Interval;
import pl.harpi.muffin.customer.mgmt.exceptions.CustomerNotFoundException;
import pl.harpi.muffin.customer.mgmt.model.CustomerDto;
import pl.harpi.muffin.customer.mgmt.repository.CustomerRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService extends BaseService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public UUID insert(CustomerDto customerDto, Date salesDate) throws ApplicationException {
        OperationContext2D operationContext = new OperationContext2D("CREATOR", new Interval(salesDate, null));

        return customerRepository.insert(customerDto, operationContext);
    }

    public CustomerDto findById(UUID id, VersionDefinition2D versionDefinition) throws ApplicationException {
        return customerRepository.findByUUID(id, versionDefinition).orElseThrow(CustomerNotFoundException::new);
    }

    public List<CustomerDto> findAll(VersionDefinition2D versionDefinition) {
        return customerRepository.findAll(versionDefinition);
    }
}
