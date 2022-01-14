package com.example.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.example.entity.Customer;

public class ReadCustomerFromFile {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(new FileInputStream(new File("src","jack.dat")));
        var jack = (Customer) ois.readObject();
        System.out.println(jack);
        ois.close();
	}

}
