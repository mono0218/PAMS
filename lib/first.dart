
import 'dart:async';
import 'dart:developer';
import 'UI.dart';
import 'package:flutter/material.dart';
import 'package:intro_slider/intro_slider.dart';
import 'package:notifications/notifications.dart';
import 'package:flutter_background/flutter_background.dart';

class IntroScreenDefault extends StatefulWidget {
  const IntroScreenDefault({Key? key}) : super(key: key);

  @override
  IntroScreenDefaultState createState() => IntroScreenDefaultState();
}

class IntroScreenDefaultState extends State<IntroScreenDefault> {
  List<Slide> slides = [];

  @override
  void initState() {
    super.initState();

    slides.add(
      Slide(
        title: "ようこそ-PAMSへ",
        description:
        "本アプリをインストールしてくださりありがとうございます。",
        pathImage: "images/sync-icon.png",
        backgroundColor: const Color(0xfff5a623),
      ),
    );

    slides.add(
      Slide(
        title: "snsアカウントを安全に",
        description:
        "このアプリは通知を読み取りフィッシングを検知しユーザーに通知することを目標としています",
        pathImage: "images/warning.png",
        backgroundColor: const Color(0xff203152),
      ),
    );

    slides.add(
      Slide(
        title: "権限の設定のお願い",
        description:
        "このアプリを利用するには通知へのアクセスとバックグラウンド実行の許可が必要です",
        pathImage: "images/warning.png",
        backgroundColor: const Color(0xff203152),
      ),
    );

    slides.add(
      Slide(
        title: "通知へのアクセス権限",
        description:
        "このボタンから設定、表示されるアプリからPAMS-smsを選択いただき許可にしてください",
        pathImage: "images/warning.png",
        onCenterItemPress: tuuken,
        backgroundColor: const Color(0xff203152),
      ),
    );

    slides.add(
      Slide(
        title: "バックグラウンド権限",
        description:
        "このボタンから設定、表示されるアプリからPAMS-smsを選択いただき許可にしてください",
        pathImage: "images/warning.png",
        onCenterItemPress: backken,
        backgroundColor: const Color(0xff203152),
      ),
    );

    slides.add(
      Slide(
        title: "let's go！",
        description:
        "今すぐアカウントを保護しましょう！",
        pathImage: "images/sec.png",
        backgroundColor: const Color(0xff9932CC),
      ),
    );
  }



  void backken()async{
    final androidConfig = FlutterBackgroundAndroidConfig(
      notificationTitle: "flutter_background example app",
      notificationText: "Background notification for keeping the example app running in the background",
      notificationImportance: AndroidNotificationImportance.Default,
      notificationIcon: AndroidResource(name: 'background_icon', defType: 'drawable'), // Default is ic_launcher from folder mipmap
    );
    bool success = await FlutterBackground.initialize(androidConfig: androidConfig);
    bool hasPermissions = await FlutterBackground.hasPermissions;

  }

  void tuuken(){
    Notifications _notifications;
    StreamSubscription<NotificationEvent> _subscription;

    void onData(NotificationEvent event) {
    print(event);
    }

    void startListening() {
      _notifications = new Notifications();
      try {
        _subscription = _notifications.notificationStream!.listen(onData);
      } on NotificationException catch (exception) {
        print(exception);
      }
    }

  }

  void onDonePress() {
    Navigator.push(context, MaterialPageRoute(
        builder: (context) => HomePage()
    ));
    log("End of slides");
  }
  @override
  Widget build(BuildContext context) {
    return IntroSlider(
      slides: slides,
      autoScroll: false,
      onDonePress: onDonePress,
    );
    }
}