package com.czm.d3_collections_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Room {
    // 1、准备一副牌
    private ArrayList<Card> allCards = new ArrayList<>();
    // 2、初始化54张牌
    {
        // 3、准备点数
        String[] numbers = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2" };
        // 4、准备花色
        String[] colors = {"♥", "♣", "♦", "♠"};
        // 5、组装每张牌对象
        int size = 0;
        for (String number : numbers) {
            size++;
            for (String color : colors) {
                // 6、创建对象封装这张牌对象
                Card c = new Card(number, color, size);
                // 7、添加到集合中
                allCards.add(c);
            }
        }
        // 8、添加大小王
        allCards.add(new Card("大王", "🫅", ++size));
        allCards.add(new Card("小王", "🃏", ++size));
        System.out.println("---新牌 = " + allCards);
    }


    public void start() {
        // 9、洗牌
        Collections.shuffle(allCards);
        System.out.println("---洗牌 = " + allCards);

        // 10、发牌
        // 定义3个玩家，3个集合
        List<Card> li = new ArrayList<>();
        List<Card> jan = new ArrayList<>();
        List<Card> tom = new ArrayList<>();

        // 依次发牌
        for (int i = 0; i < allCards.size() - 3; i++) {
            Card c = allCards.get(i);
            // ⚠️：轮询算法，采用求余，依次发牌
            if (i % 3 == 0) {
                li.add(c);
            } else if (i % 3 == 1) {
                jan.add(c);
            } else {
                tom.add(c);
            }
        }

        // 最后3张底牌
        List<Card> lastList = allCards.subList(allCards.size() - 3, allCards.size());

        // 抢地主
        tom.addAll(lastList);

        // 对牌排序
        sortCards(li);
        sortCards(jan);
        sortCards(tom);

        // 11、看牌
        System.out.println("--- li = " + li);
        System.out.println("--- jan = " + jan);
        System.out.println("--- tom = " + tom);
    }

    public void sortCards(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {

            @Override
            public int compare(Card o1, Card o2) {
                // 降序
                return o2.getSize() - o1.getSize();
            }
        });
    }
}
