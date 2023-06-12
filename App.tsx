import React from 'react';
import {Init} from './src/init'
import {Platform, SafeAreaView, ScrollView, StatusBar, Text,} from 'react-native';
import {PERMISSIONS, request} from "react-native-permissions";
import RNFS, {downloadFile} from "react-native-fs"


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
