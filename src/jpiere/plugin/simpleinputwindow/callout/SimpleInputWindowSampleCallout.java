/******************************************************************************
 * Product: JPiere                                                            *
 * Copyright (C) Hideaki Hagiwara (h.hagiwara@oss-erp.co.jp)                  *
 *                                                                            *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY.                          *
 * See the GNU General Public License for more details.                       *
 *                                                                            *
 * JPiere is maintained by OSS ERP Solutions Co., Ltd.                        *
 * (http://www.oss-erp.co.jp)                                                 *
 *****************************************************************************/
package jpiere.plugin.simpleinputwindow.callout;

import java.math.BigDecimal;

import jpiere.plugin.simpleinputwindow.base.ISimpleInputWindowCallout;
import jpiere.plugin.simpleinputwindow.form.SimpleInputWindowDataBinder;

import org.compiere.model.GridField;

public class SimpleInputWindowSampleCallout implements ISimpleInputWindowCallout {

	@Override
	public String start(SimpleInputWindowDataBinder dataBinder,int rowIndex, GridField gridField, Object newValue, Object oldValue)
	{

		BigDecimal rate = (BigDecimal)dataBinder.getValue(rowIndex, "Rate");
		BigDecimal qty = (BigDecimal)dataBinder.getValue(rowIndex, "QtyEntered");
		dataBinder.setValue(rowIndex, "GrandTotal", rate.multiply(qty));

		return "";
	}

}