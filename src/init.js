import {Foreground} from './forground'
import {CheckStatus,NotificationOn}from './NofiticationRead'
import PushNotification, {Importance} from "react-native-push-notification";
import {PermissionsAndroid, Platform} from "react-native";
import {request, PERMISSIONS} from 'react-native-permissions';

export async function Init(){
    PushNotification.createChannel( {
        channelId: 'warning',
        channelName: 'warning',
        channelDescription: 'Notification for special message',
        importance: Importance.HIGH,
        vibrate: true, },
        (created) => console.log("push create")
    );
    await Foreground()
    await CheckStatus()
    await NotificationOn()
}