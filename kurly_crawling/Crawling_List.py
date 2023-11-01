import json
from selenium import webdriver
from selenium.webdriver.common.by import By
import time

pages = 3
driver = webdriver.Edge()

item_list = []

"""
결과는 
readmd.md의 Item_List.json 참조
"""

for page in range(1,pages+1):
    # 베스트 상품 중 식품만
    url = f"https://www.kurly.com/collections/market-best?page={page}&filters=category%3A032%2C249%2C907%2C908%2C909%2C910%2C911%2C912%2C913%2C914%2C915"
    driver.get(url)
    time.sleep(1)

    action = webdriver.ActionChains(driver)

    elements = driver.find_elements(By.XPATH,f'//*[@id="container"]/div/div[2]/div[3]/a')

    for element in elements:

        # 이미지가 화면에 보여야 가져오는 듯. 그래서 직접 해당 요소까지 움직이고 크롤링
        action.move_to_element(element).perform()
        time.sleep(0.1)
        item = {}

        item_id = element.get_attribute('href').split('/')[-1]
        item['id'] = int(item_id)

        item['delivery'] = "일반배송"


        name = element.find_element(By.XPATH, 'div[3]/span[2]').text
        item['name'] = name

        title = element.find_element(By.XPATH, 'div[3]/p[1]').text
        item['title'] = title

        price_info = element.find_elements(By.XPATH, 'div[3]/div[1]/div')
        if len(price_info) == 2:
            sale = int(price_info[1].find_element(By.CLASS_NAME, 'sales-price').text.split('원')[0].replace(",",""))
            price = int(price_info[0].find_element(By.CLASS_NAME, 'dimmed-price').text.split('원')[0].replace(",",""))
        elif len(price_info) == 1:
            sale = int(price_info[0].find_element(By.CLASS_NAME, 'sales-price').text.split('원')[0].replace(",",""))
            price = sale
        else:
            print(f"예외 발생 : {item['id']}")
            continue
        
        item['sale'] = sale
        item['price'] = price

        img = element.find_element(By.XPATH, 'div[1]/div/span/img')
        img_src = img.get_attribute("src")
        item['image'] = img_src

        item_list.append(item)

with open('Item_List.json','w',encoding='utf-8') as f:
    json.dump(item_list, f, indent=4, ensure_ascii=False)
f.close()