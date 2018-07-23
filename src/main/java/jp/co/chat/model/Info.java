package jp.co.chat.model;

import java.io.Serializable;
import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Info implements Serializable {

	private static final long serialVersionUID = 1L;

	@Attribute(primaryKey = true)

	private Key key;
    private String header;
    private String address;
    private String sheetId;
    private Date createdDate;

    public Info() {
    	createdDate = new Date();
    }

	/**
	 * @return key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key セットする key
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header セットする header
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address セットする address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return sheetId
	 */
	public String getSheetId() {
		return sheetId;
	}

	/**
	 * @param sheetId セットする sheetId
	 */
	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	/**
	 * @return createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate セットする createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
