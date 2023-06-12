import RNAndroidNotificationListener, {
    RNAndroidNotificationListenerHeadlessJsName
} from "react-native-android-notification-listener";
import {AppRegistry} from "react-native";
import {PushNotification} from "./notification";
import RNFS from "react-native-fs";
const ort = require('onnxruntime-react-native');
import {Notification} from "./notification";

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
        const sess = await ort.InferenceSession.create("file://"+RNFS.DocumentDirectoryPath+"/monokamo.onnx")
        const dict= await sess.run({text: new ort.Tensor('string',[text],[1,1])},[sess.outputNames[0]]);
        const result = dict["output_label"]["data"][0]
        await Notification(result)
    }

    AppRegistry.registerHeadlessTask(
        RNAndroidNotificationListenerHeadlessJsName,
        () => headlessNotificationListener
    )
}

