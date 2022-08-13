import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:pamssms/main.dart';
import 'package:loading_animation_widget/loading_animation_widget.dart';
import 'UI.dart';

class first extends StatefulWidget {
  first({Key? key}) : super(key: key);

  @override
  firstState createState() => firstState();
}

class firstState extends State<first> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'first',
        theme: ThemeData(primarySwatch: Colors.blue),
        home: Scaffold(
            body: Center(
              child: Column(

                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
              Container(
                  margin: EdgeInsets.only(top: 40),
                  child: Text('PAMS',style: TextStyle(fontSize: 35,))
              ),

              Container(
                  margin: EdgeInsets.only(top: 40),
                  child: Image.asset('images/sync-icon.png'),
              ),

              Container(
                margin: EdgeInsets.only(top: 50),
                child:LoadingAnimationWidget.staggeredDotsWave(
                  color: Colors.black,
                  size: 50,
                ),

              ),

              Container(
                  margin: EdgeInsets.only(top: 30),
                  child: Text('読み込み中',style: TextStyle(fontSize: 15,)),
              ),

                ],
              ),
            )
        )
    );
  }
}