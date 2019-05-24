/**
 *
 * @author Md Faruk Hossain
 * Base class for all adapters as well as models, all models/adapters should extends this class.
 *
 */
package com.faruk.retrofitshimmer.appfrm;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public abstract  class AbsRecycleViewBaseAdapter extends RecyclerView.Adapter<ViewHolder> {
	// Instance of this class.
	protected AbsRecycleViewBaseAdapter mInstance = null;
	// Unique name of the adapter/model
	protected String mName = "AbsBaseAdapter";
	protected Context mContext;
	// The resulting values of this adapter
	protected ArrayList<DataObject> mListItem = new ArrayList<DataObject>();
	// This is for invalid item id
	protected long INVALID_ITEM_ID = Long.MIN_VALUE;
	// When we do call executeAsyn() we must have to pass to get the result by this observer
	protected NotifyObserver mNotifyObserver;
	//
	protected boolean mCancel;
	// The operation needs to be cancelled, so what we need just to store the controller.
	// We must have to release this controller after the result.
	Handler mHandler = new Handler();
	//
	protected DataObject mCurrentSelection;

	/**
	 * @return the mCurrentSelection
	 */
	public DataObject getmCurrentSelection() {
		return mCurrentSelection;
	}

	/**
	 * @param mCurrentSelection the mCurrentSelection to set
	 */
	public void setmCurrentSelection(DataObject mCurrentSelection) {
		this.mCurrentSelection = mCurrentSelection;
	}

	/**
	 * Constructor function that takes the name of the adapter/model
	 * @param uniqueName
	 */
	public AbsRecycleViewBaseAdapter(String uniqueName) {
		this.mName = uniqueName;
		mInstance = this;
		mCancel = false;
	}

	public AbsRecycleViewBaseAdapter() {
		mInstance = this;
		mCancel = false;
	}

	public AbsRecycleViewBaseAdapter(Context context) {
		mInstance = this;
		mCancel = false;
		mContext = context;
	}


	/**
	 * @return the mNotifyObserver
	 */
	public NotifyObserver getNotifyObserver() {
		return mNotifyObserver;
	}


	/**
	 * @param mNotifyObserver the mNotifyObserver to set
	 */
	public void setNotifyObserver(NotifyObserver mNotifyObserver) {
		this.mNotifyObserver = mNotifyObserver;
	}

	/**
	 * Notify the observer using a handler.
	 * @param response
	 */
	public synchronized void notifyObserver(ResponseObject response) {
		final ResponseObject tmpResponse = response;

		mHandler.post(new Runnable() {
			@Override
			public void run() {

				if(mNotifyObserver != null) {
					mNotifyObserver.update(tmpResponse);
				}
			}
		});
	}

	/**
	 * Cancel the operation, that is executed in different thread.
	 */
	public void cancel() {
		mCancel = true;
	}
	/**
	 * @return the mContext
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.mContext = context;
	}

	/**
	 * @return the mName
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @param mName the mName to set
	 */
	public void setName(String mName) {
		this.mName = mName;
	}

	/**
	 * Execute the operation after setting the Parameter. Never call this before calling setParameter().
	 * If this is AbsWsAdapter must call the executeAsyn().
	 * @return
	 */
	public abstract ResponseObject loadData();

	/**
	 * The operation will be performed in different thread. The result will be passed in the observer
	 * @param observer
	 */
	public  synchronized void loadData(NotifyObserver observer) {
		// We have to execute the request using a thread
		this.setNotifyObserver(observer);
		new Thread(new Runnable() {
			@Override
			public void run() {
				ResponseObject resObj = new ResponseObject();

				try {
				    loadData();
					mInstance.notifyObserver(resObj);

				} catch (Exception e) {
					// Error: Need to handle
					resObj.setResponseMsg(e.getMessage());
					LogUtil.d(e.getMessage());
				}
			}
		}).start();
	}

	/**
	 * Add an item to the model/adapter
	 * @param item
	 */
	public void add(DataObject item) {
		if(item != null) {
			this.mListItem.add(item);
		}
	}

	/**
	 * Removes the object at the specified location from this list.
	 * @param position
	 * @return
	 */
	public DataObject remove(int position) {
		return	this.mListItem.remove(position);
	}

	/**
	 * Removes one instance of the specified object from this Collection if one is contained (optional).
	 * @param object
	 */
	public void remove(DataObject object) {
		this.mListItem.remove(object);
	}

	/**
	 * Remove all items from the adapter, For Garbage collector if you have some bitmap then those needs to be
	 * recycle first, and also put null to the objects. like Bitmap b; b.recyle(); b = null;
	 * @param callGarbageCollector Whether system garbage collector will be called
	 */
	public void clear(boolean callGarbageCollector) {
		mListItem.clear();
		mCancel = false;
		if(callGarbageCollector) {
			System.gc();
		}
	}

	/**
	 * Returns the number of elements in this ArrayList.
	 */
	@Override
	public int getItemCount() {
		return this.mListItem == null ? 0 : this.mListItem.size();
	}

	/**
	 * Returns the element at the specified location in this list.
	 * @param position
	 */
	public Object getItem(int position) {
		if(position > -1 && position < this.mListItem.size()) {
			return this.mListItem.get(position);
		}

		return null;
	}

	/**
	 * Return the ID at the specified location in this list
	 * @param position
	 */
	@Override
	public long getItemId(int position) {
		Object objMain =  this.getItem(position);
		if(objMain instanceof DataObject) {
			DataObject obj = (DataObject) this.getItem(position);
			return obj.getId();
		}

		LogUtil.d("getItemId - INVALID_ITEM_ID at -" + position);

		return INVALID_ITEM_ID;
	}

	/**
	 * Context is available now.
	 */
	protected abstract void init();
}

