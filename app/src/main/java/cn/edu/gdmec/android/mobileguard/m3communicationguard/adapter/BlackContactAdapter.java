package cn.edu.gdmec.android.mobileguard.m3communicationguard.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import cn.edu.gdmec.android.mobileguard.R;
import cn.edu.gdmec.android.mobileguard.m3communicationguard.db.dao.BlackNumberDao;
import cn.edu.gdmec.android.mobileguard.m3communicationguard.entity.BlackContactInfo;

/**
 * Created by admin on 2017/10/31.
 */

public class BlackContactAdapter extends BaseAdapter {
    private List<BlackContactInfo> contactInfos;
    private Context context;
    private BlackNumberDao dao;
    private BlackConactCallBack callBack;

    class ViewHolder{
        TextView mNameTv;
        TextView mModeTv;
        TextView mPhoneTV;
        TextView mTypeTV;
        View mContactImgv;
        View mDeleteView;
    }
    public interface BlackConactCallBack{
        void DataSizeChanged();
    }
    public void setCallBack(BlackConactCallBack callBack){
        this.callBack = callBack;
    }
    public BlackContactAdapter(List<BlackContactInfo> systemContacts,
                               Context context){
        super();
        this.contactInfos = systemContacts;
        this.context = context;
        dao = new BlackNumberDao(context);
    }
    @Override
    public int getCount(){
        return contactInfos.size();
    }
    @Override
    public Object getItem(int i ){
        return contactInfos.get(i);
    }
    @Override
    public long getItemId(int i){
        return i;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup){
        ViewHolder holder = null;
        if (view == null){
            view = View.inflate(context,
                    R.layout.item_list_blackcontact,null);
            holder = new ViewHolder();
            holder.mNameTv = (TextView) view
                    .findViewById(R.id.tv_black_name);
            holder.mModeTv = (TextView) view
                    .findViewById(R.id.tv_black_mode);
            holder.mPhoneTV=(TextView) view.findViewById(R.id.tv_black_phone);
            holder.mTypeTV=(TextView) view.findViewById(R.id.tv_black_type);
            holder.mContactImgv = view
                    .findViewById(R.id.view_black_icon);
            holder.mDeleteView = view
                    .findViewById(R.id.view_black_delete);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mNameTv.setText(contactInfos.get(i).contactName);
        holder.mPhoneTV.setText(contactInfos.get(i).phoneNumber);
        holder.mTypeTV.setText(contactInfos.get(i).preventType);
        holder.mModeTv.setText(contactInfos.get(i).getModeString(contactInfos.get(i).mode));
        holder.mNameTv.setTextColor(context.getResources().getColor(R.color.bright_purple));
        holder.mTypeTV.setTextColor(context.getResources().getColor(R.color.bright_purple));
        holder.mPhoneTV.setTextColor(context.getResources().getColor(R.color.bright_purple));
        holder.mContactImgv.setBackgroundResource(R.drawable.brightpurple_contact_icon);
        holder.mDeleteView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view1){
                boolean datele = dao.detele(contactInfos.get(i));
                if (datele){
                    contactInfos.remove(contactInfos.get(i));
                    BlackContactAdapter.this.notifyDataSetChanged();
                    if(dao.getTotalNumber() == 0){
                        callBack.DataSizeChanged();
                    }else{
                        Toast.makeText(context,"删除失败!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;

    }

}
