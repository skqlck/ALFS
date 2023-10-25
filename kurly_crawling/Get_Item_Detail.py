import json
from selenium import webdriver
from selenium.webdriver.common.by import By
import time

# Load item_ids.json, {name : id}
with open('item_ids.json','r',encoding='utf-8') as f:
    item_infos = json.load(f) # List[dict[key]=value]
f.close()

# 상품 정보에서 카테고리를 읽고 값을 넣어줌.
kor2en = {
    '배송':'delivery',
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
detail_infos = []

for item_info in item_infos:
    
    product_id = item_info['id']
    images = item_info['images']

    url = base_url + product_id

    driver.get(url)

    time.sleep(1)

    # 상품 정보
    try:
        section = driver.find_element(By.XPATH, '//*[@id="product-atf"]/section')
    except:
        continue

    title = section.find_element(By.XPATH, 'div[2]/h2').text
    item_info['title'] = title

    prices = section.find_element(By.XPATH, 'h2').text
    prices = prices.split()
    price = int(prices[-2].replace(',',""))
    sale = price

    if len(prices) == 3:
        price = int(price / (100 -  int(prices[0][:-1])) * 100) 
    item_info['price'] = price

    item_info['sale'] = sale

    details = section.find_elements(By.XPATH, 'ul/li')
    for detail in details:
        key = detail.find_element(By.XPATH, 'dt').text
        value = detail.find_element(By.XPATH, 'dd').text
        if key in kor2en.keys():
            item_info[kor2en[key]] = value
        else:
            pass


    # buy_type은 일단 제외
    buy_type = ""
    item_info['buy_type'] = buy_type

    # stock은 직접 설정
    stock = 100
    item_info['stock'] = stock


    action = webdriver.ActionChains(driver)
    goods_intro = driver.find_element(By.CLASS_NAME,"goods_intro")

    action.move_to_element(goods_intro).perform()
    time.sleep(0.1)

    description_img = goods_intro.find_element(By.XPATH, 'div[1]/img').get_attribute('src')
    images['description_img'] = description_img

    content = goods_intro.find_element(By.XPATH, 'div[2]/p').text
    item_info['content'] = content

    ingredient_img_element = driver.find_element(By.XPATH, '//*[@id="detail"]/div[2]/img')
    action.move_to_element(ingredient_img_element).perform()
    time.sleep(0.1)

    ingredient_img = ingredient_img_element.get_attribute('src')
    images['ingredient_img'] = ingredient_img

    detail_infos.append(item_info)

with open('Item_Details.json','w',encoding='utf-8') as f:
    json.dump(detail_infos, f, indent=4, ensure_ascii=False)
f.close()


