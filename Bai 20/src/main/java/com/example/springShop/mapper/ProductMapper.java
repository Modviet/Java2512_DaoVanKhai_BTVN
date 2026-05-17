package com.example.springShop.mapper;

import com.example.springShop.dto.ProducResponseDto;
import com.example.springShop.dto.ProductImageDto;
import com.example.springShop.dto.ProductRequestDto;
import com.example.springShop.dto.SkuDto;
import com.example.springShop.entity.Category;
import com.example.springShop.entity.Product;
import com.example.springShop.entity.ProductImage;
import com.example.springShop.entity.ProductSku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId",source = "category.id")
    @Mapping(target = "categoryName",source = "category.name")
    ProducResponseDto toResponseDto(Product product);

    @Mapping(target = "category",source = "categoryId",qualifiedByName = "idToCategory")
    @Mapping(target = "skus",ignore = true)
    @Mapping(target = "images",ignore = true)
    Product toEntity(ProductRequestDto requestDto);

    @Mapping(target = "colorId",source = "color.id")
    @Mapping(target = "colorCode",source = "color.colorCode")
    @Mapping(target = "sizeId",source = "size.id")
    @Mapping(target = "sizeCode",source = "size.sizeCode")
    SkuDto toSkuDto(ProductSku sku);

    @Mapping(target = "colorId",source = "color.id")
    ProductImageDto toImageDto(ProductImage image);

    @Named("idToCategory")
    default Category idToCategory(Integer categoryId){
        if(categoryId == null)
            return null;
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }
}
