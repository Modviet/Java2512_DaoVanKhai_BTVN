package com.folder.productservice.service;

import com.folder.productservice.dto.request.CreatePhoneSpecRequest;
import com.folder.productservice.dto.request.UpdatePhoneSpecRequest;
import com.folder.productservice.dto.response.PhoneSpecResponse;

import java.util.UUID;

public interface PhoneSpecService {

       PhoneSpecResponse create(CreatePhoneSpecRequest request);

       PhoneSpecResponse update(UUID productId,
                                UpdatePhoneSpecRequest request);

       PhoneSpecResponse getByProductId(UUID productId);
}
