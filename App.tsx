import React from 'react';
import {Init} from './src/init'
import {Home} from "./screen/home"
import {Notification} from "./screen/notification";
import {Settings} from "./screen/settings"
import {PERMISSIONS, request} from "react-native-permissions";
import RNFS, {downloadFile} from "react-native-fs"
// @ts-ignore
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'

const Stack = createStackNavigator()

const config = {
    animation: 'slide_from_right',
};

function App() {
    return (
        <NavigationContainer>
            <Stack.Navigator
                initialRouteName="Home"
            >
                <Stack.Screen
                    name="Home"
                    component={Home}
                    options={{
                        title: "Home",
                        headerShown: false,
                    }}
                />

                <Stack.Screen
                    name="Notification"
                    component={Notification}
                    options={{
                        title: "Notification",
                        headerShown: false,
                    }}
                />

                <Stack.Screen
                    name="settings"
                    component={Settings}
                    options={{
                        title: "settings",
                        headerShown: false,
                    }}
                />
            </Stack.Navigator>

        </NavigationContainer>
    )
}

export default App;
