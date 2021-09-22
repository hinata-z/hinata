//package com.data.file;
//
//
//import com.alibaba.excel.ExcelReader;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.metadata.Sheet;
//import com.alibaba.excel.read.context.AnalysisContext;
//import com.alibaba.excel.read.event.AnalysisEventListener;
//import com.alibaba.excel.support.ExcelTypeEnum;
//import org.springframework.util.StringUtils;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//
//public class NioRead {
//    public static void main(String[] args) {
//
//        long act = System.currentTimeMillis();
//        String filepath ="E:\\all_cach\\31.xlsx";
//
//
//        List<String> sheetContent = read(filepath);
//        //write();
//
//        System.out.println("一共"+sheetContent.size()+"有效数据");
//        long end = System.currentTimeMillis();
//        System.out.println("耗时间=======:"+(end-act)+"毫秒");
//
//        System.out.println(listUser.size());
//
//
//    }
//
//    private static  List<String> read(String filepath) {
//        List<String> sheetContent = new ArrayList<>();
//
//        //String filepath ="E:\\all_cach\\1.xlsx";
//        try (InputStream inputStream = new FileInputStream(filepath)) {
//            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null,
//                    new AnalysisEventListener<List<String>>() {
//
//                        @Override
//                        public void invoke(List<String> object, AnalysisContext context) {
//                            /**
//                             * 自行添加syl 》》》
//                             */
//                            StringBuffer stringBuffer = new StringBuffer();
//                            if(object != null && !StringUtils.isEmpty(object.get(0))){
//                                for(String s: object){
//                                    if(null!=s){
//                                        stringBuffer.append(s+"=");
//                                    }
//                                }
//                                System.out.println("行=="+context.getCurrentRowNum()+":  "+stringBuffer.toString());
//
//                                sheetContent.add(stringBuffer.toString());
//                                /**
//                                 *  《《《自行添加结束
//                                 */
//                                System.out.println("当前sheet:" + context.getCurrentSheet().getSheetNo() + ",当前行:" +
//                                        context.getCurrentRowNum());
//                            }
//
//                        }
//
//                        @Override
//                        public void doAfterAllAnalysed(AnalysisContext context) {
//
//                        }
//                    });
//            excelReader.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sheetContent;
//
//    }
//
//}
