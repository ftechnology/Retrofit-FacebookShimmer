/**
 * 
 * @author Md Faruk Hossain
 * The factory class for storing, creating the adapters. It also return the adapter by name like for example
 * mFactoryAdapter.createAdapter(MainAdapter.class); will create the adapter and also keep this.
 * So only one instance of that type of adapter will be created through out the application.
 *
 */
package com.faruk.retrofitshimmer.appfrm;

import android.content.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FactoryAdapter extends ArrayList<AbsRecycleViewBaseAdapter>{

	// The instance of the adapter
	protected static FactoryAdapter mInstance = null;
	// The application context.
	protected Context mContext = null;
	
	/**
	 * The instance of the adapter
	 * @param context
	 * @return
	 */
	synchronized public static FactoryAdapter getInstance(Context context) {
		if(mInstance == null) {
			mInstance = new FactoryAdapter(context);
		}
		
		return mInstance;
	}
	
	/**
	 * Constructor function that takes context
	 * @param context
	 */
	private FactoryAdapter(Context context) {
		this.mContext = context;
	}
	
	/**
	 * Delete adapter by given name.
	 * @param name
	 */
	public void delete(AbsRecycleViewBaseAdapter obj) {
		if(obj != null) {
			if(this.indexOf(obj) >= 0) {
				this.remove(obj);		
				System.gc();
			}
		}
	}
	
	/**
	 * Delete adapter by given name.
	 * @param name
	 */
	public void delete(String name) {
		AbsRecycleViewBaseAdapter model = this.getAdapter(name);
		if(model != null) {
			this.remove(model);
			model = null;
			System.gc();
		}
	}
	
	/**
	 * Get the adapter by name if found, otherwise return null
	 * @param name
	 * @return
	 */
	public  AbsRecycleViewBaseAdapter getAdapter(String name) {
		AbsRecycleViewBaseAdapter model = null;
		
		for(int i = 0; i<this.size(); i++) {			
			model = this.get(i);		
			if(model.getName().compareToIgnoreCase(name) == 0) {
				return model;
			}
		}
		
		return null;
	}
	
	/**
	 * Create the adapter by class name. It will first check and if found then return the instance.
	 * like createAdapter(MainAdapter.class). Else it will create new one.
	 * Actual creation of the adapter based on the class name and string name. Reflection is used to create
	 * the objects, so the checking is not required for type. Adapters will have only one constructor 
	 * and have the string value as argument like public MainAdapter(String uniqueName). If need to pass the different argument then don't do this
	 * in constructor function. Create another initialize() function for this. 

	 * @param name The name of the adapter class
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public AbsRecycleViewBaseAdapter createAdapter(Class name) {
		AbsRecycleViewBaseAdapter model = null;
		model = this.getAdapter(name.toString());
		
		if(model == null) {
			model = this.doCreateAdapter(name);
			if(model != null) {
				// Add this model to list
				this.add(model);
			}
		}
		
		return model;
	}
	
	/**
	 * Call another function to create the adapter based on the Class name. 
	 * Also set some initialize for the adapter.
	 * @param name The name of the adapter
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private AbsRecycleViewBaseAdapter doCreateAdapter(Class name) {
		AbsRecycleViewBaseAdapter model = null;
		model = this.createAdapterByName(name, name.toString());

		if(model != null) {
			model.setContext(mContext);
			model.init();
		}
		
		return model;
	}	
	
	/**
	 * Actual creation of the adapter based on the class name and string name. Here the reflection is used to create
	 * the objects, so the checking is not required for type. This method assume that all adapters will have only one
	 * constructor and have the string value as argument. If need to pass the different argument then don't do this
	 * in constructor function. Create another initialize() function for this. 
	 * @param className  The name of the adapter class
	 * @param name  The name of the adapter
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private AbsRecycleViewBaseAdapter createAdapterByName(Class className, String name) {
		AbsRecycleViewBaseAdapter model = null;
				
		try {
			Constructor constructor = null;
			constructor = className.getConstructor(new Class[] { String.class });
			//BaseDBModel ctArgs = (BaseDBModel) constructor.newInstance(new Object[] { "first", "second", "third" });
			try {
				model = (AbsRecycleViewBaseAdapter) constructor.newInstance(new Object[] { name});
			} catch (InstantiationException e) {
				LogUtil.d(e.getMessage());
				//e.printStackTrace();
			} catch (IllegalAccessException e) {
				LogUtil.d(e.getMessage());
				//e.printStackTrace();
			} catch (IllegalArgumentException e) {
				LogUtil.d(e.getMessage());
				//e.printStackTrace();
			} catch (InvocationTargetException e) {
				LogUtil.d(e.getMessage());
				//e.printStackTrace();
			}

		} catch (NoSuchMethodException e) {
			LogUtil.d(e.getMessage());
			//e.printStackTrace();
		}
	
		return model;
	}
}
