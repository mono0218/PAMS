import {Box, Center, HStack, IconButton, NativeBaseProvider, Pressable, Text} from "native-base";
import Icon from "react-native-vector-icons/MaterialCommunityIcons";
import React from "react";

export function Notification({ navigation }) {
    return (
        <NativeBaseProvider>
            <Box　  paddingTop="10%" paddingLeft="8%">
                <Text fontSize="36" >履歴</Text>
            </Box>
            <Box alignSelf="center" justifyContent="center" flex="0.8">
                <Icon name="check-circle" size={250} color="black"/>
            </Box>
            <Box   width="100%" maxW="100%" bottom="0px" alignSelf="center" position="absolute">
                <HStack bg="white" width="100%" maxW="100%" alignItems="center" bottom="5px">
                    <Pressable py="3" flex={1} >
                        <Center>
                            <IconButton icon={<Icon name="home-outline" size={24} />} onPress={() => navigation.navigate('Home') }/>
                            <Text color="black" fontSize="16">
                                ホーム
                            </Text>
                        </Center>
                    </Pressable>
                    <Pressable py="3" flex={1} >
                        <Center>
                            <IconButton icon={<Icon name="bell" size={24} color="#0017C1" />} onPress={() => navigation.navigate('Notification') }/>
                            <Text color="black" fontSize="16">
                                履歴
                            </Text>
                        </Center>
                    </Pressable>
                    <Pressable py="3" flex={1} >
                        <Center>
                            <IconButton icon={<Icon name="cog-outline" size={24} />} onPress={() => navigation.navigate('settings') }/>
                            <Text color="black" fontSize="16">
                                設定
                            </Text>
                        </Center>
                    </Pressable>
                </HStack>
            </Box>
        </NativeBaseProvider>
    );
}