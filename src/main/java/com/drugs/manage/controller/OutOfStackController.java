package com.drugs.manage.controller;

import com.drugs.manage.entity.*;
import com.drugs.manage.service.InventoryService;
import com.drugs.manage.service.OutOfStackService;
import com.drugs.manage.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyliu on 2019/10/1.
 */
@RestController
@RequestMapping("outOfStack")
public class OutOfStackController {
    @Autowired
    private OutOfStackService outOfStackService;

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("list")
    public ResultData getOutOfStackList(@RequestParam("currentPage") int currPage,
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam("drugName") String drugName,
                                       @RequestParam("receiverName") String receiverName,
                                       @RequestParam("startDate") String startDate,
                                       @RequestParam("endDate") String endDate){
        try {
            ArrayList<OutOfStackReceiver> list = outOfStackService.getOutOfStackList(currPage, pageSize,drugName,receiverName,startDate,endDate);
            int total = outOfStackService.getOutOfStackCount(drugName,receiverName,startDate,endDate);

            ResultData result = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("list",list);

            Map<String,Integer> pagination = new HashMap<>();
            pagination.put("total",total);
            pagination.put("pageSize",pageSize);
            pagination.put("currentPage",currPage);
            map.put("pagination",pagination);

            result.setResult(true);
            result.setValue(map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("getItemById")
    public ResultData getOutOfStackById(int id){
        try {
            OutOfStackReceiver outOfStackReceiver = outOfStackService.getOutOfStackById(id);
            ResultData result = new ResultData();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("outOfStackReceiver",outOfStackReceiver);

            result.setResult(true);
            result.setValue(map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("/batchInsert")
    @ResponseBody
    @Transactional
    public ResultData batchInsert(@RequestBody List<OutOfStack> list){
        try {
            outOfStackService.batchInsert(list);

            List<Map> newList = new ArrayList<>();
            for(OutOfStack i : list){
                Map<String,Object> map = new HashMap<>();
                map.put("outgoingNum",i.getDrawNum());
                map.put("inventoryNum",i.getDrawNum());
                map.put("drugId",i.getDrugId());
                newList.add(map);
            }
            inventoryService.batchUpdate(newList);

            ResultData resultData = new ResultData();
            resultData.setResult(true);
            resultData.setValue(null);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("update")
    @ResponseBody
    @Transactional
    public ResultData updateById(@RequestBody OutOfStack outOfStack){
        try {
            OutOfStackReceiver item = outOfStackService.getOutOfStackById(outOfStack.getId());
            int itemCode = item.getDrugId();
            Inventory inventory = inventoryService.getInventoryByDrugId(itemCode);
            BigInteger a = BigInteger.valueOf(Integer.parseInt(item.getDrawNum()));
            BigInteger b = BigInteger.valueOf(Integer.parseInt(inventory.getInventoryNum()));
            BigInteger c = BigInteger.valueOf(Integer.parseInt(inventory.getOutgoingNum()));
            BigInteger d = BigInteger.valueOf(Integer.parseInt(outOfStack.getDrawNum()));

            String newInventoryNum = a.subtract(d).add(b).toString();
            String newOutgoingNum = d.subtract(a).add(c).toString();

            outOfStackService.updateById(outOfStack);
            inventoryService.updateInventoryOutgoing(newOutgoingNum,newInventoryNum,inventory.getId());

            ResultData resultData = new ResultData();
            resultData.setResult(true);
            resultData.setValue(null);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("deleteById")
    @Transactional
    public ResultData deleteById(int id){
        try {
            OutOfStackReceiver item = outOfStackService.getOutOfStackById(id);
            int itemCode = item.getDrugId();
            Inventory inventory = inventoryService.getInventoryByDrugId(itemCode);
            BigInteger a = BigInteger.valueOf(Integer.parseInt(item.getDrawNum()));
            BigInteger b = BigInteger.valueOf(Integer.parseInt(inventory.getInventoryNum()));
            BigInteger c = BigInteger.valueOf(Integer.parseInt(inventory.getOutgoingNum()));
            BigInteger d = BigInteger.valueOf(0);

            String newInventoryNum = a.subtract(d).add(b).toString();
            String newOutgoingNum = d.subtract(a).add(c).toString();

            outOfStackService.deleteById(id);
            inventoryService.updateInventoryOutgoing(newOutgoingNum,newInventoryNum,inventory.getId());

            ResultData resultData = new ResultData();
            resultData.setResult(true);
            resultData.setValue(null);
            return resultData;
        } catch (Exception e) {
            e.printStackTrace();
            ResultData resultData = new ResultData();
            resultData.setResult(false);
            resultData.setValue(null);
            return resultData;
        }
    }

    @RequestMapping("/ExcelDownload")
    public void ExcelDownload(HttpServletResponse response,
                              @RequestParam String drugName,
                              @RequestParam String receiverName,
                              @RequestParam String startDate,
                              @RequestParam String endDate) throws IOException {
        List<OutOfStackReceiver> bgmExcelDownloads = outOfStackService.getOutOfStackList(0,0,drugName,receiverName,startDate,endDate);
        System.out.printf("------------" + bgmExcelDownloads.toString());
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (26.25 * 20));
//        row.createCell(0).setCellValue("用户信息列表");//为第一行单元格设值

        /*为标题设计空间
         * firstRow从第1行开始
         * lastRow从第0行结束
         *
         *从第1个单元格开始
         * 从第3个单元格结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);

      /*CellRangeAddress columnRegion = new CellRangeAddress(1,4,0,0);
      sheet.addMergedRegion(columnRegion);*/

        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("Id");//为第一个单元格设值
        row.createCell(1).setCellValue("编号");//为第二个单元格设值
        row.createCell(2).setCellValue("名称");//为第三个单元格设值
        row.createCell(3).setCellValue("规格型号");//为第四个单元格设值
        row.createCell(4).setCellValue("单位");//为第四个单元格设值
        row.createCell(5).setCellValue("数量");//为第四个单元格设值
        row.createCell(6).setCellValue("单价");//为第四个单元格设值
        row.createCell(7).setCellValue("金额");//为第四个单元格设值
        row.createCell(8).setCellValue("领用人");//为第四个单元格设值
        row.createCell(9).setCellValue("领用日期");//为第四个单元格设值
        row.createCell(10).setCellValue("备注");//为第四个单元格设值
        //遍历所获取的数据
        for (int i = 0; i < bgmExcelDownloads.size(); i++) {
            row = sheet.createRow(i + 2);
            OutOfStackReceiver bgm = bgmExcelDownloads.get(i);
            row.createCell(0).setCellValue(bgm.getId());
            row.createCell(1).setCellValue(bgm.getDrugCode());
            row.createCell(2).setCellValue(bgm.getDrugName());
            row.createCell(3).setCellValue(bgm.getDrugModel());
            row.createCell(4).setCellValue(bgm.getDrugUnit());
            row.createCell(5).setCellValue(bgm.getDrawNum());
            row.createCell(6).setCellValue(bgm.getUnitPrice());
            row.createCell(7).setCellValue(bgm.getMoney());
            row.createCell(8).setCellValue(bgm.getReceiver().getReceiverName());
            row.createCell(9).setCellValue(bgm.getDrawTime());
            row.createCell(10).setCellValue(bgm.getRemarks());
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }
}
