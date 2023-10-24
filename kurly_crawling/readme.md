## Kurly 정보 싹다 털기
* Kurly는 동적 페이지라서, BeautifulSoup이 아닌 Selenium으로 해야합니다.
* WebDriver 라는 것이 필요한데, 크롬 대신에 안쓰는 Edge로 했습니다.
* 필요한 정보들
    ![](./product_erd.PNG)
    ![](./product_img_erd.PNG)
* JSON으로 정리한다면,
```javascript
    [
        {
            name : String,
            title : String,
            price : int,
            sale : int,
            images : [
                main_img : String,
                description_img : String,
                ingredient_img : String
            ],
            delivery : String,
            seller : String,
            package : String,
            count : int,
            weight : int,
            allergy : String,
            expiry_date : String,
            information : String,
            buy_type : String,
            stock : int,
            content : String
        },
        {
            ...
        },
        ...
    ]
```
* ItemID_Extract는 상품명과 상품ID를 가져온다.
    * 가져온 상품ID로 상품 상세조회를 한다.

* 이미지 분류
    * 상품 목록 이미지 (main_img)
        ![](./images/product_main_img.PNG)

    * 상품 설명 이미지 (description_img)
        ![](./images/product_description_img.PNG)

    * 상품 원재료명 이미지 (ingredient_img)
        ![](./images/ingredient_img.PNG)