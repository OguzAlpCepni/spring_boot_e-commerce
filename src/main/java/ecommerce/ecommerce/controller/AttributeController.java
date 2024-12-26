package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.core.Dtos.AttributeDtos.CreateAttributeRequest;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetAllAttributesResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetByIdAttributeResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.UpdateAttributeRequest;
import ecommerce.ecommerce.service.abstracts.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/attribute")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }
    @GetMapping
    public ResponseEntity<List<GetAllAttributesResponse>> getAllAttributes() {
        return ResponseEntity.ok(attributeService.getAllAttributes());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GetByIdAttributeResponse> getAttributeById(@PathVariable int id) {
        return ResponseEntity.ok(attributeService.GetById(id));
    }

    @PostMapping
    public ResponseEntity<?> addAttribute(@RequestBody @Validated CreateAttributeRequest createAttributeRequest) {
        this.attributeService.addAttribute(createAttributeRequest);
        return ResponseEntity.ok("Attributed added succesfully");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateAttribute(@RequestBody UpdateAttributeRequest updateAttributeRequest, @PathVariable int id) {
        this.attributeService.updateAttribute(updateAttributeRequest,id);
        return ResponseEntity.ok("Attributed updated succesfully");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteAttribute(int id) {
        this.attributeService.deleteAttribute(id);
        return ResponseEntity.ok("Attributed deleted succesfully");
    }



}
