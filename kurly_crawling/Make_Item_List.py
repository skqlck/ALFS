import json 

with open("Item_Details.json", "r", encoding="utf-8") as f:
    details = json.load(f)
f.close()

list_data = []

for detail in details:
    data = {}
    data['id'] = detail['id']
    data['name'] = detail['name']
    data['images'] = detail['images']['main_img']
    data['title'] = detail['title']
    data['price'] = detail['price']
    data['sale'] = detail['sale']
    data['delivery'] = "일반배송"
    list_data.append(data)

with open("Item_List.json", "w", encoding="utf-8") as f:
    json.dump(list_data, f, indent=4, ensure_ascii=False)
f.close()
