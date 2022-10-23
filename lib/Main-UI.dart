import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:pamssms/main.dart';
import 'package:pamssms/UI-component.dart';


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

  Widget build(BuildContext context) {
    var _screenSize = MediaQuery.of(context).size;
    return MaterialApp(
        title: 'Home',
        home: Scaffold(

            body: Center(

                child: Column(


                  children: <Widget>[

                    Futter(),
                    Main(),
                    Spacer(),
                    Down(),
                  ]
                )
            )
        )
    );
  }
}