package ecommerce.ecommerce.service.concreate;

import ecommerce.ecommerce.core.Dtos.AttributeDtos.CreateAttributeRequest;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetAllAttributesResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetByIdAttributeResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.UpdateAttributeRequest;
import ecommerce.ecommerce.core.exceptions.AttributeNotFoundException;
import ecommerce.ecommerce.model.Attribute;
import ecommerce.ecommerce.repository.AttributeRepository;
import ecommerce.ecommerce.service.abstracts.AttributeService;
import ecommerce.ecommerce.service.mapper.AttributeMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AttributeManager implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final AttributeMapper attributeMapper;

    public AttributeManager(AttributeRepository attributeRepository, AttributeMapper attributeMapper) {
        this.attributeRepository = attributeRepository;
        this.attributeMapper = attributeMapper;
    }

    @Override
    public void addAttribute(CreateAttributeRequest createAttributeRequest) {
        Attribute attribute = attributeMapper.mappingDtoToAttributeEntity(createAttributeRequest);
        attributeRepository.save(attribute);
    }

    @Override
    public List<GetAllAttributesResponse> getAllAttributes() {
        List<Attribute> attributes = attributeRepository.findAll();
        return attributeMapper.mappingAttributesToGetAllAttributesResponse(attributes);

    }

    @Override
    public GetByIdAttributeResponse GetById(int id) {
        Attribute attribute = attributeRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("Attribute not found"));
        return attributeMapper.mappingAttributeToAttributeResponseDto(attribute);
    }

    @Override
    public void updateAttribute(UpdateAttributeRequest updateAttributeRequest,int id) {
        Attribute attribute=attributeRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("Attribute not found"));
        attributeMapper.mappingUpdateRequestDtoToAttributeEntity(updateAttributeRequest,attribute);
        attributeRepository.save(attribute);

    }

    @Override
    public void deleteAttribute(int id) {
        attributeRepository.deleteById(id);
    }
}
