import 'dart:async';
import 'dart:convert';
import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter_background_service_android/flutter_background_service_android.dart';
import 'package:flutter_background_service/flutter_background_service.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';
import 'package:notifications/notifications.dart';
import 'package:pamssms/Main-UI.dart';
import 'package:http/http.dart' as http;
import 'notification.dart';
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';
import 'package:firebase_messaging/firebase_messaging.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await initializeService();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  final fcmToken = await FirebaseMessaging.instance.getToken();
  debugPrint(fcmToken! + "aaa");
  debugPrint("a");
  runApp(const MyApp());

}

// this will be used as notification channel id
const notificationChannelId = 'my_foreground';

// this will be used for notification id, So you can update your custom notification with this id.
const notificationId = 888;

Future<void> initializeService() async {
  final service = FlutterBackgroundService();

  /// OPTIONAL, using custom notification channel id
  const AndroidNotificationChannel channel = AndroidNotificationChannel(
    notificationChannelId, // id
    'PAMS FOREGROUND SERVICE', // title
    description:
    'PAMSはバックグラウンドで機能しています。', // description
    importance: Importance.low, // importance must be at low or higher level
  );

  final FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin =
  FlutterLocalNotificationsPlugin();

  await flutterLocalNotificationsPlugin
      .resolvePlatformSpecificImplementation<
      AndroidFlutterLocalNotificationsPlugin>()
      ?.createNotificationChannel(channel);

  await service.configure(
    androidConfiguration: AndroidConfiguration(
      // this will be executed when app is in foreground or background in separated isolate
      onStart: onStart,

      // auto start service
      autoStart: true,
      isForegroundMode: true,

      notificationChannelId: 'my_foreground',
      initialNotificationTitle: 'AWESOME SERVICE',
      initialNotificationContent: 'Initializing',
      foregroundServiceNotificationId: 888,
    ),
    iosConfiguration: IosConfiguration(
      // auto start service
      autoStart: true,
    ),
  );

  service.startService();

}

Future<void> onStart(ServiceInstance service) async {
  // Only available for flutter 3.0.0 and later
  DartPluginRegistrant.ensureInitialized();

  final FlutterLocalNotificationsPlugin flutterLocalNotificationsPlugin =
  FlutterLocalNotificationsPlugin();

  // bring to foreground
  if (service is AndroidServiceInstance) {
    if (await service.isForegroundService()) {
      flutterLocalNotificationsPlugin.show(
        notificationId,
        'PAMS',
        'PAMSがバックグラウンドで動作しています',
        const NotificationDetails(
          android: AndroidNotificationDetails(
            notificationChannelId,
            'MY FOREGROUND SERVICE',
            icon: 'ic_bg_service_small',
            ongoing: true,
          ),
        ),
      );
    }
  }
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
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    startListening();
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
    var message = (event.message);
    print(message);

    final url = Uri.parse('https://api.monodev.cloud/post/' + message!);
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



