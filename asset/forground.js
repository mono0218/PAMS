import VIForegroundService from "@voximplant/react-native-foreground-service";

export async function Foreground(){
    const foregroundService = VIForegroundService.getInstance();
    const channelConfig = {
        id: '1234',
        name: 'channel',
        description: 'Channel',
        enableVibration: false
    };

    console.log(foregroundService)
    await foregroundService.createNotificationChannel(channelConfig);

    const notificationConfig = {
        channelId: '1234',
        id: 3456,
        title: 'Title',
        text: 'Some text',
        icon:'icon',
    };

    try {
        await VIForegroundService.getInstance().startService(notificationConfig);
    } catch (e) {
        console.error(e);
    }
}