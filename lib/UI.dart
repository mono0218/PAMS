import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:pamssms/main.dart';

class HomePage extends StatefulWidget {
  HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
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
                          const Text('status: active'),
                          const Text('datebase: active'),
                        ]
                    ),
                  ),

                  Container(
                    margin: EdgeInsets.only(top: 20, bottom: 10),
                    child: Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          const Text('システムはすべて正常に動作しています'),
                        ]
                    ),
                  ),

                  OutlinedButton(
                    child: const Text('お問い合わせはこちら'),
                    style: OutlinedButton.styleFrom(
                      primary: Colors.black,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                      side: const BorderSide(),
                    ),
                    onPressed: () {
                      launchUrl(
                        Uri.parse('https://pams.monodev.cloud'),
                      );
                    },
                  ),
                  OutlinedButton(
                    child: const Text('お問い合わせはこちら'),
                    style: OutlinedButton.styleFrom(
                      primary: Colors.black,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(10),
                      ),
                      side: const BorderSide(),
                    ),
                    onPressed: () {},
                  ),
                ],
              ),
            )
        )
    );
  }
}