package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputViewTest {
    @Test
    @DisplayName("입력받은 금액이 1000원 단위인지 체크한다.")
    public void validateSmallestUnit() {
        assertThatThrownBy(() -> {
            if (Math.floorDiv(1000, 1000) != 0)
                throw new IndexOutOfBoundsException("로또는 1000원 단위로 구매할 수 있습니다.");
        }).isInstanceOf(IndexOutOfBoundsException.class)
        .hasMessageContaining("로또는 1000원 단위로 구매할 수 있습니다.");
    }
}
