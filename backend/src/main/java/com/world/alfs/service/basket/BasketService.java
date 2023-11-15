package com.world.alfs.service.basket;

import com.world.alfs.controller.basket.response.GetBasketResponse;
import com.world.alfs.controller.basket.response.GetPurchaseResponse;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.basket.Basket;
import com.world.alfs.domain.basket.repository.BasketRepository;
import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import com.world.alfs.domain.manufacturing_allergy.repository.ManufacturingAllergyRepository;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member.repository.MemberRepository;
import com.world.alfs.domain.member_allergy.repository.MemberAllergyRepository;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.product_img.ProductImg;
import com.world.alfs.domain.product_img.repostiory.ProductImgRepository;
import com.world.alfs.domain.product_ingredient.repostiory.ProductIngredientRepository;
import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.special.repository.SpecialRepository;
import com.world.alfs.service.basket.dto.AddBasketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional
public class BasketService {
    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;
    private final ProductImgRepository productImgRepository;
    private final ProductRepository productRepository;
    private final ProductIngredientRepository productIngredientRepository;
    private final MemberAllergyRepository memberAllergyRepository;
    private final ManufacturingAllergyRepository manufacturingAllergyRepository;
    private final SpecialRepository specialRepository;
    // 장바구니 조회
    public List<GetBasketResponse> getBasket(Long member_id) throws Exception{
        Member member = memberRepository.findById(member_id)
                .filter(m -> m.getActivate())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        List<Basket> basketList = basketRepository.findByMemberAndStatus(member, 0);
        List<GetBasketResponse> responseList = new ArrayList<>();

        for (Basket basket : basketList){
            Product product = basket.getProduct();
            ProductImg img = productImgRepository.findByProductId(product.getId());

            GetProductListResponse getProductListResponse = product.toListResponse(img,null);

            // 알러지 및 기피 필터링
            Set<Integer> filterCode = new HashSet<>();

            List<Ingredient> productIngredientList = productIngredientRepository.findAllByProduct(product)
                    .stream().map(i -> i.getIngredient()).collect(Collectors.toList());

            List<Allergy> memberAllergyList = memberAllergyRepository.findByMemberId(member_id)
                    .stream().map(memberAllergy -> memberAllergy.getAllergy())
                    .collect(Collectors.toList());

            for (Ingredient ingredient : productIngredientList) {
                for (Allergy allergy : memberAllergyList) {
                    if (ingredient.getName().equals(allergy.getAllergyName())) {
                        filterCode.add(allergy.getAllergyType());
                    }
                }
            }

            // 제조시설 알러지 필터링
            if (manufacturingAllergyRepository.findCountByProductAndAllergy(product.getId(), memberAllergyList.stream().map(memberAllergy -> memberAllergy.getId()).collect(Collectors.toList())) > 0){
                filterCode.add(2);
            };

            // 필터링된 게 없으면 안전
            if (filterCode.isEmpty()){
                filterCode.add(3);
            }

            getProductListResponse.setFilterCode(filterCode);

            GetBasketResponse response = GetBasketResponse.builder()
                    .basket_id(basket.getId())
                    .count(basket.getCount())
                    .getProductListResponse(getProductListResponse)
                    .pack(product.getPack())
                    .isCheck(true)
                    .build();

            responseList.add(response);
        }
        return responseList;
    }

    // 장바구니 추가
    // TODO : 리팩토링 필요
    public GetBasketResponse addBasket(Long member_id, Long product_id, int count) throws Exception {
        Member member = memberRepository.findById(member_id)
                .filter(m -> m.getActivate())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Product product = productRepository.findById(product_id)
                .orElseThrow(()-> new  IllegalArgumentException("존재하지 않는 상품입니다."));

        ProductImg img = productImgRepository.findByProductId(product_id);
        Optional<Basket> existedBasket = basketRepository.findByMemberAndProductAndStatus(member, product, 0);
        if (existedBasket.isEmpty()){
            AddBasketDto addBasketDto = AddBasketDto.builder()
                    .member(member)
                    .product(product)
                    .count(count)
                    .status(0)
                    .build();

            existedBasket = Optional.of(basketRepository.save(addBasketDto.toEntity()));
        }
        else {
            if (existedBasket.get().getStatus() != 0) throw new Exception("삭제 혹은 결제된 장바구니입니다.");
            changeCount(member_id, existedBasket.get().getId(), count);
        }

        GetProductListResponse getProductListResponse = product.toListResponse(img,null);

        Optional<Special> special = specialRepository.findById(product.getId());
        if (special.isPresent()) {
            int status = specialRepository.findByStatus(product.getId());
            if (status == 1) {
                getProductListResponse.setSpecialPrice(special.get().getSalePrice());
            }
        }

        return GetBasketResponse.builder()
                .basket_id(existedBasket.get().getId())
                .count(existedBasket.get().getCount())
                .getProductListResponse(getProductListResponse)
                .pack(product.getPack())
                .isCheck(true)
                .build();
    }

    public int changeCount(Long member_id, Long basket_id, int diff) throws Exception{
        Optional<Basket> basket = basketRepository.findById(basket_id);
        memberRepository.findById(member_id).filter(m -> m.getActivate()).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        if (basket.isEmpty()) throw new Exception("존재하지 않는 장바구니입니다.");
        if (basket.get().getStatus() != 0) throw new Exception("삭제 혹은 결제된 장바구니입니다.");
        if (basket.get().getMember().getId() != member_id) throw  new Exception("권한이 없습니다.");
        if (basket.get().getCount() <= 1 && diff < 0) throw new Exception("0개로 줄일 수 없습니다.");
        basket.get().changeCount(diff);
        return basketRepository.save(basket.get()).getCount();
    }

    public Long changeBasketStatus(Long member_id, Long basket_id, int status) throws Exception {
        Optional<Basket> basket = basketRepository.findById(basket_id);
        memberRepository.findById(member_id).filter(m -> m.getActivate()).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        if (basket.isEmpty()) throw new Exception("존재하지 않는 장바구니입니다.");
        if (basket.get().getMember().getId() != member_id) throw new Exception("권한이 없습니다.");
        if (basket.get().getStatus() != 0) throw new Exception("삭제 혹은 결제된 장바구니입니다.");
        basket.get().setStatus(status);
        return basketRepository.save(basket.get()).getId();
    }

    public GetPurchaseResponse purchase(Long member_id, Long basket_id) throws Exception {
        memberRepository.findById(member_id).filter(m -> m.getActivate()).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Basket basket = basketRepository.findById(basket_id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 장바구니 입니다."));
        if (basket.getStatus() != 0) throw new Exception("삭제 혹은 결제된 장바구니입니다.");
        if (basket.getMember().getId() != member_id) throw new Exception("권한이 없습니다.");
        basket.setPurchase(basket.getProduct().getSale());
        basket.setStatus(1);
        basket.setPurchaseDate(LocalDate.now().toString());
        Basket purchasedBasket = basketRepository.save(basket);
        Product product = purchasedBasket.getProduct();
        ProductImg img = productImgRepository.findByProductId(product.getId());
        return GetPurchaseResponse.builder()
                .basket_id(purchasedBasket.getId())
                .count(purchasedBasket.getCount())
                .totalPrice(purchasedBasket.getPurchase())
                .getProductListResponse(product.toListResponse(img,null))
                .date(purchasedBasket.getPurchaseDate())
                .build();
    }

    public List<GetPurchaseResponse> getPurchaseList(Long member_id) throws Exception {
        Member member = memberRepository.findById(member_id)
                .filter(m -> m.getActivate())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Basket> purchaseList = basketRepository.findByMemberAndStatus(member, 1);
        List<GetPurchaseResponse> responseList = new ArrayList<>();

        for (Basket basket : purchaseList){
            Product product = basket.getProduct();
            ProductImg img = productImgRepository.findByProductId(product.getId());

            GetProductListResponse getProductListResponse = product.toListResponse(img,null);

            // 알러지 및 기피 필터링
            Set<Integer> filterCode = new HashSet<>();

            List<Ingredient> productIngredientList = productIngredientRepository.findAllByProduct(product)
                    .stream().map(i -> i.getIngredient()).collect(Collectors.toList());

            List<Allergy> memberAllergyList = memberAllergyRepository.findByMemberId(member_id)
                    .stream().map(memberAllergy -> memberAllergy.getAllergy())
                    .collect(Collectors.toList());

            for (Ingredient ingredient : productIngredientList) {
                for (Allergy allergy : memberAllergyList) {
                    if (ingredient.getName().equals(allergy.getAllergyName())) {
                        filterCode.add(allergy.getAllergyType());
                    }
                }
            }

            // 제조시설 알러지 필터링
            if (manufacturingAllergyRepository.findCountByProductAndAllergy(product.getId(), memberAllergyList.stream().map(memberAllergy -> memberAllergy.getId()).collect(Collectors.toList())) > 0){
                filterCode.add(2);
            };

            // 필터링된 게 없으면 안전
            if (filterCode.isEmpty()){
                filterCode.add(3);
            }

            getProductListResponse.setFilterCode(filterCode);

            GetPurchaseResponse response = GetPurchaseResponse.builder()
                    .basket_id(basket.getId())
                    .count(basket.getCount())
                    .totalPrice(basket.getPurchase())
                    .getProductListResponse(getProductListResponse)
                    .date(basket.getPurchaseDate())
                    .build();
            responseList.add(response);
        }

        return responseList;
    }

    public void deleteBasket(Long id) {
        basketRepository.deleteByProductId(id);
    }
}
