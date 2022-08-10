import 'package:flutter/material.dart';
import 'package:flutter_sms_inbox/flutter_sms_inbox.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';

void main() {
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
  final FlutterLocalNotificationsPlugin  flutterLocalNotificationsPlugin =
  FlutterLocalNotificationsPlugin();

  notify() {
    final flnp = FlutterLocalNotificationsPlugin();
    return flnp.initialize(
      const InitializationSettings(
        android: AndroidInitializationSettings('@mipmap/ic_launcher'),
      ),
    ).then(
      // 通知の表示
          (_) => flnp.show(
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
          (_) => flnp.show(
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
      home: Scaffold(
        appBar: AppBar(
          title: const Text('pams-sms'),
        ),
        body: Container(
          padding: const EdgeInsets.all(10.0),
          child: ListView.builder(
            shrinkWrap: true,
            itemCount: _messages.length,
            itemBuilder: (BuildContext context, int i) {
              var message = _messages[i];
              final text = message.body;
              if(text!.contains("4")){
                notify();
              }else{
                if(text.contains("3")){

                }else{
                  if(text.contains("2")){

                  }else{
                    if(text.contains("1")){

                    }else{
                      safety();
                   }
                  }
                }
              }

              return ListTile(
                title: Text('${message.sender} [${message.date}]'),
                subtitle: Text('${message.body}'),
              );
            },
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () async {
            final FlutterLocalNotificationsPlugin  flutterLocalNotificationsPlugin =
            FlutterLocalNotificationsPlugin();

            var permission = await Permission.sms.status;
            if (permission.isGranted) {
              final messages = await _query.querySms(
                kinds: [SmsQueryKind.inbox, SmsQueryKind.sent],
                // address: '+254712345789',
                count: 10,
              );
              debugPrint('sms inbox messages: ${messages.length}');
              setState(() => _messages = messages);
            } else {
              await Permission.sms.request();
            }
          },
          child: const Icon(Icons.refresh),
        ),
      ),
    );
  }
}
