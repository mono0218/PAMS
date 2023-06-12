import PushNotification,{Importance} from 'react-native-push-notification';
export async function Notification(result){
    if(result === "spam"){
        try{
            PushNotification.localNotification({
                channelId: "warning",
                title: "Test",
                message: "Test",
                importance: Importance.HIGH,
            })
        }catch (e) {
            console.error(e)
        }
    }
}