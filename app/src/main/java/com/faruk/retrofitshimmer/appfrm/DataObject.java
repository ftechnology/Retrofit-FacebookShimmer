/**
 * @author Md Faruk Hossain
 * The attributes of object, mostly used by the adapter class.
 */
package com.faruk.retrofitshimmer.appfrm;

import org.json.JSONException;
import org.json.JSONObject;

public class DataObject extends JSONObject {

    private String ID = "ID";
    private String NAME = "NAME";

    /**
     * The id of this object
     *
     * @param id
     */
    public DataObject(long id) {
        this.setValue(ID, id);
    }

    public DataObject(String name, String key) {
        this.setValue(ID, key);
        this.setValue(NAME, name);
    }

    public DataObject(String name, long key) {
        this.setValue(ID, key);
        this.setValue(NAME, name);
    }

    /**
     * The value to be set for name.
     *
     * @param name the key.
     * @param id   the long value of the name.
     */
    public void setValue(String name, long id) {
        try {
            this.put(name, id);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * The value to be set for name.
     *
     * @param name  the key.
     * @param value the string value of the name.
     */
    public void setValue(String name, String value) {
        try {
            this.put(name, value);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * The value to be set for name.
     *
     * @param name  the key.
     * @param value the string value of the name.
     */
    public void setValue(String name, Object value) {
        try {
            this.put(name, value);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Return the value for given name, return null if no key found.
     *
     * @param name
     * @return
     */
    public Object getValue(String name) {
        try {
            return this.get(name);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Return the value for given name, return null if no key found.
     */
    public String getString(String name) {
        try {
            return super.getString(name);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return the ID of this object.
     */
    public long getId() {
        long id = (Long) this.getValue(ID);

        return id;
    }

    /**
     * Set the id of this object.
     *
     * @param id
     */
    public void setId(long id) {
        this.setValue(ID, id);
    }

    /**
     * Return the value for given name, return null if no key found.
     */
    public long getLong(String name) {
        try {
            return super.getLong(name);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return -999999999;//Invalid value
    }

    /**
     * Return the value for given name, return null if no key found.
     */
    public int getInt(String name) {
        try {
            return super.getInt(name);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return -999999999;//Invalid value
    }
}

