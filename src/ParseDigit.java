import lombok.EqualsAndHashCode;

/**
* ParseDigit contains the top, middle, and bottom from each 3x3 grid
* */
@EqualsAndHashCode(cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
public class ParseDigit {
    private String top;
    private String middle;
    private String bottom;

    public ParseDigit(final String top, final String middle, final String bottom) {
        this.top = top;
        this.middle = middle;
        this.bottom = bottom;
    }
}
