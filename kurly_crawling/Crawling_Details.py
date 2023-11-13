import json
from selenium import webdriver
from selenium.webdriver.common.by import By
import time
import random

# Load item_ids.json, {name : id}
with open('Item_List.json','r',encoding='utf-8') as f:
    item_list = json.load(f) # List[dict[key]=value]
f.close()

# 상품 정보에서 카테고리를 읽고 값을 넣어줌.
kor2en = {
    '판매자':'seller',
    '포장타입' : 'package',
    '판매단위' : 'count',
    '중량/용량' : 'weight',
    '알레르기정보' : 'allergy',
    '유통기한(또는 소비기한)정보' : 'expiry_date',
    '안내사항' : 'information',
}

base_url = "https://www.kurly.com/goods/"

driver = webdriver.Edge()

for item in item_list:

    # Item_List의 image 키를 images로 변경 및 추가
    images = {}
    item['images'] = images

    main_img = item.pop('image')
    images['main_img'] = main_img

    item["seller"] = ""
    item["package"] = ""
    item["count"] = ""
    item["weight"] = ""
    item["allergy"] = ""
    item["expiry_date"] = ""
    item["information"] = ""

    # buy_type은 일단 제외
    item['buy_type'] = ""

    # stock은 직접 설정
    item['stock'] = random.randint(100,1000)

    item["content"] = ""

    product_id = item['id']

    url = base_url + str(product_id)

    driver.get(url)

    time.sleep(1)

    # 상품 정보
    try:
        section = driver.find_element(By.XPATH, '//*[@id="product-atf"]/section')
    except:
        continue
    
    details = section.find_elements(By.XPATH, 'ul/li')
    for detail in details:
        key = detail.find_element(By.XPATH, 'dt').text
        value = detail.find_element(By.XPATH, 'dd').text
        if key in kor2en.keys():
            item[kor2en[key]] = value

    action = webdriver.ActionChains(driver)
    goods_intro = driver.find_element(By.CLASS_NAME,"goods_intro")

    action.move_to_element(goods_intro).perform()
    time.sleep(0.1)

    description_img = goods_intro.find_element(By.XPATH, 'div[1]/img').get_attribute('src')
    images['description_img'] = description_img

    content = goods_intro.find_element(By.XPATH, 'div[2]/p').text
    item['content'] = content

    ingredient_img_element = driver.find_element(By.XPATH, '//*[@id="detail"]/div[2]/img')
    action.move_to_element(ingredient_img_element).perform()
    time.sleep(0.1)

    ingredient_img = ingredient_img_element.get_attribute('src')
    images['ingredient_img'] = ingredient_img
0
with open('Item_Details.json','w',encoding='utf-8') as f:
    json.dump(item_list, f, indent=4, ensure_ascii=False)
f.close()


