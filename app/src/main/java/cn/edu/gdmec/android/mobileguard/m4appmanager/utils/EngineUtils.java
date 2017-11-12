package cn.edu.gdmec.android.mobileguard.m4appmanager.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import cn.edu.gdmec.android.mobileguard.m4appmanager.entity.AppInfo;

/**
 * Created by Hwangzbin on 2017/11/12.
 */

public class EngineUtils {
    public static void shareApplication(Context context, AppInfo appInfo){
        Intent intent=new Intent("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,
                "推荐您使用一款软件，名称叫："+appInfo.appName
                    +"下载路径：https://play.google.com/store/apps/details?id="
                    +appInfo.packageName);
        context.startActivity(intent);
    }
    public static void startApplication(Context context,AppInfo appInfo){
        PackageManager pm=context.getPackageManager();
        Intent intent=pm.getLaunchIntentForPackage(appInfo.packageName);
        if (intent!=null){
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"该应用没有启动界面",Toast.LENGTH_LONG).show();
        }
    }
    public static void SettingAppDetail(Context context,AppInfo appInfo){
        Intent intent=new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:"+appInfo.packageName));
        context.startActivity(intent);
    }
    public static void uninstallApplication(Context context,AppInfo appInfo){
        if (appInfo.isUserApp){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:"+appInfo.packageName));
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"系统应用无法卸载",Toast.LENGTH_LONG).show();
        }
    }
    public static void aboutApp(Context context,AppInfo appInfo){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(appInfo.appName);
        builder.setMessage("Version："+appInfo.mVersion+
                "\nInstall time："+appInfo.InstallTime+
                "\nCertificate issuer："+appInfo.certificate+
                "\n\nPermissions："+appInfo.permission);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
