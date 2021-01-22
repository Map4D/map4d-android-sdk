# POI
Lớp MFPOI cho phép người dùng vẽ một POI lên map.


## 1. MFPOI

```java
public final class MFPOIOptions {

    private MFLocationCoordinate position;

    private String title;

    private String subtitle;

    private String type;

    @ColorInt
    private int titleColor;

    private MFBitmapDescriptor icon;

    private View iconView;

    private float zIndex;

    private Object userData;

    private boolean visible;

    private boolean touchable;
}

public final class MFPOI extends Annotation {

    private static final float Z_INDEX = 1.0f;

    private String placeId;

    private MFLocationCoordinate position;

    private String title;

    private String subtitle;

    private String type;

    @ColorInt
    private int titleColor;

    private MFBitmapDescriptor icon;

    private View iconView;

    private boolean visible;

    private boolean touchable;
}
```

- Properties:
  + position: vị trí của POI được vẽ trên bản đồ
  + title: title của POI, được hiển thị bên cạnh POI icon
  + titleColor: màu của title
  + type: kểu của POI, dùng để quy định icon (bank, hospital, cafe, ...) 
  + iconView: POI icon
  + icon: POI icon
  + userInteractionEnabled: cho phép POI có bị tác động bởi người dùng hay không, khi POI được set userInteractionEnabled là false thì sự kiện click đối với POI đó không hoạt động

## 2. Tạo POI

  -  ![MAP4DSDK](../../resource/v1.4/poi.jpg) 
  
```java
  MFPOIOptions userPOIOptions = new MFPOIOptions();
  userPOIOptions.position(new MFLocationCoordinate(16.071876, 108.223994)).title("Test User POI").subtitle("Da Nang");
  MFPOI poi = map4D.addPOI(userPOIOptions);
  poi.setTitleColor(getResources().getColor(R.color.colorAccent));
```
 - **Chú ý**:
 - Người dùng có thể set icon cho POI bằng các cách sau (theo thứ tự ưu tiên):
   - ***Tuỳ biến lại marker bằng cách dùng hàm setIconView***
   - ***Sử dụng 1 hình ảnh làm icon dùng hàm setIcon***
   - ***Set type cho POI***

## 3. Sự kiện click POI

  - Phát sinh khi người dùng click vào POI
    ```java
     map4D.setOnPOIClickListener(new Map4D.OnPOIClickListener() {
         @Override
         public void onPOIClick(MFPOI poi) {
             Log.d(Tag, "Clicked on POI : " + poi.getTitle());
         }
     });
    ```


License
-------

Copyright (C) 2020 IOT Link Ltd. All Rights Reserved.