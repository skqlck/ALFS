import { baseAxios } from "../Api";

export async function getEvent(){
    try {
        const response = await baseAxios.get('api/event');
        return response.data.data;
    }
    catch (e) {
        console.error(e)
    }
    
}