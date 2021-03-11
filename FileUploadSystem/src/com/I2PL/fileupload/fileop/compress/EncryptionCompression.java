package com.I2PL.fileupload.fileop.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class EncryptionCompression {

	private static final int key = 2142989879;

	public void compressFile(File fileToCompress, File compressFile) throws IOException {
		try (FileInputStream fin = new FileInputStream(fileToCompress);
				FileOutputStream fout = new FileOutputStream(compressFile);
				DeflaterOutputStream dos = new DeflaterOutputStream(fout);) {

			int i;
			while ((i = fin.read()) != -1) {
				dos.write((byte) i);
				dos.flush();
			}

		}
	}

	public void encrypt(File name) throws FileNotFoundException, IOException {

		// Selecting a Image for operation
		FileInputStream fis = new FileInputStream(name);

		// Converting Image into byte array, create a
		// array of same size as Image size

		byte data[] = new byte[fis.available()];

		// Read the array
		fis.read(data);
		int i = 0;

		// Performing an XOR operation on each value of
		// byte array due to which every value of Image
		// will change.
		for (byte b : data) {
			data[i] = (byte) (b ^ key);
			i++;
		}

		// Opening a file for writing purpose
		FileOutputStream fos = new FileOutputStream(name);

		// Writing new byte array value to image which
		// will Encrypt it.

		fos.write(data);

		// Closing file
		fos.close();
		fis.close();
	}

	public void decrypt(File name) throws FileNotFoundException, IOException {
		// Selecting a Image for Decryption.

		FileInputStream fis = new FileInputStream(name);

		// Converting image into byte array,it will
		// Create a array of same size as image.
		byte data[] = new byte[fis.available()];

		// Read the array

		fis.read(data);
		int i = 0;

		// Performing an XOR operation
		// on each value of
		// byte array to Decrypt it.
		for (byte b : data) {
			data[i] = (byte) (b ^ key);
			i++;
		}

		// Opening file for writting purpose
		FileOutputStream fos = new FileOutputStream(name);

		// Writting Decrypted data on Image
		fos.write(data);
		fos.close();
		fis.close();
	}

	public void decompressFile(File fileToDeCompress, File deCompressFile) throws IOException {
		try (FileInputStream fin = new FileInputStream(fileToDeCompress);
				InflaterInputStream in = new InflaterInputStream(fin);
				FileOutputStream fout = new FileOutputStream(deCompressFile);) {

			int i;
			while ((i = in.read()) != -1) {
				fout.write((byte) i);
				fout.flush();
			}

		}
	}
}
