import axios from 'axios'
import {Notification} from "./notification";
export async function post(message){
    await axios.post("https://cfb6-103-115-217-203.ngrok-free.app/post/", {message:message})
        .then((response) => {Notification(response.data)}).catch((response)=> Notification(response.data));
}