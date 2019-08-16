
# iamport_flutter
[ ![alt text](https://img.shields.io/badge/flutter_webview_plugin-v0.3.5-orange.svg?longCache=true&style=flat-square) ](https://pub.dev/packages/flutter_webview_plugin)
[ ![alt text](https://img.shields.io/badge/url_launcher-v5.1.1-yellow.svg?longCache=true&style=flat-square) ](https://pub.dev/packages/url_launcher)
[ ![alt text](https://img.shields.io/badge/uni_links-v0.2.0-green.svg?longCache=true&style=flat-square) ](https://pub.dev/packages/uni_links)

아임포트 플러터 모듈입니다.

## 목차
- [버전정보](CHANGELOG.md)
- 지원정보
- 설치하기
- 설정하기
  - IOS 설정하기
  - [실시간 계좌이체 설정하기](example/manuals/TRANS.md)
- [예제](example/README.md)
- [콜백 함수 설정하기](example/manuals/CALLBACK.md)

## 버전정보
최신버전은 [v0.9.0](https://github.com/iamport/iamport_flutter/tree/master)입니다. 버전 히스토리는 [버전정보](CHANGELOG.md)를 참고하세요.

## 지원정보
아임포트 플러터 모듈은 일반/정기결제 및 휴대폰 본인인증 기능을 지원합니다. 결제 모듈이 지원하는 PG사 및 결제수단에 대한 자세한 내용은 아래 표를 참고해주세요.

| pg           | 이름                 |  결제수단                                                                                                 |  
| ------------ | ------------------- | ------------------------------------------------------------------------------------------------------  | 
| html5_inicis | 웹 표준 이니시스        | 신용카드, 가상계좌, 실시간 계좌이체(안드로이드 - 별도 설정 필요), 휴대폰 소액결제, 삼성페이, KPAY, 문화상품권, 스마트문상, 해피머니 |
| kcp          | NHN KCP             | 신용카드, 가상계좌, 실시간 계좌이체, 휴대폰 소액결제, 삼성페이                                                        |
| kcp_billing  | NHN KCP 정기결제      | 신용카드                                                                                                  |
| uplus        | LG 유플러스           | 신용카드, 가상계좌, 실시간 계좌이체, 휴대폰 소액결제, 문화상품권, 스마트문상, 도서상푼권                                     |
| jtnet        | JTNET               | 신용카드, 가상계좌, 실시간 계좌이체, 휴대폰 소액결제                                                                |
| nice         | 나이스 정보통신         | 신용카드, 가상계좌, 실시간 계좌이체(안드로이드 - 별도 설정 필요), 휴대폰 소액결제                                         |
| kakaopay     | 신 - 카카오페이        | 신용카드                                                                                                  |
| kakao        | 구 - LG CNS 카카오페이 | 신용카드                                                                                                  |
| danal        | 다날 휴대폰 소액결제     | 휴대폰 소액결제                                                                                            |
| danal_tpay   | 다날 일반결제          | 신용카드, 가상계좌, 실시간 계좌이체                                                                             |
| kicc         | 한국정보통신           | 신용카드, 가상계좌, 실시간 계좌이체, 휴대폰 소액결제                                                                |
| paypal       | 페이팔               | 신용카드                                                                                                  |
| mobilians    | 모빌리언스            | 신용카드, 가상계좌(준비중), 실시간 계좌이체(준비중), 휴대폰 소액결제                                                    |
| payco        | 페이코               | 신용카드                                                                                                  |
| settle       | 세틀뱅크             | 가상계좌                                                                                                   |
| naverco      | 네이버 체크아웃        | 신용카드, 가상계좌, 실시간 계좌이체, 휴대폰 소액결제                                                                 |
| naverpay     | 네이버페이            | 신용카드, 가상계좌, 실시간 계좌이체, 휴대폰 소액결제                                                                 |
| smilepay     | 스마일페이            | 신용카드                                                                                                   |

## 설치하기
`pubspec.yaml` 파일에 `iamport_flutter` 모듈을 추가해 귀하의 프로젝트에 아임포트 플러터 모듈을 설치할 수 있습니다.

```
dependencies:
  iamport_flutter: ^0.9.0
```

## 설정하기
### IOS 설정하기
IOS에서 아임포트 결제연동 모듈을 사용하기 위해서는 `info.plist` 파일에 아래 3가지 항목을 설정해주셔야 합니다. `[프로젝트 이름]/ios/Runner.xcworkspace` 파일을 열어 왼쪽 프로젝트 패널 > Runner > info.plist 파일을 클릭합니다.

#### 1. App Scheme 등록
외부 결제 앱(예) 페이코, 신한 판 페이)에서 결제 후 돌아올 때 사용할 URL identifier를 설정해야합니다.

![](assets/images/app-scheme-registry.gif)

1. `URL types` 속성을 추가합니다.
2. item `0`를 확장하여 `URL schemes` 속성을 추가합니다.
3. item `0`에 App URL Scheme 값(EX. example)을 작성합니다.

```html
...
<key>CFBundleURLTypes</key>
<array>
  <dict>
    <key>CFBundleTypeRole</key>
    <string>Editor</string>
    <key>CFBundleURLSchemes</key>
    <array>
      <!-- URL Scheme 값 설정 -->
      <string>example</string>
    </array>
  </dict>
</array>
...
```

#### 2. 외부 앱 리스트 등록
3rd party앱(예) 카드사 앱, 간편결제 앱 등)을 실행할 수 있도록 외부 앱 리스트를 등록해야합니다. 

1. [LSApplicationQueriesSchemes](https://developer.apple.com/library/content/documentation/General/Reference/InfoPlistKeyReference/Articles/LaunchServicesKeys.html#//apple_ref/doc/uid/TP40009250-SW14) 속성을 추가합니다.
2. 외부 앱 URL Scheme 값을 하나씩 추가합니다.

```html
...
<key>LSApplicationQueriesSchemes</key>
<array>
  <string>kakao0123456789abcdefghijklmn</string>
  <string>kakaokompassauth</string>
  <string>storykompassauth</string>
  <string>kakaolink</string>
  <string>kakaotalk</string>
  <string>kakaostory</string>
  <string>storylink</string>
  <string>payco</string>
  <string>kftc-bankpay</string>
  <string>ispmobile</string>
  <string>itms-apps</string>
  <string>hdcardappcardansimclick</string>
  <string>smhyundaiansimclick</string>
  <string>shinhan-sr-ansimclick</string>
  <string>smshinhanansimclick</string>
  <string>kb-acp</string>
  <string>mpocket.online.ansimclick</string>
  <string>ansimclickscard</string>
  <string>ansimclickipcollect</string>
  <string>vguardstart</string>
  <string>samsungpay</string>
  <string>scardcertiapp</string>
  <string>lottesmartpay</string>
  <string>lotteappcard</string>
  <string>cloudpay</string>
  <string>nhappvardansimclick</string>
  <string>nonghyupcardansimclick</string>
  <string>nhallonepayansimclick</string>
  <string>citispay</string>
  <string>citicardappkr</string>
  <string>citimobileapp</string>
  <string>itmss</string>
  <string>lpayapp</string>
  <string>kpay</string>
</array>
...
```

#### 3. App Transport Security 설정

![](assets/images/allow-arbitrary.gif)

1. `App Transport Security Settings` 속성에 [Allow Arbitrary Loads in Web Content](https://developer.apple.com/documentation/bundleresources/information_property_list/nsapptransportsecurity/nsallowsarbitraryloadsinwebcontent) 속성을 추가합니다.
2. 그 값을 `true`로 설정합니다.

```html
...
<key>NSAppTransportSecurity</key>
<dict>
  <!-- Allow Arbitrary Loads in Web Content 속성을 true로 설정 -->
  <key>NSAllowsArbitraryLoadsInWebContent</key>
  <true/>
  <key>NSAllowsArbitraryLoads</key>
  <true/>
</dict>
...
```

### 실시간 계좌이체 설정하기
웹 표준 이니시스와 나이스 정보통신은 뱅크페이 앱을 통해 실시간 계좌이체를 진행합니다. 뱅크페이에서 결제 인증 후 본래의 앱으로 복귀 해 다음단계로 진행을 하려면 별도 설정이 요구됩니다. 자세한 내용은 [실시간 계좌이체 설정하기](example/manuals/TRANS.md)를 참고해주세요.


## 예제
아임포트 플러터 모듈로 아래와 같이 일반/정기결제 및 휴대폰 본인인증 기능을 구현할 수 있습니다. 보다 자세한 내용은 [예제](example/README.md)를 참고하세요.

#### 일반/정기결제 예제
```dart
import 'package:flutter/material.dart';

/* 아임포트 결제 모듈을 불러옵니다. */
import 'package:iamport_flutter/iamport_payment.dart';
/* 아임포트 결제 데이터 모델을 불러옵니다. */
import 'package:iamport_flutter/model/payment_data.dart';

class Payment extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return IamportPayment(
      appBar: new AppBar(
        title: new Text('아임포트 결제'),
      ),
      /* 웹뷰 로딩 컴포넌트 */
      initialChild: Container(
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset('assets/images/iamport-logo.png'),
              Container(
                padding: EdgeInsets.fromLTRB(0.0, 30.0, 0.0, 0.0),
                child: Text('잠시만 기다려주세요...', style: TextStyle(fontSize: 20.0)),
              ),
            ],
          ),
        ),
      ),
      /* [필수입력] 가맹점 식별코드 */
      userCode: 'iamport',
      /* [필수입력] 결제 데이터 */
      data: PaymentData.fromJson({
        pg: 'html5_inicis',                                           // PG사
        pay_method: 'card',                                           // 결제수단
        name: '아임포트 결제데이터 분석',                                   // 주문명
        merchant_uid: 'mid_${DateTime.now().millisecondsSinceEpoch}', // 주문번호
        amount: '39000',                                              // 결제금액
        buyer_name: '홍길동',                                           // 구매자 이름
        buyer_tel: '01012345678',                                     // 구매자 연락처
        buyer_email: 'example@naver.com',                             // 구매자 이메일
        buyer_addr: '서울시 강남구 신사동 661-16',                         // 구매자 주소
        buyer_postcode: '06018',                                      // 구매자 우편번호
        app_scheme: 'example',                                        // 앱 URL scheme
      }),
      /* [필수입력] 콜백 함수 */
      callback: (Map<String, String> result) {
        Navigator.pushReplacementNamed(
          context,
          '/result',
          arguments: result,
        );
      },
    );
  }
}
```


#### 휴대폰 본인인증 예제
```dart
import 'package:flutter/material.dart';

/* 아임포트 휴대폰 본인인증 모듈을 불러옵니다. */
import 'package:iamport_flutter/iamport_certification.dart';
/* 아임포트 휴대폰 본인인증 데이터 모델을 불러옵니다. */
import 'package:iamport_flutter/model/certification_data.dart';

class Certification extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return IamportCertification(
      appBar: new AppBar(
        title: new Text('아임포트 본인인증'),
      ),
      /* 웹뷰 로딩 컴포넌트 */
      initialChild: Container(
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset('assets/images/iamport-logo.png'),
              Container(
                padding: EdgeInsets.fromLTRB(0.0, 30.0, 0.0, 0.0),
                child: Text('잠시만 기다려주세요...', style: TextStyle(fontSize: 20.0)),
              ),
            ],
          ),
        ),
      ),
      /* [필수입력] 가맹점 식별코드 */
      userCode: 'iamport',
      /* [필수입력] 본인인증 데이터 */
      data: CertificationData.fromJson({
        'merchant_uid': 'mid_${DateTime.now().millisecondsSinceEpoch}', // 주문번호
        'company': '아임포트',                                            // 회사명 또는 URL
        'carrier': 'SKT',                                               // 통신사
        'name': '홍길동',                                                 // 이름
        'phone': '01012341234',                                         // 전화번호
      }),
      /* [필수입력] 콜백 함수 */
      callback: (Map<String, String> result) {
        Navigator.pushReplacementNamed(
          context,
          '/result',
          arguments: result,
        );
      },
    );
  }
}
```

## 콜백 함수 설정하기
콜백 함수는 필수입력 필드로, 결제/본인인증 완료 후 라우트 이동을 위해 아래와 같이 로직을 작성할 수 있습니다. 콜백 함수에 대한 자세한 설명은 [콜백 설정하기](example/manuals/CALLBACK.md)를 참고하세요.

```dart
...
callback: (Map<String, String> result) {
  Navigator.pushReplacementNamed(
    context,
    '/result',
    arguments: result,
  );
},
...
```