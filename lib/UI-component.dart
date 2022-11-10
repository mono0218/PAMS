import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';


class Futter extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    var _screenSize = MediaQuery.of(context).size;
    return Column(
        children: <Widget>[
          Container(
            child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Container(
                    width: _screenSize.width * 0.1,
                    child: Column(
                        children: <Widget>[
                          Icon(
                            Icons.reorder,
                            color: Colors.blue,
                            size: 35.0,
                            semanticLabel: 'Text to announce in accessibility modes',
                          ),
                        ]
                    ),
                  ),

                  Container(
                    width: _screenSize.width * 0.65,
                    height: _screenSize.height * 0.2,
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          Text('PAMS', style: TextStyle(fontSize: 30, color: Colors.blue)),
                        ]
                    ),
                  ),

                  Container(
                    width: _screenSize.width * 0.1,
                    child: Column(
                        children: <Widget>[
                          Icon(
                            Icons.manage_accounts,
                            color: Colors.blue,
                            size: 35.0,
                            semanticLabel: 'Text to announce in accessibility modes',
                          ),
                        ]
                    ),
                  ),
                ]
            ),
          ),
        ]
    );
  }
}

class Main extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var _screenSize = MediaQuery.of(context).size;
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[

        Container(
          height: _screenSize.height * 0.3,
          child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                Image.asset('images/sync-icon.png'),
              ]
          ),
        ),



        Container(
          height: _screenSize.height * 0.1,
          child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                const Text('status: active', style: TextStyle(fontSize: 20, color: Colors.green)),
                const Text('database: active', style: TextStyle(fontSize: 20, color: Colors.green)),
              ]
          ),
        ),

        Container(
          height: _screenSize.height * 0.1,
          child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: <Widget>[
                const Text('システムはすべて正常に動作しています',style: TextStyle(fontSize: 15)),
              ]
          ),
        ),

        Container(
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
                launchUrl(
                  Uri.parse('https://pams.monodev.cloud'),
                );
              },
            ),
        ),
      ],
    );
  }
}


class Down extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var _screenSize = MediaQuery.of(context).size;
    return Column(
      crossAxisAlignment: CrossAxisAlignment.end,
      children: <Widget>[
        Container(
          height: _screenSize.height * 0.09,
          decoration: BoxDecoration(color: Colors.blue),
          child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
                  Icon(
                    Icons.feedback,
                  ),
                  Icon(
                    Icons.done_outline,
                  ),
            ]
          ),
        ),
      ],
    );
  }
}