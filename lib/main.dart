import 'package:flutter/material.dart';
import 'package:flutter_sms_inbox/flutter_sms_inbox.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';

void main(List<String> arguments)async {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();

}

class _MyAppState extends State<MyApp> {
  final SmsQuery _query = SmsQuery();
  List<SmsMessage> _messages = [];
  final FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin =
  FlutterLocalNotificationsPlugin();

  notify() {
    final flnp = FlutterLocalNotificationsPlugin();
    return flnp.initialize(
      const InitializationSettings(
        android: AndroidInitializationSettings('@mipmap/ic_launcher'),
      ),
    ).then(
      // 通知の表示
          (_) =>
          flnp.show(
            0,
            '(重要)PAMSからのお知らせです',
            '現在不審なsmsメッセージを受信しました。タップして詳細',
            const NotificationDetails(
              android: AndroidNotificationDetails(
                'channel_id',
                'channel_name',
              ),
            ),
          ),
    );
  }

  safety() {
    final flnp = FlutterLocalNotificationsPlugin();
    return flnp.initialize(
      const InitializationSettings(
        android: AndroidInitializationSettings('@mipmap/ic_launcher'),
      ),
    ).then(
      // 通知の表示
          (_) =>
          flnp.show(
            0,
            'PAMSからのお知らせ',
            '現在受信したsmsは安全です',
            const NotificationDetails(
              android: AndroidNotificationDetails(
                'channel_id',
                'channel_name',
              ),
            ),
          ),
    );
  }


  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: Scaffold(
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text('PAMS',style: Theme.of(context).textTheme.headline4),
              Image.asset('images/sync-icon.png'),
              const Text('status: active'),
              const Text('datebase: active'),
              const Text('システムはすべて正常に動作しています'),
              const Text('お問い合わせはこちら'),
              const Text('©2022. Hiroyoshi Muranaka'),
            ],
          ),
        )
        )
      );
  }


}