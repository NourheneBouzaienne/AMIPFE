package com.example.microservicepfe.services;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;

public class ExpoPushNotificationService {

    public static void sendPushNotification(String fcmToken, String title, String body) {
        try (Context context = Context.create("js")) {
            String script = "const { Expo } = require('expo-server-sdk');\n" +
                    "const expo = new Expo();\n" +
                    "const message = {\n" +
                    "  to: '" + fcmToken + "',\n" +
                    "  sound: 'default',\n" +
                    "  title: '" + title + "',\n" +
                    "  body: '" + body + "',\n" +
                    "};\n" +
                    "expo.sendPushNotificationsAsync([message]);\n";

            context.eval("js", script);
        } catch (PolyglotException e) {
            e.printStackTrace();
        }
    }
}
