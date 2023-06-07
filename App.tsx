import React from 'react';
import {Init} from './asset/init'
import { AppRegistry } from 'react-native'
import {Platform, SafeAreaView, ScrollView, StatusBar, Text,} from 'react-native';
import{ RNAndroidNotificationListenerHeadlessJsName } from 'react-native-android-notification-listener';
import {PERMISSIONS, request} from "react-native-permissions";

function App(): JSX.Element {
    request(PERMISSIONS.ANDROID.POST_NOTIFICATIONS).then(r => console.log(r))
    try{
        Init().then()
    }catch(error){
        console.error(error)
    }
    return (
    <SafeAreaView>
      <StatusBar/>
      <ScrollView
        contentInsetAdjustmentBehavior="automatic">
        <Text>あ</Text>
      </ScrollView>
    </SafeAreaView>
  );
}

export default App;
