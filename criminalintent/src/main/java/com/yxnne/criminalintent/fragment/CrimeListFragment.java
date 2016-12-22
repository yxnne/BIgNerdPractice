package com.yxnne.criminalintent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.yxnne.criminalintent.CrimeLab;
import com.yxnne.criminalintent.R;
import com.yxnne.criminalintent.activity.CrimePagerActivity;
import com.yxnne.criminalintent.entity.Crime;

import java.util.List;

/**
 * 记录列表
 * Created by yxnne on 2016/12/20.
 */

public class CrimeListFragment extends Fragment{
    private static final String SAVED_SUBTITTLE_VISIBLE = "subtittle";
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private boolean mSubTittleVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通知fragmentmanager 这里托管了 menu
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = (RecyclerView)
                view.findViewById(R.id.crime_recycler_view);

        mCrimeRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        if(savedInstanceState != null){
            mSubTittleVisible = savedInstanceState.getBoolean(SAVED_SUBTITTLE_VISIBLE);
        }
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState .putBoolean(SAVED_SUBTITTLE_VISIBLE,mSubTittleVisible);
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(mAdapter == null){
            mAdapter = new  CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtittle();

    }


    private class CrimeHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

        private Crime mCrime;

        private TextView mTittleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTittleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_tittle_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_crime_solved_check_box);

        }

        public void bindCrime(Crime crime){
            mCrime = crime;
            mTittleTextView.setText(mCrime.getTittle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
            Intent intent =
                    CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            Toast.makeText(getActivity(),mCrime.getId().toString(),Toast.LENGTH_SHORT)
                    .show();
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends  RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }
        public void setCrimes(List<Crime> crimes){
            mCrimes = crimes;
        }
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_crime,parent,false);

            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
                Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);

        MenuItem subtittleItem = menu.findItem(R.id.menu_item_show_subtittle);
        if(mSubTittleVisible){
            subtittleItem.setTitle(R.string.hide_subtittle);
        }else{
            subtittleItem.setTitle(R.string.show_subtittle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.meue_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimePagerActivity
                        .newIntent(getActivity(),crime.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtittle:
                mSubTittleVisible = !mSubTittleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtittle();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }
    private void updateSubtittle(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        int crimeCount = crimeLab.getCrimes().size();
        //格式化字符串
        String subTittle = getString(R.string.subtittle_format,crimeCount);
        if(!mSubTittleVisible){
            subTittle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subTittle);
    }
}
