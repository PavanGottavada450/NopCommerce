package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "registerData")
    public static Object[][] getRegisterData() throws Exception {
        String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
        ExcelUtil.setExcelFile(filePath, "Register"); // Use Register sheet
        return ExcelUtil.getData();
    }
}
