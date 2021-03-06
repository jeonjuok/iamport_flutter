package kr.iamport.iamport_flutter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;

import java.net.URISyntaxException;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** IamportFlutterPlugin */
public class IamportFlutterPlugin implements MethodCallHandler {
  private final static String BANKPAY = "kftc-bankpay";
  private final static String ISP = "ispmobile";
  private final static String PACKAGE_ISP = "kvp.jjy.MispAndroid320";
  private final static String PACKAGE_BANKPAY = "com.kftc.bankpay.android";

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "iamport_flutter");
    channel.setMethodCallHandler(new IamportFlutterPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    switch (call.method) {
      case "getPlatformVersion": {
        result.success("Android " + android.os.Build.VERSION.RELEASE);
        break;
      }
      case "getAppUrl": {
        try {
          String url = call.argument("url");
          Log.i("url", url);
          Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
          result.success(intent.getDataString());
        } catch (URISyntaxException e) {
          result.notImplemented();
        } catch (ActivityNotFoundException e) {
          result.notImplemented();
        }
        break;
      }
      case "getMarketUrl": {
        try {
          String url = call.argument("url");
          Log.i("url", url);
          Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
          String scheme = intent.getScheme();
          if (ISP.equalsIgnoreCase(scheme)) {
            result.success("market://details?id=" + PACKAGE_ISP);
          } else if (BANKPAY.equalsIgnoreCase(scheme)) {
            result.success("market://details?id=" + PACKAGE_BANKPAY);
          } else {
            String packageName = intent.getPackage();
            if (packageName != null) {
              result.success("market://details?id=" + packageName);
            }
          }
          result.notImplemented();
        } catch (URISyntaxException e) {
          result.notImplemented();
        } catch (ActivityNotFoundException e) {
          result.notImplemented();
        }
        break;
      }
      default: {
        result.notImplemented();
        break;
      }
    }
  }
}
