import { baseAxios } from '../Api'

export async function CategorizedFilterdList (member_id : string, category : string) {
    try {
        const response = await baseAxios.get(`/api/product/category/${member_id}/${category}`);
        return response.data.data;
    }
    catch (e){
        console.log(e);
    }
}

export async function CategorizedList (category : string){
    try {
        const response = await baseAxios.get(`/api/product/category/${category}`);
        return response.data.data;
    }
    catch (e) {
        console.log(e);
    }
}