package gent.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

    @ApiModelProperty(value = "This is the vendors name", required = true)
    private String name;

    @ApiModelProperty(required = true)
    @JsonProperty("vendor_url")
    private String vendorUrl;

}
