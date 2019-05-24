/**
 * 
 * @author Md Faruk Hossain
 * Base class for all Activity, all Activity should extends this class.
 *
 */
package com.faruk.retrofitshimmer.appfrm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity implements NotifyObserver{
	//
	protected BaseActivity mInstance;
	//
	protected FactoryAdapter mFactoryAdapter = null;

	public BaseActivity() {
		this.mInstance = this;
	}
	
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFactoryAdapter = FactoryAdapter.getInstance(this.getApplicationContext());
        // DONOT change this order may creates the problems
        createAdapter();
        createView(savedInstanceState);
        loadData();
    }

    /** Views should be created here, set the listeners, observer etc.. */
    protected abstract void createView(Bundle savedInstanceState);
    /** Actions should be created here */
    protected abstract void createAdapter();
	/** Concrete class should know how to load the data by adapters */
	protected abstract void loadData();
    /** Get the data from response, no need to create runOnUiThread its all ready maintained */
    public abstract void doUpdateRequest(ResponseObject response);
    
	@Override
	public synchronized void  update(ResponseObject response) {
		final ResponseObject tmpResponse = response;
		
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mInstance.doUpdateRequest(tmpResponse);
			}
		});
	}
	
	 /**
     * Gets the class name
     * @param cls
     * @return
     *      Class name
     */
    public static String getClassName(Class<?> cls) {
        String fullName = cls.getName();
        int pos = fullName.lastIndexOf ('.') + 1;
        if (pos > 0) {
            fullName = fullName.substring(pos);
        }
        return fullName;
    }
    
    /**
     * Gets view from resource
     * @param rViewId
     * @return
     */
    protected View getView(int rViewId) {
        return findViewById(rViewId);
    }
    
    /**
     * Hide the requested view.
     * @param rViewId
     */
    protected void hideView(int rViewId) {
    	View view = findViewById(rViewId);
		if(view != null) {
			view.setVisibility(View.GONE);
		}
    }
    
    /**
     * Show the requested view.
     * @param rViewId
     */
    protected void showView(int rViewId) {
    	View view = findViewById(rViewId);
		if(view != null) {
			view.setVisibility(View.VISIBLE);
		}
    }
    
    /**
     * Sets text from resource into view
     * @param rViewId
     * @param rTextId
     */
    protected void setText(int rViewId, int rTextId) {
        View view = getView(rViewId);
        if (view instanceof TextView) {
            ((TextView)view).setText(rTextId);
        }
    }

    /**
     * Sets text from resource into view but from different thread/Cross Thread
     * @param rIdText
     *      Resource id of text
     */
    protected void setTextCT(final int rViewId, final int rIdText) {
        runOnUiThread(new Runnable() {
            public void run() {
               mInstance.setText(rViewId, rIdText);
            }
        });
    }
    /**
     * Sets text into view
     * @param rViewId
     * @param text
     */
    protected void setText(int rViewId, String text) {
        View view = getView(rViewId);
        if (view instanceof TextView) {
            ((TextView)view).setText(text);
        }
    }
    
    /**
     * Get EditText text value.
     * @param rViewId
     * @return
     */
    protected String getTextEditText(int rViewId) {
    	View view = getView(rViewId);
    	 
    	if(view instanceof EditText) {
    		return ((EditText)findViewById(rViewId)).getText().toString().trim();
    	}
    	
    	return "";
    }

    /**
     * Sets background from resource
     * @param rViewId
     * @param rBackgroundId
     */
    protected void setBackGround(int rViewId, int rBackgroundId) {
        View view = getView(rViewId);
        if (view != null) {
            view.setBackgroundResource(rBackgroundId);
        }
    }
    
    @SuppressWarnings("rawtypes")
	public void startActivity(Class name) {
      
    	Intent intent = new Intent(this, name);    
        startActivity(intent);
    }

    public void startActivityForResult(Class name, int ResultCode) {
        Intent intent = new Intent(this, name);
        startActivityForResult(intent, ResultCode);
    }
}
