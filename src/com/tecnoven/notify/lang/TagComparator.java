/**
 * 
 */
package com.tecnoven.notify.lang;

import java.util.Comparator;

import com.tecnoven.notify.domain.TagData;


/**
 * 
 *
 * @version 1.0 (May 26, 2009)
 * @author Hector Rodriguez (hrodriguezve@gmail.com)
 */
public class TagComparator implements Comparator<TagData> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(com.tecnoven.notify.domain.TagData arg0, com.tecnoven.notify.domain.TagData arg1)
	 */
	public int compare(TagData arg0, TagData arg1) {
		return arg0.getTagOrder() - arg1.getTagOrder();
	}

}
