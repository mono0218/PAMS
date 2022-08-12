import 'package:flutter_local_notifications/flutter_local_notifications.dart';

notify() {
  final flnp = FlutterLocalNotificationsPlugin();
  return flnp.initialize(
    const InitializationSettings(
      android: AndroidInitializationSettings('@mipmap/ic_launcher'),
    ),
  ).then(
    // 通知の表示
        (_) =>
        flnp.show(
          0,
          '(重要)PAMSからのお知らせです',
          '現在不審なsmsメッセージを受信しました。タップして詳細',
          const NotificationDetails(
            android: AndroidNotificationDetails(
              'channel_id',
              'channel_name',
            ),
          ),
        ),
  );
}

safety() {
  final flnp = FlutterLocalNotificationsPlugin();
  return flnp.initialize(
    const InitializationSettings(
      android: AndroidInitializationSettings('@mipmap/ic_launcher'),
    ),
  ).then(
    // 通知の表示
        (_) =>
        flnp.show(
          0,
          'PAMSからのお知らせ',
          '現在受信したsmsは安全です',
          const NotificationDetails(
            android: AndroidNotificationDetails(
              'channel_id',
              'channel_name',
            ),
          ),
        ),
  );
}