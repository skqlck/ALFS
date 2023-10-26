package com.world.alfs.controller.product_img;

import com.world.alfs.service.product_img.ProductImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product_img")
@Slf4j
public class ProductImgController {

    private final ProductImgService productImgService;

}
