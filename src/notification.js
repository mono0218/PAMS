import PushNotification,{Importance} from 'react-native-push-notification';
export async function Notification(result){
    if(result === "spam"){
        try{
            PushNotification.localNotification({
                channelId: "warning",
                title: "PAMSからの警告です",
                message: "フィシングメッセージを受信した可能性があります",
                importance: Importance.HIGH,
            })
        }catch (e) {
            console.error(e)
        }
    }
}