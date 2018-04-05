package com.example.shangui.shangui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;

import butterknife.BindView;
import overlayutil.WalkingRouteOverlay;

public class BoxLocationMapActivity extends BaseActivity implements OnGetRoutePlanResultListener /*TextWatcher*/{

    @BindView(R.id.box_location_map_toolbar)
    Toolbar boxLocationMapToolbar;
//    @BindView(R.id.box_location_map_search)
//    EditText boxLocationMapSearch;
    @BindView(R.id.box_location_map)
    MapView boxLocationMap;
    @BindView(R.id.box_location_tip)
    TextView boxLoactionTip;

    private BaiduMap baiduMap;//百度地图控制类
    private LocationClient locationClient;//发起定位
    private boolean isFirst = true;//是否是第一次定位，移到自己的位置
    private RoutePlanSearch planSearch;

    private static final int BAIDU_READ_PHONE_STATE = 100;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_box_loaction_map;
    }

    @Override
    protected void initView() {
        setSupportActionBar(boxLocationMapToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
//        boxLocationMapSearch.addTextChangedListener(this);
    }

    @Override
    protected void initData() {
        //sdk23及以上需要申请权限
        if (Build.VERSION.PREVIEW_SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission();//检查权限是否通过
        } else {
            init();
        }
    }

    //初始化数据
    private void init() {
        baiduMap = boxLocationMap.getMap();
        baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true,BitmapDescriptorFactory.fromResource(R.drawable.myself)));
        baiduMap.setMyLocationEnabled(true);
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(listener);
        initLocationClient();
        locationClient.start();//开始定位
        planSearch = RoutePlanSearch.newInstance();
        planSearch.setOnGetRoutePlanResultListener(this);
        baiduMap.getUiSettings().setCompassEnabled(true);
    }

    //初始化LocationClient
    private void initLocationClient() {
        LocationClientOption mOption = new LocationClientOption();
        //默认高精度，设置定位模式
        //LocationMode.Hight_Accuracy 高精度定位模式：这种定位模式下，会同时使用
        //LocationMode.Battery_Saving 低功耗定位模式：这种定位模式下，不会使用GPS，只会使用网络定位。
        //LocationMode.Device_Sensors 仅用设备定位模式：这种定位模式下，
        mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //默认是true，设置是否使用gps定位
        //如果设置为false，即使mOption.setLocationMode(LocationMode.Hight_Accuracy)也不会gps定位
        mOption.setOpenGps(true);
        // 默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        //目前国内主要有以下三种坐标系：
        // wgs84：目前广泛使用的GPS全球卫星定位系统使用的标准坐标系；
        // gcj02：经过国测局加密的坐标；
        // bd09：为百度坐标系，其中bd09ll表示百度经纬度坐标，bd09mc表示百度墨卡托米制坐标；
        //在国内获得的坐标系类型可以是：国测局坐标、百度墨卡托坐标 和 百度经纬度坐标。
        //在海外地区，只能获得WGS84坐标。请在使用过程中注意选择坐标。
        mOption.setCoorType("bd09ll");
        // 默认0，即仅定位一次；设置间隔需大于等于1000ms，表示周期性定位
        // 如果不在AndroidManifest.xml声明百度指定的Service，周期性请求无法正常工作
        // 这里需要注意的是：如果是室外gps定位，不用访问服务器，设置的间隔是3秒，那么就是3秒返回一次位置
        //如果是WiFi基站定位，需要访问服务器，这个时候每次网络请求时间差异很大，设置的间隔是3秒，
        //只能大概保证3秒左右会返回就一次位置，有时某次定位可能会5秒才返回
        mOption.setScanSpan(3000);
        //默认false，设置是否需要地址信息
        // 返回省、市、区、街道等地址信息，这个api用处很大，
        //很多新闻类app会根据定位返回的市区信息推送用户所在市的新闻
        mOption.setIsNeedAddress(true);
        //默认false，设置是否需要位置语义化结果
        // 可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        mOption.setIsNeedLocationDescribe(true);
        //默认false,设置是否需要设备方向传感器的方向结果
        // 一般在室外gps定位时，返回的位置信息是带有方向的，但是有时候gps返回的位置也不带方向，
        //这个时候可以获取设备方向传感器的方向
        //wifi基站定位的位置信息是不带方向的，如果需要可以获取设备方向传感器的方向
        mOption.setNeedDeviceDirect(true);
        // 默认false，设置是否当gps有效时按照设定的周期频率输出GPS结果
        //室外gps有效时，周期性1秒返回一次位置信息，其实就是设置了
        //locationManager.requestLocationUpdates中的minTime参数为1000ms，1秒回调一个gps位置
        //如果设置了mOption.setScanSpan(3000)，那minTime就是3000ms了，3秒回调一个gps位置
        mOption.setLocationNotify(false);
        //默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        //如果你已经拿到了你要的位置信息，不需要再定位了，不杀死留着干嘛
        mOption.setIgnoreKillProcess(true);
         //默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        //POI就是获取到的位置附近的一些商场、饭店、银行等信息
        mOption.setIsNeedLocationPoiList(true);
        //默认false，设置是否收集CRASH信息，默认收集
        mOption.SetIgnoreCacheException(false);
        //默认false，设置定位时是否需要海拔高度信息，默认不需要，除基础定位版本都可用
        mOption.setIsNeedAltitude(false);
        locationClient.setLocOption(mOption);//设置定位参数
    }

    double latitude = 39.863175;
    double longitude = 116.400244;

    //监听位置
    private BDAbstractLocationListener listener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation==null){
                return;
            }
            if(!isFirst){
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(bdLocation.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .latitude(latitude)
                        .longitude(longitude+=0.0001)
                        .direction(0).build();
                // 设置定位数据
                baiduMap.setMyLocationData(locData);
                LatLng ll = new LatLng(latitude, longitude);
                LatLng latLng = new LatLng(39.862175, 116.403244);
//                baiduMap.setMapType();
                planSearch.walkingSearch((new WalkingRoutePlanOption()).from(PlanNode.withLocation(ll)).to(PlanNode.withLocation(latLng)));
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(39.863175)
                    .longitude(116.400244).build();
            // 设置定位数据
            baiduMap.setMyLocationData(locData);
            LatLng ll = new LatLng(39.863175, 116.400244);
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            // 放大
//            update = MapStatusUpdateFactory.zoomBy(5f);
            baiduMap.setMapStatus(MapStatusUpdateFactory.zoomBy(30));
            // 移动到某经纬度
            baiduMap.animateMapStatus(update);
            //添加箱子位置覆盖物
            LatLng latLng = new LatLng(39.862175, 116.403244);
            OverlayOptions overlayOptions = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.position));
            baiduMap.addOverlay(overlayOptions);
            baiduMap.setOnMarkerClickListener(markerClickListener);
            double m = DistanceUtil.getDistance(ll,latLng);
            Toast.makeText(getApplicationContext(),"距离目的地"+m+"米",Toast.LENGTH_SHORT).show();
            planSearch.walkingSearch((new WalkingRoutePlanOption()).from(PlanNode.withLocation(ll)).to(PlanNode.withLocation(latLng)));
            isFirst = false;
        }
    };

    //点击覆盖物调用
    private BaiduMap.OnMarkerClickListener markerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            Log.e("mark",marker.getPosition().latitude+"+++");
            return false;
        }
    };

    //检查权限
    private void requestPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(BoxLocationMapActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        }else{
            init();
        }
    }

    //申请权限返回的结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    init();
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取位置权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.registerLocationListener(listener);
        locationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        boxLocationMap.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        boxLocationMap.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boxLocationMap.onResume();
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        if(walkingRouteResult==null || walkingRouteResult.error== SearchResult.ERRORNO.RESULT_NOT_FOUND){
            Log.e("error",(walkingRouteResult==null)+"++++++++");
            return;
        }
        if(walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR){
            baiduMap.clear();
            WalkingRouteOverlay routeResult = new WalkingRouteOverlay(baiduMap);
            baiduMap.setOnMarkerClickListener(routeResult);
            routeResult.setData(walkingRouteResult.getRouteLines().get(0));
            routeResult.addToMap();
            if(isFirst){
                routeResult.zoomToSpan();
            }
            boxLoactionTip.setText(walkingRouteResult.getRouteLines().get(0).getAllStep().get(0).getInstructions());
//            Log.e("error","+++"+walkingRouteResult.getRouteLines().get(0).getAllStep().get(0).getInstructions());
        }
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    /*//监听输入框，输入前执行该方法
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        Log.e("beforeTextChanged",charSequence.toString());
    }

    //监听输入框，输入时执行该方法
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!charSequence.toString().equals("")){
            Log.e("onTextChanged",charSequence.toString());
        }
    }

    //监听输入框，输入后执行该方法
    @Override
    public void afterTextChanged(Editable editable) {
//        Log.e("afterTextChanged",editable.toString());
    }*/

}
