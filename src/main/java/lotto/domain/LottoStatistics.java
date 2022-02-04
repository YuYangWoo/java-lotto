package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.util.Statistics;

public class LottoStatistics {

    private static final int MATCH_FOUR = 4;
    private static final int MIN_WIN_COUNT = 3;
    private final List<Integer> winningNumbers;
    private final int bonusNumber;
    private final List<Lotto> lottoList;
    private final List<Statistics> resultStatistics;
    private final int lottoPrice;

    public LottoStatistics(List<Integer> winningNumbers, int bonusNumber, List<Lotto> lottoList, int lottoPrice) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        this.lottoList = lottoList;
        this.lottoPrice = lottoPrice;
        this.resultStatistics = new ArrayList<>();
        compareNumber();
    }

    public List<Statistics> getResultStatistics() {
        return Collections.unmodifiableList(resultStatistics);
    }

    private void compareNumber() {
        for (Lotto lotto : lottoList) {
            int count = matchWinningNumbers(lotto);
            getRank(count, lotto);
        }
    }

    private void getRank(final int count, final Lotto lotto) {
        if (count < MIN_WIN_COUNT) {
            return;
        }

        if (matchBonusNumber(count, lotto)) {
            resultStatistics.add(Statistics.SECOND);
        } else {
            resultStatistics.add(Statistics.getRank(count));
        }
    }

    public String getLottoEarningRate() {
        double totalPrice = 0;
        for (Statistics statistics : resultStatistics) {
            totalPrice += statistics.getMoney();
        }
        return String.format("%.2f",totalPrice / lottoPrice);
    }

    private boolean matchBonusNumber(final int count, final Lotto lotto) {
        return count == MATCH_FOUR && lotto.getLottoList().contains(bonusNumber);
    }

    private boolean isWinningNumber(final int number) {
        return winningNumbers.contains(number);
    }

    private int matchWinningNumbers(final Lotto lotto) {
        return lotto.getLottoList().stream().filter(x -> isWinningNumber(x))
            .collect(Collectors.toList()).size();
    }

}
