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

              var levels4 = ["a" , "z"];
              var levels3 = ["a" , "z"];
              var levels2 = ["a" , "z"];
              var levels1 = ["a" , "z"];

              //mongo-db list
              List level4 = levels4;
              List level3 = levels3;
              List level2 = levels2;
              List level1 = levels1;

                //level4
              for (var item4 in level4){
                if(text!.contains(item4)){
                  debugPrint('a');
                }
              }

                //level3
              for (var item3 in level3){
                if(text!.contains(item3)){
                  debugPrint('b');
                }
              }

                //level2
              for (var item2 in level2){
                if(text!.contains(item2)) {
                  debugPrint('c');
                }
              }

                //level1
              for (var item1 in level1){
                if(text!.contains(item1)){
                  debugPrint('d');
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
