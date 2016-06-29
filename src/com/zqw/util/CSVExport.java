package com.zqw.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;

public class CSVExport {
	public void write(ArrayList<OrderLst> lst, String path) {
		try {
			File csv = new File(path + "//" + "结算" + ".csv");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(csv), "GBK"));
			for (int i = 0; i < lst.size(); i++) {
				writeLine(bw, lst.get(i).getSubmitTime() + "_"
						+ lst.get(i).getCurtainShop(), "");
				List<OrderGoods> goodsLst = lst.get(i).getGoodsLst();
				for (int j = 0; j < goodsLst.size(); j++) {
					writeLine(bw, goodsLst.get(j).getSerialNumber(), goodsLst
							.get(j).getNumber() + "");
				}
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeLine(BufferedWriter bw, String serialNumber, String number) {
		try {
			bw.newLine();
			String col1 = serialNumber;
			String col2 = number;
			bw.write(col1 + "," + col2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}