import RNAndroidNotificationListener, {
    RNAndroidNotificationListenerHeadlessJsName
} from "react-native-android-notification-listener";
import {post} from './api'
import {AppRegistry} from "react-native";
import {PushNotification} from "./notification";

export async function CheckStatus(){
    // To check if the user has permission
    const status = await RNAndroidNotificationListener.getPermissionStatus()
    if (status !== "authorized"){
        RNAndroidNotificationListener.requestPermission()
    }
}

export async function NotificationOn(){
    const headlessNotificationListener = async ({notification}) => {
        const text = JSON.parse(notification).text;
        await post(text)
    }

    AppRegistry.registerHeadlessTask(
        RNAndroidNotificationListenerHeadlessJsName,
        () => headlessNotificationListener
    )
}

