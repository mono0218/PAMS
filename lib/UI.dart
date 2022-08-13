import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:pamssms/main.dart';
import 'package:notifications/notifications.dart';
import 'package:flutter_background/flutter_background.dart';
import 'main.dart';

class HomePage extends StatefulWidget {
  HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}
void _opneUrl() async {
  const url = 'https://pams.monodev.cloud';
  if (await canLaunch(url)) {
    await launch(
      url,
      forceSafariVC: true,
      forceWebView: true,
    );
  } else {
    throw 'このURLにはアクセスできません';
  }
}



class _HomePageState extends State<HomePage> {
  @override
  void initState() {
    super.initState();
  }
@override
void backkidou()async{
  bool success = await FlutterBackground.enableBackgroundExecution();
}



  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'home',
        theme: ThemeData(primarySwatch: Colors.blue),
        home: Scaffold(
            body: Center(
              child: Column(

                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Container(
                    margin: EdgeInsets.only(bottom: 60),
                    child: Text('PAMS', style: Theme
                        .of(context)
                        .textTheme
                        .headline4),
                  ),

                  Image.asset('images/sync-icon.png'),

                  Container(
                    margin: EdgeInsets.only(top: 40),
                    child: Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          const Text('status: active', style: TextStyle(fontSize: 20, color: Colors.green)),
                          const Text('datebase: active',style: TextStyle(fontSize: 20, color: Colors.green)),
                        ]
                    ),
                  ),

                  Container(
                    margin: EdgeInsets.only(top: 20, bottom: 10),
                    child: Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          const Text('システムはすべて正常に動作しています',style: TextStyle(fontSize: 15)),
                        ]
                    ),
                  ),

                  Container(
                    margin: EdgeInsets.only(top: 50),
                    child: OutlinedButton(
                      child: const Text('お問い合わせはこちら'),
                      style: OutlinedButton.styleFrom(
                        primary: Colors.black87,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10),
                        ),
                        side: const BorderSide(),
                      ),
                      onPressed: () {
                        _opneUrl();
                      },
                    ),
                  ),

                  Container(
                    margin: EdgeInsets.only(top: 50),
                    child: OutlinedButton(
                      child: const Text('お問い合わせはこちら'),
                      style: OutlinedButton.styleFrom(
                        primary: Colors.black87,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10),
                        ),
                        side: const BorderSide(),
                      ),
                      onPressed: () async{
                        bool hasPermissions = await FlutterBackground.hasPermissions;
                      },
                    ),
                  ),

                  Container(
                    alignment: Alignment.bottomCenter,
                    margin: EdgeInsets.only(),
                    child: Text('©2022. Hiroyoshi Muranaka'),
                  ),
                ],
              ),
            )
        )
    );

  }
}
