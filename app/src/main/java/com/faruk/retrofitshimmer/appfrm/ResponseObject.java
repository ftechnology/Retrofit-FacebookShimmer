/**
 * 
 * @author Faruk Hossain
 * Hold the response result for any operation.
 *
 */
package com.faruk.retrofitshimmer.appfrm;

public class ResponseObject {

	public static String mContinue = "mContinue";
	public static String mComplete = "mComplete";
	public static String mFailed = "mFailed";
	public static String mStatus = "mStatus";
	public static String mSuccess = "mSuccess";
	public static String mCancel = "mCancel";
	public static String mItemPosition = "mItemPosition";
	//
	// The response code
	private int mResponseCode = Integer.MIN_VALUE;//INVALID CODE
	// The response message
	private String mResponseMsg = "Failed";
	// The resulting object
	private Object mDataObject = null;
	// The converted data string
	private String mDataString = null;
	// The model/adapter that creates this response
	private Object mDataModel = null;
	// Server content before download.
	private long mContentMain = -1;
	// Server content after download.
	private long mContentDownloaded = -1;

	/**
	 * @return the mContentMain
	 */
	public long getContentMain() {
		return mContentMain;
	}

	/**
	 * @param mContentMain the mContentMain to set
	 */
	public void setContentMain(long mContentMain) {
		this.mContentMain = mContentMain;
	}

	/**
	 * @return the mContentDownloaded
	 */
	public long getContentDownloaded() {
		return mContentDownloaded;
	}

	/**
	 * @param mContentDownloaded the mContentDownloaded to set
	 */
	public void setContentDownloaded(long mContentDownloaded) {
		this.mContentDownloaded = mContentDownloaded;
	}


	/**
	 * @return the mDataModel
	 */
	public Object getDataModel() {
		return mDataModel;
	}

	/**
	 * @param mDataModel the mDataModel to set
	 */
	public void setDataModel(Object mDataModel) {
		this.mDataModel = mDataModel;
	}

	/**
	 * @return the mDataString
	 */
	public String getDataString() {
		return mDataString;
	}

	/**
	 * @param mDataString the mDataString to set
	 */
	public void setDataString(String mDataString) {
		this.mDataString = mDataString;
	}
	
	/**
	 * 
	 */
	public ResponseObject() {
		this.setValues(Integer.MIN_VALUE, "Failed", null);
	}

	public ResponseObject(int code, String msg, Object obj) {
		this.mResponseCode = code;
		this.mDataObject = obj;
		this.mResponseMsg = msg;
	}
	
	/**
	 * Set the values for response object
	 * @param code
	 * @param msg
	 * @param obj
	 */
	public void setValues(int code, String msg, Object obj) {
		this.mResponseCode = code;
		this.mDataObject = obj;
		this.mResponseMsg = msg;
	}
	
	/**
	 * @return the mResponseCode
	 */
	public int getResponseCode() {
		return mResponseCode;
	}
	
	/**
	 * Set the response code
	 * @param code
	 */
	public void setResponseCode(int code) {
		mResponseCode = code;
	}

	/**
	 * @return the mResponseMsg
	 */
	public String getResponseMsg() {
		return mResponseMsg;
	}

	/**
	 * Set the response message
	 * @param msg
	 */
	public void setResponseMsg(String msg) {
		mResponseMsg = msg;
	}
	/**
	 * @return the mDataObject
	 */
	public Object getDataObject() {
		return mDataObject;
	}
	
	/**
	 * Set the result object
	 * @param obj
	 */
	public void setDataObject(Object obj) {
		mDataObject = obj;
	}
}

