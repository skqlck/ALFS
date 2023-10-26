import json
from selenium import webdriver
from selenium.webdriver.common.by import By
import time

pages = 3
driver = webdriver.Edge()

item_infos = []

"""
상품 이름, 상품 ID, 상품 관련 이미지 3장 크롤링
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
        item['id'] = item_id

        name = element.find_element(By.XPATH, 'div[3]/span[2]').text
        item['name'] = name

        img = element.find_element(By.XPATH, 'div[1]/div/span/img')
        img_src = img.get_attribute("src")
        item['images'] = {}
        item['images']['main_img'] = img_src

        item_infos.append(item)

with open('Item_IDs.json','w',encoding='utf-8') as f:
    json.dump(item_infos, f, indent=4, ensure_ascii=False)
f.close()