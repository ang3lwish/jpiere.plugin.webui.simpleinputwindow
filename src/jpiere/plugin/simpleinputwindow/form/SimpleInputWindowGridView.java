package jpiere.plugin.simpleinputwindow.form;

import java.util.List;

import org.adempiere.webui.adwindow.GridTabRowRenderer;
import org.adempiere.webui.component.Checkbox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Grid;

public class SimpleInputWindowGridView implements EventListener<Event>{

	private SimpleInputWindowGridTable simpleInputWindowGridTable;
	private SimpleInputWindowListModel listModel;
	private SimpleInputWindowGridRowRenderer renderer;
	private Grid grid;
	private String tabFieldColumnName;
	private Object tabFieldValue;
	private boolean isVirtualColumn = false;

	protected Checkbox selectAll;

	public SimpleInputWindowGridView(SimpleInputWindowGridTable SIWGridTable ,SimpleInputWindowListModel listModel
			,SimpleInputWindowGridRowRenderer renderer, Grid grid, String tabFieldColumnName, Object tabFieldValue, boolean isVirtualColumn) {
		this.simpleInputWindowGridTable = SIWGridTable;
		this.listModel=listModel;
		this.renderer = renderer;
		this.grid = grid;
		this.tabFieldColumnName = tabFieldColumnName;
		this.tabFieldValue = tabFieldValue;
		this.isVirtualColumn = isVirtualColumn;

		this.renderer.setSimpleInputWindowGridView(this);
		selectAll = (Checkbox)grid.getColumns().getChildren().get(0).getChildren().get(0);
	}

	public SimpleInputWindowGridTable getSimpleInputWindowGridTable()
	{
		return simpleInputWindowGridTable;
	}

	public SimpleInputWindowListModel getSimpleInputWindowListModel()
	{
		return listModel;
	}

	public SimpleInputWindowGridRowRenderer getSimpleInputWindowGridRowRenderer()
	{
		return renderer;
	}

	public Grid getGrid()
	{
		return grid;
	}

	public Object getTabFieldValue()
	{
		return tabFieldValue;
	}

	public String getTabFieldColumnName()
	{
		return tabFieldColumnName;
	}

	public boolean isVirtualColumn()
	{
		return isVirtualColumn;
	}

	@Override
	public void onEvent(Event event) throws Exception
	{
		if (event.getName().equals("onSelectRow"))
		{

			Checkbox checkbox = (Checkbox) event.getData();
			int rowIndex = (Integer) checkbox.getAttribute(GridTabRowRenderer.GRID_ROW_INDEX_ATTR);
			if (checkbox.isChecked())
			{
				listModel.addToSelection(rowIndex);
				if (!selectAll.isChecked() && isAllSelected())
				{
					selectAll.setChecked(true);
				}
			}
			else
			{
				listModel.removeFromSelection(rowIndex);
				if (selectAll.isChecked())
					selectAll.setChecked(false);
			}

		}

	}

	private boolean isAllSelected() {
		org.zkoss.zul.Rows rows = grid.getRows();
		List<Component> childs = rows.getChildren();
		boolean all = false;
		for(Component comp : childs) {
			org.zkoss.zul.Row row = (org.zkoss.zul.Row) comp;
			Component firstChild = row.getFirstChild();
			if (firstChild instanceof Cell) {
				firstChild = firstChild.getFirstChild();
			}
			if (firstChild instanceof Checkbox) {
				Checkbox checkbox = (Checkbox) firstChild;
				if (!checkbox.isChecked())
					return false;
				else
					all = true;
			}
		}
		return all;
	}
}
