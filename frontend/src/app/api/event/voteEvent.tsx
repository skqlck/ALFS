import { baseAxios } from "../Api";

export async function voteEvent(id : number, choose_case: number){
    try {
        const response = await baseAxios.post('api/event', {
            id : id,
            choose_case : choose_case
        });
        return response.data.data;
    }
    catch (e) {
        console.error(e)
    }
    
}