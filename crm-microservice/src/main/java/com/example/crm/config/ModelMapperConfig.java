package com.example.crm.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.crm.document.CustomerDocument;
import com.example.crm.dto.request.UpdateCustomerRequest;

@Configuration
public class ModelMapperConfig {
	private static final Converter<UpdateCustomerRequest,CustomerDocument>
	   UPDATE_CUSTOMER_REQUEST_TO_CUSTOMER_DOCUMENT_CONVERTER =
	   (context) -> {
		  var source = context.getSource();
		  var destination = context.getDestination();
		  destination.setFullname(source.getFullname().toLowerCase());
		  destination.setPhoto(source.getPhoto());
		  destination.setEmail(source.getEmail());
		  destination.setType(source.getType());
		  destination.setAddresses(source.getAddresses());
		  destination.setPhone(source.getPhone());
		  // setter -> getter
		  return destination;
	   };
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(
				UPDATE_CUSTOMER_REQUEST_TO_CUSTOMER_DOCUMENT_CONVERTER, 
				UpdateCustomerRequest.class, CustomerDocument.class);
		return modelMapper;
	}
}
