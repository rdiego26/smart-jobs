package info.diegoramos.smartjobs.components;

import info.diegoramos.smartjobs.external.IndeedResult;
import info.diegoramos.smartjobs.utils.ThreadPreconditions;
import info.diegoramos.smartjobs2.R;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class IndeedResultAdapter extends BaseAdapter {

	private List<IndeedResult> indeedResults = Collections.emptyList();
	private final Activity activity;
	
	public IndeedResultAdapter(Activity context) {
		this.activity = context;
	}
	
	public void updateIndeedResults(List<IndeedResult> indeedResults) {
		//Make sure your adapter is used only from one thread, the Main thread.
		ThreadPreconditions.checkOnMainThread();
		this.indeedResults = indeedResults;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return indeedResults.size();
	}

	@Override
	public IndeedResult getItem(int position) {
		return indeedResults.get(position);
	}

	/**
	 * getItemId() is often useless, I think this should be the default
	 * implementation in BaseAdapter
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null) {
			convertView = LayoutInflater.from(activity)
				      .inflate(R.layout.indeed_result_list_item, parent, false);
		}
		
		EditText edtJobTitle = (EditText) convertView.findViewById(R.id.txt_job_title_indeed_result);
		IndeedResult indeedResult = getItem(position);
		edtJobTitle.setText(indeedResult.getJobTitle());
		
		return convertView;
	}
	
}