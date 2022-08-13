import 'dart:async';
import 'package:flutter/material.dart';
import 'package:notifications/notifications.dart';
import 'package:flutter_background/flutter_background.dart';

import 'first.dart';

void kenti () {
  Notifications? _notifications;
  StreamSubscription<NotificationEvent>? _subscription;
  List<NotificationEvent> _log = [];
  bool started = false;
}