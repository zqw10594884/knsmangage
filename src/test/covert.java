package test;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.zqw.bean.CurtainShop;
import com.zqw.bean.CurtainShopGoods;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;
import com.zqw.ui.KnsJFreeChart;
import com.zqw.util.DBUtil;

public class covert {
	public static void main(String[] args) {
		new covert().getData();
	}

	private void getData() {
		String hqlCurtainShop = "select new CurtainShop(cs.id,cs.name,cs.telephone,cs.address,cs.owner) from CurtainShop cs order by name";
		curtainShopLst = (ArrayList<CurtainShop>) DBUtil.getClassLst(
				hqlCurtainShop, "");
		for (int i = 0; i < curtainShopLst.size(); i++) {
			curtainShop = curtainShopLst.get(i);
			String sql = "select new OrderLst(g.curtainShop,g.date,g.id) from OrderLst g where curtainShop = :name";
			curtainShopOrderLst = (List<OrderLst>) DBUtil.getClassLst(sql,
					curtainShop.getName());
			for (int j = 0; j < curtainShopOrderLst.size(); j++) {
				OrderLst ol = curtainShopOrderLst.get(j);
				String sql1 = "select new OrderGoods(g.serialNumber,g.sellingPrice,g.purchasePrice,g.number,g.orderId) from OrderGoods g where orderId = :name";
				curtainShopOrderGoodsLst = (List<OrderGoods>) DBUtil
						.getClassLst(sql1, ol.getId() + "");
				ol.setGoodsLst(curtainShopOrderGoodsLst);
				for (int k = 0; k < curtainShopOrderGoodsLst.size(); k++) {
					OrderGoods og = curtainShopOrderGoodsLst.get(k);
					og.setDate(ol.getDeliveryTime());
					og.setCurtainShop(ol.getCurtainShop());
					og.setOwner(curtainShop.getOwner());
				}
				DBUtil.update(ol);
			}
		}

	}

	private ArrayList<CurtainShop> curtainShopLst = new ArrayList<CurtainShop>();
	private DefaultTableModel tableModel;
	private int curtainShopLstIndex = -1;
	private int curtainShopIndex;
	private CurtainShop curtainShop = null;
	private List<OrderLst> curtainShopOrderLst = null;
	private List<OrderGoods> curtainShopOrderGoodsLst = null;
	private List<OrderGoods> curtainShopOrderGoodsAllLst = null;
	private List<CurtainShopGoods> goodsLst;
	private KnsJFreeChart kjc = null;
	private JPanel tablePanel;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private DateChooser beginTime;
	private DateChooser lastTime;
	private ActionListener querySalesAction;
}
