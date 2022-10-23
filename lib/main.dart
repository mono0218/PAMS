import 'dart:async';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter/src/foundation/print.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';
import 'package:flutter_background/flutter_background.dart';
import 'package:notifications/notifications.dart';
import 'package:pamssms/Main-UI.dart';
import 'package:http/http.dart' as http;
import 'notification.dart';
import 'package:flutter_background_service/flutter_background_service.dart';

void main(List<String> arguments)async {
  runApp(const MyApp());

}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();

}



class _MyAppState extends State<MyApp> {
  Notifications? _notifications;
  StreamSubscription<NotificationEvent>? _subscription;
  List<NotificationEvent> _log = [];
  bool started = false;



  @override
  void initState() {
    initPlatformState();
    super.initState();
    initializeService();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    startListening();
  }

  Future<void> initializeService() async {
    final androidConfig = FlutterBackgroundAndroidConfig(
      notificationTitle: "flutter_background example app",
      notificationText: "Background notification for keeping the example app running in the background",
      notificationImportance: AndroidNotificationImportance.Default,
      notificationIcon: AndroidResource(name: 'background_icon', defType: 'drawable'),
    );
    bool success = await FlutterBackground.initialize(androidConfig: androidConfig);
    FlutterBackground.initialize();
    FlutterBackground.enableBackgroundExecution();

  }

  void startListening() {
    _notifications = Notifications();
    try {
      _subscription = _notifications!.notificationStream!.listen(onData);
      setState(() => started = true);
    } on NotificationException catch (exception) {
      print(exception);
    }
  }

  onData(NotificationEvent event)  async {
    setState(() {
      _log.add(event);
    });
    var mono = (event.message);
    print(mono);

    final url = Uri.parse('https://8381-106-180-11-37.jp.ngrok.io/post/' + mono!);
    http.Response resp = await http.post(url);
    if (resp.statusCode != 200) {
      debugPrint("エラーが発生しました");
      return;
    }else{
      debugPrint(resp.body.toString());
      Map<String, dynamic> map = jsonDecode(resp.body);
      String result = map["result"];
      debugPrint(result);

      if(result != "ham"){
        notify();
      }
    }
  }



  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'PAMS-main',
        theme: ThemeData(primarySwatch: Colors.blue),
        home: HomePage()
    );
  }
}




  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    throw UnimplementedError();
  }



