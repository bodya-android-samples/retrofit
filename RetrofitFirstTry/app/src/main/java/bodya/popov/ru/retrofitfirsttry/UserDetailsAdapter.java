package bodya.popov.ru.retrofitfirsttry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bodya.popov.ru.retrofitfirsttry.bean.UserBean;

/**
 * @author Popov Bogdan
 */

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.ViewHolder> {

    private List<UserBean> mUserBeanList;

    public UserDetailsAdapter(List<UserBean> mUserBeanList) {
        this.mUserBeanList = mUserBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserBean userBean = mUserBeanList.get(position);
        holder.mGistsUrlTextView.setText(userBean.getLogin());
        holder.mLoginTextView.setText(userBean.getGistsUrl());
    }

    @Override
    public int getItemCount() {
        if (mUserBeanList != null) {
            return mUserBeanList.size();
        } else {
            return 0;
        }
    }

    public void setData(List<UserBean> userBeanList) {
        mUserBeanList.clear();
        if (userBeanList != null) {
            mUserBeanList.addAll(userBeanList);
        }
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mLoginTextView;
        private TextView mGistsUrlTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mLoginTextView = itemView.findViewById(R.id.login_text_view);
            mGistsUrlTextView = itemView.findViewById(R.id.gists_url_text_view);
        }
    }
}
