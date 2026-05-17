package com.example.springShop.service.impl;

import com.example.springShop.dto.CategoryFormDto;
import com.example.springShop.dto.CategoryListItemDto;
import com.example.springShop.dto.CategoryTreeDto;
import com.example.springShop.entity.Category;
import com.example.springShop.mapper.CategoryMapper;
import com.example.springShop.repository.CategoryRepository;
import com.example.springShop.service.CategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

        private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryListItemDto> getCategories(String keyword, Integer parentId, int page, int size) {
       return categoryRepository.findAll(keyword, parentId, page, size)
               .stream()
               .map(CategoryMapper::toListItemDto)
               .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long countCategories(String keyword, Integer parentId) {
        return categoryRepository.countAll(keyword, parentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryTreeDto> getCategoryTree(String keyword, Integer parentId) {
        List<Category> allEntities = categoryRepository.findAll(null, null, 1 , 10000);

        Map<Integer, CategoryTreeDto> allNodes = new HashMap<>();
        for(Category entity : allEntities){
            allNodes.put(entity.getId(), CategoryMapper.toTreeDto(entity));
        }

        Set<Integer> keptIds = new HashSet<>();

        boolean hasFilter = (keyword != null && !keyword.isBlank()) || parentId != null;

        if(hasFilter){
            String kw = keyword != null ? keyword.trim().toLowerCase() : null;

            for(CategoryTreeDto node : allNodes.values()){
                boolean matchKw = kw == null || node.getName().toLowerCase().contains(kw);
                boolean matchParent = parentId == null || Objects.equals(node.getParentId(),parentId);

                if(matchKw && matchParent){
                    Integer currId = node.getId();
                    while (currId != null){
                        keptIds.add(currId);
                        CategoryTreeDto currNode = allNodes.get(currId);
                        currId = (currNode != null) ? currNode.getParentId() : null;
                    }
                }
            }
        } else {
            keptIds.addAll(allNodes.keySet());
        }

        List<CategoryTreeDto> roots = new ArrayList<>();

        for (CategoryTreeDto node : allNodes.values()){
            if(!keptIds.contains(node.getId()))
                continue;

            Integer pId = node.getParentId();
            if(pId == null || !keptIds.contains(pId)){
                roots.add(node);
            } else {
                CategoryTreeDto parentNode = allNodes.get(pId);
                if(parentNode != null){
                    parentNode.getChildren().add(node);
                }
            }
        }

        for(CategoryTreeDto root : roots){
            calculateDepthAndSort(root, 0);
        }

        roots.sort(Comparator.comparing(CategoryTreeDto::getCreatedAt).reversed());
        return roots;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryFormDto getCategoryForm(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Category not found : "+id));
        return CategoryMapper.toFormDto(category);
    }

    @Override
    public void createCategory(CategoryFormDto dto) {
       if(dto.getName() == null || dto.getName().isBlank()){
           throw new IllegalArgumentException("Category name is required");
       }

       Category category = new Category();
       category.setName(dto.getName());
       category.setDeleted((byte) 0);

       if(dto.getParentId() != null){
           Category parent = categoryRepository.findById(dto.getParentId())
                   .orElseThrow(()-> new IllegalArgumentException("Parent category not found"));
           category.setParent(parent);
       }

       categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Integer id, CategoryFormDto dto) {
        if(dto.getName() == null || dto.getName().isBlank()){
            throw new IllegalArgumentException("Category name is required");
        }

        Category existing = categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Category not found : "+ id));

        existing.setName(dto.getName());

        if(dto.getParentId() != null){
            if(dto.getParentId().equals(id)){
                throw new IllegalArgumentException("Category cannot be its own parent");
            }

            Category parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(()-> new IllegalArgumentException("Parent category not found"));
            existing.setParent(parent);
        } else {
            existing.setParent(null);
        }

        categoryRepository.save(existing);
    }

    @Override
    public void deleteCategory(Integer id) {
          int updated = categoryRepository.deleteById(id);
          if(updated == 0){
              throw new IllegalArgumentException("Category not found : "+ id);
          }
    }

    private void calculateDepthAndSort(CategoryTreeDto node, int depth) {
        node.setDepth(depth);
        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            node.getChildren().sort(Comparator.comparing(CategoryTreeDto::getCreatedAt).reversed());
            for (CategoryTreeDto child : node.getChildren()) {
                calculateDepthAndSort(child, depth + 1);
            }
        }
    }
}
