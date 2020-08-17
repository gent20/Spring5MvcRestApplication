package gent.springframework.controllers.v1;

import gent.springframework.api.v1.model.VendorDTO;
import gent.springframework.api.v1.model.VendorListDTO;
import gent.springframework.services.VendorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private VendorService vendorService;


    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getVendorList(){
        return vendorService.getAllVendors();
    }


    @ApiOperation(value = "This will get a vendor by its id" , notes = "U must write a LONG TYPE for id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id ){
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createVendor(vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.saveVendorByDto(id, vendorDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id , @RequestBody VendorDTO vendorDTO){
        return vendorService.saveVendorByDto(id ,vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendorById(id);
    }

}
