package cn.edu.gdmec.android.mobileguard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import cn.edu.gdmec.android.mobileguard.m1home.HomeActivity;
import cn.edu.gdmec.android.mobileguard.m1home.utils.MyUtils;
import cn.edu.gdmec.android.mobileguard.m1home.utils.VersionUpdateUtils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import cn.edu.gdmec.android.mobileguard.m1home.HomeActivity;
import cn.edu.gdmec.android.mobileguard.m1home.utils.MyUtils;


public class SplashActivity extends AppCompatActivity {
    private TextView mTvVersion;
    private String mVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mVersion = MyUtils.getVersion(getApplicationContext());
        mTvVersion = (TextView) findViewById(R.id.tv_splash_version);
        mTvVersion.setText("版本号:"+mVersion);
        VersionUpdateUtils.DownloadCallback downloadCallback = new VersionUpdateUtils.DownloadCallback(){
            @Override
            public void afterDownload(String filename){
                MyUtils.installApk(SplashActivity.this,filename);
            }
        };
        final VersionUpdateUtils versionUpdateUtils= new VersionUpdateUtils(mVersion,SplashActivity.this,downloadCallback,HomeActivity.class);
        new Thread(){
            @Override
            public void run(){
                super.run();
                versionUpdateUtils.getCloudVersion("http://android2017.duapp.com/updateinfo.html");
            }
        }.start();
//        startActivity(new Intent(this, HomeActivity.class));
//        finish();
    }
}