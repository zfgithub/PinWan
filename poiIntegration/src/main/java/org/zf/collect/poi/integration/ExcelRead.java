package org.zf.collect.poi.integration;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import java.io.File;

public class ExcelRead {

    @Test
    public void cellDataRead() {
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "rec_info.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet

        EasyExcel.read(fileName, new ReceiverInfoDataListener()).sheet().doRead();
    }
}
