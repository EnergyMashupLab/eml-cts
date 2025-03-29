package zachary.doubleauction;

import org.theenergymashuplab.cts.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoubleAuctionMatch {

    // Comparison function for sorting TenderIntervalDetail-based tenders by
    // quantity, in descending order
    private static int TenderReverseQuantitySort(EiTenderType t1, EiTenderType t2) {
        TenderIntervalDetail d1 = (TenderIntervalDetail) t1.getTenderDetail();
        TenderIntervalDetail d2 = (TenderIntervalDetail) t2.getTenderDetail();

        return Long.compare(d1.getQuantity(), d2.getQuantity());
    }

    public static List<TempTransactionRecord> matchTransactions(final List<EiTenderType> inMoneyTenders, final int clearingPrice) {
        List<TempTransactionRecord> transactions = new ArrayList<>();

        Iterator<EiTenderType> buyTenders = inMoneyTenders.stream()
                .filter(tender -> tender.getSide() == SideType.BUY)
                .sorted(DoubleAuctionMatch::TenderReverseQuantitySort)
                .iterator();
        Iterator<EiTenderType> sellTenders = inMoneyTenders.stream()
                .filter(tender -> tender.getSide() == SideType.SELL)
                .sorted(DoubleAuctionMatch::TenderReverseQuantitySort)
                .iterator();

        // If there are either no buy or sell offers, no matches can be made
        if (!buyTenders.hasNext() || !sellTenders.hasNext()) {
            return transactions;
        }

        EiTenderType buyTender = buyTenders.next();
        EiTenderType sellTender = sellTenders.next();

        TenderIntervalDetail buyDetail = (TenderIntervalDetail) buyTender.getTenderDetail();
        TenderIntervalDetail sellDetail = (TenderIntervalDetail) sellTender.getTenderDetail();

        long remainingBuyAmount = buyDetail.getQuantity();
        long remainingSellAmount = sellDetail.getQuantity();

        while (buyTenders.hasNext() && sellTenders.hasNext()) {
            long transactionAmount = Math.min(remainingBuyAmount, remainingSellAmount);

            if (transactionAmount > 0) {
                TempTransactionRecord transaction = new TempTransactionRecord(
                        buyTender,
                        sellTender,
                        transactionAmount,
                        clearingPrice
                );

                transactions.add(transaction);
            }

            remainingBuyAmount -= transactionAmount;
            remainingSellAmount -= transactionAmount;

            if (remainingBuyAmount == 0) {
                buyTender = buyTenders.next();
                buyDetail = (TenderIntervalDetail) buyTender.getTenderDetail();

                remainingBuyAmount = buyDetail.getQuantity();
            }

            if (remainingSellAmount == 0) {
                sellTender = sellTenders.next();
                sellDetail = (TenderIntervalDetail) sellTender.getTenderDetail();

                remainingSellAmount = sellDetail.getQuantity();
            }
        }

        return transactions;
    }

    private static EiTenderType createTestTender(SideType side, int price, int quantity) {
        TenderIntervalDetail detail = new TenderIntervalDetail(new Interval(), price, quantity);

        return new EiTenderType(Instant.EPOCH, side, detail, 2);
    }

    public static void main(String[] args) {
        List<EiTenderType> inMoneyTenders = List.of(
            createTestTender(SideType.BUY, 15, 100),
            createTestTender(SideType.BUY, 20, 100),
            createTestTender(SideType.BUY, 25, 100),
            createTestTender(SideType.BUY, 30, 100),
            createTestTender(SideType.BUY, 35, 100),

            createTestTender(SideType.SELL, 15, 100),
            createTestTender(SideType.SELL, 20, 100),
            createTestTender(SideType.SELL, 25, 100),
            createTestTender(SideType.SELL, 30, 100),
            createTestTender(SideType.SELL, 35, 100)
        );

        List<TempTransactionRecord> transactions = matchTransactions(inMoneyTenders, 25);

        System.out.printf("Transactions (%d):\n", transactions.size());

        transactions.forEach(System.out::println);
    }
}
