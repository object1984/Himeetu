package com.himeetu.util;

import com.himeetu.model.Country;

import java.util.Comparator;


public class CountryComparator implements Comparator<Country> {

	public int compare(Country o1, Country o2) {
		return o1.getEnName().compareTo(o2.getEnName());
	}

}
