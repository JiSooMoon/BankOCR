import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * BankOCRTest is a test class for BankOCR to from getting files, reading lines, and converting to numbers with
 */
public class BankOCRTest {

    private ClassLoader loader;

    private BankOCR bankOCR;

    public BankOCRTest() {
        loader = getClass().getClassLoader();
        bankOCR = new BankOCR();
    }

    @Test
    public void testWhenFileWithZerosReturnZerosValidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_zeros");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("000000000", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertTrue(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("000000000", status);
    }

    @Test
    public void testWhenFileWithZerosReturnOnesInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_ones");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("111111111", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("111111111 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnTwosInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_twos");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("222222222", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("222222222 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnThreesInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_threes");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("333333333", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("333333333 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnFoursInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_fours");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("444444444", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("444444444 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnFivesalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_fives");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("555555555", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("555555555 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnSixsInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_sixs");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("666666666", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("666666666 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnSevensInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_sevens");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("777777777", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("777777777 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnEightsInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_eights");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("888888888", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("888888888 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnNinesInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_with_nines");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("999999999", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("999999999 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnFromZeroToEightInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_from_zero_to_eight");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("012345678", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("012345678 ERR", status);
    }

    @Test
    public void testWhenFileWithMixedNumbersReturnInvalidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_two_return_invalid_checksum_with_mixed_numbers");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("490067715", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("490067715 ERR", status);
    }

    @Test
    public void testWhenFileWithZerosReturnFromOneToNinesValidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_one_return_values_from_one_to_nine");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("123456789", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertTrue(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("123456789", status);
    }

    @Test
    public void testWhenFileWithChecksumWithSevenAndOnesValidReturnValidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_two_return_valid_checksum_with_seven_and_one");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("711111111", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertTrue(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("711111111", status);
    }

    @Test
    public void testWhenFileWithChecksumWithMixedNumbersValidReturnValidChecksum() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_two_return_valid_checksum_with_mixed_numbers");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("490867715", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertTrue(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("490867715", status);
    }

    @Test
    public void testWhenFileThrowParsedDigitInvalidException() throws IOException {
        List<String> fileData = readFileAndReturnData("test_cases/use_case_three_throw_parsed_digit_invalid_exception");
        String accountNumber = bankOCR.convertLinesToNumbers(fileData);
        assertEquals("00000000?", accountNumber);
        boolean isValid = bankOCR.validateCheckSum(accountNumber);
        assertFalse(isValid);

        String status = bankOCR.generateStatus(accountNumber, isValid);
        assertEquals("00000000? ILL", status);
    }

    public List<String> readFileAndReturnData(final String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        List<String> allLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine();
        while (line != null) {
            allLines.add(line);
            line = reader.readLine();
        }
        reader.close();
        return allLines;
    }
}
