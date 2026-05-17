package com.example.springShop.controller.resource;

import com.example.springShop.entity.Color;
import com.example.springShop.entity.Size;
import com.example.springShop.repository.ColorRepository;
import com.example.springShop.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@RequiredArgsConstructor
class ColorResource {
    private final ColorRepository colorRepository;

    @GetMapping
    public ResponseEntity<List<Color>> getAllColors(){
        return ResponseEntity.ok(colorRepository.findAll());
    }

}


@RestController
@RequestMapping("/api/sizes")
@RequiredArgsConstructor
class SizeResource {
    private final SizeRepository sizeRepository;

    @GetMapping
    public ResponseEntity<List<Size>> getAllSize(){
        return ResponseEntity.ok(sizeRepository.findAll());
    }
}
