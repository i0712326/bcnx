package com.bcnx.ui.setup.bin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.springframework.context.ApplicationContext;

import com.bcnx.application.context.BcnxApplicationContext;
import com.bcnx.data.entity.Bin;
import com.bcnx.data.service.BinService;

public class BinDeleteAction implements ActionListener {
	private JTable table;
	public BinDeleteAction(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationContext context = BcnxApplicationContext.getApplicationContext();
		BinService service = (BinService) context.getBean("binService");
		int i = table.getSelectedRow();
		if(i<0){
			JOptionPane.showMessageDialog(null, "Data No Selected", "Error Dialog", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Boolean chk = (Boolean) table.getModel().getValueAt(i, 0);
		if(chk.equals(new Boolean(false)))
			return;
		
		BinParamTable model = (BinParamTable) table.getModel();
		Bin b = new Bin();
		b.setId((Integer)model.getValueAt(i,1));
		b.setBin((String)model.getValueAt(i,2));
		b.setType((String)model.getValueAt(i,3));
		b.setIin((String)model.getValueAt(i,4));
		service.delete(b);
		model.deleteData(i);
	}

}
