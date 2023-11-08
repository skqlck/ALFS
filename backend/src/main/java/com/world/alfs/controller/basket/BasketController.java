package com.world.alfs.controller.basket;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.basket.request.AddBasketRequest;
import com.world.alfs.controller.basket.request.MemberBasketRequest;
import com.world.alfs.controller.basket.request.PurchaseRequest;
import com.world.alfs.controller.basket.response.GetBasketResponse;
import com.world.alfs.controller.basket.response.GetPurchaseResponse;
import com.world.alfs.service.basket.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
@Slf4j
public class BasketController{

    private final BasketService basketService;

    // 장바구니 조회
    @GetMapping("/{member_id}")
    public ApiResponse<List<GetBasketResponse>> getBasket(@PathVariable Long member_id){
        try {
            List<GetBasketResponse> basketList = basketService.getBasket(member_id);
            return ApiResponse.ok(basketList);
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    // 장바구니 추가
    @PostMapping("/add")
    public ApiResponse addBasket(@RequestBody AddBasketRequest addBasketRequest){
        try {
            GetBasketResponse getBasketResponse = basketService.addBasket(addBasketRequest.getMember_id(), addBasketRequest.getProduct_id(), addBasketRequest.getCount());
            return ApiResponse.created("장바구니에 담겼습니다.",getBasketResponse);
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    // 장바구니 상품 개수 추가
    @PutMapping("/addCount")
    public ApiResponse addCount(@RequestBody MemberBasketRequest memberBasketRequest){
        try {
            int changedCount = basketService.changeCount(memberBasketRequest.getMember_id(), memberBasketRequest.getBasket_id(), 1);
            return ApiResponse.ok(changedCount);
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    // 장바구니 상품 개수 감소
    @PutMapping("/removeCount")
    public ApiResponse removeCount(@RequestBody MemberBasketRequest memberBasketRequest){
        try {
            int changedCount = basketService.changeCount(memberBasketRequest.getMember_id(), memberBasketRequest.getBasket_id(), -1);
            return ApiResponse.ok(changedCount);
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    // 장바구니 상품 삭제
    @PutMapping("/removeBasket")
    public ApiResponse removeBasket(@RequestBody PurchaseRequest purchaseRequest){
        Long member_id = purchaseRequest.getMember_id();
        List<Long> basket_ids = purchaseRequest.getBasket_ids();
        List<Long> successList = new ArrayList<>();
        HashMap<Long, String> failList = new HashMap<>();
        for (Long basket_id : basket_ids){
            try {
                successList.add(basketService.changeBasketStatus(member_id, basket_id, 2));
            }
            catch (Exception e){
                failList.put(basket_id, e.getMessage());
            }
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("success_list", successList);
        response.put("failed_list", failList);
        return ApiResponse.ok(response);
    }

    // 결제
    @PutMapping("purchase")
    public ApiResponse purchaseBasket(@RequestBody PurchaseRequest purchaseRequest){
        Long member_id = purchaseRequest.getMember_id();
        List<Long> basket_ids = purchaseRequest.getBasket_ids();
        List<GetPurchaseResponse> successList = new ArrayList<>();
        HashMap<Long, String> failList = new HashMap<>();
        for (Long basket_id : basket_ids){
            try {
                successList.add(basketService.purchase(member_id, basket_id));
            }
            catch (Exception e){
                failList.put(basket_id, e.getMessage());
            }
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("success_list", successList);
        response.put("failed_list", failList);
        return ApiResponse.ok(response);

    }

    // 결제정보 조회
    @GetMapping("purchase/{member_id}")
    public ApiResponse getPurchaseList(@PathVariable Long member_id) {
        try {
            return ApiResponse.ok(basketService.getPurchaseList(member_id));
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

}
