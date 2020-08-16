package gent.springframework.services;

import gent.springframework.api.v1.model.CustomerDTO;
import gent.springframework.api.v1.model.VendorDTO;
import gent.springframework.api.v1.model.VendorListDTO;
import gent.springframework.domain.Vendor;

import java.util.List;

public interface VendorService {

    VendorListDTO getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO createVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDto(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
