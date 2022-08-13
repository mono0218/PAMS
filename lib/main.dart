import 'dart:async';
import 'package:flutter/material.dart';
import 'package:notifications/notifications.dart';
import 'package:pamssms/UI.dart';
import 'notification.dart';

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
    var mono = [event.message];
    print(mono);

//mongo-db list
    List level4 = [];
    List level3 = [];
    List level2 = [];
    List level1 = [];

    //level4
    for (var item4 in level4){
      if(mono.contains(item4)){
        print('a');
      }
    }

//level3
    for (var item3 in level3){
      if(mono.contains(item3)){
        debugPrint('b');
      }
    }

//level2
    for (var item2 in level2){
      if(mono.contains(item2)) {
        debugPrint('c');
      }
    }

//level1
    for (var item1 in level1){
      if(mono.contains(item1)){
        debugPrint('d');
      }
    }
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

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(primarySwatch: Colors.blue),
        home: HomePage()
    );
  }
}



