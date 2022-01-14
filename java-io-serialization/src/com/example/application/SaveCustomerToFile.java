package com.example.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.example.entity.Address;
import com.example.entity.AddressType;
import com.example.entity.Customer;
import com.example.entity.CustomerType;

public class SaveCustomerToFile {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		var jack = new Customer();
		jack.setKimlik("1");
		jack.setFullname("jack bauer");
		jack.setBirthYear(1956);
		jack.setPhoto("photo");
		jack.setEmail("jack.bauer@example.com");
		jack.setPhone("+905555555");
		jack.setType(CustomerType.GOLD);
		jack.setAddresses(List.of(new Address(1,AddressType.BUSINESS,"kuyu","istanbul","turkey","34212")));
        var oos = new ObjectOutputStream(new FileOutputStream(new File("src","jack.dat")));
        oos.writeObject(jack);
        oos.close();
	}

}
