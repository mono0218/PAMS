

import 'package:flutter/material.dart';
import 'UI.dart';
Future<void> yomikomi() async {
  await Future.delayed(Duration(seconds: 3));
  MaterialPageRoute(builder: (context) => HomePage());
}