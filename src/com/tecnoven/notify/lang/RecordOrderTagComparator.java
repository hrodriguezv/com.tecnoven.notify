package com.tecnoven.notify.lang;

import java.util.Comparator;

import com.tecnoven.notify.domain.RecordData;


public class RecordOrderTagComparator implements Comparator<RecordData>{

	public int compare(RecordData arg0, RecordData arg1) {
		// TODO Auto-generated method stub
		return arg0.getTag().getTagOrder() - arg1.getTag().getTagOrder();
	}
}
