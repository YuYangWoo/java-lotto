package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerator {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_MAX_SIZE = 6;
    private static final List<LottoNumber> LOTTO_ONE_SHEET;

    static {
       LOTTO_ONE_SHEET = IntStream.range(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
           .mapToObj(LottoNumber::new)
           .collect(Collectors.toList());
    }

    public static Lotto generate() {
        Collections.shuffle(LOTTO_ONE_SHEET);
        Set<LottoNumber> lottoNumbers = LottoNumberGenerator.LOTTO_ONE_SHEET.stream()
            .limit(LOTTO_MAX_SIZE)
            .collect(Collectors.toSet());
        return new Lotto(lottoNumbers);
    }
}
