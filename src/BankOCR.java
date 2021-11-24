import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BankOCR is a class where you can read the paper documents and produces a file with number of entries
 */
public class BankOCR {

    private static final int LENGTH_OF_EACH_LINE = 28;

    private static final int INVALID_INDEX = -1;

    private static final int ZERO_INDEX = 0;

    private static final int FIRST_INDEX = 1;

    private static final int SECOND_INDEX = 2;

    private static final int ONE_BLOCK_SIZE = 3;

    private static final int FOURTH_INDEX = 4;

    private static final int CHECKSUM_MODULE_VALUE = 11;

    private static final int DIVISION_VALUE = 10;

    private static final String REGEX_CHECKER = "\\s+";

    private static final String REPLACEMENT = "";

    private static final String QUESTION_MARK = "?";

    private static final String ERR_TAG = "ERR";

    private static final String ILL_TAG = "ILL";

    private static final String RESULT_FORMAT_NUMBER_WITH_STATUS = "%s %s";

    private static final Map<ParseDigit, Integer> numbersByInputs = new HashMap<>();

    static {
        numbersByInputs.put(new ParseDigit("_", "||", "|_|"), 0);
        numbersByInputs.put(new ParseDigit("", "|", "|"), 1);
        numbersByInputs.put(new ParseDigit("_", "_|", "|_"), 2);
        numbersByInputs.put(new ParseDigit("_", "_|", "_|"), 3);
        numbersByInputs.put(new ParseDigit("", "|_|", "|"), 4);
        numbersByInputs.put(new ParseDigit("_", "|_", "_|"), 5);
        numbersByInputs.put(new ParseDigit("_", "|_", "|_|"), 6);
        numbersByInputs.put(new ParseDigit("_", "|", "|"), 7);
        numbersByInputs.put(new ParseDigit("_", "|_|", "|_|"), 8);
        numbersByInputs.put(new ParseDigit("_", "|_|", "_|"), 9);
    }

    /**
     * User Story1. write a program that can take this file and parse it into actual account numbers.
     *
     * @param fileData list of data which stores each line to transform to actual numbers
     * @return String of the actual account number to display
     */
    public String convertLinesToNumbers(final List<String> fileData) {
        String firstLine = fileData.get(ZERO_INDEX);
        String secondLine = fileData.get(FIRST_INDEX);
        String thirdLine = fileData.get(SECOND_INDEX);

        String accountNumber = "";
        int minimumLength = Math.min(firstLine.length(), thirdLine.length());

        for (int i = 0; i + ONE_BLOCK_SIZE < LENGTH_OF_EACH_LINE; i += ONE_BLOCK_SIZE) {
            String top = computeTopLine(firstLine, i, minimumLength);
            String middle = isIndexCloseToTheEnd(i, minimumLength) ? getSubstringBlock(secondLine, i)
                    : getSubstringLengthOfThreeBlock(secondLine, i);
            String bottom = isIndexCloseToTheEnd(i, minimumLength) ? getSubstringBlock(thirdLine, i)
                    : getSubstringLengthOfThreeBlock(thirdLine, i);

            //convert to ParseDigit
            int number = convertToParseDigit(top, middle, bottom);
            accountNumber += (number == INVALID_INDEX) ? QUESTION_MARK : String.valueOf(number);
        }
        return accountNumber;
    }

    /**
     * User Story 2. Calculates the checksum for a given number, and identifies if it is a valid account number.
     *
     * @param accountNumber the accountNumber from the scanned and computed value from User Story1.
     * @return boolean indicates whether it has valid checksum or not
     */
    public boolean validateCheckSum(final String accountNumber) {
        if (accountNumber.contains(QUESTION_MARK)) {
            return false;
        }

        int accountNumberInNumber = Integer.valueOf(accountNumber);
        int checkSum = 0;
        int increment = 1;
        while (accountNumberInNumber != 0) {
            int remainder = accountNumberInNumber % DIVISION_VALUE;
            checkSum += remainder * increment;
            increment++;
            accountNumberInNumber = accountNumberInNumber / DIVISION_VALUE;
        }
        return checkSum % CHECKSUM_MODULE_VALUE == 0;
    }

    /**
     * User Story 3. In the case of a wrong checksum, or illegible number, note in a second column indicating status.
     *
     * @param accountNumber  String of the account Number
     * @param checkSumStatus status of the account Number whether the checksum is valid or not
     * @return String of account number with status
     */
    public String generateStatus(final String accountNumber, final boolean checkSumStatus) {
        if (!accountNumber.contains(QUESTION_MARK) && !checkSumStatus) {
            return String.format(RESULT_FORMAT_NUMBER_WITH_STATUS, accountNumber, ERR_TAG);
        } else if (accountNumber.contains(QUESTION_MARK)) {
            return String.format(RESULT_FORMAT_NUMBER_WITH_STATUS, accountNumber, ILL_TAG);
        }
        return accountNumber;
    }

    private String computeTopLine(final String firstLine, final int index, final int minimumLength) {
        if (minimumLength == ZERO_INDEX || index >= minimumLength) {
            return "";
        } else if (minimumLength < ONE_BLOCK_SIZE || index + SECOND_INDEX == minimumLength) {
            return getSubstringBlock(firstLine, index);
        }
        return getSubstringLengthOfThreeBlock(firstLine, index);
    }

    private boolean isIndexCloseToTheEnd(final int currentIndex, final int minimumLength) {
        return currentIndex + SECOND_INDEX == minimumLength
                && (currentIndex + FIRST_INDEX == LENGTH_OF_EACH_LINE || currentIndex + SECOND_INDEX == LENGTH_OF_EACH_LINE
                || currentIndex + ONE_BLOCK_SIZE == LENGTH_OF_EACH_LINE || currentIndex + FOURTH_INDEX == LENGTH_OF_EACH_LINE);
    }

    private String getSubstringBlock(final String line, final int startIndex) {
        return line.substring(startIndex).replaceAll(REGEX_CHECKER, REPLACEMENT);
    }

    private String getSubstringLengthOfThreeBlock(final String line, final int startIndex) {
        return line.substring(startIndex, startIndex + ONE_BLOCK_SIZE).replaceAll(REGEX_CHECKER, REPLACEMENT);
    }

    private int convertToParseDigit(final String top, final String middle, final String bottom) {
        ParseDigit parseDigit = new ParseDigit(top, middle, bottom);
        return numbersByInputs.getOrDefault(parseDigit, INVALID_INDEX);
    }
}
