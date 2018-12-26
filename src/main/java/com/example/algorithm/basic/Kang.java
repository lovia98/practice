package com.example.algorithm.basic;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class Kang {

    @Test
    public void test03() {
        String row = "clues";
        String col = "blue";

        int result = getMaxSame(row, col);

        System.out.println(result);
    }

    private int getMaxSame(String row, String col) {
        if (StringUtils.isEmpty(row) || StringUtils.isEmpty(col)) {
            return 0;
        }

        int[][] allArr = new int[row.length()][col.length()];
        int max = 0;
        int preNum = 0;
        for (int rowNo = 0; rowNo < row.length(); rowNo++) {
            String rowTemp = row.substring(rowNo, rowNo + 1);
            for (int colNo = 0; colNo < col.length(); colNo++) {
                String colTemp = col.substring(colNo, colNo + 1);
                if (rowTemp.equals(colTemp)) {
                    allArr[rowNo][colNo] = rowNo > 0 && colNo > 0 ? allArr[rowNo - 1][colNo - 1] + 1 : 1;
                } else {
                    allArr[rowNo][colNo] =
                        rowNo > 0 ? (allArr[rowNo - 1][colNo] > preNum ? allArr[rowNo - 1][colNo] : preNum) : (colNo > 0 ? allArr[rowNo][colNo - 1] : 0);
                }
                if (max < allArr[rowNo][colNo]) {
                    max = allArr[rowNo][colNo];
                }
                preNum = allArr[rowNo][colNo];
            }
            preNum = 0;
        }

        for (int rowNo = 0; rowNo < row.length(); rowNo++) {
            for (int colNo = 0; colNo < col.length(); colNo++) {
                System.out.println("[" + rowNo + "][" + colNo + "] = " + allArr[rowNo][colNo]);
            }
        }

        return max;
    }

    @Test
    public void test02() {
        int maxWeight = 6;
        List<Goods> list = Arrays.asList(
            new Goods("물", 3, 10)
            , new Goods("책", 1, 3)
            , new Goods("음식", 2, 9)
            , new Goods("자켓", 2, 5)
            , new Goods("카메라", 1, 6)
        );

        List<Goods> result = getMaxGoods(maxWeight, list);

        System.out.println(result);
    }

    private List<Goods> getMaxGoods(int maxWeight, List<Goods> goodsList) {
        if (maxWeight <= 0 && ObjectUtils.isEmpty(goodsList)) {
            return new ArrayList<>();
        }

        Goods[][] allArr = new Goods[maxWeight + 1][goodsList.size()];
        for (int weight = 1; weight <= maxWeight; weight++) {
            Goods maxValueGoods = null;
            for (int cols = 0 ; cols < goodsList.size() ; cols ++) {
                Goods goods = goodsList.get(cols);
                if (maxValueGoods == null) {
                    if (goods.getWeight() <= weight) {
                        maxValueGoods = goods;
                    }
                } else {
                    if (maxValueGoods.getValue() < goods.getValue()) {
                        maxValueGoods = goods;
                    }
                }
                allArr[weight][cols] = maxValueGoods;
            }
        }

        for( int row = 1 ; row < maxWeight + 1 ; row ++ ){
            System.out.println(new Gson().toJson(allArr[row]));
        }

        return new ArrayList<>();
    }

    class Goods {

        private String name;
        private int weight;
        private int value;

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }

        public Goods(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }

}
