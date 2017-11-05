package cn.edu.gdmec.android.mobileguard;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.publicsuffix.PublicSuffixPatterns;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Random;

import cn.edu.gdmec.android.mobileguard.m3communicationguard.db.dao.BlackNumberDao;
import cn.edu.gdmec.android.mobileguard.m3communicationguard.entity.BlackContactInfo;

/**
 * Created by admin on 2017/10/31.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BlackNumberDaoTest {
    private Context context;
    private BlackNumberDao dao;
    @Before
    public void setUp(){
        context = InstrumentationRegistry.getTargetContext();
        dao = new BlackNumberDao(context);
    }
    @Test
    public void t1Add() throws Exception{
        Random random = new Random(8979);
        for (long i = 1; i<30; i++){
            BlackContactInfo info = new BlackContactInfo();
            info.phoneNumber = 13500000000l + i +"";
            info.contactName = "zhangsan" + i;
            info.mode = random.nextInt(3) + 1;
            dao.add(info);
        }
    }
    @Test
    public void t2Delete() throws Exception{
        BlackNumberDao dao = new BlackNumberDao(context);
        BlackContactInfo info = new BlackContactInfo();
        for (long i = 1; i<5; i++){
            info.phoneNumber=13500000000l + i +"";
            dao.detele(info);
        }
    }
    @Test
    public void t5GetTotalNumber() throws Exception {
        BlackNumberDao dao = new BlackNumberDao(context);
        int total = dao.getTotalNumber();
        Log.i("TestBlackNumberDao","数据总条目: "+total);
    }
    @Test
    public void t6IsNumberExist() throws Exception{
        BlackNumberDao dao = new BlackNumberDao(context);
        boolean isExist = dao.IsNumberExist(13500000008l +"");
        if(isExist){
            Log.i("TestBlackNumberDao","该号码在数据库中");
        }else {
            Log.i("TestBlackNumberDao","该号码不在数据库中");
        }
    }
}
