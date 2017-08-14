package cn.mrx.hr.utils;

import java.util.List;

/**
 * Author: xialiangbo
 * Date: 2017/8/6 11:57
 * Description: EasyUI DataGrid 数据网格封装
 */
public class DataGridPage {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
