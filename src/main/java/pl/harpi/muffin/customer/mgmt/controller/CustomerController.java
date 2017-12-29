package pl.harpi.muffin.customer.mgmt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.harpi.muffin.common.exceptions.ApplicationException;
import pl.harpi.muffin.common.model2d.VersionDefinition2D;
import pl.harpi.muffin.common.types.ExceptionMapper;
import pl.harpi.muffin.common.utils.BooleanHelper;
import pl.harpi.muffin.common.utils.DateHelper;
import pl.harpi.muffin.common.utils.UUIDHelper;
import pl.harpi.muffin.customer.mgmt.model.CustomerDto;
import pl.harpi.muffin.customer.mgmt.service.CustomerService;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private ExceptionMapper exceptionMapper;
    private CustomerService customerService;

    public CustomerController(CustomerService customerService, ExceptionMapper exceptionMapper) {
        this.customerService = customerService;
        this.exceptionMapper = exceptionMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCustomers(
            @RequestParam(value = "draft") String draft,
            @RequestParam(value = "sd") String salesDate,
            @RequestParam(value = "vd") String validDate
    ) throws ApplicationException {
        List<CustomerDto> customerDtoList = customerService.findAll(
                new VersionDefinition2D(BooleanHelper.toBoolean(draft), DateHelper.toDate(salesDate), DateHelper.toDate(validDate)));
        return ResponseEntity.ok(customerDtoList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerById(
            @PathVariable String id,
            @RequestParam(value = "draft") String draft,
            @RequestParam(value = "sd") String salesDate,
            @RequestParam(value = "vd") String validDate
    ) throws ApplicationException {
        CustomerDto customerDto = customerService.findById(UUID.fromString(id),
                new VersionDefinition2D(BooleanHelper.toBoolean(draft), DateHelper.toDate(salesDate), DateHelper.toDate(validDate)));
        return ResponseEntity.ok(customerDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDto customerDto, @RequestParam(value = "sd") String salesDate)
            throws ApplicationException {
        UUID id = customerService.insert(customerDto, DateHelper.toDate(salesDate));

        if (id != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}").buildAndExpand(id.toString()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity onVehicleNotFound(ApplicationException ex, Locale locale) {
        return new ResponseEntity<>(exceptionMapper.map(ex, locale), HttpStatus.BAD_REQUEST);
    }
}
