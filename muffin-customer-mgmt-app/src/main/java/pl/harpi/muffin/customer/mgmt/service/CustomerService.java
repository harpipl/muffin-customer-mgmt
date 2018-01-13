package pl.harpi.muffin.customer.mgmt.service;

import org.springframework.stereotype.Service;
import pl.harpi.muffin.common.exceptions.ApplicationException;
import pl.harpi.muffin.common.model2d.drafted.DraftedOperationContext2D;
import pl.harpi.muffin.common.model2d.drafted.DraftedVersionDefinition2D;
import pl.harpi.muffin.common.service.BaseService;
import pl.harpi.muffin.common.types.Interval;
import pl.harpi.muffin.customer.mgmt.api.model.CustomerDto;
import pl.harpi.muffin.customer.mgmt.exceptions.CustomerNotFoundException;
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
        DraftedOperationContext2D operationContext = new DraftedOperationContext2D("CREATOR", new Interval(salesDate, null));

        return customerRepository.insert(customerDto, operationContext);
    }

    public CustomerDto findById(UUID id, DraftedVersionDefinition2D versionDefinition) throws ApplicationException {
        return customerRepository.find(id, versionDefinition).orElseThrow(CustomerNotFoundException::new);
    }

    public List<CustomerDto> findAll(DraftedVersionDefinition2D versionDefinition) {
        return customerRepository.findAll(versionDefinition);
    }
}
