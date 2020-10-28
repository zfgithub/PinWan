package org.zf.collect.poi.integration;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.metadata.Cell;
import com.alibaba.excel.metadata.CellData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

public class ReceiverInfoDataListener  extends AnalysisEventListener<Map<Integer,String>> {
        //private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverInfoDataListener.class);
        /**
         * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
         */
        private static final int BATCH_COUNT = 5;
        List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();

        @Override
        public void invoke(Map<Integer,String> data, AnalysisContext context) {
            Map<Integer, Cell> format =context.readRowHolder().getCellMap();
            System.out.print("解析到一条数据:{}"+ JSON.toJSONString(data)+"这条数据的格式:{}"+ JSON.toJSONString(format));
            //list.add(data);
            if (list.size() >= BATCH_COUNT) {
                saveData();
                list.clear();
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            saveData();
            System.out.print("所有数据解析完成！");
        }

        /**
         * 加上存储数据库
         */
        private void saveData() {
            System.out.print("{}条数据，开始存储数据库！"+list.size());
            System.out.print("存储数据库成功！");
        }
}

