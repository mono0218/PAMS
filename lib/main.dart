import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';
import 'package:notifications/notifications.dart';

import 'package:pamssms/UI.dart';

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
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    startListening();
  }

  void onData(NotificationEvent event) {
    setState(() {
      _log.add(event);
    });
    print(event.toString());
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


  final FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin =
  FlutterLocalNotificationsPlugin();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(primarySwatch: Colors.blue),
        home: HomePage()
    );
  }
}



