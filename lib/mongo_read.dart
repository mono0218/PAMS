import 'package:mongo_dart/mongo_dart.dart';
import 'package:flutter/material.dart';

class Level4db {
  static var db, userCollection;

  static connect() async {
    db = await Db.create("mongodb+srv://user:user@pams-data-db.y6htnvb.mongodb.net/level4?retryWrites=true&w=majority");
    await db.open();
    userCollection = db.collection("date");
  }

  static Future<List<Map<String, dynamic>>> getDocuments() async {
    try {
      final users = await userCollection.find().toList();
      debugPrint(users + "a");

      return users;
    } catch (e) {
      debugPrint("エラーが発生しました");
      return Future.value();
    }
  }
}

class Level3db {
  static var db, userCollection;

  static connect() async {
    db = await Db.create("mongodb+srv://mono:Kamokamomonokamo@pams-data-db.y6htnvb.mongodb.net/level4?retryWrites=true&w=majority");
    await db.open();
    userCollection = db.collection("date");
  }

  static Future<List<Map<String, dynamic>>> getDocuments() async {
    try {
      final users = await userCollection.find().toList();
      debugPrint(users);

      return users;
    } catch (e) {
      debugPrint("エラーが発生しました");
      return Future.value();
    }
  }
}

class Level2db {
  static var db, userCollection;

  static connect() async {
    db = await Db.create("");
    await db.open();
    userCollection = db.collection("USER_COLLECTION");
  }

  static Future<List<Map<String, dynamic>>> getDocuments() async {
    try {
      final users = await userCollection.find().toList();
      debugPrint(users);

      return users;
    } catch (e) {
      debugPrint("エラーが発生しました");
      return Future.value();
    }
  }
}

class Level1db {
  static var db, userCollection;

  static connect() async {
    db = await Db.create("");
    await db.open();
    userCollection = db.collection("USER_COLLECTION");
  }

  static Future<List<Map<String, dynamic>>> getDocuments() async {
    try {
      final users = await userCollection.find().toList();
      debugPrint(users);

      return users;
    } catch (e) {
      debugPrint("エラーが発生しました");
      return Future.value();
    }
  }
}