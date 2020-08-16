package gent.springframework.services;

import gent.springframework.api.v1.mapper.VendorMapper;
import gent.springframework.api.v1.model.VendorDTO;
import gent.springframework.api.v1.model.VendorListDTO;
import gent.springframework.controllers.v1.CustomerController;
import gent.springframework.controllers.v1.VendorController;
import gent.springframework.domain.Vendor;
import gent.springframework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    VendorMapper vendorMapper;

    VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    private String getVendorUrl(Long id){
        return VendorController.BASE_URL + "/" + id;

    }


    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO =vendorMapper.vendorToVendorDto(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(vendorDTOS);
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDto)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }


    @Override
    public VendorDTO createVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor){
        Vendor savedVentor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDto(savedVentor);

        returnDto.setVendorUrl(getVendorUrl(savedVentor.getId()));

        return  returnDto;
    }

    @Override
    public VendorDTO saveVendorByDto(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {

            if (vendorDTO.getName() != null){
                vendor.setName(vendorDTO.getName());
            }


            return saveAndReturnDTO(vendor);

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }


}
