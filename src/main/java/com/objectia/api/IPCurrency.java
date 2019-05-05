package com.objectia.api;

import com.google.gson.annotations.SerializedName;

public final class IPCurrency {

    @SerializedName("code")
	private final String code = null; 

    @SerializedName("num_code")
	private final String numericCode = null;    

    @SerializedName("name")
	private final String name = null;           

    @SerializedName("name_plural")
	private final String pluralName = null;     

    @SerializedName("symbol")
	private final String symbol = null;         

    @SerializedName("symbol_native")
	private final String nativeSymbol = null;  

    @SerializedName("decimal_digits")
	private final int decimalDigits = 0;    

    public IPCurrency() {}

	public String getCode() {
		return this.code;
	}

	public String getNumericCode() {
		return this.numericCode;
	}

	public String getName() {
		return this.name;
	}

	public String getPluralName() {
		return this.pluralName;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public String getNativeSymbol() {
		return this.nativeSymbol;
	}

	public int getDecimalDigits() {
		return this.decimalDigits;
	}

}
