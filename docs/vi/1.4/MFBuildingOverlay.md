# Building Overlay
Lớp MFBuildingOverlay cho phép người dùng thêm vào các đối tượng building 3D theo tile


## 1. MFBuildingOverlay and MFBuildingOverlayOptions

```java

public interface MFBuildingProvider {
    String getTile(int x, int y, int zoom);
}

public class MFBuildingOverlayOptions {

    private MFBuildingProvider buildingProvider;

    public MFBuildingOverlayOptions() {
        buildingProvider = null;
    }

    /**
     * Set the buildingProvider for Building Overlay
     * @param buildingProvider
     * @return the object for which the method was called, with the new buildingProvider set.
     */
    public MFBuildingOverlayOptions buildingProvider(@NonNull MFBuildingProvider buildingProvider) {
        this.buildingProvider = buildingProvider;
        return this;
    }

    MFBuildingProvider getBuildingProvider() {
        return buildingProvider;
    }
}


public class MFBuildingOverlay extends MFLayerOverlay {

    private MFBuildingProvider buildingProvider;

    public MFBuildingOverlay(@NonNull MFBuildingOverlayOptions buildingOverlayOptions,
                             @NonNull LayerOverlayDelegate layerOverlayDelegate) {
        super(layerOverlayDelegate);
        this.buildingProvider = buildingOverlayOptions.getBuildingProvider();
    }

    public String getBuildingUrl(int x, int y, int z) {
        if (buildingProvider != null) {
            return buildingProvider.getTile(x, y, z);
        }
        return null;
    }
}
```

## 2. Tạo Building Overlay

  -  ![MAP4DSDK](../../resource/v1.4/building-overlay.png) 
  
```java
    MFBuildingOverlayOptions buildingOverlayOptions = new MFBuildingOverlayOptions().buildingProvider(new MFBuildingProvider() {
        @Override
        public String getTile(int x, int y, int zoom) {
            return "https://api.map4d.vn/sdk/tile/v2/" + zoom + "/" + x + "/" + y + "?key=98fd21346d83bee24dc734231f7609c9&mode=3d";
        }
    });
    
    MFBuildingOverlay buildingOverlay = map4D.addBuildingOverlay(buildingOverlayOptions);
```


## 3. API Response
Để sử dụng được tính năng Building Overlay trên Map4D Map SDK, bạn cần 1 API nhận 3 thông số của một `Map Tile` là `x`, `y`, `zoom` và dữ liệu trả về kiểu `JSON` theo cấu trúc sau:

```json
{
  "code": "ok",
  "message": "message",
  "result": {
    "objects": [
      {
        "id": "string",
        "name": "string",        
        "location": {
          "lng": 0,
          "lat": 0
        },
        "scale": 0,
        "bearing": 0,
        "elevation": 0,
        "camera": {
          "zoom": 0,
          "bearing": 0,
          "tilt": 0
        },
        "types": [
          "string"
        ],
        "minZoom": 0,
        "maxZoom": 0,
        "startDate": "1569801600000",
        "endDate": "1569901600000",
        "model": {
          "id": "string",
          "type": "Object", //or Polygon
          "objName": "string",
          "objUrl": "string",
          "textureName": "string",
          "textureUrl": "string",
          "color": "string",
          "height": 0,
          "coordinates": [
            {
              "lng": 0,
              "lat": 0
            }
          ]
        }
      }
    ]
  }
}
```

> Gợi ý thiết kế database  
> Cần 2 collection để lưu thông tin các tile map và thông tin đối tượng:  
> ## Tile Collection
> ```json
> {
>   "id": "string",
>   "x": "number",
>   "y": "number",
>   "zoom": "number",
>   "objects": ["objectId"]
> }
> ```  
> ## Object Collection  
> ```json
>{
>        "id": "string",
>        "name": "string",        
>        "location": {
>          "lng": 0,
>          "lat": 0
>        },
>        "scale": 0,
>        "bearing": 0,
>        "elevation": 0,
>        "camera": {
>          "zoom": 0,
>          "bearing": 0,
>          "tilt": 0
>        },
>        "types": [
>          "string"
>        ],
>        "minZoom": 0,
>        "maxZoom": 0,
>        "startDate": "1569801600000",
>        "endDate": "1569901600000",
>        "model": {
>          "id": "string",
>          "type": "Object", //or Polygon
>          "objName": "string",
>          "objUrl": "string",
>          "textureName": "string",
>          "textureUrl": "string",
>          "color": "string",
>          "height": 0,
>          "coordinates": [
>            {
>              "lng": 0,
>              "lat": 0
>            }
>          ]
>        }
>}
> ```  


License
-------

Copyright (C) 2020 IOT Link Ltd. All Rights Reserved.