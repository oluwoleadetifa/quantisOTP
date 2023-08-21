package com.idquantique.quantis;


public class Test {

	static {
		System.loadLibrary("Quantis");
	}

	public static int main(String[] args) throws QuantisException{

		// TODO Auto-generated method stub
		Quantis quantis = new Quantis(Quantis.QuantisDeviceType.QUANTIS_DEVICE_PCI, 0);

		return quantis.ReadInt();

	}




}
